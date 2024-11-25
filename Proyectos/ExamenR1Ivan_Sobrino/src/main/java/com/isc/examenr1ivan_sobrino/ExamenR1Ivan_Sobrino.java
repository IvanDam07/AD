/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.examenr1ivan_sobrino;

import com.isc.examenr1ivan_sobrino.controlador.ControlModelo;
import com.isc.examenr1ivan_sobrino.modelo.Modelo;
import com.isc.examenr1ivan_sobrino.vista.InterfazVista;
import com.isc.examenr1ivan_sobrino.vista.VistaTexto;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class ExamenR1Ivan_Sobrino {

    public static void main(String[] args) {
        
        InterfazVista vista = new VistaTexto();
        
        Modelo modeloEx = new Modelo();
        
        ControlModelo controladorModelo = new ControlModelo(vista, modeloEx);
        
        vista.arranca();
    }
}
