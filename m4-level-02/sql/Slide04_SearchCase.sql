-- Слайд 4: Поисковый CASE
-- Гибкая условная логика с различными условиями

-- 1. Основной пример: классификация фильмов по продолжительности
SELECT
    film_id,
    title,
    length,
    CASE
        WHEN length < 60 THEN 'Короткометражный'
        WHEN length BETWEEN 60 AND 120 THEN 'Средней продолжительности'
        WHEN length > 120 THEN 'Длинный'
        ELSE 'Продолжительность неизвестна'
        END AS length_category
FROM film
ORDER BY length
    LIMIT 20;

-- 2. Классификация по стоимости проката и замены
SELECT
    film_id,
    title,
    rental_rate,
    replacement_cost,
    CASE
        WHEN rental_rate < 1.00 THEN 'Бюджетный прокат'
        WHEN rental_rate BETWEEN 1.00 AND 3.00 THEN 'Стандартный прокат'
        WHEN rental_rate > 3.00 THEN 'Премиум прокат'
        ELSE 'Особая цена'
        END AS rental_category,
    CASE
        WHEN replacement_cost < 15.00 THEN 'Низкая стоимость замены'
        WHEN replacement_cost BETWEEN 15.00 AND 25.00 THEN 'Средняя стоимость замены'
        WHEN replacement_cost > 25.00 THEN 'Высокая стоимость замены'
        ELSE 'Стоимость не определена'
        END AS replacement_category
FROM film
WHERE rental_rate IS NOT NULL
ORDER BY rental_rate DESC, replacement_cost DESC
    LIMIT 15;

-- 3. Сложная бизнес-логика: оценка прибыльности фильмов
SELECT
    film_id,
    title,
    rental_rate,
    rental_duration,
    length,
    CASE
        WHEN rental_rate > 3.00 AND rental_duration <= 5 THEN 'Высокодоходный краткосрочный'
        WHEN rental_rate > 3.00 AND rental_duration > 5 THEN 'Высокодоходный долгосрочный'
        WHEN rental_rate BETWEEN 1.00 AND 3.00 AND length > 120 THEN 'Стандартный длинный'
        WHEN rental_rate BETWEEN 1.00 AND 3.00 AND length <= 120 THEN 'Стандартный короткий'
        WHEN rental_rate < 1.00 THEN 'Бюджетный'
        ELSE 'Особая категория'
        END AS profitability_type
FROM film
WHERE rental_rate IS NOT NULL AND length IS NOT NULL
ORDER BY rental_rate DESC, length DESC
    LIMIT 20;

-- 4. Анализ фильмов по рейтингу и продолжительности
SELECT
    film_id,
    title,
    rating,
    length,
    CASE
        WHEN rating IN ('G', 'PG') AND length < 90 THEN 'Короткий семейный'
        WHEN rating IN ('G', 'PG') AND length >= 90 THEN 'Длинный семейный'
        WHEN rating = 'PG-13' AND length < 100 THEN 'Короткий для подростков'
        WHEN rating = 'PG-13' AND length >= 100 THEN 'Длинный для подростков'
        WHEN rating IN ('R', 'NC-17') AND length < 110 THEN 'Короткий для взрослых'
        WHEN rating IN ('R', 'NC-17') AND length >= 110 THEN 'Длинный для взрослых'
        ELSE 'Специальная категория'
        END AS audience_length_type
FROM film
WHERE rating IS NOT NULL AND length IS NOT NULL
ORDER BY rating, length DESC
    LIMIT 15;

-- 5. Практический пример: рекомендации для клиентов
SELECT
    film_id,
    title,
    rental_rate,
    length,
    special_features,
    CASE
        WHEN rental_rate <= 1.00 AND length < 90 THEN 'Идеально для быстрого просмотра'
        WHEN rental_rate <= 1.00 AND length >= 90 THEN 'Бюджетный вариант на вечер'
        WHEN rental_rate > 3.00 AND special_features LIKE '%Commentaries%' THEN 'Премиум с бонусами'
        WHEN rental_rate > 3.00 THEN 'Премиум контент'
        WHEN length > 150 THEN 'Длинный фильм для терпеливых'
        ELSE 'Стандартный выбор'
        END AS recommendation
FROM film
WHERE rental_rate IS NOT NULL AND length IS NOT NULL
ORDER BY rental_rate, length
    LIMIT 20;

-- 6. Сравнение простого и поискового CASE
-- Простой CASE
SELECT
    film_id,
    title,
    rating,
    CASE rating
        WHEN 'G' THEN 'Для всех'
        WHEN 'PG' THEN 'С контролем'
        WHEN 'PG-13' THEN 'Для подростков'
        WHEN 'R' THEN 'Для взрослых'
        ELSE 'Другое'
        END AS simple_case_example
FROM film
WHERE rating IS NOT NULL
    LIMIT 10;

-- Поисковый CASE (отдельный запрос)
SELECT
    film_id,
    title,
    rating,
    length,
    rental_rate,
    CASE
        WHEN rating IN ('G', 'PG') AND length < 100 THEN 'Короткий семейный'
        WHEN rating IN ('G', 'PG') THEN 'Длинный семейный'
        WHEN rating = 'PG-13' THEN 'Подростковый'
        WHEN rating IN ('R', 'NC-17') THEN 'Для взрослых'
        ELSE 'Особая категория'
        END AS search_case_example
FROM film
WHERE rating IS NOT NULL AND length IS NOT NULL
    LIMIT 10;