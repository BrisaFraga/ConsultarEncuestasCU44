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
   
    private Encuesta encuestaEnviada;
    public ArrayList<RespuestaDeCliente> respuestasDeEncuesta;
    public Cliente cliente;
    public ArrayList<CambioEstado> cambiosEstado;

    //CONSTRUCTOR DE PRUEBA PARA EL JFRAME
    
     public Llamada() {
    }
//constructor de prueba para poder armar llamadas en diferentes fechas ingresada por uno mismo
    public Llamada(Encuesta encuestaEnviada, ArrayList<RespuestaDeCliente> respuestas, Cliente cliente, ArrayList<CambioEstado> cambios) throws ParseException {
        
        this.encuestaEnviada = encuestaEnviada;
        this.cambiosEstado = cambios;

        // Calcular la duración de la llamada
        

        // Inicializar la lista de respuestas de encuesta
        this.respuestasDeEncuesta = respuestas;
        this.cliente = cliente;
        // Inicializar la lista de cambios de estado
    }
    // constructor prueba solo para cargar una llamada sin respuesta encuesta
    public Llamada(Encuesta encuestaEnviada, Cliente cliente, ArrayList<CambioEstado> cambios) throws ParseException {
    this(encuestaEnviada, null, cliente, cambios);
}
    
      public Llamada(Cliente cliente) {
        this.cliente = cliente;
        ;
        this.Duracion = 0; // Se calculará posteriormente
        
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
     public ArrayList<CambioEstado> getCambiosEstado() {
        return cambiosEstado;
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

   

   
    public void setEncuestaEnviada(Encuesta encuestaEnviada) {
        this.encuestaEnviada = encuestaEnviada;
    }

        
   
    public Date getFechaHoraFin() {
        return cambiosEstado.get(cambiosEstado.size()-1).getFechaHoraInicio();
        
    }  


    public Date getFechaHoraInicioLlamada() {

            return cambiosEstado.get(0).getFechaHoraInicio();
                }

    public boolean esDePeriodo(Date fechaInicio, Date fechaFin){
        return getFechaHoraInicioLlamada().after(fechaInicio) && getFechaHoraInicioLlamada().before(fechaFin);

    }
    public boolean existeRespuestaDeEncuesta(){
        return getRespuestasDeEncuesta() != null;
    }

    public ArrayList<String> getRespuestasDeClienteToString(){
    int  cont = 0 ;
        
       ArrayList <String> respuestas = new ArrayList();

       for (RespuestaDeCliente respuesta : respuestasDeEncuesta) {
            cont += 1;
            respuestas.add(cont +" Respuesta: "+ respuesta.getRespuestaSeleccionada().getDescripcion() + " \n");
        }
        return respuestas;
    }
    
    
   public Estado getEstadoActual(){
        Estado estadito ;
        
        estadito = cambiosEstado.get(cambiosEstado.size()-1).getEstado();
        
        return estadito;
    }
    
    
   

    

    public void agregarEncuesta(Encuesta encuesta) {
        encuestaEnviada = encuesta;
    }

    public void calcularDuracion() {
        long duracionMillis = getFechaHoraFin().getTime() - getFechaHoraInicioLlamada().getTime();
        this.Duracion = duracionMillis / 1000f / 60f;
    }

    @Override
    public String toString() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaInicioStr = sdf.format(getFechaHoraInicioLlamada());
        String fechaFinStr = sdf.format(getFechaHoraFin());
        return "Llamada: " +  " Cliente: [" + cliente + "] | Duracion: " + Duracion + " | fechaHoraInicio: " + fechaInicioStr + " | fechaHoraFin: " + fechaFinStr + " | Cambios de Estado: [ " + cambiosEstado + " ] |  Encuesta Enviada: [ " + encuestaEnviada + " ] | Respuestas De Encuesta: [" + respuestasDeEncuesta + "] ";
    }

    

    
    
}
