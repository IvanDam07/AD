/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.mvc;

import com.mmc.mvc.controlador.ControlConversor;
import com.mmc.mvc.modelo.ConversorEurosPesetas;
import com.mmc.mvc.vista.InterfazVista;
import com.mmc.mvc.vista.VentanaConversor;
import com.mmc.mvc.vista.VentanaConversorTexto;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 * 
 */
public class Mvc {

    public static void main(String[] args) {
        
        InterfazVista vista = (InterfazVista) new VentanaConversor();
        
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        
        ControlConversor control = new ControlConversor(vista, modelo);
        
        
    }
}
