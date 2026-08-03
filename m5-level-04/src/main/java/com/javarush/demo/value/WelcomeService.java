package com.javarush.demo.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    // двоеточие отделяет значение по умолчанию
    @Value("${app.greeting.message:Hello}")
    private String greetingMessage;

    public String getGreeting() {
        return greetingMessage;
    }
}
