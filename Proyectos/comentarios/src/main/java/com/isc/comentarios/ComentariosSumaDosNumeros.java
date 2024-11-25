/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.comentarios;

/**
 * Suma dos números
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 11 sept 2024
 */
public class ComentariosSumaDosNumeros {

    public static void main(String[] args) {
        System.out.println("Suma de dos números.");
        System.out.println("Resultado:" + suma(3,6));
    }
    
    /**
     * Suma dos números enteros
     * @param sumando1 Primer número a sumar
     * @param sumando2 Segundo número a sumar
     * @return         Resultado de la suma
     */
    public static int suma (int sumando1, int sumando2) {
        return sumando1+sumando2;
    }
}
