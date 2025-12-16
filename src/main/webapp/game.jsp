<%--
  Created by IntelliJ IDEA.
  User: sergeyproshchaev
  Date: 16.12.2025
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Космический квест</title>
</head>

<body>

<h1>Космический квест</h1>

<div>
    <p>Игрок: ${sessionScope.playerName}</p>
    <p>Сыграно игр: ${sessionScope.gamesPlayed != null ? sessionScope.gamesPlayed : 0}</p>
</div>

<hr>
<h2>${step.text}</h2>
<c:choose>

    <c:when test="${not empty step.option1}">
            <form action="game" method="post">
                <input type="hidden" name="currentStep" value="${step.id}">
                <p>
                    <input type="radio" name="answer" value="1" required>
                    ${step.option1}
                </p>

                <p>
                    <input type="radio" name="answer" value="2" required>
                    ${step.option2}
                </p>

                <button type="submit">Ответить</button>

            </form>
    </c:when>

    <c:otherwise>
        <h3>${step.text}</h3>
        <p>
            <a href="game?step=start">Играть снова</a> |
            <a href="start">Начать с начала</a>
        </p>
    </c:otherwise>

</c:choose>

<hr>

<div>
    <p>ID сессии: ${pageContext.session.id}</p>
    <p>Текущий шаг: ${step.id}</p>
</div>
</body>
</html>
