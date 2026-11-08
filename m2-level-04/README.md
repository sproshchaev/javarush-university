# m2-level-04

**Сергей Прощаев**

[![Website](https://img.shields.io/badge/prosoft.pages.dev-blue?logo=googlechrome&logoColor=white)](https://prosoft.pages.dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sproshchaev-0A66C2)](https://www.linkedin.com/in/sproshchaev/)
[![Habr](https://img.shields.io/badge/Habr-sproshchaev-65A3BE?logo=habr&logoColor=white)](https://habr.com/ru/users/sproshchaev/)

---

## 1. Что это

Примеры к вебинару курса JavaRush University: Модуль 2, Уровень 4, ООП и интерфейсы.

На занятии я показываю их в порядке слайдов: объявление интерфейса, роли и способности, методы по умолчанию, утка с двумя интерфейсами. В конце сравнение с абстрактным классом и две задачи с собеседований.

## 2. Что внутри

Примеры лежат в пакете `com.javarush.demo`, один пример в одном файле. Имя файла совпадает с классом, где есть метод `main`.

| № | Класс | Что показывает |
|---|-------|----------------|
| 1 | `SwimmableDemo` | Интерфейс `Swimmable` со слайда, две несвязанные реализации |
| 2 | `HiddenImplementationDemo` | Сервис знает только интерфейс, подмена хранилища во время работы |
| 3 | `InterfaceInheritanceDemo` | Интерфейс с двумя интерфейсами-родителями |
| 4 | `RolesDemo` | Вещи описаны классами, способности интерфейсами |
| 5 | `DogCommandDemo` | Голосовой интерфейс собаки, ошибка для класса без интерфейса |
| 6 | `ContractDemo` | Две сортировки с одним результатом в пределах ожиданий |
| 7 | `ImplementStepsDemo` | Три шага реализации, ошибка при пропущенном методе |
| 8 | `DefaultMethodDemo` | Метод по умолчанию, класс `UnusualCar` со слайда |
| 9 | `DuckDemo` | Утка со слайда: `FlyingBird` и `Waterfowl` в одном классе |
| 10 | `DefaultConflictDemo` | Два одинаковых метода по умолчанию, выбор через `Phone.super.greet()` |
| 11 | `AbstractVsInterfaceDemo` | Абстрактный класс и интерфейс вместе, константа интерфейса |
| З1 | `FilterTask` | Задача: подсчет по условию, условие передается объектом интерфейса |
| З2 | `BracketsTask` | Задача: правильные скобки на стеке за интерфейсом |

В четырех файлах есть закомментированный фрагмент с ошибкой компиляции. Его стоит раскомментировать, прочитать текст ошибки и закомментировать обратно.

## 3. Требования

JDK 17. Для запуска из терминала Maven не нужен.

## 4. Быстрый старт

Ветка по умолчанию `main` пустая, примеры лежат в ветке `base`.

```bash
git clone -b base https://github.com/sproshchaev/javarush-university.git
```

```bash
cd javarush-university/m2-level-04
```

```bash
javac -encoding UTF-8 -d target/demo src/main/java/com/javarush/demo/*.java
```

```bash
java -cp target/demo com.javarush.demo.SwimmableDemo
```

Остальные примеры запускаются последней командой с другим именем класса из таблицы. В IntelliJ IDEA хватит зеленой кнопки запуска рядом с методом `main`.

## 5. Примеры прошлого прогона

Код декабря 2025 года, пакеты `example`, `interfacedemo`, `drawable`, `notification`, `someinterface`.

### Базовые концепции (Слайды 3, 4, 5, 8)
[Slide03_BasicInterface.java](src/main/java/com/javarush/example/Slide03_BasicInterface.java)  
- Демонстрирует объявление интерфейсов и их реализацию в классах
- Показывает работу через ссылку на интерфейс (полиморфизм)
- Иллюстрирует преимущества интерфейсов: гибкость и смену реализаций
- Пример разделения "что такое объект" (классы) и "что он умеет" (интерфейсы)

### Дефолтные методы (Слайд 9)
[Slide09_DefaultMethods.java](src/main/java/com/javarush/example/Slide09_DefaultMethods.java)  
- Демонстрирует default-методы в интерфейсах (Java 8+)
- Показывает переопределение дефолтных методов в классах
- Иллюстрирует решение проблемы "ромба" при множественной реализации
- Пример использования статических методов в интерфейсах

### Множественное наследование (Слайд 10)
[Slide10_MultipleInterfaces.java](src/main/java/com/javarush/example/Slide10_MultipleInterfaces.java)  
- Показывает реализацию нескольких интерфейсов одним классом
- Демонстрирует работу с объектом через разные интерфейсные ссылки
- Иллюстрирует композицию поведения через интерфейсы
- Пример обработки объектов по их ролям (способностям)

## 6. Полезные ссылки

- Презентация занятия: https://drive.google.com/file/d/18kfu5kWAcfwIisAd7Bxor3NxdUwBQUDW
- Запись вебинара появится здесь после эфира 13.11.2026.
