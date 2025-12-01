-- Демонстрация ключей в базе данных Sakila

-- 1. Показываем первичные ключи
SELECT
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'sakila'
  AND CONSTRAINT_NAME = 'PRIMARY'
ORDER BY TABLE_NAME;

-- 2. Показываем внешние ключи
SELECT
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'sakila'
  AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME;

-- 3. Пример составного ключа в film_actor
DESCRIBE sakila.film_actor;

-- 4. Попытка нарушить целостность данных (вызовет ошибку)
-- INSERT INTO sakila.film_actor (actor_id, film_id) VALUES (99999, 1);