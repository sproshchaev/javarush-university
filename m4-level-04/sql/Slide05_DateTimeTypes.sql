-- Слайд 5: Типы даты и времени
-- DATE, TIME, DATETIME, TIMESTAMP, YEAR

-- 1. DATETIME и TIMESTAMP в Sakila
SELECT
    rental_date,
    last_update,
    DATEDIFF(return_date, rental_date) AS rental_days
FROM sakila.rental
WHERE return_date IS NOT NULL
    LIMIT 5;

-- 2. YEAR тип
SELECT
    title,
    release_year,
    CASE
        WHEN release_year < 2000 THEN 'XX век'
        ELSE 'XXI век'
        END AS century
FROM sakila.film
         LIMIT 10;

-- 3. Извлечение частей даты
SELECT
    rental_date,
    YEAR(rental_date) AS year,
    MONTHNAME(rental_date) AS month,
    DAYOFWEEK(rental_date) AS day_of_week
FROM sakila.rental
    LIMIT 5;