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

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * JavaRush-University, Проект модуля 4
 */
public class App {

    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisClient redisClient;

    // Конструктор без параметров (инициализация внутри за счет приватных методов)
    public App() {
        this.sessionFactory = prepareRelationDb();
        this.filmDao = new FilmDao(sessionFactory);
        this.redisClient = getRedisClient();
    }

    public static void main(String[] args) {
        App app = new App();
        // (1) Получение фильмов из БД
        List<Film> films = app.fetchAllFilms();
        System.out.println("Total films count: " + films.size());
        // (2) Преобразовали фильмы в формат который удобен для Redis
        List<FilmDetail> details = app.transformData(films);
        System.out.println("Total film details count: " + details.size());
        // (3) Записали в Redis в том формате, который для него удобен
        app.pushToRedis(details);
        System.out.println("Количество ключей в Redis: " + app.getCountFromRedis());
        // Завершение приложения
        app.shutdown();
    }

    // См. pom.xml -> Lettuce (Redis клиент)
    private RedisClient getRedisClient() {
        RedisClient client = RedisClient.create(
                // см. запуск контейнера Redis на localhost: docker run -d --name redis -p 6379:6379 redis:6.2-alpine
                RedisURI.create("localhost", 6379));
        // Создание соединения с Redis
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            System.out.println("Connected to redis");
        }
        return client;
    }

    private List<FilmDetail> transformData(List<Film> films) {
        return films.stream().map(film -> {
            // Начало лямбды
            // Создать результат - FilmDetail
            FilmDetail detail = new FilmDetail();

            // Заполнение полей экземпляра FilmDetail
            detail.setId(film.getId());                    //  films.id -> FilmDetail.id
            detail.setTitle(film.getTitle());              //  films.title -> FilmDetail.title
            detail.setDescription(film.getDescription());  //  films.description -> FilmDetail.descriptio
            detail.setReleaseYear(film.getReleaseYear());  //  films.releaseYear -> FilmDetail.releaseYear
            detail.setRentalRate(film.getRentalRate());    //  films.rentalRate -> FilmDetail.rentalRate
            detail.setRating(film.getRating());            //  films.rating -> FilmDetail.rating

            // Преобразовать актеров в список строк
            List<String> actorNames = film.getActors().stream()
                    .map(actor -> actor.getFirstName() + " "
                            + actor.getLastName()).toList();
            detail.setActors(actorNames);                  // films.actors -> FilmDetail.actors

            // Категории
            List<String> categoryNames = film.getCategories().stream()
                    .map(Category::getName).toList();
            detail.setCategories(categoryNames);           // films.categories -> FilmDetail.categories
            return detail;

            // Конец лямбды
        }).collect(Collectors.toList());
    }

    // Метод записи данных из List<FilmDetail> в Redis
    // Redis аналогичен тому как работает Map<ключ, значение>,
    // где ключ - это film:id, а значение - сериализованный JSON объект FilmDetail
    // "film:1", {...} <- value (фильм 1)
    // "film:2", {...} <- value (фильм 2)
    // ...
    // "actor:1", {...} <- value (актер 1)
    // "actor:2", {...} <- value (актер 2)
    private void pushToRedis(List<FilmDetail> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            // Аналог Map<String, String> в Redis
            RedisStringCommands<String, String> stringCommands = connection.sync();
            for (FilmDetail detail : data) {
                String key  = "film:" + detail.getId();
                String value = objectMapper.writeValueAsString(detail);
                stringCommands.set(key, value);
            }
        } catch (JsonProcessingException e) { // исключение от objectMapper
            throw new RuntimeException(e);
        }
    }

    // Получить размер данных из Redis
    private Long getCountFromRedis() {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            return connection.sync().dbsize();
        }
    }

    // TODO получить все фильмы
    private List<Film> fetchAllFilms() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Film> films = filmDao.getAll();
            session.getTransaction().commit();
            return films;
        }
    }

    // TODO: вынести в отдельный класс, чтобы не засорять App
    private static SessionFactory prepareRelationDb() {
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect"); // TODO если исп PG диалект посмотреть в документации
        props.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        props.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/sakila"); // TODO уточнить для PG
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "sakila");
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        props.put(Environment.HBM2DDL_AUTO, "none");
        props.put(Environment.STATEMENT_BATCH_SIZE, "100");

        return new Configuration()
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Category.class)
                .addProperties(props)
                .buildSessionFactory();
    }

    //  TODO Завершение приложения
    private void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
