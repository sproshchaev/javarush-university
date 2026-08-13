# Модуль 5. Уровень 7: Spring MVC и построение веб-приложений

Запуск: `mvn spring-boot:run`, затем http://localhost:8080/

Для подключения к консоли:
http://localhost:8080/h2-console/

```
JDBC URL:  jdbc:h2:mem:testdb
User Name: sa
Password:  (пустой)
```

## Ссылки для демо (в порядке показа)

```
1. http://localhost:8080/                          — HomeController + index.html
2. http://localhost:8080/users-web/list            — список, счётчик, th:each / th:if
3. http://localhost:8080/users-web/list?name=Alice — @RequestParam, поиск по имени
4. http://localhost:8080/users-web/list?name=Zzz   — пустой результат, второй th:if
5. http://localhost:8080/users-web/view/1          — @PathVariable, детали
6. http://localhost:8080/users-web/view/999        — ResourceNotFoundException -> error-404.html
7. http://localhost:8080/users-web/new             — форма: th:object, th:field, POST + redirect:
8. http://localhost:8080/users                     — тот же DispatcherServlet, но JSON
```

| № | Приём | Где в коде |
|---|---|---|
| 1 | простейший Controller + View | `HomeController.homePage` → `index.html` |
| 2 | `@RequestParam`, `th:each`, `th:if` | `UserWebController.listUsers` → `user-list.html` |
| 3 | `@PathVariable`, `th:text` | `UserWebController.viewUserDetails` → `user-details.html` |
| 4 | `@ModelAttribute` на методе | `UserWebController.totalUsers` — общий счётчик для всех страниц контроллера |
| 5 | форма: `th:object`, `th:field`, `redirect:` | `showCreateForm` + `processCreateForm` → `user-form.html` |
| 6 | `@ExceptionHandler` (локальный) | `UserWebController.handleNotFound` → `error-404.html` |
| 7 | `@ControllerAdvice` (глобальный) | `GlobalExceptionHandler` — включается, если закомментировать локальный |
| 8 | `@RestController` против `@Controller` | `HelloController` — возвращает данные, а не имя шаблона |

База in-memory: перезапуск приложения возвращает исходных Alice, Bob и Charlie.
Колонка `email` уникальна — повторное сохранение той же почты через форму даст ошибку.

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
  -d '{"name": "Karl", "email": "karl@example.com"}' | jq .
```

Удаление пользователя (DELETE)
```bash
curl -X DELETE http://localhost:8080/users/1
```

Обновление пользователя полностью (PUT)
```bash
curl -s -X PUT http://localhost:8080/users/2 \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice Updated", "email": "alice.new@example.com"}' | jq .
```

### References 
1. Ветка с заготовкой spring-data-jpa & CRUD https://github.com/sproshchaev/javarush-university/blob/m5-level-06-spring-data-jpa/m5-level-06/README.md  