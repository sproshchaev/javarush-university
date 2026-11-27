# Модуль 3. Уровень 9. Протокол HTTP

**Сергей Прощаев**

[![Website](https://img.shields.io/badge/prosoft.pages.dev-blue?logo=googlechrome&logoColor=white)](https://prosoft.pages.dev)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sproshchaev-0A66C2)](https://www.linkedin.com/in/sproshchaev/)
[![Habr](https://img.shields.io/badge/Habr-sproshchaev-65A3BE?logo=habr&logoColor=white)](https://habr.com/ru/users/sproshchaev/)

---

Практика к занятию JavaRush University «Протокол HTTP». В Postman отправим четыре запроса к JSONPlaceholder и посмотрим, как адрес, метод, заголовки и тело выглядят в реальном обмене.

## 1. Что внутри

- [Коллекция Postman](JR-M3L9-HTTP.postman_collection.json) содержит четыре запроса в порядке показа.
- [Окружение Postman](JR-M3L9-JSONPlaceholder.postman_environment.json) задает `base_url` со значением `https://jsonplaceholder.typicode.com`.

## 2. Быстрый старт

Понадобятся настольный Postman и доступ к JSONPlaceholder. Запросы проверены в Postman 12.19.6. Ключ API для учебного сервиса не требуется.

1. Скачайте оба JSON-файла. В меню **File** выберите **Import…**, затем нажмите **files** и укажите оба файла.
2. Нажмите **Import**. В разделе **Collections** появится **JavaRush M3L9 — Протокол HTTP**.
3. В правом верхнем списке окружений выберите **JR M3L9 — JSONPlaceholder**. Postman подставит `base_url` в адреса запросов.
4. Откройте **01 GET — Получить пост** и нажмите **Send**. Статус, заголовки и тело ответа появятся в нижней части окна.

## 3. Что смотреть в ответах

| Запрос | Ожидаемый результат | Что проверить в Postman |
| --- | --- | --- |
| `GET /posts/1` | `200 OK` | В заголовках ответа тип `application/json`, в теле — `id: 1`. |
| `GET /posts?userId=1` | `200 OK` | Во вкладке **Params** значение `userId` равно `1`. В теле ответа список постов этого пользователя. |
| `GET /posts/99999` | `404 Not Found` | Метод тот же. Изменился идентификатор в пути, и сервер сообщил, что пост отсутствует. |
| `POST /posts` | `201 Created` | В заголовках запроса `Content-Type: application/json`, во вкладке **Body** три поля из презентации. В ответе появится `id: 101`. |

JSONPlaceholder работает с вымышленными данными и имитирует создание поста. Ответ на POST содержит идентификатор, сама запись на учебном сервере не сохраняется. [Руководство JSONPlaceholder](https://jsonplaceholder.typicode.com/#guide)
