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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConexionDB;
import modelo.vehiculo;

@WebServlet("/DisponibilidadServlet")
public class DisponibilidadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener las fechas del formulario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Crear un formateador de fechas
        LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"), formatter);
        LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"), formatter);

        // Conectar a la base de datos
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();

            // Obtener la lista de vehículos disponibles
            List<vehiculo> listaVehiculosDisponibles = obtenerVehiculosDisponibles(conn, fechaInicio, fechaFin);

            // Guardar la lista en el request
            request.setAttribute("listaVehiculosDisponibles", listaVehiculosDisponibles);

            // Redirigir a disponibilidad.jsp
            request.getRequestDispatcher("disponibilidad.jsp").forward(request, response);

        } catch (SQLException e) {
            // Manejar errores de conexión
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

    // Método para obtener la lista de vehículos disponibles
    private List<vehiculo> obtenerVehiculosDisponibles(Connection conn, LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
        List<vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos " +
                     "WHERE idVehiculo NOT IN (SELECT idVehiculo FROM solicitudes " +
                     "WHERE (fechaInicio <= ? AND fechaFin >= ?) OR (fechaInicio >= ? AND fechaInicio <= ?))"; 
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
            stmt.setDate(2, java.sql.Date.valueOf(fechaFin));
            stmt.setDate(3, java.sql.Date.valueOf(fechaInicio));
            stmt.setDate(4, java.sql.Date.valueOf(fechaFin));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idVehiculo = rs.getInt("idVehiculo");
                    String modelo = rs.getString("modelo");
                    String matricula = rs.getString("matricula");
                    String estado = rs.getString("estado");
                    listaVehiculos.add(new vehiculo(idVehiculo, modelo, matricula, estado));
                }
            }
        }
        return listaVehiculos;
    }
}

