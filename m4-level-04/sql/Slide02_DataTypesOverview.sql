-- Слайд 2: Обзор типов данных в SQL
-- Демонстрация различных типов данных в базе Sakila

-- 1. Покажем структуру таблиц и типы данных
DESCRIBE sakila.actor;
DESCRIBE sakila.film;
DESCRIBE sakila.payment;

-- 2. Примеры значений разных типов
SELECT
    actor_id AS 'SMALLINT (целое число)',
    first_name AS 'VARCHAR (строка)',
    last_update AS 'TIMESTAMP (дата/время)'
FROM sakila.actor
         LIMIT 3;

-- 3. Тип DECIMAL для денежных значений
SELECT
    payment_id,
    amount AS 'DECIMAL(5,2) - денежная сумма',
    payment_date
FROM sakila.payment
         LIMIT 3;