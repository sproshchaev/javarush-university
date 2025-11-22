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
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=YOUR_PASSWORD -d -p 3306:3306 restsql/mysql-sakila
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

## Как использовать:

1. Установите базу данных Sakila (инструкции в Slide03_Sakila_Setup.sql)
2. Откройте файлы в MySQL Workbench или другой SQL-среде
3. Выполняйте запросы последовательно для изучения возможностей SQL
4. Экспериментируйте с изменением параметров запросов

## Требования:
- MySQL Server 8.0 или выше
- База данных Sakila
- MySQL Workbench или другая SQL-среда разработки

Все примеры протестированы на базе данных Sakila и готовы к выполнению.