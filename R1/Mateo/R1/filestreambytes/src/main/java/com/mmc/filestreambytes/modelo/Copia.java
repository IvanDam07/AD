/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.filestreambytes.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 4 oct 2024
 *
 */
public class Copia extends Fichero{
    
    private File rutaDestino;
    
    public Copia(String ruta, String rutaDestino){
        
        super(ruta);
        this.rutaDestino = new File(rutaDestino);
        
    }
    
    
    public void copiarArchivo(){
        
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        
        int length = 0;
        
        try {
            
            inputStream = new FileInputStream(getRuta());
            outputStream = new FileOutputStream(this.rutaDestino);
            
            byte[] datosTemporales = new byte[1024];
            
            length = inputStream.read(datosTemporales);
            
            
            //Cuando la longitud sea 0 significa que ya no estoy leyendo nada
            while (length > 0){
                
                outputStream.write(datosTemporales, 0, length);
                length = inputStream.read(datosTemporales);
                
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
