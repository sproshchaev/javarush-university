# Модуль 5. Уровень 6: Управление транзакциями в Spring 

Для подключения к консоли:
http://localhost:8080/h2-console/

```
JDBC URL:  jdbc:h2:mem:testdb
User Name: sa
Password:  (пустой)
```

## Ссылки для демо (в порядке показа)

Все demo-эндпоинты сделаны GET сознательно — чтобы вызывать их из закладок браузера.

```
0.  http://localhost:8080/users
0.  http://localhost:8080/demo/audit
0.  http://localhost:8080/h2-console/

1.  http://localhost:8080/demo/tx/update-emails?id1=1&id2=2&email1=alice.ok@example.com&email2=bob.ok@example.com&fail=false
2.  http://localhost:8080/demo/tx/update-emails?id1=1&id2=2&email1=alice.bad@example.com&email2=bob.bad@example.com&fail=true
3.  http://localhost:8080/demo/tx/checked?id=1&email=alice.checked@example.com
4.  http://localhost:8080/demo/tx/checked-rollback?id=1&email=alice.rollback@example.com
5.  http://localhost:8080/demo/tx/rename?id=1&name=Alice_renamed&fail=true
6.  http://localhost:8080/demo/tx/read-only?id=2
7.  http://localhost:8080/demo/tx/dirty-checking?id=2
8.  http://localhost:8080/demo/tx/self-invocation?id=3&email=charlie.self@example.com
9.  http://localhost:8080/demo/tx/external-call?id=3&email=charlie.external@example.com
10. http://localhost:8080/demo/tx/register?name=John&email=john@example.com&fail=true
```

| № | Пример | Ожидаемый результат | Ключевые строки лога |
|---|---|---|---|
| 1 | Откат на unchecked-исключении | оба адреса откатились | `Getting transaction for` → `Completing transaction for ... after exception` → `Initiating transaction rollback` |
| 2 | Checked-исключение | адрес остался в базе | `Completing transaction for ... after exception` → `Initiating transaction commit` |
| 3 | `rollbackFor` | адрес откатился | то же, но `Initiating transaction rollback` |
| 4 | `REQUIRED` vs `REQUIRES_NEW` | в журнале одна запись `[REQUIRES_NEW]` | `Suspending current transaction` / `Resuming suspended transaction` |
| 5 | `readOnly` и dirty checking | у `readOnly` только `select`, у обычного — `select` + `update` | — |
| 6 | Самовызов через `this` | self-invocation сохраняет, external-call откатывает | у self-invocation нет строки `Getting transaction for [...UserService.updateEmailInTransaction]` |
| 7 | `TransactionTemplate` | John в базе отсутствует | `Initiating transaction rollback` после `Sending email` |

База in-memory: перезапуск приложения возвращает данные в исходное состояние.

### CURL команды для тестирования HelloController

```bash
curl http://localhost:8080/
```

Запрос для обновления двух пользователей
```bash
curl -X POST "http://localhost:8080/users/update-emails?id1=1&id2=2&email1=alice.new@example.com&email2=bob.new@example.com" \
  -H "Content-Type: application/json"
```

Метод, который выбрасывает checked исключение после сохранения
```bash
curl -X POST "http://localhost:8080/users/1/email?email=alice.default@example.com"
```

Получение всех пользователей (GET)
```bash
curl -s http://localhost:8080/users | jq .
```

Сервис RegistrationService с использованием TransactionTemplate
```bash
curl -X POST "http://localhost:8080/register?name=John&email=john@example.com"
```

### References 
1. Ветка с заготовкой spring-data-jpa & CRUD https://github.com/sproshchaev/javarush-university/blob/m5-level-06-spring-data-jpa/m5-level-06/README.md  