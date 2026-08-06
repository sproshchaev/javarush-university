package com.javarush.repository;

import com.javarush.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * JOIN FETCH загружает авторов вместе с книгами одним SQL-запросом.
     * DISTINCT убирает дубликаты авторов, которые появляются после соединения таблиц.
     */
    @Query("SELECT DISTINCT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllWithBooks();
}
