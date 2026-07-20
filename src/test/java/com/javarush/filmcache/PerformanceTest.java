package com.javarush.filmcache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.filmcache.dao.FilmDao;
import com.javarush.filmcache.domain.Actor;
import com.javarush.filmcache.domain.Category;
import com.javarush.filmcache.domain.Film;
import com.javarush.filmcache.redis.FilmDetail;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PerformanceTest {

    private static SessionFactory sessionFactory;
    private static FilmDao filmDao;
    private static RedisClient redisClient;
    private static ObjectMapper mapper;
    private static List<Integer> testIds = List.of(1, 20, 45, 100, 250, 300, 400, 500, 600, 700);

    @BeforeAll
    static void setup() {
        // Инициализация Hibernate (локальное окружение)
        sessionFactory = preparedRelationDb();
        filmDao = new FilmDao(sessionFactory);

        // Инициализация Redis
        redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("Connected to Redis");
        }

        mapper = new ObjectMapper();

        // Загружаем все фильмы из MySQL и заполняем Redis (один раз перед тестами)
        List<Film> films;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            films = filmDao.getAll();
            session.getTransaction().commit();
        }

        List<FilmDetail> details = transformData(films);
        pushToRedis(details);
    }

    // testRedisPerformance()
    @Test
    void testRedisPerformance() {
        long start = System.currentTimeMillis();

        try (StatefulRedisConnection<String, String> conn = redisClient.connect()) {
            RedisStringCommands<String, String> sync = conn.sync();
            for (Integer id : testIds) {
                String json = sync.get("film:" + id);
                assertNotNull(json, "Данные для фильма " + id + " не найдены в Redis");
                mapper.readValue(json, FilmDetail.class);
            }
         } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("Redis чтение 10 фильмов: " + duration + " ms");
    }

    // testMySqlPerformance()
    @Test
    void testMySqlPerformance() {
        long start = System.currentTimeMillis();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : testIds) {
                Optional<Film> film = filmDao.getById(id);
                assertNotNull(film, "Фильм с id " + id + " не найден в MySQL");
                film.get().getActors().size();
                film.get().getCategories().size();
            }
            session.getTransaction().commit();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("MySQL чтение 10 фильмов: " + duration + " ms");
    }

    @AfterAll
    static void tearDown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        if (redisClient != null) {
            redisClient.shutdown();
        }
    }

    private static void pushToRedis(List<FilmDetail> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (FilmDetail detail : data) {
                String key = "film:" + detail.getId();
                String value = mapper.writeValueAsString(detail);
                sync.set(key, value);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<FilmDetail> transformData(List<Film> films) {
        return films.stream().map(film -> {
            FilmDetail detail = new FilmDetail();
            detail.setId(film.getId());
            detail.setTitle(film.getTitle());
            detail.setDescription(film.getDescription());
            detail.setReleaseYear(film.getReleaseYear());
            detail.setRentalRate(film.getRentalRate());
            detail.setRating(film.getRating());

            List<String> actorNames = film.getActors().stream()
                    .map(actor -> actor.getFirstName() + " " + actor.getLastName())
                    .toList();

            detail.setActors(actorNames);

            List<String> categoryNames = film.getCategories().stream()
                    .map(Category::getName)
                    .toList();

            detail.setCategories(categoryNames);
            return  detail;
        }).toList();
    }

    // Метод для тестового окружения должен быть свой
    private static SessionFactory preparedRelationDb() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/sakila");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "sakila");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "none");
        properties.put(Environment.STATEMENT_BATCH_SIZE, "100");
        return new Configuration()
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Category.class)
                .addProperties(properties)
                .buildSessionFactory();
    }


}