/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import entidades.CambioEstado;
import entidades.Encuesta;
import entidades.Estado;
import entidades.Llamada;
import entidades.RespuestaDeCliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Brisa
 */
public class ServicioLlamada {

    public ServicioLlamada() {
    }
    
    private static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm:ss";
    
    public void finalizarLlamada(Llamada llamada) {
        // Obtener la fecha y hora actual
        Date fechaHoraActual = new Date();
        
        // Establecer la fecha y hora de finalización
        llamada.setFechaHoraFin(fechaHoraActual, FORMATO_FECHA);
        
        // Calcular la duración de la llamada
        calcularDuracion(llamada);
    }
    
    private void calcularDuracion(Llamada llamada) {
        Date fechaHoraInicio = llamada.getFechaHoraInicio();
        Date fechaHoraFin = llamada.getFechaHoraFin();
        
        // Calcular la duración en milisegundos
        long duracionMilisegundos = fechaHoraFin.getTime() - fechaHoraInicio.getTime();
        
        // Convertir la duración a segundos o minutos
        float duracionSegundos = duracionMilisegundos / 1000f; // Duración en segundos
        
        llamada.setDuracion(duracionSegundos);
    }
    
    public void finalizarLlamadaPrueba(String fechaFinString , Llamada llamada) {
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            llamada.fechaHoraFin = formatoFechaHora.parse(fechaFinString);
            calcularDuracion(llamada);
        } catch (ParseException e) {
            System.out.println("Error al analizar la fecha de finalización: " + e.getMessage());
        }
        calcularDuracion(llamada);
    }
    
    public void agregarCambioEstado(Llamada llamada,CambioEstado cambioestado) {
        
        llamada.cambiosEstado.add(cambioestado);
    }
        
    public void agregarRespuestaEncuesta(Llamada llamada,RespuestaDeCliente respuesta) {
        llamada.respuestasDeEncuesta.add(respuesta);
    }
    
    public void agregarEncuesta(Llamada llamada, Encuesta encuesta){
        
        llamada.setEncuestaEnviada(encuesta);
    }
    
   
    
}
