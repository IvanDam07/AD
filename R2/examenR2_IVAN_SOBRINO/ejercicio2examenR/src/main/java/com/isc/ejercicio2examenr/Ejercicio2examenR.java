/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.ejercicio2examenr;

import com.isc.ejercicio2examenr.controlador.Controlador;
import com.isc.ejercicio2examenr.modelo.Modelo;
import com.isc.ejercicio2examenr.vista.InterfazVista;
import com.isc.ejercicio2examenr.vista.VistaTexto;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio2examenR {

    public static void main(String[] args) {
        InterfazVista vista = (InterfazVista) new VistaTexto();
        Modelo modelo = new Modelo();
        
        modelo.utilidadAbrirConexion();
        
        Controlador controlador = new Controlador(vista,modelo);
        
        modelo.utilidadCerrarConexion();
    }
}
