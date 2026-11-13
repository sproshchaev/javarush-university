# m3-level-05

**Сергей Прощаев**

[![Website](https://img.shields.io/badge/prosoft.pages.dev-blue?logo=googlechrome&logoColor=white)](https://prosoft.pages.dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sproshchaev-0A66C2)](https://www.linkedin.com/in/sproshchaev/)
[![Habr](https://img.shields.io/badge/Habr-sproshchaev-65A3BE?logo=habr&logoColor=white)](https://habr.com/ru/users/sproshchaev/)

---

## 1. Что это

Примеры к занятию «Модуль 3. Уровень 5. Логирование» курса JavaRush University. В `demo` я собрал полный набор для самостоятельного запуска и пересмотра записи. На эфире мы набираем шесть основных приемов в пакете `com.javarush.live.logback` этого же модуля, затем смотрим остальные готовые классы.

## Оглавление

- [Что внутри](#2-что-внутри)
- [Требования](#3-требования)
- [Быстрый старт](#4-быстрый-старт)
- [Маршрут эфира](#5-маршрут-эфира)
- [Ограничения](#6-ограничения)
- [Полезные ссылки](#7-полезные-ссылки)

## 2. Что внутри

| Слайды | Класс | Тема | Запуск |
|---|---|---|---|
| 5 | `jul.JulDemo` | Встроенный JUL | `main` в IDEA |
| 4, 9–11 | `log4j.Log4jLegacyDemo` | Исторический Log4j 1 | `main` в IDEA |
| 6 | `commons.CommonsLoggingDemo` | Обертка Commons Logging | `main` в IDEA |
| 7–8 | `logback.Slf4jLogbackDemo` | Фасад и реализация | `main` в IDEA |
| 9–11, 13 | `logback.LevelsDemo` | Порог записи событий | `main` в IDEA |
| 4, 13 | `logback.PackageLevelDemo` | Уровень отдельного пакета | `main` в IDEA |
| 3, 16 | `PatternDemo` | Формат записи | `main` в IDEA |
| 8, 14 | `logback.ParametersDemo` | Подстановка параметров | `main` в IDEA |
| 11–12 | `logback.ExceptionDemo` | Исключение с трассой | `main` в IDEA |
| 3, 12 | `LifecycleDemo` | Границы работы и внешняя зависимость | `main` в IDEA |
| 16 | `logback.ConsoleFileDemo` | Консоль и файл | `main` в IDEA |
| 14, 16 | `RotationDemo` | Ротация файла по размеру | `main` в IDEA |
| 15 | `Log4j2FiltersDemo` | Все шесть фильтров из таблицы | `main` в IDEA |

`DemoLogging` — общая настройка Logback. Полные пути классов: `src/main/java/com/javarush/demo/`.
Текстовые слайды без листинга раскрываются минимальными примерами. Для Log4Shell дан разбор механизма в суфлере, исполняемого примера атаки нет. Сетевые и БД-аппендеры перечислены как варианты получателей, внешнюю инфраструктуру для занятия не поднимаем.

### Набор в live

Примеры распределены по пакетам внутри `com.javarush.live`: `jul` — JUL, `commons` — Commons Logging, `log4j` — Log4j 1, `logback` — SLF4J с Logback.

| Класс | Назначение |
|---|---|
| `logback.DemoLogging` | Общая настройка Logback, helper без main |
| `logback.Slf4jLogbackDemo` | Фасад и реализация |
| `logback.LevelsDemo` | Порог WARN |
| `logback.PackageLevelDemo` | Отладка одного пакета |
| `logback.ParametersDemo` | Подстановка значений и проверка уровня |
| `logback.ExceptionDemo` | Полная трасса исключения |
| `logback.ConsoleFileDemo` | Консоль и файл |
| `jul.JulDemo` | Запускаемый пример встроенного логгера |
| `commons.CommonsLoggingDemo` | Запускаемая обертка над JUL |
| `log4j.Log4jLegacyDemo` | Набранный на эфире пример Log4j 1 |
| `logback.LogLevelsDemo` | Начатый на эфире разбор заказа, дополненный обработкой ошибки |

У `LogLevelsDemo` расчет намеренно вызывает деление на ноль: ошибка записывается с трассой, сообщение об успешной обработке заказа после отказа не выводится.

### Примеры прошлого прогона

Сохранены `com.javarush.Main`, `com.javarush.example.Log4jDemo`, `JclDemo`, `JulDemoBasic`, `LogbackDemo` и пустой класс `Example`. У старого `LogbackDemo` импорты `org.apache.log4j`: он фактически использует Log4j. Настоящий вариант с Logback — `com.javarush.demo.Slf4jLogbackDemo`. Старые исходники и ресурсы не перезаписываются этим набором.

## 3. Требования

- JDK 17.
- Maven для получения зависимостей либо уже импортированный проект в IntelliJ IDEA.
- Для основных примеров — SLF4J 1.7.36, Logback Classic и Core 1.2.12, Commons Logging 1.2 и Log4j 1.2.17 из существующего учебного модуля.
- Для `Log4j2FiltersDemo` дополнительно нужны `org.apache.logging.log4j:log4j-api:2.24.0` и `org.apache.logging.log4j:log4j-core:2.24.0`. Они уже подключены в `pom.xml` модуля, без моста SLF4J.


Версии зафиксированы для воспроизведения учебного комплекта. Log4j 1 снят с поддержки, эти исторические зависимости не следует переносить в новый рабочий проект как рекомендуемый стек.

## 4. Быстрый старт

Импортируйте зависимости Maven в IDEA, выберите JDK 17 и запустите `main` нужного класса в модуле `m3-level-05`. Общая настройка вывода находится в `DemoLogging`.

Проверенная сборка из корня репозитория с JDK 17:

```bash
mvn -pl m3-level-05 clean test
```

При уже загруженных зависимостях проверен и офлайн-вариант `mvn -o -pl m3-level-05 clean test`. В модуле нет JUnit-тестов: эта команда проверяет компиляцию всех классов, отдельные main дополнительно запускались при подготовке.

Запуск встроенного логгера без внешних зависимостей из корня `m3-level-05`:

```bash
javac -encoding UTF-8 -d target/demo-jul src/main/java/com/javarush/demo/JulDemo.java
```

```bash
java -cp target/demo-jul com.javarush.demo.JulDemo
```

Остальные классы запускаются через `main` в IDEA после импорта зависимостей. Ожидаемый вывод каждого примера приведен в суфлере занятия. Для проверки код заново извлечен из md и запущен на JDK 17: совпало 13 из 13.

## 5. Маршрут эфира

Набираем в `com.javarush.live.logback`: `Slf4jLogbackDemo`, `LevelsDemo`, `PackageLevelDemo`, `ParametersDemo`, `ExceptionDemo`, `ConsoleFileDemo`. Общий `DemoLogging` готовим заранее. На набор и пояснения оставляем 35–40 минут. Затем открываем остальные классы `demo`: встроенный логгер, исторический Log4j, обертку Commons Logging, формат, события жизненного цикла, ротацию и шесть фильтров второй ветки.

## 6. Ограничения

Файловый пример пишет в `target/logging-demo/application.log`, при повторном запуске заменяет учебный файл. Пример ротации создает отдельный временный каталог и выводит только результат проверок. Ожидаемая трасса исключения привязана к JDK 17 и приведенному исходнику.

В `Log4j2FiltersDemo` вся конфигурация локальна: она собрана из строк в методе настройки. Никакие серверы и базы не нужны. Условия пропуска сообщений видны рядом с каждым фильтром. Пример ротации показывает ограничение размера, исторический слайд описывает ротацию по времени.

## 7. Полезные ссылки

- [Презентация](https://drive.google.com/file/d/1BYpz--8yEB3-K1plXOiHLNwjL0KsUNHt)
- [Фасад SLF4J](https://www.slf4j.org/manual.html)
- [Архитектура Logback](https://logback.qos.ch/manual/architecture.html)
- [Фильтры Log4j 2](https://logging.apache.org/log4j/2.x/manual/filters.html)
- [Статус Log4j 1](https://logging.apache.org/log4j/1.x/)
- [Запись вебинара 12.11.2026](https://youtu.be/QcHY6dYxYks)


## 8. Проверка комплекта

Maven-сборка проверена на JDK 17 с pom.xml и ресурсами этого модуля. Запущены 13 примеров demo, их вывод сравнен с суфлером, затем 10 main из пакетов live. Шесть приемов live также проверены по одному с общим DemoLogging. Проверка не использовала дополнительные зависимости сверх pom.xml.
