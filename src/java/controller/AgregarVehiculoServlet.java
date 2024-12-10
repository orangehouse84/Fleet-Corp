/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConexionDB;

@WebServlet(name = "AgregarVehiculoServlet", urlPatterns = {"/AgregarVehiculoServlet"})
public class AgregarVehiculoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Obtener los datos del formulario
        String modelo = request.getParameter("modelo");
        String matricula = request.getParameter("matricula");
        String estado = request.getParameter("estado");

        // 2. Validar los datos
        if (modelo == null || modelo.isEmpty()) {
            response.sendRedirect("agregar_vehiculo.jsp?error=modelo_vacio");
            return;
        }

        if (modelo.length() > 100) {
            response.sendRedirect("agregar_vehiculo.jsp?error=modelo_largo");
            return;
        }

        if (matricula == null || matricula.isEmpty()) {
            response.sendRedirect("agregar_vehiculo.jsp?error=matricula_vacia");
            return;
        }

        // Validar formato de matrícula (puedes usar una expresión regular)
        if (!matricula.matches("[A-Z]{3}-[0-9]{3}")) {
            response.sendRedirect("agregar_vehiculo.jsp?error=matricula_invalida");
            return;
        }

        // 3. Conectar a la base de datos
        Connection conn = null;
        try {
            // Obtener la conexión usando ConexionDB.obtenerConexion()
            conn = ConexionDB.obtenerConexion(); 

            // Verificar si la matrícula ya existe
            String sql = "SELECT COUNT(*) FROM vehiculos WHERE matricula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        response.sendRedirect("agregar_vehiculo.jsp?error=matricula_existe");
                        return;
                    }
                }
            }

            // 4. Insertar el nuevo vehículo en la base de datos
            sql = "INSERT INTO vehiculos (modelo, matricula, estado) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, modelo);
                stmt.setString(2, matricula);
                stmt.setString(3, estado);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Registro exitoso
                    response.sendRedirect("gestion_vehiculos.jsp?mensaje=vehiculo_agregado");
                } else {
                    // Error al registrar
                    response.sendRedirect("agregar_vehiculo.jsp?error=error_db");
                }
            }

        } catch (SQLException e) {
            // Manejar errores de conexión o inserción
            response.sendRedirect("agregar_vehiculo.jsp?error=error_db");
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Manejar errores al cerrar la conexión
                    e.printStackTrace();
                }
            }
        }
    }

    // ... (otros métodos del servlet) ...
}






