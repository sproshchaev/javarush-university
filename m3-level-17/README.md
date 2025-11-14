# Behavioral Patterns and Antipatterns Examples

Примеры кода для вебинара по поведенческим паттернам проектирования и антипаттернам в Java.
Каждый файл соответствует одному слайду и содержит исполняемый пример.

## Слайды и соответствующие классы:

### Поведенческие паттерны:

1. **Итератор и Наблюдатель** - базовые поведенческие паттерны
    - [Slide01_IteratorExample.java](src/main/java/com/javarush/example/slide01iterator/Slide01_IteratorExample.java)
    - [Slide01_ObserverExample.java](src/main/java/com/javarush/example/slide01observer/Slide01_ObserverExample.java)

4. **Состояние и Стратегия** - паттерны управления поведением объектов
    - [Slide04_StatePattern.java](src/main/java/com/javarush/example/slide04state/Slide04_StatePattern.java)
    - [Slide04_StrategyPattern.java](src/main/java/com/javarush/example/slide04strategy/Slide04_StrategyPattern.java)

5. **Шаблонный метод** - определение скелета алгоритма
    - [Slide05_TemplateMethodPattern.java](src/main/java/com/javarush/example/slide05templatemethod/Slide05_TemplateMethodPattern.java)

### Многопоточные паттерны:

6. **Блокировки и Семафоры** - механизмы синхронизации потоков
    - [Slide06_MutexExample.java](src/main/java/com/javarush/example/slide06locks/Slide06_MutexExample.java)
    - [Slide06_SemaphoreExample.java](src/main/java/com/javarush/example/slide06locks/Slide06_SemaphoreExample.java)
    - [Slide06_ReadWriteLockExample.java](src/main/java/com/javarush/example/slide06locks/Slide06_ReadWriteLockExample.java)

7. **Монитор** - высокоуровневый механизм синхронизации
    - [Slide07_BoundedBufferMonitor.java](src/main/java/com/javarush/example/slide07monitor/Slide07_BoundedBufferMonitor.java)
    - [Slide07_LockConditionMonitor.java](src/main/java/com/javarush/example/slide07monitor/Slide07_LockConditionMonitor.java)
    - [Slide07_SimpleMonitorExample.java](src/main/java/com/javarush/example/slide07monitor/Slide07_SimpleMonitorExample.java)

8. **Double Checked Locking и Планировщик** - оптимизации и управление потоками
    - [Slide08_DoubleCheckedLocking.java](src/main/java/com/javarush/example/slide08doublechecked/Slide08_DoubleCheckedLocking.java)
    - [Slide08_ModernSingletonAlternatives.java](src/main/java/com/javarush/example/slide08doublechecked/Slide08_ModernSingletonAlternatives.java)
    - [Slide08_TaskScheduler.java](src/main/java/com/javarush/example/slide08scheduler/Slide08_TaskScheduler.java)
    - [Slide08_PriorityScheduler.java](src/main/java/com/javarush/example/slide08scheduler/Slide08_PriorityScheduler.java)

### Антипаттерны:

9. **Обзор антипаттернов** - распространенные ошибки разработки
    - [Slide09_AntipatternsDemo.java](src/main/java/com/javarush/example/slide09antipatterns/Slide09_AntipatternsDemo.java)
    - [Slide09_RealWorldExamples.java](src/main/java/com/javarush/example/slide09antipatterns/Slide09_RealWorldExamples.java)

10. **Магические числа и Класс-бог** - конкретные антипаттерны
    - [Slide10_MagicNumbersStrings.java](src/main/java/com/javarush/example/slide10antipatterns/Slide10_MagicNumbersStrings.java)
    - [Slide10_GodClassAntipattern.java](src/main/java/com/javarush/example/slide10antipatterns/Slide10_GodClassAntipattern.java)

## Структура пакетов:

- `slide01iterator` - паттерн Итератор
- `slide01observer` - паттерн Наблюдатель
- `slide04state` - паттерн Состояние
- `slide04strategy` - паттерн Стратегия
- `slide05templatemethod` - паттерн Шаблонный метод
- `slide06locks` - блокировки и семафоры
- `slide07monitor` - мониторы и условные переменные
- `slide08doublechecked` - double checked locking
- `slide08scheduler` - планировщики задач
- `slide09antipatterns` - общие антипаттерны
- `slide10antipatterns` - конкретные антипаттерны

## Как использовать:

1. Каждый класс содержит исполняемый метод `main()`
2. Примеры демонстрируют как правильные подходы, так и антипаттерны
3. Код содержит комментарии с объяснениями
4. Для многопоточных примеров - запускайте и наблюдайте за поведением потоков

## Ключевые темы вебинара:

- Поведенческие паттерны проектирования
- Многопоточное программирование в Java
- Синхронизация и управление потоками
- Распространенные антипаттерны и как их избежать
- Best practices в enterprise разработке