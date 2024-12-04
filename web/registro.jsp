<%-- 
    Document   : registro
    Created on : Nov 19, 2024, 4:48:31 PM
    Author     : oscar
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Fleet Corp Registro</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Registro de usuario</h1>

        <%-- Mostrar mensajes de error --%>
        <% if (request.getParameter("error") != null) { %>
            <div class="error">${request.getParameter("error")}</div> 
        <% } %>

        <form action="RegistroServlet" method="post">  
            <div>
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required>
            </div>
            <div>
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required>
            </div>
            <div>
                <label for="idRol">Rol:</label>
                <select id="idRol" name="idRol">
                    <option value="1">Administrador</option>
                    <option value="2">Usuario</option>
                </select>
            </div>
            <button type="submit">Crear Registro</button>
            <a href="index.jsp">Iniciar sesión</a> 
        </form>
    </div>
</body>
</html>

