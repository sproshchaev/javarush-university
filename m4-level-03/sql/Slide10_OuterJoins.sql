-- LEFT JOIN и RIGHT JOIN

-- 1. LEFT JOIN: все актеры, даже если у них нет фильмов
-- Для демонстрации создадим актера без фильмов
INSERT INTO sakila.actor (first_name, last_name) VALUES ('Безфильмовый', 'Актер');

SELECT
    a.first_name,
    a.last_name,
    fa.film_id
FROM sakila.actor a
         LEFT JOIN sakila.film_actor fa ON a.actor_id = fa.actor_id
WHERE a.first_name = 'Безфильмовый' OR a.actor_id = 1;

-- 2. RIGHT JOIN: все языки, даже если нет фильмов на них
SELECT
    l.name AS language,
    COUNT(f.film_id) AS film_count
FROM sakila.film f
    RIGHT JOIN sakila.language l ON f.language_id = l.language_id
GROUP BY l.language_id
ORDER BY film_count DESC;

-- 3. INNER JOIN vs LEFT JOIN сравнение
-- Удаляем тестового актера
DELETE FROM sakila.actor WHERE first_name = 'Безфильмовый';