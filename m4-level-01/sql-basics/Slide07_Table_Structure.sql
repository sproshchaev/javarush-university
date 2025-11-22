-- Основы SQL и структура данных
-- Слайды 7-9: Таблицы, синтаксис SQL, базовые запросы

-- Слайд 7: Структура таблиц и типы данных
DESCRIBE sakila.film;

-- Выводим данные с разными типами
SELECT film_id, title, release_year, rental_rate
FROM sakila.film
         LIMIT 5;

-- Слайд 8: Базовый синтаксис SQL
-- Комментарии и разные регистры
SELECT * FROM sakila.actor;
select * from sakila.actor;
SELECT first_name, last_name FROM sakila.actor;

-- Слайд 9: WHERE и форматирование запросов
-- Разные способы написания
SELECT * FROM sakila.city WHERE country_id = 44;

SELECT title, description
FROM sakila.film
WHERE rating = 'PG-13';

-- Разные типы условий
SELECT * FROM sakila.actor WHERE first_name = 'PENELOPE';
SELECT * FROM sakila.film WHERE length > 120;
SELECT * FROM sakila.payment WHERE amount BETWEEN 2 AND 5;