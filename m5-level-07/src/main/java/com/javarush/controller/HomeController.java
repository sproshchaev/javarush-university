package com.javarush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/") // localhost:8080/
    public String homePage() {
        return "index"; // index.html
    }

}
