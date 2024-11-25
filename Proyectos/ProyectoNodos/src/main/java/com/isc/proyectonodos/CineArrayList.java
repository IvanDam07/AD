/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.proyectonodos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 17 sept 2024
 */
public class CineArrayList {
     public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Persona> cola = new ArrayList<>();
        int totalPersonas = generaNumeroAleatorio(0, 50); // Generamos el número de personas en la cola
        double totalRecaudado = 0.0;

        // Generamos las personas y las añadimos al ArrayList
        for (int i = 0; i < totalPersonas; i++) {
            int edad = generaNumeroAleatorio(5, 60);
            Persona persona = new Persona(edad);
            cola.add(persona);
        }

        // Creamos un iterador para recorrer la lista
        Iterator<Persona> iterador = cola.iterator();

        // Calculamos el total recaudado mientras recorremos la lista con el iterador
        while (iterador.hasNext()) {
            Persona persona = iterador.next();
            int edad = persona.getEdad();
            double precioEntrada = calcularPrecioEntrada(edad);
            System.out.println("Persona con edad " + edad + " paga " + precioEntrada + " euros.");
            totalRecaudado += precioEntrada;
            // Eliminamos la persona de la lista después de procesarla
            iterador.remove();
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
     * Genera un número aleatorio entre el mínimo y el máximo (ambos incluidos).
     *
     * @param minimo Valor mínimo
     * @param maximo Valor máximo
     * @return Número aleatorio
     */
    public static int generaNumeroAleatorio(int minimo, int maximo) {
        return (int) Math.floor(Math.random() * (maximo - minimo + 1) + minimo);
    }
}
