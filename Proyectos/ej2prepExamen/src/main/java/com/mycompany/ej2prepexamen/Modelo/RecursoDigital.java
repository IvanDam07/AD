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
public class RecursoDigital extends RecursoBiblioteca implements Serializable{
    
    private String formato;
    private double tamanioMB;

    /**
     * Constructor de la clase RecursoDigital.
     * 
     * @param titulo     Título del recurso digital.
     * @param autor      Autor del recurso digital.
     * @param añoPublicacion Año de publicación del recurso digital.
     * @param formato    Formato del recurso digital (PDF, EPUB, etc.).
     * @param tamañoMB   Tamaño del recurso en MB.
     */
    public RecursoDigital(String autor, String titulo, int anioPublicacion, String formato, double tamanioMB) {
        super(autor, titulo, anioPublicacion);
        this.formato = formato;
        this.tamanioMB = tamanioMB;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getTamanioMB() {
        return tamanioMB;
    }

    public void setTamanioMB(double tamanioMB) {
        this.tamanioMB = tamanioMB;
    }
    
    
}
