-- Соединение таблиц с условием WHERE
-- Правильный способ связи таблиц (старый синтаксис)

-- 1. Осмысленное соединение с WHERE
SELECT
    a.actor_id,
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor a, sakila.film_actor fa
WHERE a.actor_id = fa.actor_id
ORDER BY a.actor_id
    LIMIT 15;

-- 2. Сравнение количества строк
SELECT
    'Декартово произведение' AS join_type,
    (SELECT COUNT(*) FROM sakila.actor) *
    (SELECT COUNT(*) FROM sakila.film_actor) AS row_count
UNION ALL
SELECT
    'Соединение с WHERE',
    COUNT(*)
FROM sakila.actor a, sakila.film_actor fa
WHERE a.actor_id = fa.actor_id;