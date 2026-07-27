package com.javarush;

import com.javarush.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class LegacyApp {

    public static void main(String[] args) {

        // Веб приложение C(reate) R(ead) U(pdate) D(elete)
        // http-запросы                 POST     GET    POST    DELETE
        // Модель User
        // -------------- Слой контроллеров (UI)
        // Контроллер -> Сервис -> Репозиторий -> Бд (user)
        // -------------- Сервисный слой ---------------------------------
        // Сервис класс UserService: метод создания, чтения (ед, всех), изменение, удаление
        // --------------- Слой репозиториев -----------------------------
        // Репозиторий класс UserRepository: метод создания, чтения (ед, всех), изменение, удаление
        // --------------- Уровень БД ---------------------
        // БД PG/MySQL/Oracle/... application.properties

        // Модель Book
        // ...

        // Традиционный подход
        //UserService userService = new UserService();
        //userService.registerUser("Alice");
        //userService.registerUser("Bob");
        //System.out.println("Users: " + userService.getAllUsers());

    }

}
