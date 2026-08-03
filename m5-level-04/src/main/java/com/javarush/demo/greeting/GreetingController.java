package com.javarush.demo.greeting;

import com.javarush.starter.GreetingFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingFormatter formatter;

    public GreetingController(GreetingFormatter formatter) {
        this.formatter = formatter;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return formatter.format("Hello from Spring Boot 4");
    }
}
