-- Работа с NULL значениями
-- Слайды 17-18

-- Слайд 17: Особенности NULL
-- НЕПРАВИЛЬНО: этот запрос не работает
SELECT title, original_language_id
FROM sakila.film
WHERE original_language_id = NULL;

-- Проверяем количество NULL значений
SELECT COUNT(*)
FROM sakila.film
WHERE original_language_id IS NULL;

-- Сравнение с обычными значениями
SELECT title, original_language_id
FROM sakila.film
WHERE original_language_id = 1
    LIMIT 5;

-- Демонстрация арифметики с NULL
SELECT 1 + NULL;

-- Слайд 18: Правильная работа с NULL
SELECT title, original_language_id
FROM sakila.film
WHERE original_language_id IS NULL
    LIMIT 10;

SELECT title, original_language_id
FROM sakila.film
WHERE original_language_id IS NOT NULL
    LIMIT 10;

-- Комбинирование условий
SELECT title, language_id, original_language_id
FROM sakila.film
WHERE language_id = 1 AND original_language_id IS NOT NULL;

-- Сравнение IS NULL vs = NULL
SELECT COUNT(*) as is_null_count
FROM sakila.film
WHERE original_language_id IS NULL;

SELECT COUNT(*) as equals_null_count
FROM sakila.film
WHERE original_language_id = NULL;