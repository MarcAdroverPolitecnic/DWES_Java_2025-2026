<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieFlix - Mis Películas</title>
    <style>
        :root {
            --primary: #3b82f6;
            --primary-hover: #2563eb;

            --danger: #dc2626;
            --danger-hover: #b91c1c;

            --success: #16a34a;

            --gray-50: #f9fafb;
            --gray-100: #f3f4f6;
            --gray-200: #e5e7eb;
            --gray-300: #d1d5db;
            --gray-700: #374151;
            --gray-900: #111827;

            --radius: 10px;
            --shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: var(--gray-100);
            padding: 40px 20px;
            color: var(--gray-900);
        }

        .container {
            max-width: 1100px;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            color: var(--gray-900);
            font-size: 2.4rem;
            margin-bottom: 2rem;
            font-weight: 600;
        }

        /* Tarjeta formulario */
        .add-movie-card {
            background: white;
            padding: 2rem;
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            margin-bottom: 3rem;
        }

        .add-movie-card h2 {
            margin-bottom: 1.2rem;
            color: var(--gray-700);
            font-size: 1.4rem;
        }

        .form-group {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
            gap: 1rem;
            margin-bottom: 1.5rem;
        }

        input[type="text"], input[type="number"] {
            padding: 12px 14px;
            border: 1px solid var(--gray-300);
            border-radius: 6px;
            font-size: 1rem;
        }

        input[type="text"]:focus, input[type="number"]:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.2);
        }

        /* Tabla */
        .table-container {
            background: white;
            border-radius: var(--radius);
            overflow: hidden;
            box-shadow: var(--shadow);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background: var(--gray-200);
            color: var(--gray-900);
        }

        thead th {
            padding: 1rem;
            text-align: left;
            font-weight: 600;
            font-size: 1rem;
        }

        tbody tr {
            transition: background 0.15s;
        }

        tbody tr:hover {
            background: var(--gray-50);
        }

        td {
            padding: 1rem;
            border-bottom: 1px solid var(--gray-200);
        }

        /* Botones */
        .btn {
            padding: 8px 14px;
            border: none;
            border-radius: 6px;
            font-size: 0.9rem;
            font-weight: 600;
            cursor: pointer;
            transition: 0.15s ease;
            text-decoration: none;
            display: inline-block;
        }

        .btn-view {
            background: var(--primary);
            color: white;
        }

        .btn-view:hover {
            background: var(--primary-hover);
        }

        .btn-delete {
            background: var(--danger);
            color: white;
        }

        .btn-delete:hover {
            background: var(--danger-hover);
        }

        .btn-submit {
            background: var(--success);
            color: white;
            padding: 12px 24px;
            font-size: 1rem;
        }

        .btn-submit:hover {
            opacity: 0.9;
        }

        .no-movies {
            text-align: center;
            padding: 2.5rem;
            color: var(--gray-700);
            font-size: 1.1rem;
        }

        @media (max-width: 768px) {
            h1 { font-size: 2rem; }
            .form-group {
                grid-template-columns: 1fr;
            }
        }

        /* Caja de usuario */
        .user-box {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: white;
            padding: 1rem 1.5rem;
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            margin-bottom: 2rem;
        }

        /* Texto del usuario */
        .user-box h2 {
            margin: 0;
            font-size: 1.1rem;
            color: var(--gray-900);
            font-weight: 600;
        }

        /* Botón logout */
        .logout-btn {
            background: var(--danger);
            color: white;
            padding: 8px 16px;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            transition: 0.15s ease;
        }

        .logout-btn:hover {
            background: var(--danger-hover);
        }


    </style>
</head>
<body>
<div class="container">
    <h1>MovieFlix</h1>

    <div class="user-box">
        <h2>Usuario: ${username}</h2>
        <a href="${pageContext.request.contextPath}/login" class="logout-btn">Cerrar sesión</a>
    </div>

    <div class="add-movie-card">
        <h2>Añadir nueva película</h2>
        <form action="${pageContext.request.contextPath}/movies" method="post">
            <div class="form-group">
                <input type="text" name="title" placeholder="Título" required />
                <input type="text" name="description" placeholder="Descripción" required />
                <input type="number" name="year" placeholder="Año" min="1900" max="2100" required />
            </div>
            <button type="submit" class="btn btn-submit">Añadir Película</button>
        </form>
    </div>

    <div class="table-container">
        <c:choose>
            <c:when test="${not empty movies}">
                <table>
                    <thead>
                    <tr>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Año</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td><strong>${movie.title}</strong></td>
                            <td>${movie.description}</td>
                            <td>${movie.year}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/movies" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="${movie.id}">
                                    <button type="submit" class="btn btn-view">Ver Detalles</button>
                                </form>

                                <form action="${pageContext.request.contextPath}/movies" method="post" style="display:inline;"
                                      onsubmit="return confirm('¿Seguro que quieres eliminar «${movie.title}»?');">
                                    <input type="hidden" name="_method" value="DELETE" />
                                    <input type="hidden" name="id" value="${movie.id}" />
                                    <button type="submit" class="btn btn-delete">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="no-movies">
                    <h3>No hay películas aún</h3>
                    <p>¡Añade la primera!</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>