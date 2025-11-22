# Java Date Time API Examples

Примеры кода для вебинара по работе со временем и датой в Java.  
Каждый файл соответствует группе слайдов и содержит исполняемые примеры работы с Date Time API.

## Слайды и соответствующие классы:

1. **Устаревшие классы Date и Calendar** [Slide03_LegacyDateProblems.java](src/main/java/com/javarush/example/Slide03_LegacyDateProblems.java)
    - Работа с классом Date (создание, получение фрагментов)
    - Проблемы устаревшего API (нумерация месяцев с 0, год от 1900)
    - Форматирование дат через SimpleDateFormat
    - Сравнение с современным API

2. **Современный LocalDate** [Slide11_ModernLocalDate.java](src/main/java/com/javarush/example/Slide11_ModernLocalDate.java)
    - Создание объектов LocalDate (now(), of())
    - Получение фрагментов даты (год, месяц, день недели)
    - Операции с датами (plus, minus методы)
    - Неизменяемость объектов

3. **LocalTime и LocalDateTime** [Slide14_ModernTimeAndDateTime.java](src/main/java/com/javarush/example/Slide14_ModernTimeAndDateTime.java)
    - Работа только со временем (LocalTime)
    - Комбинированная дата и время (LocalDateTime)
    - Операции с временем (добавление/вычитание часов, минут)
    - Получение фрагментов времени

4. **Instant и ZonedDateTime** [Slide18_InstantAndZonedDateTime.java](src/main/java/com/javarush/example/Slide18_InstantAndZonedDateTime.java)
    - Машинное время (Instant) для системных задач
    - Работа с часовыми поясами (ZonedDateTime)
    - Конвертация между Instant и ZonedDateTime
    - Создание объектов из временных меток

5. **Форматирование и парсинг дат** [Slide23_DateTimeFormatterExample.java](src/main/java/com/javarush/example/Slide23_DateTimeFormatterExample.java)
    - Создание форматтеров по шаблонам
    - Форматирование дат в строки
    - Парсинг строк в объекты дат
    - Работа с различными локалями и шаблонами

