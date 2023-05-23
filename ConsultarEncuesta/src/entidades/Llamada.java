/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Brisa
 */
public class Llamada {
    public float Duracion;
    public Date fechaHoraInicio;
    public Date fechaHoraFin;
    private Encuesta encuestaEnviada;
    public ArrayList<RespuestaDeCliente> respuestasDeEncuesta;
    public Cliente cliente;
    public ArrayList<CambioEstado> cambiosEstado;

    //CONSTRUCTOR DE PRUEBA PARA EL JFRAME
    
     public Llamada() {
    }
//constructor de prueba para poder armar llamadas en diferentes fechas ingresada por uno mismo
    public Llamada(String fechaHoraInicioStr, String fechaHoraFinStr, Encuesta encuestaEnviada, ArrayList<RespuestaDeCliente> respuestas, Cliente cliente, ArrayList<CambioEstado> cambios) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        this.fechaHoraInicio = dateFormat.parse(fechaHoraInicioStr);
        this.fechaHoraFin = dateFormat.parse(fechaHoraFinStr);
        this.encuestaEnviada = encuestaEnviada;

        // Calcular la duración de la llamada
        long duracionMillis = fechaHoraFin.getTime() - fechaHoraInicio.getTime();
        this.Duracion = duracionMillis / 1000f;

        // Inicializar la lista de respuestas de encuesta
        this.respuestasDeEncuesta = respuestas;
        this.cliente = cliente;
        // Inicializar la lista de cambios de estado
        this.cambiosEstado = cambios;
    }
    // constructor prueba solo para cargar una llamada sin respuesta encuesta
    public Llamada(String fechaHoraInicioStr, String fechaHoraFinStr, Encuesta encuestaEnviada, Cliente cliente, ArrayList<CambioEstado> cambios) throws ParseException {
    this(fechaHoraInicioStr, fechaHoraFinStr, encuestaEnviada, null, cliente, cambios);
}
    
      public Llamada(Cliente cliente) {
        this.cliente = cliente;
        this.fechaHoraInicio = new Date();
        this.Duracion = 0; // Se calculará posteriormente
        this.fechaHoraFin = null; // Se establecerá al finalizar la llamada
        this.encuestaEnviada = null; // Se generará y enviará posteriormente
        this.respuestasDeEncuesta = new ArrayList<>();
        this.cambiosEstado = new ArrayList<>();

       
        
        // Crear cambio de estado inicial "En curso"
        Estado estadito = new Estado("En Curso");
        CambioEstado cambioEstado ;
        cambioEstado = new CambioEstado(estadito);
        cambiosEstado.add(cambioEstado);
    }

     
    public float getDuracion() {
        return Duracion;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public Encuesta getEncuestaEnviada() {
        return encuestaEnviada;
    }

    public ArrayList<RespuestaDeCliente> getRespuestasDeEncuesta() {
        return respuestasDeEncuesta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<CambioEstado> getCambiosEstado() {
        return cambiosEstado;
    }

   
    public void setEncuestaEnviada(Encuesta encuestaEnviada) {
        this.encuestaEnviada = encuestaEnviada;
    }

     public void setFechaHoraFin(Date fechaHoraFin, String formato) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fechaHoraFinStr = sdf.format(fechaHoraFin);
            this.fechaHoraFin = sdf.parse(fechaHoraFinStr);
        } catch (ParseException e) {
            // Manejar la excepción en caso de que la fecha no pueda ser parseada correctamente
            e.printStackTrace();
        }
    }

   
    
   

    

    public void agregarEncuesta(Encuesta encuesta) {
        encuestaEnviada = encuesta;
    }

    public void setDuracion(float Duracion) {
        this.Duracion = Duracion;
    }

    @Override
    public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaInicioStr = sdf.format(fechaHoraInicio);
        String fechaFinStr = sdf.format(fechaHoraFin);
        return "Llamada: " +  " Cliente: [" + cliente + "] | Duracion: " + Duracion + " | fechaHoraInicio: " + fechaInicioStr + " | fechaHoraFin: " + fechaFinStr + " | Cambios de Estado: [ " + cambiosEstado + " ] |  Encuesta Enviada: [ " + encuestaEnviada + " ] | Respuestas De Encuesta: [" + respuestasDeEncuesta + "] ";
    }

    public String getEstadoActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
