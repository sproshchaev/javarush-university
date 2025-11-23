-- Слайд 9: Продвинутая группировка
-- WITH ROLLUP и HAVING

-- 1. Базовый пример WITH ROLLUP: итоги по рейтингам
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration
FROM film
GROUP BY rating WITH ROLLUP;

-- 2. WITH ROLLUP с несколькими колонками: иерархические итоги (ИСПРАВЛЕННЫЙ)
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price
FROM film
WHERE rating IS NOT NULL AND language_id IS NOT NULL
GROUP BY rating, language_id WITH ROLLUP;

-- 3. HAVING: фильтрация групп по количеству фильмов
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration
FROM film
GROUP BY rating
HAVING COUNT(*) > 100
ORDER BY film_count DESC;

-- 4. HAVING: фильтрация по средней стоимости проката
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    AVG(replacement_cost) AS avg_replacement_cost
FROM film
GROUP BY rating
HAVING AVG(rental_rate) > 2.50
ORDER BY avg_rental_price DESC;

-- 5. HAVING: сложные условия с несколькими агрегатами
SELECT
    rental_duration,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    MIN(rental_rate) AS min_price,
    MAX(rental_rate) AS max_price
FROM film
WHERE rental_duration IS NOT NULL
GROUP BY rental_duration
HAVING COUNT(*) >= 50 AND AVG(length) > 100
ORDER BY rental_duration;

-- 6. Сравнение WHERE и HAVING

-- WHERE: фильтрация ДО группировки (по отдельным строкам)
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration
FROM film
WHERE length > 120  -- Фильтруем только длинные фильмы
GROUP BY rating;

-- HAVING: фильтрация ПОСЛЕ группировки (по группам)
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration
FROM film
GROUP BY rating
HAVING AVG(length) > 120;  -- Фильтруем группы со средней длиной > 120

-- 7. Комбинирование WHERE и HAVING
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    AVG(rental_rate) AS avg_rental_price
FROM film
WHERE length IS NOT NULL  -- WHERE: убираем фильмы без указания длительности
GROUP BY rating, language_id
HAVING COUNT(*) >= 10 AND AVG(rental_rate) > 2.00  -- HAVING: фильтруем группы
ORDER BY rating, film_count DESC;

-- 8. WITH ROLLUP для бизнес-отчетов (ИСПРАВЛЕННЫЙ)
SELECT
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджет'
        WHEN rental_rate BETWEEN 1.00 AND 2.99 THEN 'Стандарт'
        WHEN rental_rate >= 3.00 THEN 'Премиум'
        ELSE 'Не определено'
        END AS price_category,
    rating,
    COUNT(*) AS film_count,
    SUM(replacement_cost) AS total_inventory_value,
    AVG(rental_rate) AS avg_rental_price
FROM film
WHERE rating IS NOT NULL AND rental_rate IS NOT NULL
GROUP BY
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджет'
        WHEN rental_rate BETWEEN 1.00 AND 2.99 THEN 'Стандарт'
        WHEN rental_rate >= 3.00 THEN 'Премиум'
        ELSE 'Не определено'
        END,
    rating
    WITH ROLLUP;

-- 9. Практический пример: анализ рентабельности
SELECT
    rating,
    rental_duration,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    AVG(replacement_cost) AS avg_replacement_cost,
    -- Расчет рентабельности (упрощенный)
    ROUND(AVG(rental_rate) / AVG(replacement_cost) * 100, 2) AS profitability_percentage
FROM film
WHERE rating IS NOT NULL AND rental_duration IS NOT NULL
GROUP BY rating, rental_duration
HAVING COUNT(*) >= 20 AND AVG(replacement_cost) > 15.00
ORDER BY profitability_percentage DESC
    LIMIT 15;

-- 10. WITH ROLLUP с вычисляемыми полями (ИСПРАВЛЕННЫЙ)
SELECT
    CASE
        WHEN length < 60 THEN 'Короткие'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние'
        WHEN length > 120 THEN 'Длинные'
        ELSE 'Неизвестно'
        END AS duration_group,
    rating,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_rate
FROM film
WHERE length IS NOT NULL AND rating IS NOT NULL
GROUP BY
    CASE
        WHEN length < 60 THEN 'Короткие'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние'
        WHEN length > 120 THEN 'Длинные'
        ELSE 'Неизвестно'
        END,
    rating
    WITH ROLLUP;

-- 11. HAVING с разными агрегатными функциями
SELECT
    language_id,
    COUNT(*) AS total_films,
    COUNT(DISTINCT rating) AS unique_ratings,
    AVG(length) AS avg_duration,
    SUM(replacement_cost) AS total_value
FROM film
GROUP BY language_id
HAVING
    COUNT(*) > 50
   AND COUNT(DISTINCT rating) >= 3
   AND AVG(length) BETWEEN 80 AND 120
ORDER BY total_value DESC;

-- 12. Демонстрация разницы NULL в WITH ROLLUP
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count
FROM film
GROUP BY rating, language_id WITH ROLLUP;
-- NULL в итоговых строках ROLLUP означает "все значения" этой колонки

-- 13. АЛЬТЕРНАТИВА: подзапрос для сортировки ROLLUP результатов
SELECT * FROM (
                  SELECT
                      rating,
                      language_id,
                      COUNT(*) AS film_count
                  FROM film
                  GROUP BY rating, language_id WITH ROLLUP
              ) AS rolled_up
ORDER BY
    CASE WHEN rating IS NULL THEN 1 ELSE 0 END,
    rating,
    CASE WHEN language_id IS NULL THEN 1 ELSE 0 END,
    language_id;