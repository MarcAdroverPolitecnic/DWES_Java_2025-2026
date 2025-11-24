<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar «<c:out value="${movie.title}" />» - MovieFlix</title>
    <style>
        :root {
            --primary: #6366f1;
            --primary-dark: #4f46e5;
            --danger: #ef4444;
            --success: #10b981;
            --gray-100: #f3f4f6;
            --gray-200: #e5e7eb;
            --gray-700: #374151;
            --gray-900: #111827;
            --shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            --radius: 12px;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 40px 20px;
            color: var(--gray-900);
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 1.5rem;
            color: white;
            text-decoration: none;
            font-weight: 600;
            font-size: 1.1rem;
            padding: 8px 16px;
            background: rgba(255, 255, 255, 0.2);
            border-radius: 8px;
            backdrop-filter: blur(10px);
            transition: all 0.2s;
        }
        .back-link:hover { background: rgba(255, 255, 255, 0.3); transform: translateX(-4px); }
        .form-card {
            background: white;
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            padding: 2rem;
        }
        h1 {
            text-align: center;
            color: var(--primary-dark);
            margin-bottom: 2rem;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
        }
        label {
            font-weight: 600;
            margin-bottom: 0.5rem;
        }
        input, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid var(--gray-200);
            border-radius: 8px;
            font-size: 1rem;
            background: var(--gray-100);
        }
        textarea { resize: vertical; min-height: 120px; }
        .actions {
            display: flex;
            gap: 1rem;
            justify-content: center;
            flex-wrap: wrap;
        }
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.2s;
            text-decoration: none;
            min-width: 150px;
            text-align: center;
        }
        .btn-update {
            background: var(--success);
            color: white;
        }
        .btn-update:hover {
            background: #059669;
            transform: translateY(-2px);
        }
        .btn-cancel {
            background: var(--gray-700);
            color: white;
        }
        .btn-cancel:hover {
            background: #1f2937;
            transform: translateY(-2px);
        }
        @media (max-width: 768px) {
            form { gap: 1rem; }
            .btn { width: 100%; max-width: 300px; }
        }
    </style>
</head>
<body>
<div class="container">

    <a href="${pageContext.request.contextPath}/movies" class="back-link">← Volver a la lista</a>

    <div class="form-card">
        <h1>Editar Película</h1>
        <form action="${pageContext.request.contextPath}/movies" method="POST">
            <input type="hidden" name="_method" value="PUT" />
            <input type="hidden" name="id" value="${movie.id}" />

            <div>
                <label for="title">Título</label>
                <input type="text" id="title" name="title" value="${movie.title}" required />
            </div>

            <div>
                <label for="year">Año</label>
                <input type="number" id="year" name="year" value="${movie.year}" required min="1880" max="2100" />
            </div>

            <div>
                <label for="description">Descripción</label>
                <textarea id="description" name="description" required><c:out value="${movie.description}" /></textarea>
            </div>

            <div class="actions">
                <button type="submit" class="btn btn-update">Actualizar Película</button>
                <a href="${pageContext.request.contextPath}/movies" class="btn btn-cancel">Cancelar</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>
