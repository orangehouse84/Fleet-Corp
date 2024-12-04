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
import javax.servlet.http.HttpSession;
import modelo.ConexionDB;
import modelo.solicitudVehiculo;

@WebServlet("/ListaSolicitudesServlet")
public class ListaSolicitudesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");

        // Validar si el usuario está en la sesión
        if (usuario == null || usuario.isEmpty()) {
            response.sendRedirect("index.jsp?error=no_ha_iniciado_sesion");
            return;
        }

        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();

            // Obtener el idUsuario de la base de datos
            int idUsuario = obtenerIdUsuario(conn, usuario);

            // Obtener la lista de solicitudes del usuario
            List<solicitudVehiculo> listaSolicitudes = obtenerSolicitudesDelUsuario(conn, idUsuario);

            // Guardar la lista en el request
            request.setAttribute("listaSolicitudes", listaSolicitudes);

            // Redirigir a historial_solicitudes.jsp
            request.getRequestDispatcher("historial_solicitudes.jsp").forward(request, response);

        } catch (SQLException e) {
            // Manejar errores de conexión o consulta
            e.printStackTrace();
            // Puedes redirigir a una página de error o mostrar un mensaje de error
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

    // Método para obtener la lista de solicitudes del usuario
    private List<solicitudVehiculo> obtenerSolicitudesDelUsuario(Connection conn, int idUsuario) throws SQLException {
        List<solicitudVehiculo> listaSolicitudes = new ArrayList<>();
        String sql = "SELECT * FROM solicitudes WHERE idUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idSolicitud = rs.getInt("idSolicitud");
                    int idUsuarioSol = rs.getInt("idUsuario"); 
                    java.sql.Date fechaSolicitud = rs.getDate("fechaSolicitud");
                    java.sql.Date fechaInicio = rs.getDate("fechaInicio");
                    java.sql.Date fechaFin = rs.getDate("fechaFin");
                    java.sql.Time horaInicio = rs.getTime("horaInicio");
                    java.sql.Time horaFin = rs.getTime("horaFin");
                    String motivo = rs.getString("motivo");
                    String estado = rs.getString("estado");
                    listaSolicitudes.add(new solicitudVehiculo(idSolicitud, idUsuarioSol, 0, fechaSolicitud, fechaInicio, fechaFin, horaInicio, horaFin, motivo, estado));
                }
            }
        }
        return listaSolicitudes;
    }
}

