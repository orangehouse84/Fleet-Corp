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

        Connection conn = null; // Conexión a la base de datos
        try {
            // 1. Conectar a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn = ConexionDB.obtenerConexion(); 

            // 2. Obtener la lista de vehículos
            List<vehiculo> listaVehiculos = new ArrayList<>();
            String sql = "SELECT * FROM vehiculos";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int idVehiculo = rs.getInt("idVehiculo");
                    String modelo = rs.getString("modelo");
                    String matricula = rs.getString("matricula");
                    String estado = rs.getString("estado");
                    listaVehiculos.add(new vehiculo(idVehiculo, modelo, matricula, estado));
                }
            }

          
            request.setAttribute("listaVehiculos", listaVehiculos);

           
            request.getRequestDispatcher("gestion_vehiculos.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            // Manejar errores de conexión o consulta
            e.printStackTrace();
            
        } finally {
            // Cerrar la conexión 
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
