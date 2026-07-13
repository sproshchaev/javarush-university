package com.javarush.filmcache.dao;

import com.javarush.filmcache.domain.Film;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

public class FilmDao {

    private final SessionFactory sessionFactory;

    public FilmDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // TODO Получить все фильмы
    public List<Film> getAll() {

        // Исп JOIN FETCH чтобы загрузить актеров и категории одним запросом
        Query<Film> query = sessionFactory.getCurrentSession()
                .createQuery("select distinct f from Film f " +
                        "left join fetch f.actors " +
                        "left join fetch f.categories", Film.class);
        return query.list();
    }

    // TODO Фильм по id
    public Optional<Film> getById(Integer id) {
        Query<Film> query = sessionFactory.getCurrentSession().createQuery(
                "select f from Film f " +
                        "left join fetch f.actors " +
                        "left join fetch f.categories " +
                        "where f.id = :id", Film.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    // TODO Число фильмов
    public Integer getTotalCount() {
        Query<Integer> query = sessionFactory.getCurrentSession().createQuery(
                "select count(f) from Film f", Integer.class);
        return query.uniqueResult();
    }

}
