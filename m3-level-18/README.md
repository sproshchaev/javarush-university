Отлично! Создам README.md для репозитория m3-level-18 на основе проделанной работы.

# Memory Management in Java Examples

Примеры кода для вебинара по работе с памятью в Java.  
Каждый файл соответствует одному или нескольким слайдам и содержит исполняемый пример.

## Слайды и соответствующие классы:

1. **Слайды 3-6: Память JVM - Стек vs Куча** [Slide03_06_StackVsHeap.java](src/main/java/com/javarush/example/Slide03_06_StackVsHeap.java)
    - Структура памяти JVM
    - Стек потока и локальные переменные
    - Куча и объекты
    - Взаимодействие потоков с общими объектами

2. **Слайды 9-10: Видимость и состояние гонки** [Slide09_10_MemoryVisibilityRaceCondition.java](src/main/java/com/javarush/example/Slide09_10_MemoryVisibilityRaceCondition.java)
    - Проблема видимости изменений между потоками
    - Ключевое слово volatile
    - Состояние гонки (Race Condition)
    - Решения: synchronized и Atomic классы

3. **Слайд 17: Молодое поколение и Minor GC** [Slide17_YoungGenerationDemo.java](src/main/java/com/javarush/example/Slide17_YoungGenerationDemo.java)
    - Eden Space и выделение памяти
    - Survivor Space и переход объектов
    - Minor GC и его поведение
    - Практические рекомендации

4. **Слайд 18: Старшее поколение и Major GC** [Slide18_OldGenerationDemo.java](src/main/java/com/javarush/example/Slide18_OldGenerationDemo.java)
    - Процесс старения объектов
    - Переход в Old Generation
    - Major GC (Full GC)
    - Настройки кучи (-Xms, -Xmx)

5. **Слайд 19: Metaspace и управление метаданными** [Slide19_MetaspaceDemo.java](src/main/java/com/javarush/example/Slide19_MetaspaceDemo.java)
    - Сравнение PermGen vs Metaspace
    - Загрузка классов и использование памяти
    - Автоматическое управление Metaspace
    - Рекомендации по настройке

6. **Слайд 20: Виды сборщиков мусора** [Slide20_GarbageCollectorsDemo.java](src/main/java/com/javarush/example/Slide20_GarbageCollectorsDemo.java)
    - Serial GC для маленьких приложений
    - Parallel GC для высокой пропускной способности
    - CMS GC для низких задержек
    - Сравнение производительности

7. **Слайд 21: Современные сборщики мусора** [Slide21_ModernCollectorsDemo.java](src/main/java/com/javarush/example/Slide21_ModernCollectorsDemo.java)
    - G1 GC (региональная организация)
    - Shenandoah GC (конкурентная эвакуация)
    - Z GC (масштабируемость до терабайт)
    - Миграция с legacy GC

8. **Слайд 22: Выбор сборщика мусора** [Slide22_GCSelectionGuide.java](src/main/java/com/javarush/example/Slide22_GCSelectionGuide.java)
    - Подход по умолчанию (доверие JVM)
    - Выбор по сценариям использования
    - Дерево решений для выбора GC
    - Практические примеры настройки

9. **Слайд 23: Рекомендации по сборке мусора** [Slide23_GCBestPractices.java](src/main/java/com/javarush/example/Slide23_GCBestPractices.java)
    - Избегание ручных триггеров GC
    - Инструменты для анализа (VisualVM, JConsole)
    - Настройки по умолчанию
    - Полезные флаги JVM

10. **Слайд 24: Специальные ссылки в Java** [Slide24_SpecialReferences.java](src/main/java/com/javarush/example/Slide24_SpecialReferences.java)
    - Strong Reference (обычные ссылки)
    - Weak Reference (для метаданных)
    - Soft Reference (для кэшей)
    - Phantom Reference (для cleanup)

11. **Слайд 25: WeakReference vs SoftReference** [Slide25_WeakVsSoftReference.java](src/main/java/com/javarush/example/Slide25_WeakVsSoftReference.java)
    - Разница в поведении при GC
    - Use case для WeakReference (метаданные)
    - Use case для SoftReference (кэши)
    - Сравнительная таблица характеристик

12. **Слайд 26: PhantomReference** [Slide26_PhantomReferenceDemo.java](src/main/java/com/javarush/example/Slide26_PhantomReferenceDemo.java)
    - Особенности PhantomReference
    - Работа с ReferenceQueue
    - Cleanup операции вместо finalize()
    - Сравнение с другими типами ссылок
