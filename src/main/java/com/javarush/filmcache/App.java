package com.javarush.filmcache;

import com.javarush.filmcache.dao.FilmDao;
import com.javarush.filmcache.domain.Actor;
import com.javarush.filmcache.domain.Category;
import com.javarush.filmcache.domain.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.List;
import java.util.Properties;

/**
 * JavaRush-University, Проект модуля 4
 */
public class App {

    private final SessionFactory sessionFactory;
    private final FilmDao filmDao;

    public App() {
        this.sessionFactory = prepareRelationDb();
        this.filmDao = new FilmDao(sessionFactory);
    }

    public static void main(String[] args) {
        App app = new App();
        List<Film> films = app.fetchAllFilms();
        System.out.println("Total films count: " + films.size());
        app.shutdown();
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
