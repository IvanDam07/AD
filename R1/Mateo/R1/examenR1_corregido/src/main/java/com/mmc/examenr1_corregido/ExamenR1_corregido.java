/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.examenr1_corregido;

import com.mmc.examenr1_corregido.controlador.Controlador;
import com.mmc.examenr1_corregido.modelo.Modelo;
import com.mmc.examenr1_corregido.vista.InterfazVista;
import com.mmc.examenr1_corregido.vista.VistaTexto;

/**
 *
 * @author mmc20
 */
public class ExamenR1_corregido {

    public static void main(String[] args) {
        
        InterfazVista vista = new VistaTexto();
        Modelo modelo = new Modelo();
        
        Controlador controlador = new Controlador(vista, modelo);
        vista.arranca();
        
        
    }
}
