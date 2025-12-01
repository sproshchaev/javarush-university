-- Подзапросы, возвращающие список значений

-- 1. IN: актеры, снимавшиеся в фильмах с рейтингом 'PG-13'
SELECT
    a.first_name,
    a.last_name
FROM sakila.actor a
WHERE a.actor_id IN (
    SELECT fa.actor_id
    FROM sakila.film_actor fa
    WHERE fa.film_id IN (
        SELECT f.film_id
        FROM sakila.film f
        WHERE f.rating = 'PG-13'
    )
)
    LIMIT 15;

-- 2. NOT IN: актеры, которые не снимались в 'R' фильмах
SELECT
    a.first_name,
    a.last_name
FROM sakila.actor a
WHERE a.actor_id NOT IN (
    SELECT DISTINCT fa.actor_id
    FROM sakila.film_actor fa
             INNER JOIN sakila.film f ON fa.film_id = f.film_id
    WHERE f.rating = 'R'
)
    LIMIT 15;

-- 3. Фильмы, в которых снимались актеры из США
SELECT
    f.title,
    f.rating
FROM sakila.film f
WHERE f.film_id IN (
    SELECT fa.film_id
    FROM sakila.film_actor fa
    WHERE fa.actor_id IN (
        SELECT a.actor_id
        FROM sakila.actor a
        WHERE a.last_name LIKE '%S%' -- упрощенный пример
    )
)
    LIMIT 10;