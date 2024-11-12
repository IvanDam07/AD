/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreambytes.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Copia extends Fichero{

    private File rutaDestino;
    
    public Copia(String ruta, String rutaDestino) {
        super(ruta);
        this.rutaDestino = new File(rutaDestino);
    }

    
    /**
     * Copia un archivo binario al destino indicado en rutaDestino
     */
    public void copiarArchivo(){
   
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
                
        try {
             int length = 0;
             inputStream = new FileInputStream(getRuta());
             outputStream = new FileOutputStream(this.rutaDestino);
             
             byte[] tempData = new byte[1024];
             length = inputStream.read(tempData);
             
             while (length > 0) {
                 outputStream.write(tempData, 0, length);
                 length = inputStream.read(tempData);
             }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Copia.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Copia.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             try {
                 inputStream.close();
                 outputStream.close();
             } catch (IOException ex) {
                 Logger.getLogger(Copia.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
}