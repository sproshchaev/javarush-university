-- Слайд 10: Функции работы с датами
-- DATE_ADD, DATE_SUB, DATEDIFF, DATE_FORMAT

-- 1. Текущие дата и время
SELECT
    NOW() AS now,
    CURDATE() AS today,
    CURTIME() AS current_t;

-- 2. DATE_ADD и DATE_SUB
SELECT
    rental_date,
    DATE_ADD(rental_date, INTERVAL 3 DAY) AS due_date,
    DATE_SUB(rental_date, INTERVAL 1 MONTH) AS month_ago
FROM sakila.rental
         LIMIT 5;

-- 3. DATEDIFF и форматирование
SELECT
    rental_date,
    return_date,
    DATEDIFF(return_date, rental_date) AS days_rented,
    DATE_FORMAT(rental_date, '%d.%m.%Y %H:%i') AS formatted_date
FROM sakila.rental
WHERE return_date IS NOT NULL
    LIMIT 5;