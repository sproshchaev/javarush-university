package com.javarush.filmcache;

import com.javarush.filmcache.domain.Actor;
import com.javarush.filmcache.domain.Category;
import com.javarush.filmcache.domain.Film;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * JavaRush-University, Проект модуля 4
 */
public class App {


    public static void main(String[] args) {

    }

    private static SessionFactory prepareRelationDb() {
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        props.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/sakila");
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

}
