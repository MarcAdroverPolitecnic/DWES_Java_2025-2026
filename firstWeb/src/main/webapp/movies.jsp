<%@ page import="org.example.firstweb.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marca
  Date: 06/11/2025
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movies</title>
</head>
<body>
<h1>Movie JSP</h1>
<h2>Nom usuari: ${username}</h2>

<p>
    <c:forEach var="movie" items="${movies}">
        ${movie.id}<br>
        ${movie.title}<br>
        ${movie.description}<br>
        ${movie.year}<br>
    </c:forEach>
</p>

<form action="movies" method="post">
    <input type="text" name="title"/>
    <input type="text" name="description"/>
    <input type="text" name="year"/>
    <input type="submit" name="submit"/>
</form>

<form action="movies" method="post">
    <input type="text" name="id">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" name="submit">
</form>

</body>
</html>
