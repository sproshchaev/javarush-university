# SQL Basics Examples

Примеры SQL-запросов для вебинара по основам работы с базами данных и SQL.  
Каждый файл соответствует группе слайдов и содержит исполняемые SQL-запросы для базы данных Sakila.


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

## Слайды и соответствующие файлы:

1. **Установка и настройка базы данных Sakila** [Slide03_Sakila_Setup.sql](sql-basics/Slide03_Sakila_Setup.sql)  
    - Подключение к базе данных Sakila
    - Проверка структуры таблиц
    - Первоначальная проверка данных

2. **Основы SQL и структура данных** [Slide07_Table_Structure.sql](sql-basics/Slide07_Table_Structure.sql)
    - Изучение структуры таблиц и типов данных
    - Базовый синтаксис SQL (SELECT, FROM)
    - Использование WHERE для фильтрации данных
    - Форматирование SQL-запросов

3. **Фильтрация данных: логические операторы** [Slide10_Logical_Operations.sql](sql-basics/Slide10_Logical_Operations.sql)
    - Логические операции AND, OR, NOT
    - Работа с диапазонами значений (BETWEEN)
    - Проверка принадлежности к списку (IN, NOT IN)
    - Поиск по шаблонам строк (LIKE, NOT LIKE)

4. **Ограничение и сортировка результатов** [Slide14_Limit_Offset.sql](sql-basics/Slide14_Limit_Offset.sql)
    - Ограничение количества строк (LIMIT)
    - Постраничный вывод (OFFSET)
    - Удаление дубликатов (DISTINCT)
    - Сортировка результатов (ORDER BY)

5. **Работа с NULL значениями** [Slide17_NULL_Operations.sql](sql-basics/Slide17_NULL_Operations.sql)
    - Особенности значения NULL в SQL
    - Правильное сравнение с NULL (IS NULL, IS NOT NULL)
    - Комбинирование условий с NULL
    - Распространенные ошибки при работе с NULL

## Учебная база данных Sakila

Все примеры подготовлены на учебной базе данных **Sakila** - официальной демонстрационной базе данных от MySQL.

### Установка базы данных Sakila

#### Способ 1: Docker (рекомендуется)

**Что такое Docker Desktop?**

**Docker Desktop** — это настольное приложение, которое упрощает установку и работу с Docker на вашем компьютере.

[Скачать Docker Desktop](https://www.docker.com/products/docker-desktop/)

**Команда для терминала:**
```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=sakila -d -p 3306:3306 restsql/mysql-sakila
```

#### Способ 2: Ручная установка

1. **Официальная документация и схема Sakila**:  
   [https://dev.mysql.com/doc/sakila/en/sakila-structure.html](https://dev.mysql.com/doc/sakila/en/sakila-structure.html)

2. **Скачать базу данных (Version 8.0)**:  
   [https://dev.mysql.com/doc/index-other.html](https://dev.mysql.com/doc/index-other.html)

   Или прямая ссылка на архив: https://downloads.mysql.com/docs/sakila-db.zip

### Что такое Sakila?

Sakila - это стандартная демонстрационная база данных, которая представляет модель бизнеса по аренде фильмов. Она содержит реалистичные данные о фильмах, актерах, клиентах, арендах и платежах, что делает её идеальной для обучения SQL.

## Как использовать

1. Установите базу данных Sakila в вашу MySQL
2. Выполняйте SQL-запросы из соответствующих файлов
3. Изучайте результаты и экспериментируйте с параметрами