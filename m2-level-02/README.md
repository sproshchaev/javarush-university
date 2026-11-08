# m2-level-02

**Сергей Прощаев**

[![Website](https://img.shields.io/badge/prosoft.pages.dev-blue?logo=googlechrome&logoColor=white)](https://prosoft.pages.dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sproshchaev-0A66C2)](https://www.linkedin.com/in/sproshchaev/)
[![Habr](https://img.shields.io/badge/Habr-sproshchaev-65A3BE?logo=habr&logoColor=white)](https://habr.com/ru/users/sproshchaev/)

---

## 1. Что это

Примеры к вебинару курса JavaRush University: Модуль 2, Уровень 2, ООП, перегрузка, переопределение и абстрактные классы.

На занятии я показываю их в порядке слайдов. Сначала абстрактные классы, потом перегрузка, в конце переопределение и две задачи с собеседований.

## 2. Что внутри

Примеры лежат в пакете `com.javarush.demo`, один пример в одном файле. Имя файла совпадает с классом, где есть метод `main`.

| № | Класс | Что показывает |
|---|-------|----------------|
| 1 | `AbstractShapeDemo` | Абстрактный класс с абстрактным и готовым методом, два наследника |
| 2 | `NoInstanceDemo` | Объект абстрактного класса создать нельзя |
| 3 | `UnfinishedChildDemo` | Наследник реализовал не все методы, слово abstract по цепочке |
| 4 | `TemplateStateDemo` | Заготовка с полями, конструктором и готовым методом |
| 5 | `NoMultipleInheritanceDemo` | Два родителя запрещены, второй класс подключается полем |
| 6 | `ImplicitImplementationDemo` | Неявная реализация: метод получен от промежуточного родителя |
| 7 | `WiderAccessDemo` | Расширение видимости с `protected` до `public` |
| 8 | `CovariantReturnDemo` | Сужение типа результата с `Fruit` до `Apple` |
| 9 | `OverloadDemo` | Перегрузка и сигнатура, имена параметров в нее не входят |
| 10 | `OverloadWideningDemo` | Выбор перегруженного метода: расширение есть, сужения нет |
| 11 | `OverrideRulesDemo` | Переопределение и перегрузка рядом, пометка `@Override` |
| 12 | `VoiceDemo` | Один метод `voice()` у собаки, кошки и коровы |
| З1 | `OverloadOverrideQuizTask` | Задача: что напечатает программа |
| З2 | `ExpressionTreeTask` | Задача: калькулятор выражений на абстрактном классе |

В семи файлах есть закомментированный фрагмент с ошибкой компиляции. Его стоит раскомментировать, прочитать текст ошибки и закомментировать обратно.

## 3. Требования

JDK 17. Для запуска из терминала Maven не нужен.

## 4. Быстрый старт

Ветка по умолчанию `main` пустая, примеры лежат в ветке `base`.

```bash
git clone -b base https://github.com/sproshchaev/javarush-university.git
```

```bash
cd javarush-university/m2-level-02
```

```bash
javac -encoding UTF-8 -d target/demo src/main/java/com/javarush/demo/*.java
```

```bash
java -cp target/demo com.javarush.demo.AbstractShapeDemo
```

Остальные примеры запускаются последней командой с другим именем класса из таблицы. В IntelliJ IDEA хватит зеленой кнопки запуска рядом с методом `main`.

## 5. Примеры прошлого прогона

Код декабря 2025 года, пакеты `example`, `abstractdemo`, `abstractdemo2`, `overloading`, `parent`, `technics`.

### Абстрактные классы
1. **Абстрактные классы: основы и правила** (Слайды 2-5)  
   [Slide02_AbstractClassDemo.java](src/main/java/com/javarush/example/Slide02_AbstractClassDemo.java)  
   *Демонстрация абстрактных классов: синтаксис, правила создания, наследование, полиморфизм.*

2. **Почему классы называют абстрактными** (Слайд 4)  
   *Только теория — связь с принципом абстракции, концепция "сырой заготовки".*

3. **Почему в Java нет множественного наследования** (Слайд 6)  
   *Только теория — проблема ромбовидного наследования и неоднозначности.*

4. **Реализация абстрактных методов** (Слайд 7)  
   *Интегрировано в примеры — правила совместимости: расширение видимости, сужение типа результата.*

### Перегрузка и переопределение методов
5. **Перегрузка методов (Overloading)** (Слайд 8)  
   [Slide08_OverloadOverrideDemo.java](src/main/java/com/javarush/example/Slide08_OverloadOverrideDemo.java)  
   *Демонстрация перегрузки: разные сигнатуры методов, автоматическое расширение типов.*

6. **Переопределение методов (Overriding)** (Слайды 9-11)  
   [Slide08_OverloadOverrideDemo.java](src/main/java/com/javarush/example/Slide08_OverloadOverrideDemo.java)  
   *Демонстрация переопределения: полиморфизм, ограничения, преимущества единого интерфейса.*

## 6. Полезные ссылки

- Презентация занятия: https://drive.google.com/file/d/1W0zYK_RsCXRLaD73q2WfQLaZwHRyczzs
- Запись вебинара появится здесь после эфира 10.11.2026.
