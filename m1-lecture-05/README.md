# Циклы в Java — примеры к лекции

Примеры кода для вебинара «Модуль 1. Лекция #5. Циклы».
Каждый класс содержит исполняемый метод `main` и запускается независимо от остальных.

## Блок 1. Цикл while

| № | Класс | Тема | Слайд |
|---|-------|------|-------|
| 1 | [Demo01_WhileCountdown](src/main/java/com/javarush/example/Demo01_WhileCountdown.java) | Базовый цикл while: условие и тело | 3 |
| 2 | [Demo02_WhileSum](src/main/java/com/javarush/example/Demo02_WhileSum.java) | Счётчик и накопитель: сумма чисел от 1 до 10 | 3 |
| 3 | [Demo03_InfiniteLoop](src/main/java/com/javarush/example/Demo03_InfiniteLoop.java) | Бесконечный цикл: причина и исправление | 3 |

## Блок 2. Цикл for

| № | Класс | Тема | Слайд |
|---|-------|------|-------|
| 4 | [Demo04_ForBasic](src/main/java/com/javarush/example/Demo04_ForBasic.java) | Три части цикла for в одной строке | 4 |
| 5 | [Demo05_ForVsWhile](src/main/java/com/javarush/example/Demo05_ForVsWhile.java) | Одна задача в записи while и for | 4 |
| 6 | [Demo06_ForStep](src/main/java/com/javarush/example/Demo06_ForStep.java) | Шаг цикла: через два и в обратную сторону | 4 |
| 7 | [Demo07_ForFactorial](src/main/java/com/javarush/example/Demo07_ForFactorial.java) | Накопление произведения: факториал | 4 |

## Блок 3. Цикл do-while

| № | Класс | Тема | Слайд |
|---|-------|------|-------|
| 8 | [Demo08_DoWhileConsole](src/main/java/com/javarush/example/Demo08_DoWhileConsole.java) | Чтение с клавиатуры до слова exit | 5 |
| 9 | [Demo09_WhileVsDoWhile](src/main/java/com/javarush/example/Demo09_WhileVsDoWhile.java) | Ноль витков против одного при ложном условии | 5 |

## Блок 4. Команды break и continue

| № | Класс | Тема | Слайд |
|---|-------|------|-------|
| 10 | [Demo10_BreakExit](src/main/java/com/javarush/example/Demo10_BreakExit.java) | Связка `while (true)` и `break` | 6 |
| 11 | [Demo11_BreakSearch](src/main/java/com/javarush/example/Demo11_BreakSearch.java) | Остановка перебора сразу после находки | 6 |
| 12 | [Demo12_ContinueBug](src/main/java/com/javarush/example/Demo12_ContinueBug.java) | Зависание из-за пропущенного `i++` | 7 |
| 13 | [Demo13_ContinueFixed](src/main/java/com/javarush/example/Demo13_ContinueFixed.java) | Рабочий пропуск значений через цикл for | 7 |

## Блок 5. Цикл в цикле

| № | Класс | Тема | Слайд |
|---|-------|------|-------|
| 14 | [Demo14_NestedRectangle](src/main/java/com/javarush/example/Demo14_NestedRectangle.java) | Прямоугольник: внутренний цикл на каждом витке внешнего | 8 |
| 15 | [Demo15_NestedTriangle](src/main/java/com/javarush/example/Demo15_NestedTriangle.java) | Треугольник: условие внутреннего цикла зависит от внешнего | 8 |
| 16 | [Demo16_NestedMultiplicationTable](src/main/java/com/javarush/example/Demo16_NestedMultiplicationTable.java) | Таблица умножения: оба счётчика в вычислении | 8 |

## Запуск

Требуется JDK 17 или новее. Каждый класс запускается отдельно из IDE.

Классы `Demo03_InfiniteLoop` и `Demo12_ContinueBug` содержат бесконечные циклы намеренно —
они демонстрируют типичные ошибки и останавливаются кнопкой Stop.
Классы `Demo08_DoWhileConsole` и `Demo10_BreakExit` ожидают ввода с клавиатуры,
для выхода наберите `exit`.
