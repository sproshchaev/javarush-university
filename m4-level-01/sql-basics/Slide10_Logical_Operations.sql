-- Фильтрация данных: логические операторы, BETWEEN, IN, LIKE
-- Слайды 10-13

-- Слайд 10: Логические операции AND, OR, NOT
SELECT first_name, last_name
FROM sakila.actor
WHERE first_name = 'PENELOPE' AND last_name = 'GUINESS';

SELECT title, rating
FROM sakila.film
WHERE rating = 'PG' OR rating = 'G';

SELECT title, length
FROM sakila.film
WHERE NOT length < 60;

-- Скобки меняют логику!
SELECT title, rating, length
FROM sakila.film
WHERE (rating = 'PG-13' OR rating = 'R') AND length > 120;

-- Слайд 11: BETWEEN для диапазонов
SELECT title, length
FROM sakila.film
WHERE length BETWEEN 90 AND 120;

-- То же самое без BETWEEN
SELECT title, length
FROM sakila.film
WHERE length >= 90 AND length <= 120;

SELECT payment_id, amount
FROM sakila.payment
WHERE amount BETWEEN 2.99 AND 4.99;

-- BETWEEN для строк
SELECT first_name, last_name
FROM sakila.actor
WHERE first_name BETWEEN 'A' AND 'D'
ORDER BY first_name;

-- Слайд 12: IN для списков значений
SELECT title, rating
FROM sakila.film
WHERE rating IN ('G', 'PG', 'PG-13');

-- То же самое с OR
SELECT title, rating
FROM sakila.film
WHERE rating = 'G' OR rating = 'PG' OR rating = 'PG-13';

SELECT payment_id, customer_id, amount
FROM sakila.payment
WHERE customer_id IN (1, 2, 3);

-- NOT IN
SELECT title, rating
FROM sakila.film
WHERE rating NOT IN ('NC-17', 'R');

-- Слайд 13: LIKE для поиска по шаблону
SELECT first_name, last_name
FROM sakila.actor
WHERE first_name LIKE 'A%';

SELECT title
FROM sakila.film
WHERE title LIKE '%LOVE%';

SELECT name
FROM sakila.category
WHERE name LIKE '_E_';

SELECT name
FROM sakila.category
WHERE name NOT LIKE '%NEW%';

-- Дополнительный пример
SELECT first_name
FROM sakila.actor
WHERE first_name LIKE '_____';