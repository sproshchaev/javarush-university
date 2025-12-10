# Generics in Java Examples

Примеры кода для вебинара по Generics (Дженерикам) в Java.
Каждый файл соответствует ключевым концепциям и содержит исполняемый пример с подробными комментариями.

## Структура примеров по темам:


### 1. Стирание типов (Type Erasure) и его обход
**Слайды 4, 10, 11:** Как Generics работают внутри и способы обхода ограничений
- [Slide04_TypeErasureAndBypass.java](src/main/java/com/javarush/example/Slide04_TypeErasureAndBypass.java) — подробный пример, объединяющий темы  
  *(Показывает: стирание типов в runtime + паттерн обхода через передачу Class<T> в конструктор)*
- [Slide04_LiveSimple.java](src/main/java/com/javarush/example/Slide04_LiveSimple.java) — простой пример для лайв-кодинга
- [Slide10_LiveSimple.java](src/main/java/com/javarush/example/Slide10_LiveSimple.java) — пример обхода стирания
- [Slide11_LiveSimple.java](src/main/java/com/javarush/example/Slide11_LiveSimple.java) — практическое использование Class<T>

### 2. Создание собственных Generic-классов с ограничениями
**Слайды 5-6:** Параметризованные классы и ограничения через `extends`
- [Slide05_GenericClassWithBounds.java](src/main/java/com/javarush/example/Slide05_GenericClassWithBounds.java) — подробный пример  
  *(Создание: простого generic-класса Zoo<T> + класса с ограничением Zoo<T extends Animal>)*
- [Slide05_06_LiveSimple.java](src/main/java/com/javarush/example/Slide05_06_LiveSimple.java) — простой пример для лайв-кодинга
- [Slide06_LiveExtendsDemo.java](src/main/java/com/javarush/example/Slide06_LiveExtendsDemo.java) — дополнительный пример ограничений

### 3. Wildcards (Маски) и принцип PECS
**Слайды 7, 9:** Ковариантность/контравариантность, `? extends` и `? super`
- [Slide07_WildcardsPecsExample.java](src/main/java/com/javarush/example/Slide07_WildcardsPecsExample.java) — подробный пример  
  *(Объясняет: почему List<Child> не подтип List<Parent> + принцип Producer Extends, Consumer Super)*
- [Slide07_LiveWildcardExtendsDemo.java](src/main/java/com/javarush/example/Slide07_LiveWildcardExtendsDemo.java) — простой пример extends
- [Slide09_LiveSimple.java](src/main/java/com/javarush/example/Slide09_LiveSimple.java) — простой пример extends vs super

### 4. Generic-методы
**Слайд 8:** Параметризованные методы, независимые от класса
- [Slide08_GenericMethodsInPractice.java](src/main/java/com/javarush/example/Slide08_GenericMethodsInPractice.java) — подробный пример  
  *(Показывает: утилитные generic-методы, работа с коллекциями, ограничения Comparable)*
- [Slide08_LiveSimple.java](src/main/java/com/javarush/example/Slide08_LiveSimple.java) — простой пример для лайв-кодинга

