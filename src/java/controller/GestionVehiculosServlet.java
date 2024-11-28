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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConexionDB;
import modelo.vehiculo; 

@WebServlet(name = "GestionVehiculosServlet", urlPatterns = {"/gestion_vehiculos.jsp"})
public class GestionVehiculosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn1 = null; // Conexión a usuariosdb
        Connection conn2 = null; // Conexión a vehiculosdb
        try {
            // 1. Conectar a las bases de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn1 = ConexionDB.obtenerConexionUsuarios();
            conn2 = ConexionDB.obtenerConexionVehiculos();

            // 2. Obtener la lista de vehículos
            List<vehiculo> listaVehiculos = new ArrayList<>();
            String sql = "SELECT * FROM vehiculos";
            try (PreparedStatement stmt = conn2.prepareStatement(sql); // Usar conn2 para vehiculosdb
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int idVehiculo = rs.getInt("idVehiculo");
                    String modelo = rs.getString("modelo");
                    String matricula = rs.getString("matricula");
                    String estado = rs.getString("estado");
                    listaVehiculos.add(new vehiculo(idVehiculo, modelo, matricula, estado));
                }
            }

            // 3. Guardar la lista en el request
            request.setAttribute("listaVehiculos", listaVehiculos);

            // 4. Redirigir a gestion_vehiculos.jsp
            request.getRequestDispatcher("gestion_vehiculos.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            // ...
        }
        // ...
         finally {
            // Cerrar las conexiones en un bloque finally
            try {
                if (conn1 != null) {
                    conn1.close();
                }
                if (conn2 != null) {
                    conn2.close(); // Cerrar conn2 aquí
                }
            } catch (SQLException e) {
                // ...
            }
        }
    }
}





