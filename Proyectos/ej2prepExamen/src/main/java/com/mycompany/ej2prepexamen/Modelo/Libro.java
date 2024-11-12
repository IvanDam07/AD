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
public class Libro extends RecursoBiblioteca implements Serializable{
    
    public String ISBN;
    public String editorial;
    public int numPaginas;

    /**
     * Constructor de la clase Libro.
     * 
     * @param titulo     Título del libro.
     * @param autor      Autor del libro.
     * @param añoPublicacion Año de publicación del libro.
     * @param ISBN       ISBN del libro.
     * @param editorial  Editorial del libro.
     * @param numeroPaginas Número de páginas del libro.
     */
    public Libro(String autor, String titulo, int anioPublicacion, String ISBN, String editorial, int numPaginas) {
        super(autor, titulo, anioPublicacion);
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.numPaginas = numPaginas;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
    
    
}
