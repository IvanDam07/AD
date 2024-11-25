/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreamcaracteres.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public class Lectura extends Fichero{
        
    public Lectura(String ruta) {
        super(ruta);
    }
    
    /**
     * Lee el contenido de un archivo carácter por carácter.
     * @return Cadena con el contenido del archivo
     */
    public String leerCaracterACaracter() {
        StringBuilder sb = new StringBuilder();
        try (FileReader ficheroIn = new FileReader(getRuta())) {
            int i;
            while ((i = ficheroIn.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    /**
     * Lee el contenido de un archivo usando un array de caracteres.
     * @return Cadena con el contenido del archivo
     */
    public String leerArrayCaracteres() {
        StringBuilder sb = new StringBuilder();
        try (FileReader ficheroIn = new FileReader(getRuta())) {
            char[] cadena = new char[5];
            int i;
            while ((i = ficheroIn.read(cadena)) != -1) {
                sb.append(cadena, 0, i);
                Arrays.fill(cadena, ' ');
            }
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    /**
     * Lee el contenido de un archivo utilizando BufferedReader.
     * @return Cadena con el contenido del archivo
     */
    public String leerCaracteresBufferReader() {
        StringBuilder sb = new StringBuilder();
        try (FileReader ficheroIn = new FileReader(getRuta());
             BufferedReader bufferFicheroIn = new BufferedReader(ficheroIn)) {

            String linea;
            while ((linea = bufferFicheroIn.readLine()) != null) {
                sb.append(linea).append("\n");
            }

        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
}
