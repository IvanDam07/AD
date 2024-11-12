/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej2prepexamen.Modelo;

import java.io.Serializable;

/**
 *
 * @author Ivan Sobrino
 */
public class Revista extends RecursoBiblioteca implements Serializable{
    
    private String mesPublicacion;
    private int numEdicion;

    /**
     * Constructor de la clase Revista.
     * 
     * @param titulo     Título de la revista.
     * @param autor      Autor de la revista.
     * @param añoPublicacion Año de publicación de la revista.
     * @param numeroEdicion Número de edición de la revista.
     * @param mesPublicacion Mes de publicación de la revista.
     */
    public Revista(String autor, String titulo, int anioPublicacion, int numEdicion, String mesPublicacion) {
        super(autor, titulo, anioPublicacion);
        this.mesPublicacion = mesPublicacion;
        this.numEdicion = numEdicion;
    }

    public int getNumEdicion() {
        return numEdicion;
    }

    public void setNumEdicion(int numEdicion) {
        this.numEdicion = numEdicion;
    }

    public String getMesPublicacion() {
        return mesPublicacion;
    }

    public void setMesPublicacion(String mesPublicacion) {
        this.mesPublicacion = mesPublicacion;
    }
    
    
}
