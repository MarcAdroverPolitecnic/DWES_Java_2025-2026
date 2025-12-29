<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Historic of decks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/decks.css">
</head>
<body>

<main>
    <a href="${pageContext.request.contextPath}/login?action=logout">Logout</a>
    <h2>Create a new deck</h2>

    <form action="decks" method="post">
        <label for="numberOfDecks">Number of deck sets</label>
        <input type="number" id="numberOfDecks" name="numberOfDecks" value="1" min="1">
        <input type="submit" value="New deck">
    </form>

    <h2>Historic of Decks</h2>
    <c:if test="${not empty decks}">
        <table>
            <thead>
            <tr>
                <th>Deck ID</th>
                <th>Remaining</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="deck" items="${decks}">
                <tr>
                    <td>${deck.id}</td>
                    <td>${deck.remaining}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/decks?id=${deck.id}">Play deck</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty decks}">
        <p>No decks available.</p>
    </c:if>

</main>
</body>
</html>