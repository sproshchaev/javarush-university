package com.javarush.demo.props;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiClient {

    private final ApiConfig apiConfig;

    public ApiClient(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    @GetMapping("/api-config")
    public String show() {
        return apiConfig.getUrl() + " | timeout=" + apiConfig.getTimeout();
    }
}
