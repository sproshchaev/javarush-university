-- Слайд 4: Строковые типы данных
-- CHAR, VARCHAR, TEXT, ENUM, SET

-- 1. VARCHAR для строк переменной длины
SELECT
    first_name,
    LENGTH(first_name) AS byte_length,
    CHAR_LENGTH(first_name) AS char_length
FROM sakila.actor
    LIMIT 5;

-- 2. ENUM тип (рейтинг фильмов)
SELECT DISTINCT rating
FROM sakila.film;

-- 3. SET тип (особенности фильмов)
SELECT
    title,
    special_features,
    FIND_IN_SET('Trailers', special_features) AS has_trailers
FROM sakila.film
WHERE special_features IS NOT NULL
    LIMIT 5;

-- 4. TEXT для больших описаний
SELECT
    title,
    LEFT(description, 50) AS short_description,
    LENGTH(description) AS description_length
FROM sakila.film
WHERE description LIKE '%Epic%'
    LIMIT 5;