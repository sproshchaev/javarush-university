-- Слайд 7: Колонки при группировке
-- Правила использования колонок в SELECT с GROUP BY

-- 1. ПРАВИЛЬНО: только колонки группировки и агрегатные функции
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    MAX(rental_rate) AS max_rental_price
FROM film
GROUP BY rating, language_id
ORDER BY rating, film_count DESC
    LIMIT 15;

-- 2. ПРАВИЛЬНО: группировка по одной колонке, агрегаты по другим
SELECT
    rental_duration,
    COUNT(*) AS total_films,
    COUNT(DISTINCT rating) AS unique_ratings,
    AVG(rental_rate) AS avg_rental_price,
    SUM(replacement_cost) AS total_replacement_value
FROM film
GROUP BY rental_duration
ORDER BY rental_duration;

-- 3. ПРАВИЛЬНО: вычисляемые поля в GROUP BY
SELECT
    CASE
        WHEN length < 60 THEN 'Короткие'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние'
        ELSE 'Длинные'
        END AS length_category,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_price
FROM film
WHERE length IS NOT NULL
GROUP BY
    CASE
        WHEN length < 60 THEN 'Короткие'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние'
        ELSE 'Длинные'
        END
ORDER BY film_count DESC;

-- 4. ДЕМОНСТРАЦИЯ ОШИБКИ: колонка не в GROUP BY и не в агрегате
-- Раскомментируйте для демонстрации ошибки:
-- SELECT
--     rating,
--     title,        -- ОШИБКА: title не в GROUP BY и не в агрегатной функции
--     COUNT(*) AS film_count
-- FROM film
-- GROUP BY rating;

-- 5. КАК ИСПРАВИТЬ ОШИБКУ: варианты решения

-- Вариант 1: Убрать лишнюю колонку
SELECT
    rating,
    COUNT(*) AS film_count
FROM film
GROUP BY rating;

-- Вариант 2: Добавить колонку в GROUP BY
SELECT
    rating,
    title,
    COUNT(*) AS film_count
FROM film
GROUP BY rating, title
ORDER BY rating
    LIMIT 15;

-- Вариант 3: Использовать агрегатную функцию для колонки
SELECT
    rating,
    COUNT(*) AS film_count,
    GROUP_CONCAT(title ORDER BY title SEPARATOR ', ') AS sample_titles,
    MIN(rental_rate) AS min_rental_rate,
    MAX(rental_rate) AS max_rental_rate
FROM film
GROUP BY rating
ORDER BY film_count DESC;

-- 6. ПРАВИЛЬНО: сложная группировка с агрегатами
SELECT
    rating,
    rental_duration,
    language_id,
    COUNT(*) AS film_count,
    AVG(length) AS avg_length,
    MIN(rental_rate) AS min_price,
    MAX(rental_rate) AS max_price,
    -- Агрегатная функция для текстовой колонки
    COUNT(DISTINCT special_features) AS unique_feature_combinations
FROM film
WHERE rating IS NOT NULL
  AND rental_duration IS NOT NULL
GROUP BY rating, rental_duration, language_id
ORDER BY rating, film_count DESC
    LIMIT 20;

-- 7. ПРАКТИЧЕСКИЙ ПРИМЕР: анализ без нарушения правил
SELECT
    f.rating,
    l.name AS language_name,
    COUNT(*) AS total_films,
    -- Агрегатные функции вместо отдельных значений
    MIN(f.length) AS shortest_film,
    MAX(f.length) AS longest_film,
    AVG(f.rental_rate) AS avg_rental_price,
    -- Группировка текстовых значений
    GROUP_CONCAT(
        DISTINCT
        CASE
            WHEN f.rental_rate < 1.00 THEN 'Бюджет'
            WHEN f.rental_rate BETWEEN 1.00 AND 3.00 THEN 'Стандарт'
            ELSE 'Премиум'
        END
        ORDER BY
        CASE
            WHEN f.rental_rate < 1.00 THEN 'Бюджет'
            WHEN f.rental_rate BETWEEN 1.00 AND 3.00 THEN 'Стандарт'
            ELSE 'Премиум'
        END
    ) AS price_categories
FROM film f
         JOIN language l ON f.language_id = l.language_id
WHERE f.rating IS NOT NULL
GROUP BY f.rating, l.name
ORDER BY total_films DESC;

-- 8. ЧАСТЫЕ ОШИБКИ И КАК ИХ ИЗБЕЖАТЬ

-- ОШИБКА: забыли агрегатную функцию
-- SELECT rating, rental_rate FROM film GROUP BY rating;

-- РЕШЕНИЕ: используйте агрегатную функцию
SELECT
    rating,
    AVG(rental_rate) AS avg_rental_rate,
    MIN(rental_rate) AS min_rental_rate,
    MAX(rental_rate) AS max_rental_rate
FROM film
GROUP BY rating;

-- ОШИБКА: не все не-агрегированные колонки в GROUP BY
-- SELECT rating, language_id, rental_duration, COUNT(*)
-- FROM film
-- GROUP BY rating, language_id;

-- РЕШЕНИЕ: добавьте все не-агрегированные колонки в GROUP BY
SELECT
    rating,
    language_id,
    rental_duration,
    COUNT(*) AS film_count
FROM film
GROUP BY rating, language_id, rental_duration
ORDER BY film_count DESC
    LIMIT 15;