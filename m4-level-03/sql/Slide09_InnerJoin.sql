-- Использование INNER JOIN для соединения таблиц

-- 1. Базовый INNER JOIN
SELECT
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor a
         INNER JOIN sakila.film_actor fa ON a.actor_id = fa.actor_id
    LIMIT 15;

-- 2. INNER JOIN с несколькими таблицами
SELECT
    a.first_name,
    a.last_name,
    f.title,
    f.rating
FROM sakila.actor a
         INNER JOIN sakila.film_actor fa ON a.actor_id = fa.actor_id
         INNER JOIN sakila.film f ON fa.film_id = f.film_id
WHERE a.actor_id = 1
ORDER BY f.title;

-- 3. Сравнение со старым синтаксисом
SELECT
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor a, sakila.film_actor fa
WHERE a.actor_id = fa.actor_id
    LIMIT 15;