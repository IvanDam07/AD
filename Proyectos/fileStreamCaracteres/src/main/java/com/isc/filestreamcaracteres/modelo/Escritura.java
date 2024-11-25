/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreamcaracteres.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public class Escritura extends Fichero {
    
    public Escritura(String ruta) {
        super(ruta);
    }
    
   /**
     * Escribe un carácter en el archivo. Crea el archivo si no existe, de lo contrario agrega el carácter.
     * @param caracter Carácter a escribir
     * @param sobreescribe Si es true, sobrescribe el archivo, si es false, agrega al final del archivo
     */
    public void escribirCaracter(char caracter, boolean sobreescribe) {
        try (FileWriter ficheroOut = new FileWriter(getRuta(), !sobreescribe)) {
            ficheroOut.write(caracter);
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Escribe un array de caracteres en el archivo. Crea el archivo si no existe, de lo contrario agrega el array.
     * @param caracteres Array de caracteres a escribir
     * @param sobreescribe Si es true, sobrescribe el archivo, si es false, agrega al final del archivo
     */
    public void escribirArrayCaracteres(char[] caracteres, boolean sobreescribe) {
        try (FileWriter ficheroOut = new FileWriter(getRuta(), !sobreescribe)) {
            ficheroOut.write(caracteres);
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Escribe una cadena de texto utilizando BufferedWriter. Crea el archivo si no existe, de lo contrario agrega la cadena.
     * @param cadena Cadena de texto a escribir
     * @param sobreescribe Si es true, sobrescribe el archivo, si es false, agrega al final del archivo
     */
    public void escribirStreamBufferedCaracteres(String cadena, boolean sobreescribe) {
        try (FileWriter ficheroOut = new FileWriter(getRuta(), !sobreescribe);
             BufferedWriter bufferFicheroOut = new BufferedWriter(ficheroOut)) {

            bufferFicheroOut.write(cadena);
            bufferFicheroOut.newLine();
            bufferFicheroOut.flush();

        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando PrintWriter
     * 
     * @param cadena            Cadena de caracteres a escribir en el archivo
     * @param sobreescribe   Si vamos a sobreescribir el archivo o no
     */
    public void escribirBufferedPrintCaracteres(String cadena, boolean sobreescribe) {
        
        FileWriter ficheroOut = null;
        
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            PrintWriter bufferFicheroOut = new PrintWriter(ficheroOut);
            
            bufferFicheroOut.println(cadena);
            
            //Guardamos los cambios del fichero
            bufferFicheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     /**
     * Encripta el contenido del archivo y crea un nuevo archivo encriptado.
     */
    public void encriptarFichero() {
        // Cargar el archivo original, encriptar el contenido y guardarlo en un nuevo archivo
        String contenido = new Lectura(getRuta()).leerCaracterACaracter();
        StringBuilder encryptedContent = new StringBuilder();

        for (char c : contenido.toCharArray()) {
            encryptedContent.append((char) (c + 1));  // Encriptación simple desplazando los caracteres
        }

        try (FileWriter writer = new FileWriter(getRuta() + ".txt")) {
            writer.write(encryptedContent.toString());
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Desencripta un archivo previamente encriptado y muestra el contenido.
     */
    public void desencriptarFichero() {
        String contenido = new Lectura(getRuta()).leerCaracterACaracter();
        StringBuilder decryptedContent = new StringBuilder();

        for (char c : contenido.toCharArray()) {
            decryptedContent.append((char) (c - 1));  // Desencriptación invirtiendo el desplazamiento
        }

        System.out.println("Contenido desencriptado:");
        System.out.println(decryptedContent.toString());
    }
}
