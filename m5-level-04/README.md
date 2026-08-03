# Демо. Уровень 4: Углубленное изучение Spring Boot (Spring Boot 4)

Демо-проект к вебинару. Один Maven-проект, примеры разложены по пакетам, база — встроенная H2.
Хронометраж: 10–15 минут. Примеры 1–7 — основная линия, примеры 8–9 показываются при наличии времени.

## Стек

| Компонент | Версия / значение |
|-----------|-------------------|
| Spring Boot | 4.0.3 (ветка 4.x) |
| Java | 17 |
| Сборщик | Maven |
| Веб-слой | `spring-boot-starter-webmvc` |
| БД | H2 (in-memory) через `spring-boot-starter-data-jpa` |

Важно про Spring Boot 4:

1. Стартер `spring-boot-starter-web` объявлен устаревшим, его заменяет `spring-boot-starter-webmvc`.
2. Кодовая база разбита на модули `spring-boot-<технология>`, корневые пакеты — `org.springframework.boot.<технология>`.
   Из-за этого классы автоконфигурации переехали в новые пакеты (см. пример 5).
3. Undertow как встроенный сервер убран; остались Tomcat, Jetty и Reactor Netty.

## Запуск

```bash
mvn spring-boot:run
# либо fat JAR
mvn clean package
java -jar target/m5-level-04-0.0.1-SNAPSHOT.jar
```

## Таблица соответствия примеров

| № | Пример | Слайд | Что демонстрируется |
|---|--------|-------|---------------------|
| 1 | `pom.xml` + `dependency:tree` | 4 | Стартер приносит семейство согласованных зависимостей |
| 2 | `Boot4DemoApplication` + `--debug` | 5–6 | Отчёт автоконфигурации: positive и negative matches |
| 3 | `GreetingAutoConfiguration` | 6 | `@AutoConfiguration`, `@ConditionalOnClass`, файл `AutoConfiguration.imports` |
| 4 | `CustomFormatterConfig` | 5–6 | `@ConditionalOnMissingBean`: ручной бин имеет приоритет |
| 5 | `exclude = DataSourceAutoConfiguration` | 7 | Точечное отключение автоконфигурации и его последствия |
| 6 | `WelcomeService` | 10 | `@Value` и значение по умолчанию после двоеточия |
| 7 | `ApiConfig` + `ApiClient` | 11 | `@ConfigurationProperties`, биндинг группы свойств, проверка типов |
| 8 | `application-{profile}.yml` | 9, 12 | Профили и иерархия приоритетов источников |
| 9 | Fat JAR | 15 | Самодостаточный архив: классы, библиотеки, сервер, загрузчик |

## Команды по примерам

```bash
# Пример 1
mvn dependency:tree -Dincludes=org.hibernate*,com.zaxxer*,org.springframework*

# Пример 2: отчёт автоконфигурации (CONDITIONS EVALUATION REPORT)
mvn spring-boot:run -Dspring-boot.run.arguments=--debug

# Примеры 3/4: /greeting — с CustomFormatterConfig вернёт ">>> ... <<<",
# без него (закомментировать класс) — "[auto] ..."
curl http://localhost:8080/greeting

# Пример 6: @Value
curl http://localhost:8080/welcome

# Пример 7: @ConfigurationProperties
curl http://localhost:8080/api-config

# Пример 8: профили и приоритеты источников
java -jar target/m5-level-04-0.0.1-SNAPSHOT.jar
java -jar target/m5-level-04-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
SERVER_PORT=9001 java -jar target/m5-level-04-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --server.port=9000

# Пример 9: fat JAR
mvn clean package
ls -lh target/m5-level-04-0.0.1-SNAPSHOT.jar
unzip -l target/m5-level-04-0.0.1-SNAPSHOT.jar | head -25
java -jar target/m5-level-04-0.0.1-SNAPSHOT.jar
```

Пример 5 (`exclude`) включается раскомментированием импорта и аннотации
`@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` в `Boot4DemoApplication`.
Класс лежит в пакете `org.springframework.boot.jdbc.autoconfigure` — следствие модуляризации Spring Boot 4.

## Структура

```
m5-level-04/
├── README.md
├── pom.xml
└── src/main/
    ├── java/com/javarush/
    │   ├── demo/
    │   │   ├── Boot4DemoApplication.java
    │   │   ├── greeting/
    │   │   │   ├── GreetingController.java
    │   │   │   └── CustomFormatterConfig.java
    │   │   ├── value/
    │   │   │   ├── WelcomeService.java
    │   │   │   └── WelcomeController.java
    │   │   └── props/
    │   │       ├── ApiConfig.java
    │   │       └── ApiClient.java
    │   └── starter/
    │       ├── GreetingFormatter.java
    │       └── GreetingAutoConfiguration.java
    └── resources/
        ├── application.yml
        ├── application-dev.yml
        ├── application-prod.yml
        └── META-INF/spring/
            └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
```
