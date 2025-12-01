-- Слайд 3: Числовые типы данных
-- Целые числа и числа с плавающей точкой

-- 1. Целочисленные типы в Sakila
SELECT
    'SMALLINT (actor_id)' AS type,
    MIN(actor_id) AS min_value,
    MAX(actor_id) AS max_value,
    COUNT(*) AS count
FROM sakila.actor
UNION ALL
SELECT
    'INT (address_id)' AS type,
    MIN(address_id),
    MAX(address_id),
    COUNT(*)
FROM sakila.address;

-- 2. DECIMAL для точных вычислений (деньги)
SELECT
    rental_rate,
    replacement_cost,
    rental_rate * 1.1 AS increased_rate,
    ROUND(replacement_cost / rental_rate, 2) AS cost_ratio
FROM sakila.film
         LIMIT 5;

-- 3. Проблема UNSIGNED типов
SELECT
    length,
    CAST(length AS SIGNED) - 100 AS safe_subtraction
FROM sakila.film
WHERE length > 100
    LIMIT 5;