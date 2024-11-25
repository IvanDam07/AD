/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.ejercicio1examen;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio1examen {

    public static void main(String[] args) {
        
        String nombreArchivo = "PRUEBA2.txt";
        String ruta = "./ORIGEN";
        String rutaCopia = "./COPIA";
        
        Modelo modelo = new Modelo();
        
        modelo.busquedaEjercicio1(nombreArchivo,ruta,rutaCopia);
    }
}
