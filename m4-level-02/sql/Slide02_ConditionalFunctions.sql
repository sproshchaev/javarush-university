-- Слайд 2: Условные функции IF, IFNULL, NULLIF
-- Упрощенные и гарантированно рабочие примеры

-- 1. Базовые примеры условных функций (раздельно для надежности)
SELECT
    film_id,
    title,
    length,
    -- Простой IF без вложенности
    IF(length > 120, 'Длинный', 'Короткий или средний') AS length_simple
FROM film
WHERE length IS NOT NULL
    LIMIT 10;

-- 2. IFNULL для работы с NULL значениями
SELECT
    film_id,
    title,
    original_language_id,
    -- Замена NULL на значение по умолчанию
    IFNULL(original_language_id, 1) AS effective_language
FROM film
         LIMIT 10;

-- 3. NULLIF для исключения определенных значений
SELECT
    rental_id,
    rental_date,
    return_date,
    -- Скрываем return_date если он равен rental_date
    NULLIF(return_date, rental_date) AS filtered_return_date
FROM rental
         LIMIT 10;

-- 4. Комбинированный пример с простыми условиями
SELECT
    film_id,
    title,
    rental_rate,
    replacement_cost,
    -- Отдельные простые условия вместо вложенных IF
    IF(rental_rate > 2.99, 'Дорогой прокат', 'Стандартный прокат') AS rental_category,
    IF(replacement_cost > 20, 'Высокая стоимость', 'Обычная стоимость') AS replacement_category
FROM film
         LIMIT 15;

-- 5. Практический пример: анализ доступности фильмов
SELECT
    f.film_id,
    f.title,
    f.rental_duration,
    f.rental_rate,
    -- Классификация по сроку аренды
    IF(f.rental_duration > 5, 'Длительная аренда', 'Краткосрочная аренда') AS rental_type,
    -- Классификация по рейтингу
    IF(f.rating = 'PG-13', 'Для подростков', 'Другая аудитория') AS audience_type
FROM film f
WHERE f.rental_rate IS NOT NULL
ORDER BY f.rental_rate DESC
    LIMIT 15;