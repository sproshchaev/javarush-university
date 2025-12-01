-- Слайд 7: Числовые функции
-- Арифметические операции и функции округления

-- 1. Арифметические операции
SELECT
    15 + 7 AS addition,
    15 - 7 AS subtraction,
    15 * 7 AS multiplication,
    15 / 7 AS division,
    15 DIV 7 AS integer_division,
    15 % 7 AS modulus;

-- 2. Функции округления
SELECT
    rental_rate,
    ROUND(rental_rate) AS rounded,
    FLOOR(rental_rate) AS floored,
    CEILING(rental_rate) AS ceiled,
    TRUNCATE(rental_rate, 1) AS truncated
FROM sakila.film
         LIMIT 5;

-- 3. ABS и MOD
SELECT
    length,
    ABS(CAST(length AS SIGNED) - 120) AS diff_from_120,
    MOD(length, 60) AS minutes_remainder
FROM sakila.film
         LIMIT 5;