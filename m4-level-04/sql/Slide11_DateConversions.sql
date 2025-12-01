-- Слайд 11: Преобразования дат и часовые пояса
-- STR_TO_DATE, DATE_FORMAT, CONVERT_TZ

-- 1. STR_TO_DATE - преобразование строк в даты
SELECT
    STR_TO_DATE('2006-02-15', '%Y-%m-%d') AS from_iso,
    STR_TO_DATE('15/02/2006', '%d/%m/%Y') AS from_european,
    STR_TO_DATE('February 15, 2006', '%M %d, %Y') AS from_text;

-- 2. DATE_FORMAT - даты в строки
SELECT
    last_update,
    DATE_FORMAT(last_update, '%d.%m.%Y') AS eu_format,
    DATE_FORMAT(last_update, '%Y-%m-%d') AS iso_format,
    DATE_FORMAT(last_update, '%H:%i:%s') AS time_only
FROM sakila.actor
         LIMIT 3;

-- 3. CONVERT_TZ - часовые пояса (если поддерживается)
SELECT
    last_update AS original,
    CONVERT_TZ(last_update, '+00:00', '+03:00') AS moscow_time
FROM sakila.actor
         LIMIT 3;