<%-- 
    Document   : index
    Created on : Nov 19, 2024, 4:47:14 PM
    Author     : oscar
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Fleet Corp</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Bienvenido a Fleet Corp.</h1>
        <form action="LoginServlet" method="post">  
            <div>
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario">
            </div>
            <div>
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena">
            </div>
            <button type="submit">Iniciar sesión</button>
            <a href="registro.jsp">Registrarse</a> 
        </form>
    </div>
</body>
</html>





