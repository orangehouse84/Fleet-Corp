<%-- 
    Document   : portal
    Created on : Nov 21, 2024, 6:52:03 PM
    Author     : oscar
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Portal</title>
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

        nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            background-color: #333;
            text-align: center;
        }

        nav li {
            display: inline-block;
        }

        nav a {
            display: block;
            padding: 10px 20px;
            color: #fff;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #555;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            line-height: 1.6;
            color: #555;
        }

        a {
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenido, ${sessionScope.usuario}!</h1>

        <%-- Mostrar contenido solo para administradores --%>
        <c:if test="${sessionScope.idRol == 1}">
            <p>Contenido exclusivo para administradores.</p>
            <a href="gestion_vehiculos.jsp">Gestionar Vehículos</a> 
        </c:if>

        <%-- Mostrar contenido solo para usuarios --%>
        <c:if test="${sessionScope.idRol == 2}">
            <p>Contenido exclusivo para usuarios.</p>
            <nav>
            <ul>
                <li><a href="historial_solicitudes.jsp">Ver historial de solicitudes</a></li>
            </ul>
        </nav>
        </c:if>

        <%-- Mostrar contenido para todos los usuarios --%>
        <p>Contenido para todos los usuarios.</p>

        <nav>
            <ul>
                <li><a href="solicitud.jsp">Solicitar Vehículo</a></li>
                <li><a href="disponibilidad.jsp">Consultar Disponibilidad</a></li>
                <li><a href="CerrarSesionServlet">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>




