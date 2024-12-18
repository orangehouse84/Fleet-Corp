<%-- 
    Document   : gestion_vehiculos
    Created on : Nov 25, 2024, 6:25:04 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrador</title>
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
        <h1>Gestión de Vehículos</h1>

        <nav>
            <ul>
                <li><a href="agregar_vehiculo.jsp">Agregar nuevo vehículo</a></li>
                <li><a href="editar_vehiculo.jsp">Editar vehículo</a></li>
                <li><a href="eliminar_vehiculo.jsp">Eliminar vehículo</a></li>
            </ul>
        </nav>

        <h2>Lista de Vehículos</h2>

        <%-- Mostrar mensajes --%>
        <c:if test="${not empty param.error}">
            <div class="error">${param.error}</div> 
        </c:if>

        <c:if test="${not empty param.mensaje}">
            <div class="mensaje">${param.mensaje}</div> 
        </c:if>

        <%-- Mostrar un mensaje si la lista está vacía --%>
        <c:if test="${empty listaVehiculos}">
            <p>No hay vehículos registrados.</p>
        </c:if>
        
        <c:if test="${not empty listaVehiculos}"> <%-- Verificar si la lista no está vacía --%>
            <table class="tabla-vehiculos"> 
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Modelo</th>
                        <th>Matrícula</th>
                        <th>Estado</th>
                        <th>Acciones</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehiculo" items="${listaVehiculos}">  
                        <tr>
                            <td>${vehiculo.idVehiculo}</td>
                            <td>${vehiculo.modelo}</td>
                            <td>${vehiculo.matricula}</td>
                            <td>${vehiculo.estado}</td>
                            <td>
                                <a href="ver_vehiculo.jsp?id=${vehiculo.idVehiculo}">Ver</a> | 
                                <a href="editar_vehiculo.jsp?id=${vehiculo.idVehiculo}">Editar</a> |  
                                <a href="eliminar_vehiculo.jsp?id=${vehiculo.idVehiculo}">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>

