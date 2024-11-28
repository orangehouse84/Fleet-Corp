/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        // 1. Obtener el idRol del formulario
        int idRol = Integer.parseInt(request.getParameter("idRol")); 

        // 1. Validar los datos (puedes agregar más validaciones)
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            // Manejar error: campos vacíos
            response.sendRedirect("registro.jsp?error=campos_vacios");  
            return; 
        }

        // 2. Conexión a la base de datos
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/usuariosdb", "root", "");

            // 3. Hashear la contraseña
            String contrasenaHasheada = hashearContrasena(contrasena);
            
            System.out.println("Contraseña hasheada: " + contrasenaHasheada); //remover este print despues de verificar que funciona el hash!!!!!!!!!!!!!!!!!

            // 4. Insertar los datos en la base de datos (usando PreparedStatement)
            // Incluir el idRol en la consulta SQL
            String sql = "INSERT INTO usuarios (usuario, password, idRol) VALUES (?, ?, ?)"; 
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasenaHasheada);
                stmt.setInt(3, idRol); // Establecer el valor de idRol

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    // Registro exitoso
                    response.sendRedirect("index.jsp?registro=exito"); 
                } else {
                    // Error al registrar
                    response.sendRedirect("registro.jsp?error=registro_fallido"); 
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            // Manejar errores de conexión o inserción
            response.sendRedirect("registro.jsp?error=error_db"); 
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Manejar errores al cerrar la conexión
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
            // Manejar la excepción (puedes registrar el error o mostrar un mensaje)
            e.printStackTrace(); // Imprime la traza del error en la consola
            return null;
        }
    }
}

        


