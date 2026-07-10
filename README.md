# javarush-university

```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=sakila -d -p 3306:3306 restsql/mysql-sakila
docker run -d --name redis -p 6379:6379 redis:6.2-alpine
```

### Для тех, кто будет использовать PostgreSQL вместо MySQL, можно использовать следующий образ:
```bash
docker run --name postgres -e POSTGRES_USER=sakila -e POSTGRES_PASSWORD=sakila -e POSTGRES_DB=sakila -d -p 5432:5432 postgres:18-alpine
docker run -d --name redis -p 6379:6379 redis:6.2-alpine
```

Параметры подключения:

| Параметр | Значение |
|---|---|
| Хост | `localhost` |
| Порт | `5432` |
| Пользователь | `sakila` |
| Пароль | `sakila` |
| База данных | `sakila` |
| JDBC URL | `jdbc:postgresql://localhost:5432/sakila` |

Настройки для `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sakila
spring.datasource.username=sakila
spring.datasource.password=sakila
spring.datasource.driver-class-name=org.postgresql.Driver
```