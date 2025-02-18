/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.ejercicio3examen;

import com.mmc.ejercicio3examen.controlador.Controlador;
import com.mmc.ejercicio3examen.modelo.Modelo;
import com.mmc.ejercicio3examen.vista.InterfazVista;
import com.mmc.ejercicio3examen.vista.VistaTexto;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Ejercicio3examen {

    public static void main(String[] args) {
        
        InterfazVista vista = new VistaTexto();
        Modelo modelo = new Modelo("productos");
        
        Controlador controlador = new Controlador(vista, modelo);
        vista.arranca();
        
    }
}
