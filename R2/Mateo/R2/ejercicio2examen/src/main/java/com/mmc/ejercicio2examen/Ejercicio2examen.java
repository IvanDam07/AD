/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.ejercicio2examen;

import com.mmc.ejercicio2examen.controlador.Controlador;
import com.mmc.ejercicio2examen.modelo.Modelo;
import com.mmc.ejercicio2examen.vista.InterfazVista;
import com.mmc.ejercicio2examen.vista.VistaTexto;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        
        InterfazVista vista = (InterfazVista) new VistaTexto();
        Modelo modelo = new Modelo();
        
        modelo.utilidadAbrirConexion();
        
        Controlador controlador = new Controlador(vista,modelo);
        
        modelo.utilidadCerrarConexion();
        
    }
}
