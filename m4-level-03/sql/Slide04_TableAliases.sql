-- Использование алиасов (псевдонимов) для таблиц

-- 1. Без алиасов (длинный вариант)
SELECT
    sakila.actor.first_name,
    sakila.actor.last_name,
    sakila.film_actor.film_id
FROM sakila.actor, sakila.film_actor
WHERE sakila.actor.actor_id = sakila.film_actor.actor_id
    LIMIT 10;

-- 2. С алиасами (короткий и понятный вариант)
SELECT
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor a, sakila.film_actor fa
WHERE a.actor_id = fa.actor_id
    LIMIT 10;

-- 3. Алиасы с AS (полная форма)
SELECT
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor AS a, sakila.film_actor AS fa
WHERE a.actor_id = fa.actor_id
    LIMIT 10;