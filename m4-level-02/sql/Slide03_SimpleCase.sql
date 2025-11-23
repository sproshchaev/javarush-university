-- Слайд 3: Оператор CASE - простая форма
-- Преобразование кодов в читаемые значения

-- 1. Основной пример: преобразование рейтингов фильмов
SELECT
    film_id,
    title,
    rating,
    CASE rating
        WHEN 'G' THEN 'Для всех возрастов'
        WHEN 'PG' THEN 'Рекомендуется присутствие родителей'
        WHEN 'PG-13' THEN 'Детям до 13 лет просмотр не желателен'
        WHEN 'R' THEN 'Лицам до 17 лет обязательно присутствие взрослого'
        WHEN 'NC-17' THEN 'Лицам до 18 лет просмотр запрещён'
        ELSE 'Рейтинг не определён'
        END AS rating_description
FROM film
ORDER BY rating
    LIMIT 20;

-- 2. CASE для классификации по rental_duration
SELECT
    film_id,
    title,
    rental_duration,
    CASE rental_duration
        WHEN 3 THEN 'Краткосрочная аренда (3 дня)'
        WHEN 5 THEN 'Стандартная аренда (5 дней)'
        WHEN 7 THEN 'Длительная аренда (7 дней)'
        ELSE 'Особые условия аренды'
        END AS rental_period
FROM film
WHERE rental_duration IS NOT NULL
ORDER BY rental_duration
    LIMIT 15;

-- 3. Практический пример: анализ стоимости проката (упрощенный)
SELECT
    film_id,
    title,
    rental_rate,
    CASE rental_rate
        WHEN 0.99 THEN 'Бюджетный'
        WHEN 2.99 THEN 'Стандартный'
        WHEN 4.99 THEN 'Премиум'
        ELSE 'Особая цена'
        END AS price_category
FROM film
WHERE rental_rate IS NOT NULL
ORDER BY rental_rate DESC
    LIMIT 15;

-- 4. Отдельный пример с IF для сравнения
SELECT
    film_id,
    title,
    rental_rate,
    IF(rental_rate > 2.99, 'Дорогой', 'Доступный') AS simple_classification
FROM film
WHERE rental_rate IS NOT NULL
ORDER BY rental_rate DESC
    LIMIT 15;

-- 5. Сложный пример: анализ фильмов по нескольким критериям (исправленный)
SELECT
    film_id,
    title,
    rating,
    rental_duration,
    rental_rate, -- убрана лишняя запятая здесь
    CASE rating
        WHEN 'G' THEN 'Семейный'
        WHEN 'PG' THEN 'Детский с контролем'
        WHEN 'PG-13' THEN 'Подростковый'
        WHEN 'R' THEN 'Взрослый'
        WHEN 'NC-17' THEN 'Строго 18+'
        ELSE 'Не классифицирован'
        END AS audience_type,
    CASE rental_duration
        WHEN 3 THEN 'Экспресс-просмотр'
        WHEN 5 THEN 'Стандартный просмотр'
        WHEN 7 THEN 'Недельный просмотр'
        ELSE 'Расширенный доступ'
        END AS rental_type
FROM film
WHERE length IS NOT NULL AND rental_rate IS NOT NULL
ORDER BY rating, rental_rate DESC
    LIMIT 20;

-- 6. Статистика по рейтингам с использованием CASE
SELECT
    CASE rating
        WHEN 'G' THEN 'Для всех возрастов'
        WHEN 'PG' THEN 'Родительский контроль'
        WHEN 'PG-13' THEN 'Детям до 13 лет не рекомендуется'
        WHEN 'R' THEN 'Только для взрослых'
        WHEN 'NC-17' THEN 'Строго 18+'
        ELSE 'Не определено'
        END AS rating_group,
    COUNT(*) AS film_count,
    AVG(length) AS avg_duration,
    AVG(rental_rate) AS avg_rental_price
FROM film
WHERE rating IS NOT NULL
GROUP BY rating
ORDER BY film_count DESC;