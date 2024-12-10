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

        input[type="date"], input[type="time"], textarea {
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

        .error {
            color: red;
            margin-bottom: 10px;
        }

        .mensaje {
            color: green;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
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
    </div>
</body>
</html>




