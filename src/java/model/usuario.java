/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class usuario {
    private int idUsuario;
    private String usuario;
    private String contrasena;
    private int idRol;

    // Constructor
    public usuario(int idUsuario, String usuario, String contrasena, int idRol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdRol() {
        return idRol;
    }

    // Setters
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}

