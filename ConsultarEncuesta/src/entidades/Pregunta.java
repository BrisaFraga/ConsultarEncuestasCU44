/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Brisa
 */
public class Pregunta {
    private String pregunta;
    private Set<RespuestaPosible> respuestas;

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
        this.respuestas = new HashSet();
    }

    

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Set<RespuestaPosible> getRespuesta() {
        return respuestas;
    }

     public void agregarRespuesta(RespuestaPosible respuesta) {
        respuestas.add(respuesta);
    }

    @Override
    public String toString() {
        return  "[Pregunta: " + pregunta + " - Respuestas: (" + respuestas +") ]";
    }
    
}
