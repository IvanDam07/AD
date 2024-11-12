/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clasefile.Modelo;

import java.io.File;

/**
 *
 * @author Ivan Sobrino
 */
public class Carpeta {
    private String ruta;
    
    public Carpeta(String ruta) {
        this.ruta = ruta;
    }
    
    public Carpeta() {
        
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public String getRuta() {
        return ruta;
    }
    
    /**
     * Devuelve en un File la ruta de la carpeta
     * @return File con la carpeta
     */
    public File getFileDeRuta() {
        File directorioRaiz = new File(ruta);
        return directorioRaiz;
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta completa
     */
    public void crearCarpeta() {
        File directorioNuevo = new File(ruta);
        directorioNuevo.mkdir();
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta del directorio padre y el nombre del nuevo directorio
     * 
     * @param nombreDirectorio  Nombre del nuevo directorio
     */
    public void crearCarpeta(String nombreDirectorio) {
        File directorioNuevo = new File(ruta,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio) {
        File directorioNuevo = new File(directorioRaiz, nombreDirectorio);
        directorioNuevo.mkdir(); //Crea el directorio
    }
}
