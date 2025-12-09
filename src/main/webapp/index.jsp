<%--
  Created by IntelliJ IDEA.
  User: sergeyproshchaev
  Date: 09.12.2025
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloQuest</title>
</head>
<body>

<!-- 1. заголовок страницы -->
<h1>Добро пожаловать на борт!</h1>
<p>Вы стоите в космическом порту. Принять вызов?</p>

<!-- 2. Блок для отображения сообщений об ошибках -->
<% if (request.getAttribute("message") != null) { %>
<p style="color: red;><%= request.getAttribute("message")%> </p>
<% } %>

<!-- 3. Форма для отправки ответа -->
<form action="game" method="post">
   <!-- Кнопки для выбора ответа -->
   <input type="radio" name="answer" value="accept">Принять вызов<br>
   <input type="radio" name="answer" value="decline">Отклонить вызов<br>
   <input type="submit" value="Ответить">
</form>

</body>
</html>
