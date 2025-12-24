# SQL-запросы к нескольким таблицам - Примеры для вебинара

Примеры SQL-запросов для вебинара по работе с несколькими таблицами.  
Каждый файл соответствует одному слайду и содержит практические примеры для работы с базой данных Sakila.

## Слайды и соответствующие SQL-файлы:

1. **Слайд 2: Декартово произведение** [Slide02_CartesianProduct.sql](sql/Slide02_CartesianProduct.sql)  
   Проблема соединения таблиц через запятую, создание неосмысленных комбинаций.

2. **Слайд 3: Соединение с WHERE** [Slide03_JoinWithWhere.sql](sql/Slide03_JoinWithWhere.sql)  
   Правильный способ связи таблиц с использованием условия WHERE в старом синтаксисе.

3. **Слайд 4: Алиасы таблиц** [Slide04_TableAliases.sql](sql/Slide04_TableAliases.sql)  
   Использование псевдонимов для упрощения сложных запросов.

4. **Слайд 5-6: Ключи в SQL** [Slide05_KeysDemo.sql](sql/Slide05_KeysDemo.sql)  
   Демонстрация первичных, внешних и составных ключей в базе Sakila.

5. **Слайд 7: Предпосылки JOIN** [Slide07_JoinPreconditions.sql](sql/Slide07_JoinPreconditions.sql)  
   Проблемы производительности при работе с большими таблицами.

6. **Слайд 8: Визуализация JOIN** [Slide08_JoinVisualization.sql](sql/Slide08_JoinVisualization.sql)  
   Диаграммы Венна для понимания разных типов соединений.

7. **Слайд 9: INNER JOIN** [Slide09_InnerJoin.sql](sql/Slide09_InnerJoin.sql)  
   Внутреннее соединение таблиц, сравнение со старым синтаксисом.

8. **Слайд 10: OUTER JOIN** [Slide10_OuterJoins.sql](sql/Slide10_OuterJoins.sql)  
   LEFT JOIN, RIGHT JOIN и их практическое применение.

9. **Слайд 11: Вложенные запросы** [Slide11_SubqueriesIntro.sql](sql/Slide11_SubqueriesIntro.sql)  
   Введение в подзапросы и их использование в разных частях SQL-запроса.

10. **Слайд 12: Скалярные подзапросы** [Slide12_ScalarSubquery.sql](sql/Slide12_ScalarSubquery.sql)  
    Подзапросы, возвращающие одно значение для сравнений и вычислений.

11. **Слайд 13: Подзапросы со списком значений** [Slide13_ListSubquery.sql](sql/Slide13_ListSubquery.sql)  
    Использование IN и NOT IN с подзапросами для фильтрации данных.

12. **Слайд 14: CTE (WITH выражение)** [Slide14_CTE.sql](sql/Slide14_CTE.sql)  
    Common Table Expressions для структурирования сложных запросов (альтернативы для старых версий MySQL).

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

1. **Официальная документация и схема Sakilia**:  
   [https://dev.mysql.com/doc/sakila/en/sakila-structure.html](https://dev.mysql.com/doc/sakila/en/sakila-structure.html)

2. **Скачать базу данных (Version 8.0)**:  
   [https://dev.mysql.com/doc/index-other.html](https://dev.mysql.com/doc/index-other.html)

   Или прямая ссылка на архив: https://downloads.mysql.com/docs/sakila-db.zip

### Что такое Sakila?

Sakila - это стандартная демонстрационная база данных, которая представляет модель бизнеса по аренде фильмов. Она содержит реалистичные данные о фильмах, актерах, клиентах, арендах и платежах, что делает её идеальной для обучения SQL, особенно для работы с несколькими таблицами.

**Основные таблицы в Sakila:**
- `actor` - актеры
- `film` - фильмы
- `film_actor` - связь фильмов и актеров (составной ключ)
- `category` - категории фильмов
- `customer` - клиенты
- `rental` - аренды
- `payment` - платежи
- `inventory` - инвентарь фильмов
- `staff` - сотрудники
- `store` - магазины

## Как использовать эти примеры

### Для студентов:
1. Установите базу данных Sakila
2. Откройте SQL-файлы в вашем любимом SQL-клиенте (MySQL Workbench, DBeaver, etc.)
3. Выполняйте запросы последовательно, начиная со слайда 2
4. Экспериментируйте с параметрами запросов
5. Пробуйте модифицировать запросы для лучшего понимания

