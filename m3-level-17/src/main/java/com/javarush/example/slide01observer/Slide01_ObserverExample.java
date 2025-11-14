package com.javarush.example.slide01observer;

import java.util.ArrayList;
import java.util.List;

// 1. Интерфейс наблюдателя (подписчика)
interface Observer {
    void update(String message);
}

// 2. Интерфейс субъекта (издателя)
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// 3. Конкретный издатель (Группа в соцсети)
class SocialMediaGroup implements Subject {
    private String groupName;
    private String lastPost;
    private List<Observer> subscribers = new ArrayList<>();

    public SocialMediaGroup(String groupName) {
        this.groupName = groupName;
    }

    public void publishPost(String post) {
        this.lastPost = post;
        System.out.println("В группе '" + groupName + "' опубликован новый пост: " + post);
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(lastPost);
        }
    }
}

// 4. Конкретный подписчик (Пользователь)
class User implements Observer {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String message) {
        System.out.println("Пользователь " + userName + " получил уведомление: " + message);
    }
}

// 5. Демонстрация работы
public class Slide01_ObserverExample {
    public static void main(String[] args) {
        // Создаем группу
        SocialMediaGroup javaGroup = new SocialMediaGroup("Java Programmers");

        // Создаем пользователей
        User alice = new User("Алиса");
        User bob = new User("Боб");

        // Подписываем пользователей на группу
        javaGroup.attach(alice);
        javaGroup.attach(bob);

        // Публикуем пост - все подписчики автоматически получат уведомление
        javaGroup.publishPost("Сегодня изучаем паттерн Наблюдатель!");

        System.out.println("\n--- Боб отписывается от группы ---");
        javaGroup.detach(bob);

        // Публикуем еще один пост - уведомление получит только Алиса
        javaGroup.publishPost("Завтра вебинар по Коллекциям!");
    }
}