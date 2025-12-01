-- Скалярные подзапросы (возвращают одно значение)

-- 1. Фильмы дороже средней цены проката
SELECT
    title,
    rental_rate,
    (SELECT AVG(rental_rate) FROM sakila.film) AS average_rate
FROM sakila.film
WHERE rental_rate > (SELECT AVG(rental_rate) FROM sakila.film)
ORDER BY rental_rate DESC
    LIMIT 10;

-- 2. Самый длинный фильм и сравнение с другими
SELECT
    title,
    length,
    (SELECT MAX(length) FROM sakila.film) AS max_length,
    (SELECT MAX(length) FROM sakila.film) - length AS difference_from_max
FROM sakila.film
ORDER BY length DESC
    LIMIT 10;

-- 3. Фильмы с самой высокой стоимостью замены
SELECT
    title,
    replacement_cost
FROM sakila.film
WHERE replacement_cost = (SELECT MAX(replacement_cost) FROM sakila.film);