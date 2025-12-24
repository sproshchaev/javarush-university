# Модуль 4 Уровень 7 JDBC

#### Ссылки
1. Официальная документация и схема Sakila:
https://dev.mysql.com/doc/sakila/en/sakila-structure.html

2. Скачать базу данных (Version 8.0):
https://dev.mysql.com/doc/index-other.html

3. Готовый Docker-образ:
https://www.docker.com/products/docker-desktop/

4. Docker Desktop:
https://hub.docker.com/r/restsql/mysql-sakila/

#### Команда для терминала
```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=sakila -d -p 3306:3306 restsql/mysql-sakila
```
