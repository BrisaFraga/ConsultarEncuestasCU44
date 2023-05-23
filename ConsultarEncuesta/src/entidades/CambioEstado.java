/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Brisa
 */
public class CambioEstado {
    private Date fechaHoraInicio;
    private Estado estado;

    public CambioEstado(Estado estado) {
        this.fechaHoraInicio = new Date();
        this.estado = estado;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaInicioStr = sdf.format(fechaHoraInicio);
        return "CambioEstado: " + "Fecha y Hora Inicio: " + fechaInicioStr + " | " + estado + '}';
    }
    
    
}
