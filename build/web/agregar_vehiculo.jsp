<%-- 
    Document   : agregar_vehiculo
    Created on : Nov 25, 2024, 8:35:39 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- Agrega la directiva taglib --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Vehiculo</title>
    </head>
    <body>
        <h1>Agregar Vehiculo</h1>

        <%-- Mostrar mensajes de error (si los hay) --%>
        <c:if test="${not empty param.error}"> <%-- Usa la etiqueta c:if --%>
            <div class="error">
                <c:choose> <%-- Usa c:choose para mostrar diferentes mensajes de error --%>
                    <c:when test="${param.error == 'modelo_vacio'}">El modelo no puede estar vacío.</c:when>
                    <c:when test="${param.error == 'modelo_largo'}">El modelo no puede tener más de 100 caracteres.</c:when>
                    <c:when test="${param.error == 'matricula_vacia'}">La matrícula no puede estar vacía.</c:when>
                    <c:when test="${param.error == 'matricula_invalida'}">La matrícula debe tener el formato AAA-123.</c:when>
                    <c:when test="${param.error == 'matricula_existe'}">Ya existe un vehículo con esa matrícula.</c:when>
                    <c:otherwise>Error al agregar el vehículo.</c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <form action="AgregarVehiculoServlet" method="post">  
            <div>
                <label for="modelo">Modelo:</label>
                <input type="text" id="modelo" name="modelo" required>
            </div>
            <div>
                <label for="matricula">Matrícula:</label>
                <input type="text" id="matricula" name="matricula" required>
            </div>
            <div>
                <label for="estado">Estado:</label>
                <select id="estado" name="estado">
                    <option value="Disponible">Disponible</option>
                    <option value="Prestado">Prestado</option>
                    <option value="En mantenimiento">En mantenimiento</option>
                </select>
            </div>
            <button type="submit">Agregar</button>
        </form>

    </body>
</html>



