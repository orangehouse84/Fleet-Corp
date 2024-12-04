/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/usuariosdb"; // URL de la base de datos
    private static final String USUARIO = "root"; // Nombre de usuario de la base de datos
    private static final String CONTRASENA = ""; // Contraseña de la base de datos

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a: " + URL);
            return conexion;
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
            throw new SQLException("Error al cargar el driver JDBC", e);
        }
    }
}



