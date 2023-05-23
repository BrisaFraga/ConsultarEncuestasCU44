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
public class RespuestaDeCliente {
    private Date fechaEncuesta;
    private RespuestaPosible respuestaSeleccionada;

    public RespuestaDeCliente(Date fechaEncuesta, RespuestaPosible respuestaSeleccionada) {
        this.fechaEncuesta = fechaEncuesta;
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public Date getFechaEncuesta() {
        return fechaEncuesta;
    }

    public void setFechaEncuesta(Date fechaEncuesta) {
        this.fechaEncuesta = fechaEncuesta;
    }

    public RespuestaPosible getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(RespuestaPosible respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    @Override
    public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaEn = sdf.format(fechaEncuesta);
        return "[Fecha Encuesta:" + fechaEn+ " | Respuesta Seleccionada: " + respuestaSeleccionada + ']';
    }
    
    
    
}
