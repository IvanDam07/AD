/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreamcaracteres.Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Escritura extends Fichero{
    
    public Escritura(String ruta) {
        super(ruta);
    }
    
    /**
     * Escribe un carécter en un archivo
     * 
     * @param caracter      Carácter a escribir
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirCaracter(char caracter, boolean sobreescribe) {
        
        FileWriter ficheroOut = null;
        
        try {
            
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            ficheroOut.write(caracter);
            
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Escribe un array de caracteres en un archivo
     * 
     * @param caracteres    Array de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no 
     */
    public void escribirArrayCaracteres(char[] caracteres, boolean sobreescribe) {
        
        FileWriter ficheroOut = null;
        
        try {
            
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            ficheroOut.write(caracteres);
            
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando BufferedWriter
     * 
     * @param cadena        Cadena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirStreamBufferedCaracteres(String cadena, boolean sobreescribe) {
        FileWriter ficheroOut = null;
        
        try {
            
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            
            BufferedWriter bufferFicheroOut = new BufferedWriter(ficheroOut);
            
            bufferFicheroOut.append(cadena);
            
            bufferFicheroOut.newLine();
            
            //Guardamos los cambios en el fichero
            bufferFicheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Escribe una cadena de caracteres en un archivo utilizando PrintWriter
     * 
     * @param cadena        adena de caracteres a escribir en el archivo
     * @param sobreescribe  Si vamos a sobreescribir el archivo o no
     */
    public void escribirBufferedPrintCaracteres(String cadena, boolean sobreescribe) {
        FileWriter  ficheroOut = null;
        
        try {
            ficheroOut = new FileWriter(getRuta(), sobreescribe);
            PrintWriter bufferFicheroOut = new PrintWriter(ficheroOut);
            bufferFicheroOut.println(cadena);
            
            bufferFicheroOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void encriptarFichero(String archivoOrigen) {
        
        File archivoEncriptado = new File(archivoOrigen+".copia");
        
        FileReader  ficheroIn = null;
        FileWriter ficheroOut = null;
        
        try {
            //Creamos el flujo de lectura
            ficheroIn = new FileReader(archivoOrigen);
            
            //Creamos el flujo de escritura
            ficheroOut = new FileWriter(archivoEncriptado);
            
            //Leemos el código ASCII del carácter contenido en el fichero
            int i;
            
            i = ficheroIn.read();
            
            while(i != -1) {
                
                i += 1;
                ficheroOut.write((char) i);
                i = ficheroIn.read();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ficheroIn.close();
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //metodo para desencriptar el fichero
    public void desencriptarFichero(String archivoEncriptado){
        
        File archivoDesencriptado = new File(archivoEncriptado + ".desencriptado");
        
        FileReader ficheroIn = null;
        FileWriter ficheroOut = null;
        
        try {
            
            //Como antes, creo un flujo de lectura
            ficheroIn = new FileReader(archivoEncriptado);
            
            //Creo tambien el flujo de escritura para el archivo desencriptado
            ficheroOut = new FileWriter(archivoDesencriptado);
            
            //Leo el codigo ascii del caracter
            int i;
            i = ficheroIn.read();
            
            //proceso cada caracter
            while(i != -1){
                
                 i -= 1;//decremento lo que antes incrementaba para encriptar
                 
                 ficheroOut.write((char) i);
                 i = ficheroIn.read();
            }
                 
        } catch (IOException e){
                    e.printStackTrace();
                    
        } finally {
            
            try {
               
                ficheroOut.close();
                ficheroIn.close();
                
            } catch(IOException e){
                e.printStackTrace();
            }
            
        }
        
    }
}
