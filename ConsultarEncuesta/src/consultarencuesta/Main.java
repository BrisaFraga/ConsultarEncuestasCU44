/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultarencuesta;

import control.GestorConsultarEncuesta;
import java.text.ParseException;
import vistas.ConsultarEncuesta;

/**
 *
 * @author Brisa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) throws ParseException {
        // Crear una instancia de ConsultarEncuesta
        ConsultarEncuesta consultarEncuesta = new ConsultarEncuesta();

        // Hacer visible la ventana
        consultarEncuesta.setVisible(true);
        
    }


}
