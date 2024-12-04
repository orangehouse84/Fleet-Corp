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
    <link rel="stylesheet" href="style.css">  
</head>
<body>
    <h1>Bienvenido, ${sessionScope.usuario}!</h1>

    <%-- Mostrar contenido solo para administradores --%>
    <c:if test="${sessionScope.idRol == 1}">
        <p>Contenido exclusivo para administradores.</p>
        <a href="gestion_vehiculos.jsp">Gestionar Vehículos</a> 
    </c:if>

    <%-- Mostrar contenido solo para usuarios --%>
    <c:if test="${sessionScope.idRol == 2}">
        <p>Contenido exclusivo para usuarios.</p>
        <a href="historial_solicitudes.jsp">Ver historial de solicitudes</a>
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

</body>
</html>




