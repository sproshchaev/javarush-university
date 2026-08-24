# Модуль 5. Уровень 10: Создание REST API с Spring

Для подключения к консоли:
http://localhost:8080/h2-console/

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

ДОБАВЛЕНО ДЛЯ ДЕМО: некорректные данные — статус 400 и список замечаний
```bash
curl -i -X POST "http://localhost:8080/users" \
-H "Content-Type: application/json" \
-d '{"name": "", "email": "not-an-email"}'
```

Несуществующий пользователь — статус 404
```bash
curl -i -s "http://localhost:8080/users/999"
```

Создание пользователя через POST
```bash
curl -i -X POST "http://localhost:8080/users" \
-H "Content-Type: application/json" \
-d '{"name": "Dmitry", "email": "dmitry@example.com"}' 
```

Получить пользователя с ID = 1
```bash
curl -s "http://localhost:8080/users/1" | jq .
```

Обновить пользователя с ID = 1
```bash
curl -X PUT "http://localhost:8080/users/1" \
-H "Content-Type: application/json" \
-d '{"name": "Alice Updated", "email": "alice.updated@example.com"}' | jq .
```

Удалить пользователя с ID = 1
```bash
curl -i -X DELETE "http://localhost:8080/users/1" 
```


### References 
1. Ветка с заготовкой spring-data-jpa & CRUD https://github.com/sproshchaev/javarush-university/blob/m5-level-06-spring-data-jpa/m5-level-06/README.md  