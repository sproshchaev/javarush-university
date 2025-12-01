-- Common Table Expressions (CTE) - альтернатива для старых версий MySQL
-- Используем подзапросы в FROM как замену CTE

-- 1. Аналог CTE: средняя длина по рейтингу через подзапрос в FROM
SELECT
    f.title,
    f.rating,
    f.length,
    abr.avg_length,
    abr.film_count
FROM sakila.film f
         INNER JOIN (
    SELECT
        rating,
        AVG(length) AS avg_length,
        COUNT(*) AS film_count
    FROM sakila.film
    GROUP BY rating
) abr ON f.rating = abr.rating
WHERE f.length > abr.avg_length
ORDER BY f.rating, f.length DESC
    LIMIT 15;

-- 2. Сложный запрос с несколькими подзапросами (аналог CTE с несколькими выражениями)
-- Первый подзапрос: статистика по актерам
SELECT
    ai.first_name,
    ai.last_name,
    ai.film_count,
    f.title AS most_recent_film
FROM (
         -- actor_info подзапрос
         SELECT
             a.actor_id,
             a.first_name,
             a.last_name,
             as_stats.film_count,
             as_stats.last_film
         FROM sakila.actor a
                  INNER JOIN (
             -- actor_stats подзапрос
             SELECT
                 actor_id,
                 COUNT(*) AS film_count,
                 MIN(film_id) AS first_film,
                 MAX(film_id) AS last_film
             FROM sakila.film_actor
             GROUP BY actor_id
             HAVING COUNT(*) > 10
         ) as_stats ON a.actor_id = as_stats.actor_id
     ) ai
         INNER JOIN sakila.film f ON ai.last_film = f.film_id
ORDER BY ai.film_count DESC
    LIMIT 10;

-- 3. Пример с использованием VIEW (если нужна переиспользуемость)
-- Создаем представление как аналог CTE
-- CREATE VIEW actor_film_count_view AS
-- SELECT
--     actor_id,
--     COUNT(*) AS film_count
-- FROM sakila.film_actor
-- GROUP BY actor_id;

-- 4. Простой пример для сравнения: фильмы длиннее среднего
-- С CTE (для MySQL 8.0+):
/*
WITH film_avg AS (
    SELECT AVG(length) AS avg_len FROM sakila.film
)
SELECT title, length
FROM sakila.film, film_avg
WHERE length > avg_len
LIMIT 10;
*/

-- Без CTE (работает везде):
SELECT
    f.title,
    f.length,
    (SELECT AVG(length) FROM sakila.film) AS average_length
FROM sakila.film f
WHERE f.length > (SELECT AVG(length) FROM sakila.film)
    LIMIT 10;

-- 5. Группировка с подзапросом: актеры и количество их фильмов
SELECT
    a.first_name,
    a.last_name,
    film_counts.film_count
FROM sakila.actor a
         INNER JOIN (
    SELECT
        actor_id,
        COUNT(*) AS film_count
    FROM sakila.film_actor
    GROUP BY actor_id
    ORDER BY film_count DESC
        LIMIT 10
) film_counts ON a.actor_id = film_counts.actor_id;