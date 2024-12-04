/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import modelo.ConexionDB;

@WebServlet("/SolicitarVehiculoServlet")
public class SolicitarVehiculoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Obtener los datos del formulario
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario"); // Obtener el nombre de usuario de la sesión

        // Validar si el usuario está en la sesión
        if (usuario == null || usuario.isEmpty()) {
            response.sendRedirect("solicitud.jsp?error=usuario_invalido");
            return;
        }

        LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
        LocalTime horaInicio = LocalTime.parse(request.getParameter("horaInicio"));
        LocalTime horaFin = LocalTime.parse(request.getParameter("horaFin"));
        String motivo = request.getParameter("motivo");

        // 2. Validar los datos (opcional)
        // ... (puedes agregar validaciones aquí) ...

        // 3. Conectar a la base de datos
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();

            // 4. Obtener el idUsuario de la base de datos
            int idUsuario = obtenerIdUsuario(conn, usuario);

            // 5. Insertar la solicitud en la base de datos
            String sql = "INSERT INTO solicitudes (idUsuario, fechaSolicitud, fechaInicio, fechaFin, horaInicio, horaFin, motivo, estado) " +
                         "VALUES (?, CURDATE(), ?, ?, ?, ?, ?, 'Pendiente')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                stmt.setDate(2, java.sql.Date.valueOf(fechaInicio));
                stmt.setDate(3, java.sql.Date.valueOf(fechaFin));
                stmt.setTime(4, java.sql.Time.valueOf(horaInicio));
                stmt.setTime(5, java.sql.Time.valueOf(horaFin));
                stmt.setString(6, motivo);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Solicitud registrada exitosamente
                    response.sendRedirect("portal.jsp?mensaje=solicitud_registrada");
                } else {
                    // Error al registrar la solicitud
                    response.sendRedirect("solicitud.jsp?error=error_db");
                }
            }

        } catch (SQLException e) {
            // Manejar errores de conexión o inserción
            response.sendRedirect("solicitud.jsp?error=error_db");
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

    // Método para obtener el idUsuario de la base de datos
    private int obtenerIdUsuario(Connection conn, String usuario) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    // Manejar el caso en que el usuario no se encuentra
                    throw new SQLException("Usuario no encontrado en la base de datos");
                }
            }
        }
    }
}


