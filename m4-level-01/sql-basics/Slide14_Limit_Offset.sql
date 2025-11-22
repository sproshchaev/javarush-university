-- Ограничение и сортировка результатов
-- Слайды 14-16: LIMIT, OFFSET, DISTINCT, ORDER BY

-- Слайд 14: LIMIT и OFFSET
SELECT title FROM sakila.film LIMIT 5;

SELECT title FROM sakila.film LIMIT 5 OFFSET 5;

-- Альтернативный синтаксис
SELECT title FROM sakila.film LIMIT 10, 5;

-- Постраничный вывод
SELECT title FROM sakila.film
                      LIMIT 10 OFFSET 20;

-- Топ-10 самых длинных фильмов
SELECT title, length
FROM sakila.film
ORDER BY length DESC
    LIMIT 10;

-- Слайд 15: DISTINCT
SELECT DISTINCT rating FROM sakila.film;

SELECT DISTINCT first_name, last_name FROM sakila.actor;

-- Сравним с обычным SELECT
SELECT first_name, last_name FROM sakila.actor LIMIT 10;

SELECT DISTINCT rental_rate FROM sakila.film;

SELECT DISTINCT rating, rental_rate FROM sakila.film;

-- Слайд 16: ORDER BY
SELECT title, length FROM sakila.film ORDER BY length;

SELECT title, length FROM sakila.film ORDER BY length DESC;

-- Множественная сортировка
SELECT title, rating, length
FROM sakila.film
ORDER BY rating ASC, length DESC;

-- Сортировка по алфавиту
SELECT title FROM sakila.film ORDER BY title;

-- Сортировка по номеру колонки
SELECT title, rating, length
FROM sakila.film
ORDER BY 2, 3 DESC;