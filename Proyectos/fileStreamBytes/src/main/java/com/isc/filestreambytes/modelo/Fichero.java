/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreambytes.modelo;

import java.io.File;

/**
 * Clase que trabaja con el contenido de ficheros
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public class Fichero {
    private File ruta;
    
    /**
     * Constructor
     * 
     * @param ruta Ruta del archivo
     */
    public Fichero(String ruta) {
        this.ruta = new File(ruta);
    }
    
    /**
     * Devuelve el atributo ruta
     * 
     * @return String con la ruta del archivo
     */
    /*public String getPathRuta() {
        return ruta.getAbsolutePath();
    }*/
    
    public File getRuta(){
        return ruta;
    }
    /**
     * Pone el valor al atributo ruta
     * 
     * @param ruta 
     */
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
    
    public boolean existeFichero() {
        if(ruta.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
