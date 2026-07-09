# javarush-university

```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=sakila -d -p 3306:3306 restsql/mysql-sakila
docker run -d --name redis -p 6379:6379 redis:6.2-alpine
```

### Для тех, кто будет использовать PostgreSQL вместо MySQL, можно использовать следующий образ:
```text
postgres: image: postgres:18-alpine
```