/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.proyectonodos;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 17 sept 2024
 */
public class Persona {

    private int edad;

    /**
     * Constructor por defecto
     *
     * @param edad
     */
    public Persona(int edad) {
        this.edad = edad;
    }

    /**
     * Devuelve la edad
     *
     * @return Edad acutal
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Modifica la edad
     *
     * @param edad Valor edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
