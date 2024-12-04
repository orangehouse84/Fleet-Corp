/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

public class solicitudVehiculo {
    private int idSolicitud;
    private int idUsuario;
    private int idVehiculo;
    private Date fechaSolicitud;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private String motivo;
    private String estado; // "Pendiente", "Aprobada", "Rechazada"

    // Constructor
    public solicitudVehiculo(int idSolicitud, int idUsuario, int idVehiculo, Date fechaSolicitud, 
                            Date fechaInicio, Date fechaFin, Time horaInicio, Time horaFin, 
                            String motivo, String estado) {
        this.idSolicitud = idSolicitud;
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Getters
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

