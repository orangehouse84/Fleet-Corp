<%-- 
    Document   : historial_solicitudes
    Created on : Dec 3, 2024, 1:36:08 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Historial de Solicitudes</title>
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

        .mensaje {
            color: green;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container"> <%-- Agregar un contenedor --%>
        <h1>Historial de Solicitudes</h1>

        <%-- Mostrar mensajes (si los hay) --%>
        <c:if test="${not empty param.mensaje}">
            <div class="mensaje">${param.mensaje}</div>
        </c:if>

        <h2>Solicitudes</h2>
        <table>
            <thead>
                <tr>
                    <th>ID Solicitud</th>
                    <th>Fecha Solicitud</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Hora Inicio</th>
                    <th>Hora Fin</th>
                    <th>Motivo</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="solicitud" items="${listaSolicitudes}">
                    <tr>
                        <td>${solicitud.idSolicitud}</td>
                        <td>${solicitud.fechaSolicitud}</td>
                        <td>${solicitud.fechaInicio}</td>
                        <td>${solicitud.fechaFin}</td>
                        <td>${solicitud.horaInicio}</td>
                        <td>${solicitud.horaFin}</td>
                        <td>${solicitud.motivo}</td>
                        <td>${solicitud.estado}</td> 
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="portal.jsp">Volver al Portal</a>
    </div>
</body>
</html>

