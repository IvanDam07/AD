/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreambytes.Modelo;

import java.io.File;

/**
 *
 * @author Ivan Sobrino
 */
public class Fichero {
    private File ruta;

    public Fichero(String ruta) {
        this.ruta = new File(ruta);
    }

    /**
     * Devuelve el atributo ruta
     * 
     * @return String con la ruta del archivo
     */
    public String getPathRuta() {
        return ruta.getAbsolutePath();
    }
    
    public File getRuta() {
        return ruta;
    }
    

    /**
     * Pone el valor al atributo ruta
     * 
     * @param ruta Ruta completa al archivo
     */
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
       
    /**
     * Nos dice si existe el archivo de la ruta especificada
     * @return Boolean que indoca si existe o no el archivo.
     */
    public boolean existeFichero(){
        if (ruta.exists()){
            return true;
        } else{
            return false;
        }
    }
}
