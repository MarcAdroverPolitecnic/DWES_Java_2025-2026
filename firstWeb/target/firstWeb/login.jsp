<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Página de Login</h1>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="login" method="post">
    <label>Usuario:</label><br>
    <input type="text" name="username" required/><br><br>

    <label>Contraseña:</label><br>
    <input type="password" name="password" required/><br><br>

    <input type="submit" value="Entrar"/>
</form>

</body>
</html>