/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ventanagrafica;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 15 ene 2025
 */
public class Usuario {
    
    private final String nombre;
    private final String contrasena;
    
    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
        
}
