# Модуль 5. Уровень 12: Spring Boot для микросервисов

Для подключения к консоли:
http://localhost:8080/h2-console/

### CURL команды для тестирования HelloController

```bash
curl http://localhost:8080/
```

Запрос для обновления двух пользователей
```bash
curl -X POST "http://localhost:8080/users/update-emails?id1=1&id2=2&email1=alice.new@example.com&email2=bob.new@example.com" \
  -H "Content-Type: application/json"
```

Метод, который выбрасывает checked исключение после сохранения
```bash
curl -X POST "http://localhost:8080/users/1/email?email=alice.default@example.com"
```

Получение всех пользователей (GET)
```bash
curl -s http://localhost:8080/users | jq .
```

Сервис RegistrationService с использованием TransactionTemplate
```bash
curl -X POST "http://localhost:8080/register?name=John&email=john@example.com"
```

Тестирование RestTemplate
```bash
curl http://localhost:8080/client/users/1
```

Получение пользователя по id через RestTemplate
```bash
curl -s http://localhost:8080/users/1 | jq .
```

Тестирование @FeignClient
```bash
curl -s http://localhost:8080/feign/users/1 | jq .
```

### Запуск с профилем prod (слайд про профили)

```bash
mvn -pl m5-level-12 -am -DskipTests package
java -jar target/m5-level-12-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

То же через переменную окружения:
```bash
SPRING_PROFILES_ACTIVE=prod java -jar target/m5-level-12-0.0.1-SNAPSHOT.jar
```

Под профилем prod сервис поднимается на порту 9090, а `app.feign.base-url` указывает
на `http://product-service:8081` — эндпоинты `/client/**` и `/feign/**` в этом режиме
работать не будут, это ожидаемо.

### Actuator

```bash
curl -s http://localhost:8080/actuator/health | jq .
curl -s http://localhost:8080/actuator/info | jq .
```

Эндпоинт `/actuator/info` наполняется целью `build-info` maven-плагина, поэтому данные
о сборке видны только при запуске собранного jar, а не через `spring-boot:run`.

### References
1. Ветка с заготовкой spring-data-jpa & CRUD https://github.com/sproshchaev/javarush-university/blob/m5-level-06-spring-data-jpa/m5-level-06/README.md  