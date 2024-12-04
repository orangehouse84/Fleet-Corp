<%-- 
    Document   : solicitud
    Created on : Dec 3, 2024, 11:22:13 AM
    Author     : oscar
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Solicitar Vehículo</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Solicitar Vehículo</h1>

    <%-- Mostrar mensajes --%>
    <c:if test="${not empty param.error}">
        <div class="error">
            <c:choose>
                <c:when test="${param.error == 'error_db'}">Error al procesar la solicitud. Por favor, inténtalo de nuevo más tarde.</c:when>
                <c:when test="${param.error == 'fechas_invalidas'}">Las fechas o horas son inválidas. Por favor, verifica la información.</c:when>
                <c:when test="${param.error == 'usuario_invalido'}">Error: Usuario inválido. Por favor, inicia sesión de nuevo.</c:when> 
                <c:otherwise>Error desconocido.</c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <c:if test="${not empty param.mensaje}">
        <div class="mensaje">
            <c:choose>
                <c:when test="${param.mensaje == 'solicitud_registrada'}">Solicitud registrada exitosamente. ¡Gracias!</c:when>
                <c:otherwise>Mensaje desconocido.</c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <form action="SolicitarVehiculoServlet" method="post">
        <div>
            <label for="fechaInicio">Fecha de inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>
        </div>
        <div>
            <label for="fechaFin">Fecha de fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" required>
        </div>
        <div>
            <label for="horaInicio">Hora de inicio:</label>
            <input type="time" id="horaInicio" name="horaInicio" required>
        </div>
        <div>
            <label for="horaFin">Hora de fin:</label>
            <input type="time" id="horaFin" name="horaFin" required>
        </div>
        <div>
            <label for="motivo">Motivo:</label>
            <textarea id="motivo" name="motivo" required></textarea>
        </div>

        <input type="hidden" name="idUsuario" value="${sessionScope.idUsuario}"> 

        <button type="submit">Solicitar</button>
    </form>
</body>
</html>




