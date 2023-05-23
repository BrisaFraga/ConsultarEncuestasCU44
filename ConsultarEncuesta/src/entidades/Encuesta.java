/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Brisa
 */
public class Encuesta {
    private Date fechaFinVigencia;
    public Set<Pregunta> preguntas;
    private String descripcion;
    
 public Encuesta(String fechaFinVigenciaString, Set<Pregunta> preguntas, String descripcion) {
        this.preguntas = preguntas;
        this.descripcion = descripcion;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            this.fechaFinVigencia = sdf.parse(fechaFinVigenciaString);
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejo de la excepción en caso de que la fecha no pueda ser parseada correctamente
        }
    }
    public String getFormattedFechaFinVigencia(String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fechaFinVigencia);
    }

public void setFechaFinVigencia(String fechaFinVigenciaString, String formatoFecha) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
        try {
            this.fechaFinVigencia = sdf.parse(fechaFinVigenciaString);
        } catch (ParseException e) {

            // Manejo de la excepción en caso de que la fecha no pueda ser parseada correctamente
            
        }
    }
    public Set<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFin = sdf.format(fechaFinVigencia);
        return " Descripcion: " + descripcion + " | Fecha Fin Vigencia: " + fechaFin + "| Preguntas: [ " + preguntas + " ] ";
    }
    
    
}
