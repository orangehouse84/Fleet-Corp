/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ConexionDB;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // 1. Validar los datos (puedes agregar más validaciones)
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            // Manejar error: campos vacíos
            response.sendRedirect("index.jsp?error=campos_vacios");
            return;
        }

        // 2. Conexión a la base de datos
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = ConexionDB.obtenerConexion(); // Usar el método obtenerConexion()

            // 3. Consultar la base de datos para verificar las credenciales
            String sql = "SELECT * FROM usuarios WHERE usuario = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Obtener el hash de la contraseña y el idRol almacenados en la base de datos
                        String contrasenaHasheadaDB = rs.getString("password");
                        int idRol = rs.getInt("idRol");

                        // Hashear la contraseña ingresada por el usuario
                        String contrasenaHasheadaInput = hashearContrasena(contrasena);

                        // Comparar las contraseñas hasheadas
                        if (contrasenaHasheadaDB.equals(contrasenaHasheadaInput)) {
                            // Inicio de sesión exitoso
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", usuario);
                            session.setAttribute("idRol", idRol); // Guardar el idRol en la sesión
                            response.sendRedirect("portal.jsp");
                        } else {
                            // Credenciales incorrectas
                            response.sendRedirect("index.jsp?error=credenciales_incorrectas");
                        }
                    } else {
                        // Usuario no encontrado
                        response.sendRedirect("index.jsp?error=usuario_no_encontrado"); 
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            // Manejar errores de conexión o consulta
            response.sendRedirect("index.jsp?error=error_db");
            e.printStackTrace(); // Imprime la excepción para depuración
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Manejar errores al cerrar la conexión
                    e.printStackTrace(); // Imprime la excepción para depuración
                }
            }
        }
    }

    // Método para hashear la contraseña (SHA-256)
    private String hashearContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(contrasena.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
