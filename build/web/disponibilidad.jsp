<%-- 
    Document   : disponibilidad
    Created on : Dec 3, 2024, 2:30:39 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Consultar Disponibilidad</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        form div {
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="date"], input[type="time"], textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        a {
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Consultar Disponibilidad de Vehículos</h1>

        <form action="DisponibilidadServlet" method="post">
            <div>
                <label for="fechaInicio">Fecha de inicio:</label>
                <input type="text" id="fechaInicio" name="fechaInicio" required>
            </div>
            <div>
                <label for="fechaFin">Fecha de fin:</label>
                <input type="text" id="fechaFin" name="fechaFin" required>
            </div>
            <button type="submit">Consultar</button>
        </form>

        <script>
            flatpickr("#fechaInicio", {
                dateFormat: "d/m/Y", 
                minDate: "today" 
            });
            flatpickr("#fechaFin", {
                dateFormat: "d/m/Y", 
                minDate: "today" 
            });
        </script>

        <h2>Vehículos Disponibles</h2>
        <c:if test="${not empty listaVehiculosDisponibles}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Modelo</th>
                        <th>Matrícula</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehiculo" items="${listaVehiculosDisponibles}">
                        <tr>
                            <td>${vehiculo.idVehiculo}</td>
                            <td>${vehiculo.modelo}</td>
                            <td>${vehiculo.matricula}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty listaVehiculosDisponibles}">
            <p>No hay vehículos disponibles en las fechas seleccionadas.</p>
        </c:if>

        <a href="portal.jsp">Volver al Portal</a>
    </div>
</body>
</html>


