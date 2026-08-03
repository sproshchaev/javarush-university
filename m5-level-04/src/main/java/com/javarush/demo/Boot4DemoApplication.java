package com.javarush.demo;

import com.javarush.demo.props.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
// Пример 5: точечное отключение автоконфигурации.
// В Spring Boot 4 из-за модуляризации класс переехал в пакет
// org.springframework.boot.jdbc.autoconfigure (корень модуля — org.springframework.boot.<технология>).
// Раскомментируйте импорт и exclude ниже, чтобы увидеть падение старта:
//   "Failed to configure a DataSource" — JPA ждёт источник данных, а автонастройку отключили.
// import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})   // пример 5
@EnableConfigurationProperties(ApiConfig.class)   // пример 7: биндинг group свойств app.api.*
public class Boot4DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot4DemoApplication.class, args);
    }

}
