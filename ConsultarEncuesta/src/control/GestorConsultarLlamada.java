/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.opencsv.CSVWriter;
import entidades.CambioEstado;
import entidades.Cliente;
import entidades.Encuesta;
import entidades.Estado;
import entidades.Llamada;
import entidades.Pregunta;
import entidades.RespuestaDeCliente;
import entidades.RespuestaPosible;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Brisa
 */
public class GestorConsultarLlamada {
    //el arraList llamadas donde cargamos Llamadas porque no usamos bda
    public ArrayList<Llamada> llamadas;
    private ArrayList<Llamada> llamadasFiltradas;
    public Llamada llamadaSeleccionada;

    public Llamada getLlamadaSeleccionada() {
        return llamadaSeleccionada;
    }

    public void setLlamadaSeleccionada(Llamada llamadaSeleccionada) {
        this.llamadaSeleccionada = llamadaSeleccionada;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(ArrayList<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    public ArrayList<Llamada> getLlamadasConEncuesta() {
        return llamadasFiltradas;
    }

    public void setLlamadasConEncuesta(ArrayList<Llamada> llamadasFiltradas) {
        this.llamadasFiltradas = llamadasFiltradas;
    }
    
    

   
// un constructor de gestor donde se guardan objetos Llamadas para realizar pruebas sin necesidad de un bd
    public GestorConsultarLlamada() throws ParseException {
        Cliente cliente= new Cliente("Brisa Fraga", 43556677L, 3567845876L);
       //array de cambios estados de prueba
        ArrayList<CambioEstado> cambios = new ArrayList();
        cambios.add(new CambioEstado(new Estado("En Curso")));
        cambios.add(new CambioEstado(new Estado("Finalizada")));
        //preguntas pruebas para armar encuesta 
        Set<Pregunta> preguntas = new HashSet();
        Pregunta pregunta = new Pregunta("¿El problema se soluciono?");
        pregunta.agregarRespuesta(new RespuestaPosible(1,"Si"));
        pregunta.agregarRespuesta(new RespuestaPosible(2,"No"));
        preguntas.add(pregunta);
        pregunta.setPregunta("¿La atencion del operador fue agradable?");
        preguntas.add(pregunta);
        //encuetsa prueba
        Encuesta encuesta = new Encuesta("11/05/2023 12:00:00", preguntas,"Encuesta para saber la conformidad del cliente con la atencion y resolucion del problema");
        //respuestas de encuesta prueba solo realizare 2
        ArrayList<RespuestaDeCliente> respuestas = new ArrayList();
        respuestas.add(new RespuestaDeCliente(new Date(),new RespuestaPosible(1,"Si")));
        respuestas.add(new RespuestaDeCliente(new Date(),new RespuestaPosible(2,"No")));
        
        //llamadas de prueba
        llamadas = new ArrayList<>();
        llamadas.add(new Llamada("10/05/2023 12:02:02","10/05/2023 13:00:00",encuesta,respuestas, cliente, cambios));
        llamadas.add(new Llamada("11/05/2023 12:02:02","11/05/2023 13:00:00",encuesta,respuestas, cliente, cambios));
        llamadas.add(new Llamada("20/05/2023 12:02:02","20/05/2023 13:00:00",encuesta,respuestas, cliente, cambios));
        llamadas.add(new Llamada("12/05/2023 12:02:02","13/05/2023 13:00:00",encuesta, cliente, cambios));

        this.llamadaSeleccionada = new Llamada();
        this.llamadasFiltradas = new ArrayList();
    }

   
    
    public void buscarLlamadasPorFechas(Date fechaInicio, Date fechaFin) {
        ArrayList<Llamada> llamadasEncontradas = new ArrayList<>();
        for (Llamada llamada : llamadas) {
            Date fechaLlamada = llamada.getFechaHoraInicio();
            if (fechaLlamada.after(fechaInicio) && fechaLlamada.before(fechaFin)  ) {
                if (llamada.getRespuestasDeEncuesta()!= null){
                llamadasEncontradas.add(llamada);
            }}
        }
        llamadasFiltradas =llamadasEncontradas;
    }

     public static Estado getEstadoActual(Llamada llamada){
        Estado estadito ;
        ArrayList <CambioEstado> cambios= llamada.getCambiosEstado();
        
        estadito = cambios.get(cambios.size()-1).getEstado();
        return estadito;
    }
     
    public String formatearLlamadaSelecionada(Llamada llamadaSelecionada){
    // Obtener los datos de la llamada
        String cliente = llamadaSeleccionada.getCliente().getNombreCompleto();
        String estadoActual = getEstadoActual(llamadaSeleccionada).toString();
        float duracionLlamada = llamadaSeleccionada.getDuracion();

        // Obtener los datos de las respuestas asociadas a la llamada
        ArrayList<RespuestaDeCliente> respuestas = llamadaSeleccionada.getRespuestasDeEncuesta();
        Set<Pregunta> preguntas = llamadaSeleccionada.getEncuestaEnviada().getPreguntas();
        String respuestasSeleccionadas = "";
        String descripcionPreguntas = "";
        String descripcionEncuesta = llamadaSeleccionada.getEncuestaEnviada().getDescripcion();
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
                
                + " - Encuesta: \nDescripción de la encuesta: " + descripcionEncuesta +"\n"
                + "Descripción de las preguntas: \n " + descripcionPreguntas + "\n"
                + "Respuestas seleccionadas: \n" + respuestasSeleccionadas + "\n";
                
                

    return mensaje;}
    
    
    //generar csv
    public void generarCSV() {
    // Ruta del archivo CSV a generar
    String archivoCSV = "C:/Users/usuario/OneDrive/Escritorio/CSVGenerados/Llamada.csv";

    try (CSVWriter csvWriter = new CSVWriter(new FileWriter(archivoCSV))) {
        // Escribir encabezados
        String[] encabezados = {"Nombre del cliente", "Estado actual de la llamada", "Duración de la llamada"};
        csvWriter.writeNext(encabezados);

        // Obtener los datos de la llamada seleccionada
        Llamada llamada = getLlamadaSeleccionada();
        String cliente = llamada.getCliente().getNombreCompleto();
        String estadoActual = getEstadoActual(llamada).toString();
        float duracionLlamada = llamada.getDuracion();

        // Escribir fila de datos de la llamada
        String[] filaLlamada = {cliente, estadoActual, String.valueOf(duracionLlamada)};
        csvWriter.writeNext(filaLlamada);

        // Obtener las preguntas y respuestas asociadas a la llamada
        Set<Pregunta> preguntas = llamada.getEncuestaEnviada().getPreguntas();
        ArrayList<RespuestaDeCliente> respuestas = llamada.getRespuestasDeEncuesta();
           int cont = 0;
        // Escribir las preguntas y respuestas en filas separadas
        for (Pregunta pregunta : preguntas) {
            String descripcionPregunta = pregunta.getPregunta();
            String descripcionRespuesta = respuestas.get(cont).getRespuestaSeleccionada().toString();

           
            
            
            cont += 1;
            // Escribir fila de datos de la pregunta y respuesta
            String[] filaPregunta = {descripcionPregunta, descripcionRespuesta};
            csvWriter.writeNext(filaPregunta);
        }

        // Flushing y cerrando el escritor de CSV
        csvWriter.flush();
    } catch (IOException e) {
    }
}

    public String mostrarLlamadasString(Llamada llamada) {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaInicioStr = sdf.format(llamada.fechaHoraInicio);
        String fechaFinStr = sdf.format(llamada.fechaHoraFin);
        return "Llamada: " +  " Cliente: [" + llamada.cliente + "] | Duracion: " + llamada.Duracion + " | fechaHoraInicio: " + fechaInicioStr + " | fechaHoraFin: " + fechaFinStr ;
    
    }
    

}
