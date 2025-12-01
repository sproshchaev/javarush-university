-- Слайд 12: Вызов функций в SQL
-- Функции в разных частях запроса

-- 1. Функции в SELECT
SELECT
    title,
    LENGTH(title) AS title_length,
    UPPER(title) AS title_uppercase
FROM sakila.film
         LIMIT 5;

-- 2. Функции в WHERE
SELECT title, length
FROM sakila.film
WHERE CHAR_LENGTH(title) > 15
    LIMIT 5;

-- 3. Функции в ORDER BY
SELECT title
FROM sakila.film
ORDER BY LENGTH(title) DESC
    LIMIT 5;

-- 4. Функции в GROUP BY и HAVING
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS avg_length
FROM sakila.film
GROUP BY rating
HAVING AVG(length) > 115
ORDER BY film_count DESC;