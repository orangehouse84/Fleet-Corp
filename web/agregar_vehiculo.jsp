<%-- 
    Document   : agregar_vehiculo
    Created on : Nov 25, 2024, 8:35:39 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Vehiculo</title>
    </head>
    <body>
        <h1>Agregar Vehiculo</h1>

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

