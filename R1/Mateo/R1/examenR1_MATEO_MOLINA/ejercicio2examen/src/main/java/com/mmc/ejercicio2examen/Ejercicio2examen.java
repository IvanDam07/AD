/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.ejercicio2examen;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Ejercicio2examen {

    public static void main(String[] args) {
        
        Modelo m = new Modelo();
        
        Reforma r = new Reforma(4,"Renovar Fontanería","Calle Toledo 47 2A, Ciudad Real",600);
        
        m.insertaEjercicio2(r);
        
        m.muestraEjercicio2(4);
        
        r.setCoste(700);
        
        m.muestraEjercicio2(4);
        
        
        
    }

}
