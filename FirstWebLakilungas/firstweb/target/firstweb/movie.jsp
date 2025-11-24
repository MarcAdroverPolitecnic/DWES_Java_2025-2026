<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${movie.title}" /> - MovieFlix</title>

    <style>
        :root {
            --primary: #3b82f6;
            --primary-hover: #2563eb;

            --danger: #dc2626;
            --gray-50: #f9fafb;
            --gray-100: #f3f4f6;
            --gray-200: #e5e7eb;
            --gray-300: #d1d5db;
            --gray-700: #374151;
            --gray-900: #111827;

            --radius: 10px;
            --shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: var(--gray-100);
            padding: 40px 20px;
            color: var(--gray-900);
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        /* Volver */
        .back-link {
            display: inline-block;
            margin-bottom: 1.5rem;
            background: var(--primary);
            color: white;
            padding: 10px 18px;
            border-radius: var(--radius);
            text-decoration: none;
            font-weight: 600;
            transition: 0.15s ease;
            box-shadow: var(--shadow);
        }

        .back-link:hover {
            background: var(--primary-hover);
            transform: translateX(-3px);
        }

        /* Card */
        .card {
            background: white;
            padding: 2rem;
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            margin-bottom: 2rem;
        }

        h1 {
            text-align: center;
            margin-bottom: 1.2rem;
            font-size: 2rem;
            color: var(--gray-900);
        }

        h2 {
            margin-bottom: 1rem;
            color: var(--gray-700);
        }

        /* Campos */
        .field {
            margin-bottom: 1.5rem;
        }

        .label {
            font-weight: 600;
            color: var(--gray-700);
            display: block;
            margin-bottom: 6px;
        }

        .value {
            background: var(--gray-50);
            padding: 12px 14px;
            border-radius: 6px;
            border: 1px solid var(--gray-200);
            font-size: 1rem;
        }

        /* Botón editar */
        .btn-view {
            padding: 10px 20px;
            background: var(--primary);
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 0.95rem;
            font-weight: 600;
            cursor: pointer;
            transition: 0.15s ease;
            margin-top: 1rem;
            box-shadow: var(--shadow);
        }

        .btn-view:hover {
            background: var(--primary-hover);
        }

        /* Comentarios */
        .comment-box {
            background: var(--gray-50);
            padding: 14px;
            margin-bottom: 1rem;
            border-radius: 6px;
            border: 1px solid var(--gray-200);
            box-shadow: var(--shadow);
        }

        .comment-date {
            font-size: 0.85rem;
            color: var(--gray-700);
            margin-bottom: 4px;
        }
    </style>
</head>

<body>
<div class="container">

    <a href="${pageContext.request.contextPath}/movies" class="back-link">← Volver a la lista</a>

    <div class="card">
        <h1><c:out value="${movie.title}" /></h1>

        <div class="field">
            <span class="label">Título</span>
            <div class="value"><c:out value="${movie.title}" /></div>
        </div>

        <div class="field">
            <span class="label">Año</span>
            <div class="value"><c:out value="${movie.year}" /></div>
        </div>

        <div class="field">
            <span class="label">Descripción</span>
            <div class="value"><c:out value="${movie.description}" /></div>
        </div>

        <form action="${pageContext.request.contextPath}/movies" method="get" style="display:inline;">
            <input type="hidden" name="id" value="${movie.id}">
            <input type="hidden" name="update" value="true">
            <button type="submit" class="btn btn-view">Editar</button>
        </form>
    </div>

    <div class="card">
        <h2>Comentarios</h2>

        <c:if test="${empty comments}">
            <p>No hay comentarios aún.</p>
        </c:if>

        <c:forEach var="c" items="${comments}">
            <div class="comment-box">
                <div><c:out value="${c.comment_text}" /></div>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
