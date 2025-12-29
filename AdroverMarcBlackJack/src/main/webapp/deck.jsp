<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>BlackJack</title>
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/decks">Home</a>
        <a href="${pageContext.request.contextPath}/login?action=logout">Logout</a>
    </nav>
</header>

<main>
    <h2>Deck ID: ${deck.id}</h2>
    <h3>Remaining cards: ${deck.remaining}</h3>

    <form action="draw" method="post">
        <input type="hidden" name="deckId" value="${deck.id}">
        <label for="numberOfCards">Cards to draw</label>
        <input type="number" id="numberOfCards" name="numberOfCards" value="1" min="1">
        <input type="submit" value="Draw cards">
    </form>
    <h2>Drawn cards: ${deck.drawnCards.size()}</h2>
    <c:forEach var="card" items="${deck.drawnCards}">
        <img src="${card.image}" alt="${card.code}" width="150px"/>
    </c:forEach>

</main>
</body>
</html>