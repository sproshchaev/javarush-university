-- Слайд 5: Агрегатные функции
-- Статистический анализ данных

-- 1. Основной пример: статистика по рейтингам фильмов
SELECT
    rating,
    COUNT(*) AS film_count,
    AVG(length) AS average_length,
    MAX(rental_rate) AS max_rental_rate,
    MIN(rental_rate) AS min_rental_rate,
    AVG(replacement_cost) AS avg_replacement_cost
FROM film
GROUP BY rating
ORDER BY film_count DESC;

-- 2. Анализ фильмов по языку
SELECT
    language_id,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    SUM(replacement_cost) AS total_replacement_value,
    MAX(rental_duration) AS max_rental_period
FROM film
GROUP BY language_id
ORDER BY film_count DESC;

-- 3. Статистика по продолжительности фильмов
SELECT
    CASE
        WHEN length < 60 THEN 'Короткометражные'
        WHEN length BETWEEN 60 AND 120 THEN 'Средние'
        WHEN length > 120 THEN 'Длинные'
        ELSE 'Неизвестно'
        END AS duration_category,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    MIN(replacement_cost) AS min_replacement_cost,
    MAX(replacement_cost) AS max_replacement_cost
FROM film
WHERE length IS NOT NULL
GROUP BY duration_category
ORDER BY film_count DESC;

-- 4. Анализ стоимости проката по категориям
SELECT
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджетные'
        WHEN rental_rate BETWEEN 1.00 AND 3.00 THEN 'Стандартные'
        WHEN rental_rate > 3.00 THEN 'Премиум'
        ELSE 'Особые'
        END AS price_category,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    SUM(replacement_cost) AS total_replacement_value,
    COUNT(DISTINCT rating) AS unique_ratings_count
FROM film
WHERE rental_rate IS NOT NULL
GROUP BY price_category
ORDER BY avg_duration DESC;

-- 5. Подробная статистика по рейтингам с дополнительными метриками
SELECT
    rating,
    COUNT(*) AS total_films,
    COUNT(length) AS films_with_known_length,
    COUNT(DISTINCT rental_rate) AS unique_rental_prices,
    AVG(length) AS average_duration,
    MIN(length) AS shortest_film,
    MAX(length) AS longest_film,
    AVG(rental_rate) AS avg_rental_price,
    SUM(replacement_cost) AS total_replacement_value
FROM film
GROUP BY rating
ORDER BY total_films DESC;

-- 6. Анализ сроков аренды
SELECT
    rental_duration,
    COUNT(*) AS film_count,
    AVG(rental_rate) AS avg_rental_price,
    AVG(length) AS avg_duration,
    SUM(rental_rate * rental_duration) AS potential_revenue
FROM film
WHERE rental_duration IS NOT NULL AND rental_rate IS NOT NULL
GROUP BY rental_duration
ORDER BY rental_duration;

-- 7. Сравнение агрегатных функций с разными параметрами (ИСПРАВЛЕННЫЙ)
SELECT
    rating,
    COUNT(*) AS total_rows,
    COUNT(length) AS films_with_length,
    COUNT(original_language_id) AS films_with_original_language,
    AVG(length) AS avg_length,
    AVG(rental_rate) AS avg_rental_rate,
    MIN(rental_rate) AS min_rental,
    MAX(rental_rate) AS max_rental -- убрана лишняя запятая
FROM film
GROUP BY rating
ORDER BY total_rows DESC;

-- 8. Практический пример: анализ для бизнес-решений
SELECT
    rating,
    COUNT(*) AS film_count,
    ROUND(AVG(length), 2) AS avg_duration_minutes,
    ROUND(AVG(rental_rate), 2) AS avg_rental_price,
    ROUND(AVG(replacement_cost), 2) AS avg_replacement_cost,
    ROUND(SUM(replacement_cost), 2) AS total_inventory_value,
    ROUND(SUM(rental_rate * 10), 2) AS estimated_monthly_revenue
FROM film
GROUP BY rating
ORDER BY total_inventory_value DESC;