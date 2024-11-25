/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.mvc;

import com.isc.mvc.controlador.ControlConversor;
import com.isc.mvc.modelo.ConversorEurosPesetas;
import com.isc.mvc.vista.InterfazVista;
import com.isc.mvc.vista.VentanaConversor;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Mvc {

    public static void main(String[] args) {
        
        InterfazVista vista = new VentanaConversor();
        
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        
        ControlConversor control = new ControlConversor(vista, modelo);
    }
}
