# Сложные SQL-запросы - Примеры для вебинара

Примеры SQL-запросов для вебинара по сложным SQL-запросам.  
Каждый файл соответствует одному слайду и содержит практические примеры для работы с базой данных Sakila.

## Слайды и соответствующие SQL-файлы:

2. Условные функции: IF, IFNULL, NULLIF [Slide02_ConditionalFunctions.sql](sql/Slide02_ConditionalFunctions.sql)
3. Оператор CASE - простая форма [Slide03_SimpleCase.sql](sql/Slide03_SimpleCase.sql)
4. Оператор CASE - поисковая форма [Slide04_SearchCase.sql](sql/Slide04_SearchCase.sql)
5. Агрегатные функции: COUNT, SUM, AVG, MIN, MAX [Slide05_AggregateFunctions.sql](sql/Slide05_AggregateFunctions.sql)
6. Оператор GROUP BY - группировка данных [Slide06_GroupBy.sql](sql/Slide06_GroupBy.sql)
7. Колонки при группировке - правила использования [Slide07_GroupByColumns.sql](sql/Slide07_GroupByColumns.sql)
8. Группировка по нескольким колонкам [Slide08_MultiColumnGroupBy.sql](sql/Slide08_MultiColumnGroupBy.sql)
9. Продвинутая группировка: WITH ROLLUP и HAVING [Slide09_AdvancedGrouping.sql](sql/Slide09_AdvancedGrouping.sql)  


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