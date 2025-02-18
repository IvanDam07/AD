/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.ejercicio1examen;

import static com.mmc.ejercicio1examen.Modelo.busquedaEjercicio1;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Ejercicio1examen {

    public static void main(String[] args) {
        
        Modelo m = new Modelo();
        
        String nombreArchivo = "archivo.txt";
        String ruta = "./files";
        String rutaCopia = "filesCopia";
        
        m.busquedaEjercicio1(nombreArchivo,ruta,rutaCopia);
        
        
    }
}
