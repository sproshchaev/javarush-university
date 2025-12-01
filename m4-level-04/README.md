# SQL-типы данных и SQL-функции - Примеры для вебинара

Примеры SQL-запросов для вебинара по SQL-типам данных и функциям.  
Каждый файл соответствует одному слайду и содержит практические примеры для работы с базой данных Sakila.

## Слайды и соответствующие SQL-файлы:

1. **Слайд 2: Обзор типов данных** [Slide02_DataTypesOverview.sql](sql/Slide02_DataTypesOverview.sql)  
   Введение в основные группы типов данных: числовые, строковые, дата/время.

2. **Слайд 3: Числовые типы** [Slide03_NumericTypes.sql](sql/Slide03_NumericTypes.sql)  
   Целые числа, числа с плавающей точкой, DECIMAL для денежных значений.

3. **Слайд 4: Строковые типы** [Slide04_StringTypes.sql](sql/Slide04_StringTypes.sql)  
   CHAR, VARCHAR, TEXT, ENUM и SET - когда и какой тип использовать.

4. **Слайд 5: Типы даты и времени** [Slide05_DateTimeTypes.sql](sql/Slide05_DateTimeTypes.sql)  
   DATE, TIME, DATETIME, TIMESTAMP, YEAR - работа с временными данными.

5. **Слайд 7: Числовые функции** [Slide07_NumericFunctions.sql](sql/Slide07_NumericFunctions.sql)  
   Арифметические операции, функции округления, ABS, MOD.

6. **Слайд 10: Функции работы с датами** [Slide10_DateFunctions.sql](sql/Slide10_DateFunctions.sql)  
   DATE_ADD, DATE_SUB, DATEDIFF, DATE_FORMAT и другие функции дат.

7. **Слайд 11: Преобразования дат** [Slide11_DateConversions.sql](sql/Slide11_DateConversions.sql)  
   STR_TO_DATE, CONVERT_TZ, работа с часовыми поясами и форматами.

8. **Слайд 13: Строковые функции** [Slide13_StringFunctions.sql](sql/Slide13_StringFunctions.sql)  
   CONCAT, SUBSTRING, REPLACE, UPPER/LOWER и другие строковые операции.

9. **Слайд 12: Вызов функций** [Slide12_FunctionUsage.sql](sql/Slide12_FunctionUsage.sql)  
   Использование функций в SELECT, WHERE, ORDER BY, GROUP BY, HAVING.

10. **Все примеры в одном файле** [All_Slides_Examples.sql](sql/All_Slides_Examples.sql)  
    Полный набор всех примеров для быстрого доступа.

## Учебная база данных Sakila

Все примеры подготовлены на учебной базе данных **Sakila** - официальной демонстрационной базе данных от MySQL.

### Установка базы данных Sakila

```bash
docker run --name mysql-sakila -e MYSQL_ROOT_PASSWORD=your_password -d -p 3306:3306 restsql/mysql-sakila
```