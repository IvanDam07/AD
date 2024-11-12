/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreamcaracteres.Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Lectura extends Fichero{
    
    public Lectura(String ruta) {
        super(ruta);
    }
    
    /**
     * Lee el contenido de un archivo carácter a carácter
     * 
     * @return Cadena con el contenido del archivo
     */
    public String leerCaracterACaracter() {
        
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
            
            //Creamos el flujo de lectura:
            ficheroIn = new FileReader(getRuta());
            //Leemos el código ASCII del carácter contenido en el fichero
            int i;
            
            i= ficheroIn.read();
            
            while(i != -1) {
                texto.append((char)i);
                i = ficheroIn.read();
            }
        } catch (IOException e) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ficheroIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
    }
    
    /**
     * Lee el contenido de un archivo utilizando un array de caracteres
     * 
     * @return Cadena con el contenido del archivo
     */
    public String leerArrayCaracteres() {
        
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
            
            ficheroIn = new FileReader(getRuta());
            
            int i;
            char cadena[] = new char[5];
            i = ficheroIn.read(cadena);
            
            while (i != -1) {
                texto.append(cadena);
                Arrays.fill(cadena, ' ');
                i = ficheroIn.read(cadena);
            }
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
    }
    
    public String leerCaracteresBufferReader() {
        FileReader ficheroIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
            int i =0;
            ficheroIn = new FileReader(getRuta());
            
            BufferedReader bufferFicheroIn = new BufferedReader(ficheroIn);
            
            String linea;
            
            linea = bufferFicheroIn.readLine();
            
            while (linea != null) {
                texto.append(linea);
                texto.append("\n");
                linea = bufferFicheroIn.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
    }
}
