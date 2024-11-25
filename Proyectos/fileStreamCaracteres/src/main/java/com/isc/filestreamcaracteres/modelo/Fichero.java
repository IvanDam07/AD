/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreamcaracteres.modelo;

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
    
    public Fichero(String ruta) {
        this.ruta = new File(ruta);
    }
    
    /**
     * Devuelve el atributo ruta
     * 
     * @return String con la ruta del archivo
     */
    public String getRuta() {
        return ruta.getAbsolutePath();
    }
    
    /**
     * Pone el valor al atributo ruta
     * 
     * @param ruta 
     */
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
}
