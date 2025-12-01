-- Декартово произведение таблиц
-- Показывает проблему соединения таблиц через запятую

-- 1. Декартово произведение (все комбинации)
SELECT
    a.actor_id AS actor_id_from_actor,
    a.first_name,
    a.last_name,
    fa.actor_id AS actor_id_from_film_actor,
    fa.film_id
FROM sakila.actor a, sakila.film_actor fa
ORDER BY a.actor_id, fa.film_id
    LIMIT 20;

-- 2. Сколько строк получилось?
SELECT
    (SELECT COUNT(*) FROM sakila.actor) AS actors_count,
    (SELECT COUNT(*) FROM sakila.film_actor) AS film_actors_count,
    (SELECT COUNT(*) FROM sakila.actor) *
    (SELECT COUNT(*) FROM sakila.film_actor) AS cartesian_product;