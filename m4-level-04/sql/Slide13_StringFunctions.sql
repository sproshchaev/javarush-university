-- Слайд 13: Строковые функции
-- CONCAT, SUBSTRING, REPLACE, UPPER/LOWER

-- 1. CONCAT и извлечение подстрок
SELECT
    first_name,
    last_name,
    CONCAT(first_name, ' ', last_name) AS full_name,
    LEFT(first_name, 1) AS first_initial,
    SUBSTRING(last_name, 1, 3) AS last_prefix
FROM sakila.actor
    LIMIT 5;

-- 2. REPLACE и поиск
SELECT
    title,
    REPLACE(title, 'LOVE', 'ADORE') AS replaced_title,
    LOCATE('LOVE', title) AS love_position
FROM sakila.film
WHERE title LIKE '%LOVE%'
    LIMIT 5;

-- 3. UPPER, LOWER, REVERSE
SELECT
    title,
    UPPER(title) AS uppercase,
    LOWER(title) AS lowercase,
    REVERSE(title) AS reversed
FROM sakila.film
         LIMIT 5;