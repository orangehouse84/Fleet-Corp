<%-- 
    Document   : index
    Created on : Nov 19, 2024, 4:47:14 PM
    Author     : oscar
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fleet Corp</title>
    <style>
        .container {
            width: 300px;
            margin: 0 auto;
            text-align: center;
        }

        .input-group {
            text-align: left;
            margin-bottom: 10px; 
        }

        input[type="text"], input[type="password"] {
            margin: 5px 0; 
            padding: 5px;
            width: 100%; 
            box-sizing: border-box; 
        }

        button {
            margin: 10px;
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenido a Fleet Corp.</h1>

        <%-- Mostrar mensaje de error --%>
        <div class="error"> 
            <c:if test="${not empty param.error and param.error == 'contrasena_incorrecta'}">
                <span>Contraseña incorrecta. Inténtalo de nuevo.</span> 
            </c:if>
        </div>

        <form action="LoginServlet" method="post">  
            <div class="input-group"> 
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario">
            </div>
            <div class="input-group"> 
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena">
            </div>
            <button type="submit">Iniciar sesión</button>
            <a href="registro.jsp">Registrarse</a> 
        </form>
    </div>
</body>
</html>







