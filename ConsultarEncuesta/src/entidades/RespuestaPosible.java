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
public class RespuestaPosible {
    private Integer valor;
    private String Descripcion;

    public RespuestaPosible(Integer valor, String Descripcion) {
        this.valor = valor;
        this.Descripcion = Descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return "Valor: " + valor + " | Descripcion: " + Descripcion + '|';
    }
    
    
   
    
}
