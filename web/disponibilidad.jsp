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
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Consultar Disponibilidad de Vehículos</h1>

    <form action="DisponibilidadServlet" method="post"> <%-- Ajustar la acción del formulario --%>
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
</body>
</html>

