<%@ page import="org.example.firstweb.dto.MovieDto" %>
<%@ page import="org.example.firstweb.dto.CommentDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Movie Details</title>
</head>
<body>

<% MovieDto movie = (MovieDto) request.getAttribute("movie"); %>

<h1><%= movie.getTitle() %></h1>
<p><strong>ID:</strong> <%= movie.getId() %></p>
<p><strong>Description:</strong> <%= movie.getDescription() %></p>
<p><strong>Year:</strong> <%= movie.getYear() %></p>

<h2>Edit Movie</h2>
<form action="movies" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="hidden" name="id" value="<%= movie.getId() %>">

    <label>Title:</label>
    <input type="text" name="title" value="<%= movie.getTitle() %>" required/><br>

    <label>Description:</label>
    <input type="text" name="description" value="<%= movie.getDescription() %>" required/><br>

    <label>Year:</label>
    <input type="number" name="year" value="<%= movie.getYear() %>" required/><br>

    <input type="submit" value="Update"/>
</form>

<h2>Comments</h2>
<% if (movie.getComments() != null && !movie.getComments().isEmpty()) { %>
<ul>
    <% for (CommentDto comment : movie.getComments()) { %>
    <li><%= comment.getCommentText() %></li>
    <% } %>
</ul>
<% } else { %>
<p>No comments yet.</p>
<% } %>

<br>
<a href="movies">Back to list</a>

</body>
</html>