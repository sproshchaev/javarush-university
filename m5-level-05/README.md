# Модуль 5. Уровень 5: Spring Data и работа с базами данных

Для подключения к консоли:
http://localhost:8080/h2-console/

Параметры подключения:

```
JDBC URL:  jdbc:h2:mem:testdb
User Name: sa
Password:  (пустой)
```

## Ссылки для демо

| № | Ссылка | Что демонстрирует | Слайд |
|---|--------|-------------------|-------|
| 1 | `/users` + `/h2-console/` | H2 в памяти, вывод SQL в логи | 5 |
| 2 | `/demo/users/sorted` | `findAll(Sort)` из JpaRepository | 9 |
| 3 | `/demo/users/page?page=0&size=2` | `findAll(Pageable)`, пагинация | 9 |
| 4 | `/demo/users/by-domain?domain=example.com` | `@Query` на JPQL, `@Param`, `LIKE` | 12 |
| 5 | `/demo/users/upper-case?domain=example.com` | Нативный SQL, `@Modifying`, транзакция | 12 |
| 6 | консоль H2, таблицы AUTHORS и BOOKS | `@OneToMany`, `@ManyToOne`, `@JoinColumn` | 13 |
| 7 | `/demo/authors/n-plus-1` | Проблема N+1 в логах | 15 |
| 8 | `/demo/authors/join-fetch` | Решение через `JOIN FETCH` | 16 |
| 9 | `/demo/books` | EAGER по умолчанию для `@ManyToOne` | 14 |
