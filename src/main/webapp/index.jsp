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
    <title>HelloQuest (||) начало</title>
</head>
<body>

<h2>Пролог</h2>
<p>Текст предыстории ... </p>

<p>Как ваше имя?</p>

<form action="start" method="post">
    <label for="playerName">"Представьтесь, командир:"</label><br>
    <input type="text" id="playerName" name = "playerName"
           placeholder="Введите ваше имя" required
           value="${sessionScope.playerName != null ? sessionScope.playerName : ''}"><br><br>
    <button type="submit">Начать приключение</button>
</form>
</body>
</html>
