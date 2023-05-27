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
public class GestorConsultarEncuesta {
    //el arraList llamadas donde cargamos Llamadas porque no usamos bda
    public ArrayList<Llamada> llamadas;
    private ArrayList<Llamada> llamadasConEncuestasEncontradas;
    public Llamada llamadaSeleccionada;
    public Cliente cliente;
    public Estado estadoActual;
    public Encuesta encuesta;
    public ArrayList<Pregunta> preguntas;
    public ArrayList <RespuestaDeCliente> respuestas;
    public Float duracion;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public ArrayList<RespuestaDeCliente> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<RespuestaDeCliente> respuestas) {
        this.respuestas = respuestas;
    }

 

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    
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

    public ArrayList<Llamada> getLlamadasConEncuestaEncontradas() {
        return llamadasConEncuestasEncontradas;
    }

    public void setLlamadasConEncuestaEncontradas(ArrayList<Llamada> llamadasFiltradas) {
        this.llamadasConEncuestasEncontradas = llamadasFiltradas;
    }
    
    

   
// un constructor de gestor donde se guardan objetos Llamadas para realizar pruebas sin necesidad de un bd
    public GestorConsultarEncuesta() throws ParseException {
        Cliente cliente0= new Cliente("Brisa Fraga", 43556677L, 3567845876L);
        Cliente cliente1= new Cliente("Vale Mazan", 47987653L, 3567845876L);
       //array de cambios estados de prueba
        ArrayList<CambioEstado> cambios1 = new ArrayList();
        cambios1.add(new CambioEstado("10/05/2023 12:02:02",new Estado("En Curso")));
        cambios1.add(new CambioEstado("11/05/2023 12:02:02",new Estado("Finalizada")));
        ArrayList<CambioEstado> cambios2 = new ArrayList();
        cambios2.add(new CambioEstado("11/05/2023 12:02:02",new Estado("En Curso")));
        cambios2.add(new CambioEstado("11/05/2023 13:02:02",new Estado("Finalizada")));
        cambios2.add(new CambioEstado("11/05/2023 13:02:02",new Estado("Encuesta Enviada")));
        
        ArrayList<CambioEstado> cambios3 = new ArrayList();
        cambios3.add(new CambioEstado("20/05/2023 12:02:02",new Estado("En Curso")));
        cambios3.add(new CambioEstado("20/05/2023 13:02:02",new Estado("Finalizada")));
        

        //preguntas pruebas para armar encuesta 
        ArrayList<Pregunta> preguntasPrueba = new ArrayList();
        Pregunta pregunta = new Pregunta("¿La atencion del operador fue agradable?");
        pregunta.agregarRespuesta(new RespuestaPosible(1,"Si"));
        pregunta.agregarRespuesta(new RespuestaPosible(2,"No"));
        preguntasPrueba.add(pregunta);
      
        //encuetsa prueba
        Encuesta encuestaPrueba = new Encuesta("11/05/2023 12:00:00", preguntasPrueba,"Encuesta para saber la conformidad del cliente con la atencion y resolucion del problema");
        //respuestas de encuesta prueba solo realizare 1
        ArrayList<RespuestaDeCliente> respuestasPrueba = new ArrayList();
        respuestasPrueba.add(new RespuestaDeCliente(new Date(),new RespuestaPosible(1,"Si")));
       
        
        //llamadas de prueba
        llamadas = new ArrayList<>();
        llamadas.add(new Llamada(encuestaPrueba,respuestasPrueba, cliente0, cambios1));
        llamadas.add(new Llamada(encuestaPrueba,respuestasPrueba, cliente1, cambios2));
        llamadas.add(new Llamada(encuestaPrueba,respuestasPrueba, cliente0, cambios3));
        llamadas.add(new Llamada(encuestaPrueba, cliente1, cambios1));
        
        for (Llamada llamada : llamadas){
            llamada.calcularDuracion();
        }
        this.llamadaSeleccionada = new Llamada();
        this.llamadasConEncuestasEncontradas = new ArrayList();
    }

    //PONERLO EN LLAMADA Y SE BUSCA EL INICIO DE LA LLAMADA POR CAMBIOS DE ESTADO, corregir 
    
   
    public void getLlamadasConEncuesta(Date fechaInicio, Date fechaFin) {
        ArrayList<Llamada> llamadasEncontradas = new ArrayList<>();
        for (Llamada llamada : llamadas) {
            if (llamada.esDePeriodo(fechaInicio,fechaFin) ) {
                if (llamada.existeRespuestaDeEncuesta()){
            
                llamadasEncontradas.add(llamada);
            } }
        }
        llamadasConEncuestasEncontradas = llamadasEncontradas;
    }

    public void getDatosLlamadaSeleccionada(){
    // Obtener los datos de la llamada
        this.cliente= llamadaSeleccionada.getCliente();
        //cambiar el metodo get estado actual
        this.estadoActual= llamadaSeleccionada.getEstadoActual();
        this.duracion = llamadaSeleccionada.getDuracion();
        this.encuesta = llamadaSeleccionada.getEncuestaEnviada();
        //PEGUNTAR A SOL QUE FALTA AQUI CAMBIAR 
        this.preguntas = encuesta.getPreguntas();
        this.respuestas = llamadaSeleccionada.getRespuestasDeEncuesta();
    }
     
    public String formatearLlamadaSelecionada(){
           // Obtener los datos de las respuestas asociadas a la llamada
        String respuestasSeleccionadas = "";
        String descripcionPreguntas = "";
        
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
        String mensaje = "Cliente: " + cliente.toString() + "\n"
                + "Estado actual: " + estadoActual.getNombre() + "\n"
                + "Duración de la llamada: " + duracion + " minutos\n\n"
                
                + " - Encuesta: \nDescripción de la encuesta: " + encuesta.getDescripcion() +"\n"
                + "Descripción de las preguntas: \n " + descripcionPreguntas + "\n"
                + "Respuestas seleccionadas: \n" + respuestasSeleccionadas + "\n";
                
                

    return mensaje;}
    
    
   
        //generar csv
public void generarCSV() {
    // Ruta del archivo CSV a generar
   // String archivoCSV = "C:/Users/usuario/OneDrive/Escritorio/CSVGenerados/" + nombreArchivo + ".csv";
    SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy");
    String fechaInicioStr = sdf.format(llamadaSeleccionada.getFechaHoraInicioLlamada());
    String archivoCSV = "C:/Users/usuario/OneDrive/Escritorio/CSVGenerados/Llamada_" + cliente.getDni()+"_Fecha_"+fechaInicioStr+".csv";

     try (CSVWriter csvWriter = new CSVWriter(new FileWriter(archivoCSV), '|', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
        // Escribir encabezados
        String[] encabezados = {"Nombre del cliente", "Estado actual de la llamada", "Duración de la llamada"};
        csvWriter.writeNext(encabezados);

     
     
      

        // Escribir fila de datos de la llamada
        String[] filaLlamada = {cliente.getNombreCompleto(), estadoActual.toString(), String.valueOf(duracion)};
        csvWriter.writeNext(filaLlamada);

        // Obtener las preguntas y respuestas asociadas a la llamada
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
        String fechaInicioStr = sdf.format(llamada.getFechaHoraInicioLlamada());
        String fechaFinStr = sdf.format(llamada.getFechaHoraFin());
        return "Llamada: " +  " Cliente: [" + llamada.cliente + "] | Duracion: " + llamada.Duracion + " | fechaHoraInicio: " + fechaInicioStr + " | fechaHoraFin: " + fechaFinStr ;
    
    }
    

}
