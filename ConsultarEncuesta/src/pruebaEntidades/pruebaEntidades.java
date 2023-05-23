/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaEntidades;

import entidades.Cliente;
import control.GestorConsultarLlamada;
import entidades.Llamada;
import entidades.Pregunta;
import entidades.RespuestaDeCliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import servicio.ServicioLlamada;

/**
 *
 * @author Brisa
 */
public class pruebaEntidades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
       GestorConsultarLlamada gestorConsultarLlamada = new GestorConsultarLlamada();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Date fechaInicio = dateFormat.parse("10/05/2023");
        Date fechaFin = dateFormat.parse("13/05/2023");
        
        gestorConsultarLlamada.buscarLlamadasPorFechas(fechaInicio, fechaFin); 
        
        ArrayList<Llamada> llamadas = new ArrayList<>(gestorConsultarLlamada.getLlamadas());
        for (Llamada llamada : llamadas) {
            System.out.println(llamada.toString());
            
        }
         llamadas = (gestorConsultarLlamada.getLlamadasFiltradas());
         System.out.println("--------------------------------");
        for (Llamada llamada : llamadas) {
            System.out.println(llamada.toString());
            
        }
       int selectedIndex = 1;
    // Verificar si se seleccionó una llamada
    if (selectedIndex != -1) {
        // Obtener la llamada seleccionada
        Llamada seleccionada;
        seleccionada = gestorConsultarLlamada.getLlamadasFiltradas().get(selectedIndex); 

        gestorConsultarLlamada.setLlamadaSeleccionada(seleccionada);
        // Obtener los datos de la llamada
        String cliente = gestorConsultarLlamada.llamadaSeleccionada.getCliente().toString();
        String estadoActual = gestorConsultarLlamada.getEstadoActual(gestorConsultarLlamada.getLlamadaSeleccionada()).toString();
        float duracionLlamada = gestorConsultarLlamada.getLlamadaSeleccionada().getDuracion();

        // Obtener los datos de las respuestas asociadas a la llamada
        ArrayList<RespuestaDeCliente> respuestas = gestorConsultarLlamada.getLlamadaSeleccionada().getRespuestasDeEncuesta();
        Set<Pregunta> preguntas = gestorConsultarLlamada.getLlamadaSeleccionada().getEncuestaEnviada().getPreguntas();
        String respuestasSeleccionadas = "";
        String descripcionPreguntas = "";
        String descripcionEncuesta = gestorConsultarLlamada.getLlamadaSeleccionada().getEncuestaEnviada().getDescripcion();
        int cont = 0 ;
        for (RespuestaDeCliente respuesta : respuestas) {
            cont += 1;
            respuestasSeleccionadas += cont +" Respuesta: "+ respuesta.getRespuestaSeleccionada() + " \n";
        }
        cont = 0;
        for (Pregunta pregunta : preguntas){
        cont += 1;
        descripcionPreguntas += cont +" Pregunta: "+ pregunta.getPregunta() + " \n";
        }
        // Crear el mensaje a mostrar en la ventana emergente
        String mensaje = "Cliente: " + cliente + "\n"
                + "Estado actual: " + estadoActual + "\n"
                + "Duración de la llamada: " + duracionLlamada + " minutos\n\n"
                + "Respuestas seleccionadas: " + respuestasSeleccionadas + "\n"
                + "Descripción de las preguntas: " + descripcionPreguntas + "\n"
                + "Descripción de la encuesta: " + descripcionEncuesta;
        
        System.out.println(mensaje);
    
    }
    
}}
