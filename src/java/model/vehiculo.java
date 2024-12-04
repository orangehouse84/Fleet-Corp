/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class vehiculo {
    private int idVehiculo;
    private String modelo;
    private String matricula;
    private String estado; // "Disponible", "Prestado", "En mantenimiento"

    // Constructor
    public vehiculo(int idVehiculo, String modelo, String matricula, String estado) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.matricula = matricula;
        this.estado = estado;
    }

    // Getters
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
