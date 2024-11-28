/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static final String CONFIG_FILE = "config.properties";
    private static String URL_USUARIOS;
    private static String URL_VEHICULOS;
    private static String USUARIO;
    private static String CONTRASENA;

    static {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            URL_USUARIOS = prop.getProperty("db.usuarios.url");
            URL_VEHICULOS = prop.getProperty("db.vehiculos.url");
            USUARIO = prop.getProperty("db.user");
            CONTRASENA = prop.getProperty("db.password");
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de configuración: " + ex.getMessage());
        }
    }

    public static Connection obtenerConexionUsuarios() throws SQLException {
        return obtenerConexion(URL_USUARIOS);
    }

    public static Connection obtenerConexionVehiculos() throws SQLException {
        return obtenerConexion(URL_VEHICULOS);
    }

    private static Connection obtenerConexion(String url) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a: " + url);
            return conexion;
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
            throw new SQLException("Error al cargar el driver JDBC", e);
        }
    }
}


        




