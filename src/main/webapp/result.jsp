<%--
  Created by IntelliJ IDEA.
  User: sergeyproshchaev
  Date: 09.12.2025
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Результат</title>
</head>
<body>

<!-- Отображаем результат -->
<h1>${sessionScope.result}</h1>

<!-- Ссылка для наала заново -->
<a href="hello">Начать заново</a>

<hr>
<!-- Статистика сессии -->
<h3>Статистика сессии:</h3>
<p>ID сессии: ${pageContext.session.id}</p>
<p>Время создания: ${pageContext.session.creationTime}</p>
</body>
</html>
