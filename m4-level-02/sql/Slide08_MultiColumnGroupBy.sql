-- Слайд 8: Группировка по нескольким колонкам
-- Многомерный анализ данных

-- 1. Базовый пример: группировка по рейтингу и языку
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    AVG(rental_rate) AS avg_rental_price
FROM film
GROUP BY rating, language_id
ORDER BY rating, film_count DESC;

-- 2. Группировка по трем колонкам: рейтинг, язык, срок аренды
SELECT
    rating,
    language_id,
    rental_duration,
    COUNT(*) AS film_count,
    MIN(rental_rate) AS min_rental_rate,
    MAX(rental_rate) AS max_rental_rate,
    AVG(replacement_cost) AS avg_replacement_cost
FROM film
WHERE rating IS NOT NULL
  AND language_id IS NOT NULL
  AND rental_duration IS NOT NULL
GROUP BY rating, language_id, rental_duration
ORDER BY rating, language_id, rental_duration
    LIMIT 20;

-- 3. Анализ фильмов по рейтингу и продолжительности (с категориями)
SELECT
    rating,
    CASE
        WHEN length < 60 THEN 'Короткие (<60 мин)'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние (60-120 мин)'
        WHEN length > 120 THEN 'Длинные (>120 мин)'
        ELSE 'Неизвестно'
        END AS duration_category,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_rate,
    SUM(replacement_cost) AS total_replacement_value
FROM film
WHERE length IS NOT NULL AND rating IS NOT NULL
GROUP BY rating,
         CASE
             WHEN length < 60 THEN 'Короткие (<60 мин)'
             WHEN length BETWEEN 60 AND 120 THEN 'Средние (60-120 мин)'
             WHEN length > 120 THEN 'Длинные (>120 мин)'
             ELSE 'Неизвестно'
             END
ORDER BY rating, film_count DESC;

-- 4. Анализ ценовой политики по рейтингу и сроку аренды
SELECT
    rating,
    rental_duration,
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджет'
        WHEN rental_rate BETWEEN 1.00 AND 2.99 THEN 'Стандарт'
        WHEN rental_rate >= 3.00 THEN 'Премиум'
        ELSE 'Не определено'
        END AS price_category,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    MIN(replacement_cost) AS min_replacement,
    MAX(replacement_cost) AS max_replacement
FROM film
WHERE rating IS NOT NULL AND rental_duration IS NOT NULL AND rental_rate IS NOT NULL
GROUP BY rating, rental_duration,
         CASE
             WHEN rental_rate < 1.00 THEN 'Бюджет'
             WHEN rental_rate BETWEEN 1.00 AND 2.99 THEN 'Стандарт'
             WHEN rental_rate >= 3.00 THEN 'Премиум'
             ELSE 'Не определено'
             END
ORDER BY rating, rental_duration, film_count DESC
    LIMIT 25;

-- 5. Практический пример: анализ для закупки контента
SELECT
    l.name AS language_name,
    f.rating,
    COUNT(*) AS total_films,
    AVG(f.rental_rate) AS avg_rental_price,
    AVG(f.replacement_cost) AS avg_replacement_cost,
    -- Расчет ROI (Return on Investment) - упрощенный
    ROUND(AVG(f.rental_rate) / AVG(f.replacement_cost) * 100, 2) AS roi_percentage,
    SUM(f.replacement_cost) AS total_investment_value
FROM film f
         JOIN language l ON f.language_id = l.language_id
WHERE f.rating IS NOT NULL
GROUP BY l.name, f.rating
ORDER BY l.name, roi_percentage DESC;

-- 6. Анализ специальных возможностей по рейтингам
SELECT
    rating,
    CASE
        WHEN special_features LIKE '%Trailers%' THEN 'С трейлерами'
        ELSE 'Без трейлеров'
        END AS has_trailers,
    CASE
        WHEN special_features LIKE '%Commentaries%' THEN 'С комментариями'
        ELSE 'Без комментариев'
        END AS has_commentaries,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    AVG(length) AS avg_duration
FROM film
WHERE rating IS NOT NULL AND special_features IS NOT NULL
GROUP BY rating,
         CASE
             WHEN special_features LIKE '%Trailers%' THEN 'С трейлерами'
             ELSE 'Без трейлеров'
             END,
         CASE
             WHEN special_features LIKE '%Commentaries%' THEN 'С комментариями'
             ELSE 'Без комментариев'
             END
ORDER BY rating, film_count DESC
    LIMIT 20;

-- 7. Сравнение группировки по одной vs нескольким колонкам

-- По одной колонке (общая статистика по рейтингам)
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_rate
FROM film
GROUP BY rating
ORDER BY film_count DESC;

-- По двум колонкам (детальная статистика)
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_rate
FROM film
GROUP BY rating, language_id
ORDER BY rating, film_count DESC
    LIMIT 15;

-- 8. Продвинутый пример: анализ распределения фильмов
SELECT
    rating,
    rental_duration,
    language_id,
    COUNT(*) AS film_count,
    -- Абсолютные значения вместо процентов
    (SELECT COUNT(*) FROM film WHERE rating IS NOT NULL AND rental_duration IS NOT NULL) AS total_filtered_films
FROM film
WHERE rating IS NOT NULL AND rental_duration IS NOT NULL
GROUP BY rating, rental_duration, language_id
ORDER BY rating, film_count DESC
    LIMIT 20;

-- 9. ДОПОЛНИТЕЛЬНО: расчет процентов через подзапросы
-- Общее количество для сравнения
SELECT COUNT(*) AS total_films FROM film;

-- Детальная группировка с ручным расчетом процентов
SELECT
    rating,
    language_id,
    COUNT(*) AS film_count,
    -- Процент от общего количества (округленно)
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM film), 2) AS percent_of_total
FROM film
GROUP BY rating, language_id
HAVING COUNT(*) > 10  -- Только значимые группы
ORDER BY percent_of_total DESC
    LIMIT 15;