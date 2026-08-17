# Модуль 5. Уровень 8: Валидация и обработка ошибок в Spring MVC

Запуск: `mvn spring-boot:run`, затем http://localhost:8080/users-web/list

Форма создания: http://localhost:8080/users-web/new

Для подключения к консоли:
http://localhost:8080/h2-console/

```
JDBC URL:  jdbc:h2:mem:testdb
User Name: sa
Password:  (пустой)
```

## Ссылки для демо (в порядке показа)

```
1. http://localhost:8080/users-web/list      — список пользователей, поиск по имени
2. http://localhost:8080/users-web/new       — форма: th:object, th:field, @Valid + BindingResult
3. http://localhost:8080/users-unsafe/new    — та же форма без BindingResult: исключение и потеря ввода
4. http://localhost:8080/users-web/1         — детали пользователя
5. http://localhost:8080/users-web/999       — ResourceNotFoundException -> error-404.html
6. http://localhost:8080/users-web/abc       — ошибка привязки типа -> error-500.html
7. http://localhost:8080/users               — тот же DispatcherServlet, но JSON
```

| № | Файл / класс | Что демонстрирует |
|---|--------------|-------------------|
| 1 | `pom.xml` | Стартер `spring-boot-starter-validation` — без него аннотации не работают |
| 2 | `CreateUserDto` | `@NotBlank`, `@Size`, `@Email` — правила на строковых полях |
| 3 | `CreateUserDto`, `User`, `schema.sql` | `@NotNull`, `@Min`, `@Max`, `@Pattern` — числа и регулярное выражение |
| 4 | `UserWebController.showCreateForm`, `create-user.html` | `th:object`, `th:field` — привязка формы к объекту |
| 5 | `UnvalidatedUserController` | `@Valid` без `BindingResult` → `MethodArgumentNotValidException` |
| 6 | `UserWebController.createUser` | `@Valid` + `BindingResult`, правило порядка параметров |
| 7 | `create-user.html` | `#fields.hasErrors`, `th:errors` — вывод ошибок и сохранение ввода |
| 8 | `ValidationMessages.properties` | Вынос текстов из кода, плейсхолдеры `{min}`, `{max}`, `{value}` |
| 9 | `ValidationMessages_de.properties` | Локализация сообщений, заголовок `Accept-Language` |
| 10 | `ResourceNotFoundException`, локальный `@ExceptionHandler` | Проблема дублирования кода, нарушение DRY |
| 11 | `GlobalExceptionHandler` | `@ControllerAdvice`, страницы 404 и 500, приоритет обработчиков |
| 12 | `GlobalExceptionHandler` + `WebRequest` | Логирование стектрейса и адреса запроса |
| 13 | `RestValidationExceptionHandler` | `@RestControllerAdvice` — та же валидация для REST API |

База in-memory: перезапуск приложения возвращает исходных Alice, Bob и Charlie.
Колонка `email` уникальна — повторное сохранение той же почты через форму даст ошибку.

## Заметки к демонстрации

**Демо 5.** В приложении живёт глобальный обработчик с методом на `Exception`, поэтому вместо
белой страницы Spring будет показана `error-500`. Чтобы увидеть сам `MethodArgumentNotValidException`,
на время закомментируйте `handleGenericException` в `GlobalExceptionHandler`.

**Демо 11.** Локальный `@ExceptionHandler` в `UserWebController` перекрывает глобальный.
Чтобы страницу 404 отдал `GlobalExceptionHandler`, закомментируйте метод `handleNotFound` в контроллере.

**Демо 13.** На `RestValidationExceptionHandler` стоит `@Order(Ordered.HIGHEST_PRECEDENCE)`.
Без него `GlobalExceptionHandler` со своим методом на `Exception` перехватит исключение раньше
и вернёт HTML-страницу вместо JSON.

**О файлах свойств.** `ValidationMessages.properties` — это бандл самого Bean Validation.
Обращение к нему идёт по ключу в фигурных скобках прямо из атрибута `message`, и именно так
работают плейсхолдеры `{min}`, `{max}`, `{value}`. Ключи вида `NotBlank.createUserDto.name`
в этом файле не ищутся — они относятся к другому механизму, файлу Spring `messages.properties`,
где атрибут `message` можно не указывать, но именованные плейсхолдеры недоступны.

**О пустых полях формы.** Незаполненное поле приходит пустой строкой, а не `null`,
поэтому `@Pattern` на пустом телефоне дал бы ошибку. В обоих web-контроллерах стоит `@InitBinder`
с `StringTrimmerEditor(true)`, который превращает пустые строки в `null`.

### Проверка локализации сообщений

Немецкие тексты (заголовок `Accept-Language: de`):
```bash
curl -s -X POST http://localhost:8080/users-web/create \
  -H "Accept-Language: de" \
  -d "name=&email=bad&age=5&phone=123" | grep -i "erforderlich"
```

Те же ошибки без заголовка — тексты русские:
```bash
curl -s -X POST http://localhost:8080/users-web/create \
  -d "name=&email=bad&age=5&phone=123" | grep -i "обязательно"
```

### CURL команды для тестирования HelloController

```bash
curl http://localhost:8080/
```

Получение всех пользователей (GET)
```bash
curl -s http://localhost:8080/users | jq .
```

Получение пользователя по ID (GET)
```bash
curl -s http://localhost:8080/users/1 | jq .
```

Получение пользователя по email (GET)
```bash
curl -s "http://localhost:8080/users/email?email=bob@example.com" | jq .
```

Создание пользователя (POST)
```bash
curl -s -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Karl", "email": "karl@example.com", "age": 33, "phone": "+79001112233"}' | jq .
```

Валидация в REST: некорректные данные дают статус 400 и карту ошибок по полям
```bash
curl -s -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "", "email": "not-an-email", "age": 5}' | jq .
```

Удаление пользователя (DELETE)
```bash
curl -X DELETE http://localhost:8080/users/1
```

Обновление пользователя полностью (PUT)
```bash
curl -s -X PUT http://localhost:8080/users/2 \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice Updated", "email": "alice.new@example.com", "age": 31, "phone": "+79009998877"}' | jq .
```

### References
1. Предыдущий уровень: Spring MVC и построение веб-приложений — `m5-level-07`
