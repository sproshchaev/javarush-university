# Модуль 4 Уровень 7 JDBC

#### Ссылки
1. Официальная документация и схема Sakila:
https://dev.mysql.com/doc/sakila/en/sakila-structure.html

2. Скачать базу данных (Version 8.0):
https://dev.mysql.com/doc/index-other.html

3. Готовый Docker-образ MySQL 5.7:
https://hub.docker.com/r/restsql/mysql-sakila/

4. Docker Desktop:
https://www.docker.com/products/docker-desktop/

#### Команда для терминала
```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=sakila -d -p 3306:3306 restsql/mysql-sakila
```
