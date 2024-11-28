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
        <link rel="stylesheet" href="style.css">  
    </head>
    <body>
        <h1>Gestión de Vehículos</h1>

        <nav>
            <ul>
                <li><a href="agregar_vehiculo.jsp">Agregar nuevo vehículo</a></li>
                <li><a href="editar_vehiculo.jsp">Editar vehículo</a></li>
                <li><a href="eliminar_vehiculo.jsp">Eliminar vehículo</a></li>
            </ul>
        </nav>

        <h2>Lista de Vehículos</h2>
        <table>
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
                            <a href="editar_vehiculo.jsp?id=${vehiculo.idVehiculo}">Editar</a> |  
                            <a href="eliminar_vehiculo.jsp?id=${vehiculo.idVehiculo}">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

