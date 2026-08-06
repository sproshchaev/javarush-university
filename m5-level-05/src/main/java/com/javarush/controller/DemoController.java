package com.javarush.controller;

import com.javarush.dto.AuthorDto;
import com.javarush.dto.PageDto;
import com.javarush.entity.Book;
import com.javarush.entity.User;
import com.javarush.repository.BookRepository;
import com.javarush.service.AuthorService;
import com.javarush.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final UserService userService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    public DemoController(UserService userService,
                          AuthorService authorService,
                          BookRepository bookRepository) {
        this.userService = userService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
    }

    // Пример 2: сортировка
    @GetMapping("/users/sorted")
    public List<User> sorted() {
        return userService.findAllSorted();
    }

    // Пример 2: пагинация
    @GetMapping("/users/page")
    public PageDto page(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "2") int size) {
        return userService.findPage(page, size);
    }

    // Пример 3: JPQL-запрос
    @GetMapping("/users/by-domain")
    public List<User> byDomain(@RequestParam String domain) {
        return userService.findByEmailDomain(domain);
    }

    // Пример 4: нативный изменяющий запрос
    @GetMapping("/users/upper-case")
    public String upperCase(@RequestParam String domain) {
        int updated = userService.upperCaseNamesByDomain(domain);
        return "Изменено строк: " + updated;
    }

    // Пример 6: проблема N+1
    @GetMapping("/authors/n-plus-1")
    public List<AuthorDto> nPlusOne() {
        return authorService.findAllWithNPlusOne();
    }

    // Пример 7: решение через JOIN FETCH
    @GetMapping("/authors/join-fetch")
    public List<AuthorDto> joinFetch() {
        return authorService.findAllWithJoinFetch();
    }

    // Пример 8: EAGER на стороне ManyToOne
    @GetMapping("/books")
    public List<Book> books() {
        return bookRepository.findAll();
    }
}
