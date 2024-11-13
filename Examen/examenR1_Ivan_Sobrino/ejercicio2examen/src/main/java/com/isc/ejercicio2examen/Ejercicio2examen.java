/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.ejercicio2examen;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        Reforma reforma1 = new Reforma(4,"Renovar fontanería","Calle Toledo 47 2A, Ciudad Real", 600);
        Modelo modelo = new Modelo();
        
        modelo.insertaEjercicio2(reforma1);
        modelo.muestraEjercicio2(4);
        reforma1.setCoste(700);
        modelo.insertaEjercicio2(reforma1);
        modelo.muestraEjercicio2(4);
    }
}
