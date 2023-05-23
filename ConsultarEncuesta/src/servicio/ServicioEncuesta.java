/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import entidades.Encuesta;
import entidades.Pregunta;

/**
 *
 * @author Brisa
 */
public class ServicioEncuesta {
    
    public void agregarPregunta(Encuesta encuesta, Pregunta pregunta) {
        encuesta.preguntas.add(pregunta);
    }
 }
