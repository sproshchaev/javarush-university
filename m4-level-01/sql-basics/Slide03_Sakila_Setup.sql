-- Установка и проверка базы данных Sakila
-- Слайд 3: Знакомство с Sakila

-- Подключаемся к базе sakila
USE sakila;

-- Проверяем структуру таблицы actor
DESCRIBE actor;

-- Выводим первых 5 актеров для проверки данных
SELECT * FROM actor LIMIT 5;

-- Проверяем другие основные таблицы
DESCRIBE film;
SELECT * FROM film LIMIT 3;

DESCRIBE customer;
SELECT * FROM customer LIMIT 3;