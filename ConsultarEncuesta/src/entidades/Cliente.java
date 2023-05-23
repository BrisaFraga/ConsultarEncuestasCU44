/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Brisa
 */
public class Cliente {
    private String nombreCompleto;
    private Long dni;
    private Long nroCelular;
    
    public Cliente(String nombreCompleto, Long dni, Long nroCelular) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.nroCelular = nroCelular;
    }
   

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(Long nroCelular) {
        this.nroCelular = nroCelular;
    }

     @Override
    public String toString() {
        return " Nombre Completo: " + nombreCompleto + " | DNI: " + dni + " | NroCelular: " + nroCelular ;
    }
    
}
