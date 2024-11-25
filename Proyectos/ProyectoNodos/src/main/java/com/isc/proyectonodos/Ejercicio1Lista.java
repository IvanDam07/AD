/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.proyectonodos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 16 sept 2024
 */
public class Ejercicio1Lista {
    
    public static void main(String[] args) {
        Random random = new Random();
        ListaEnlazada<Persona> cola = new ListaEnlazada<>();
        int totalPersonas = generaNumeroAleatorio(0, 50); // Generamos el número de personas en la cola
        double totalRecaudado = 0.0;

        // Generamos las personas y las añadimos a la cola
        for (int i = 0; i < totalPersonas; i++) {
            int edad = generaNumeroAleatorio(5, 60);
            Persona persona = new Persona(edad);
            cola.insertarUltimo(persona);
        }

        // Calculamos el total recaudado mientras vaciamos la cola
        while (!cola.estaVacia()) {
            Persona persona = cola.devolverYBorrarPrimero();
            int edad = persona.getEdad();
            double precioEntrada = calcularPrecioEntrada(edad);
            System.out.println("Persona con edad " + edad + " paga " + precioEntrada + " euros.");
            totalRecaudado += precioEntrada;
        }

        // Mostramos el total recaudado
        System.out.println("Total recaudado: " + totalRecaudado + " euros.");
    }
    
    /**
     * Calcula el precio de la entrada según la edad de la persona.
     *
     * @param edad Edad de la persona
     * @return Precio de la entrada
     */
    public static double calcularPrecioEntrada(int edad) {
        if (edad >= 5 && edad <= 10) {
            return 1.0;
        } else if (edad >= 11 && edad <= 17) {
            return 2.5;
        } else {
            return 3.5;
        }
    }
    
    /**
    * Genera un numero aleatorio entre dos numeros.
    * Entre el minimo y el maximo incluidos
    * @param minimo Número mínimo
    * @param maximo Número máximo
    * @return Número entre minimo y maximo
    */
    
    
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (minimo
                - (maximo + 1)) + (maximo + 1));
        return num;
    }

    /**
     * Genera un numero aleatorio entre dos numeros reales. Entre el minimo y el
     * maximo incluidos Devuelve un numero con dos decimales.
     *
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return Número entre minimo y maximo
     */
    public static double generaNumeroRealAleatorio(double minimo, double maximo) {
        double num = Math.rint(Math.floor(Math.random() * (minimo
                - ((maximo * 100) + 1)) + ((maximo * 100) + 1))) / 100;
        return num;
    }
    
}
