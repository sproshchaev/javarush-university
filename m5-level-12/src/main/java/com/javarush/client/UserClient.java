package com.javarush.client;

import com.javarush.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    // Адрес внешнего сервиса вынесен в конфигурацию: значение приходит
    // из application.yaml, а при профиле prod – из application-prod.yaml.
    @Value("${app.feign.base-url}")
    private String baseUrl;

    public Optional<UserDto> getUserById(Long id) {
        String url = baseUrl + "/users/" + id;
        return Optional.ofNullable(restTemplate.getForObject(url, UserDto.class));
    }

}
