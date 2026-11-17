# m2-level-05

**Сергей Прощаев**

[![Website](https://img.shields.io/badge/prosoft.pages.dev-blue?logo=googlechrome&logoColor=white)](https://prosoft.pages.dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sproshchaev-0A66C2)](https://www.linkedin.com/in/sproshchaev/)
[![Habr](https://img.shields.io/badge/Habr-sproshchaev-65A3BE?logo=habr&logoColor=white)](https://habr.com/ru/users/sproshchaev/)

---

## 1. О занятии

JavaRush University, Модуль 2, Уровень 5: Generics. Разбираем проверку типов при компиляции, параметризованные классы и методы, ограничения типов и wildcards.

## 2. Примеры

Пути в таблице ведут на готовые классы. Каждый запускается своим методом `main`.

| Слайды | Класс | Что показываю |
|---|---|---|
| 3 | [Main](src/main/java/com/javarush/Main.java) | Raw-список и список строк. Ошибочные строки закомментированы, вывода в консоль нет |
| 4, 10–11 | [Slide04_TypeErasureAndBypass](src/main/java/com/javarush/example/Slide04_TypeErasureAndBypass.java) | Стирание типов и создание объекта через переданный Class<T> |
| 5–6 | [Slide05_GenericClassWithBounds](src/main/java/com/javarush/example/Slide05_GenericClassWithBounds.java) | Собственный generic-класс и верхняя граница типа |
| 7, 9 | [Slide07_WildcardsPecsExample](src/main/java/com/javarush/example/Slide07_WildcardsPecsExample.java) | Чтение через extends, запись через super, PECS |
| 8 | [Slide08_GenericMethodsInPractice](src/main/java/com/javarush/example/Slide08_GenericMethodsInPractice.java) | Generic-методы, коллекции и Comparable |

Дополнительные короткие примеры:

- [ErasureTypeDemo](src/main/java/com/javarush/erasetype/ErasureTypeDemo.java): стирание и Class<T>.
- [ExtendDemo](src/main/java/com/javarush/extend/ExtendDemo.java): ограничение типа в сервисе ремонта устройств.
- [GenericDemoMethod](src/main/java/com/javarush/method/GenericDemoMethod.java): середина массива и сравнение значений.
- [WildcardsDemo](src/main/java/com/javarush/wildcards/WildcardsDemo.java): границы wildcard на иерархии животных.

## 3. Требования

JDK 17 и Maven 3.9. Проверено на Liberica JDK 17 и Maven 3.9.16. Модуль использует родительский `pom.xml`, поэтому нужен клон репозитория целиком.

## 4. Сборка и запуск

В терминале из корня репозитория, с JDK 17 в `JAVA_HOME`:

```bash
mvn -o -f m2-level-05/pom.xml clean test
```

Ключ `-o` использует локальный кеш Maven. При первой сборке с пустым кешем его нужно убрать для загрузки плагинов.

```bash
java -cp m2-level-05/target/classes com.javarush.example.Slide04_TypeErasureAndBypass
```

Для запуска другого примера замените полное имя класса. В IntelliJ IDEA выберите SDK 17 и нажмите Run рядом с его методом `main`.

## 5. Ограничения и проверка

17.11.2026 копия модуля с исходным родительским POM собрана на JDK 17: 10 исходных файлов. Все девять классов с `main` завершились с кодом 0. Автоматических тестов в модуле нет. `Example` служит заготовкой и не имеет точки входа.

Предупреждение unchecked в `Main` относится к примеру raw-списка. Строки с ожидаемыми ошибками оставлены комментариями, их можно включать по одной при разборе.

Короткие примеры для набора на вебинаре появляются по ходу занятия.
