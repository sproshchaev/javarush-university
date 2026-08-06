package com.javarush.service;

import com.javarush.dto.AuthorDto;
import com.javarush.entity.Author;
import com.javarush.entity.Book;
import com.javarush.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Демонстрация проблемы N+1.
     * Запрос 1 - список авторов, запросы 2..N+1 - книги каждого автора по отдельности.
     */
    @Transactional(readOnly = true)
    public List<AuthorDto> findAllWithNPlusOne() {
        // Запрос 1: получаем всех авторов
        List<Author> authors = authorRepository.findAll();

        List<AuthorDto> result = new ArrayList<>();
        for (Author author : authors) {
            // Запросы 2..N+1: на каждой итерации отдельный поход в базу за книгами
            List<String> titles = author.getBooks().stream()
                    .map(Book::getTitle)
                    .toList();
            result.add(new AuthorDto(author.getId(), author.getName(), titles));
        }
        return result;
    }

    /**
     * Решение проблемы N+1: один SQL-запрос благодаря JOIN FETCH.
     */
    @Transactional(readOnly = true)
    public List<AuthorDto> findAllWithJoinFetch() {
        // Один запрос: авторы приходят с уже заполненными коллекциями книг
        List<Author> authors = authorRepository.findAllWithBooks();

        return authors.stream()
                .map(a -> new AuthorDto(
                        a.getId(),
                        a.getName(),
                        a.getBooks().stream().map(Book::getTitle).toList()))
                .toList();
    }
}
