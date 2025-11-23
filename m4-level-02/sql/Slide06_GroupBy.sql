-- Слайд 6: Оператор GROUP BY
-- Группировка данных для аналитики

-- 1. Базовый пример: количество фильмов по рейтингам
SELECT
    rating,
    COUNT(*) AS film_count
FROM film
GROUP BY rating
ORDER BY film_count DESC;

-- 2. Группировка по сроку аренды
SELECT
    rental_duration,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    AVG(length) AS avg_duration
FROM film
WHERE rental_duration IS NOT NULL
GROUP BY rental_duration
ORDER BY rental_duration;

-- 3. Анализ фильмов по году выпуска
SELECT
    release_year,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    MIN(rental_rate) AS min_price,
    MAX(rental_rate) AS max_price
FROM film
WHERE release_year IS NOT NULL
GROUP BY release_year
ORDER BY release_year DESC;

-- 4. Группировка по языку с дополнительной статистикой
SELECT
    language_id,
    COUNT(*) AS total_films,
    COUNT(DISTINCT rating) AS unique_ratings,
    AVG(length) AS average_length,
    SUM(replacement_cost) AS total_replacement_value
FROM film
GROUP BY language_id
ORDER BY total_films DESC;

-- 5. Анализ по специальным возможностям (special_features)
-- Сначала посмотрим какие есть special_features
SELECT DISTINCT special_features FROM film LIMIT 10;

-- Группировка по наличию определенных фич
SELECT
    CASE
        WHEN special_features LIKE '%Trailers%' THEN 'Есть трейлеры'
        ELSE 'Без трейлеров'
        END AS has_trailers,
    CASE
        WHEN special_features LIKE '%Commentaries%' THEN 'Есть комментарии'
        ELSE 'Без комментариев'
        END AS has_commentaries,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price
FROM film
GROUP BY
    CASE
        WHEN special_features LIKE '%Trailers%' THEN 'Есть трейлеры'
        ELSE 'Без трейлеров'
        END,
    CASE
        WHEN special_features LIKE '%Commentaries%' THEN 'Есть комментарии'
        ELSE 'Без комментариев'
        END
ORDER BY film_count DESC;

-- 6. Группировка с вычисляемыми полями
SELECT
    CASE
        WHEN length < 60 THEN 'Короткие (<60 мин)'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние (60-120 мин)'
        WHEN length > 120 THEN 'Длинные (>120 мин)'
        ELSE 'Неизвестно'
        END AS duration_group,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_rate,
    AVG(replacement_cost) AS avg_replacement_cost
FROM film
WHERE length IS NOT NULL
GROUP BY
    CASE
        WHEN length < 60 THEN 'Короткие (<60 мин)'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние (60-120 мин)'
        WHEN length > 120 THEN 'Длинные (>120 мин)'
        ELSE 'Неизвестно'
        END
ORDER BY film_count DESC;

-- 7. Комбинированная группировка по нескольким полям
SELECT
    rating,
    rental_duration,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    AVG(rental_rate) AS avg_rental_price
FROM film
WHERE rating IS NOT NULL AND rental_duration IS NOT NULL
GROUP BY rating, rental_duration
ORDER BY rating, rental_duration;

-- 8. Практический пример: анализ для ценовой политики
SELECT
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджет (менее $1)'
        WHEN rental_rate BETWEEN 1.00 AND 2.00 THEN 'Эконом ($1-2)'
        WHEN rental_rate BETWEEN 2.01 AND 3.00 THEN 'Стандарт ($2-3)'
        WHEN rental_rate > 3.00 THEN 'Премиум (более $3)'
        ELSE 'Не определено'
        END AS price_category,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    MIN(replacement_cost) AS min_replacement,
    MAX(replacement_cost) AS max_replacement,
    -- Процент от общего количества
    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM film), 2) AS percentage
FROM film
WHERE rental_rate IS NOT NULL
GROUP BY
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджет (менее $1)'
        WHEN rental_rate BETWEEN 1.00 AND 2.00 THEN 'Эконом ($1-2)'
        WHEN rental_rate BETWEEN 2.01 AND 3.00 THEN 'Стандарт ($2-3)'
        WHEN rental_rate > 3.00 THEN 'Премиум (более $3)'
        ELSE 'Не определено'
        END
ORDER BY film_count DESC;