/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Modelo {
    
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_INT = 4;
    private final int LONGITUD_CHAR=2;
    
    private final int CARACTERES_DESC_DIRECCION=31;
    
    private final int LONGITUD_ID = LONGITUD_INT;
    private final int LONGITUD_DESC = CARACTERES_DESC_DIRECCION * LONGITUD_CHAR;
    private final int LONGITUD_DIRECCION = CARACTERES_DESC_DIRECCION * LONGITUD_CHAR;
    private final int LONGITUD_COSTE = LONGITUD_DOUBLE;
    
    private final int LONGITUD_TOTAL = LONGITUD_ID+LONGITUD_DESC+LONGITUD_DIRECCION+LONGITUD_COSTE;
    
    
    public Modelo(){}
    
    
    public void insertaEjercicio2(Reforma reforma){
        
        RandomAccessFile randomFile = null; 
        
        try {
            
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "rw");
            
            long posReforma = (reforma.getId() - 1) * this.LONGITUD_TOTAL;
            
            randomFile.seek(posReforma);

            randomFile.writeLong(reforma.getId());

            StringBuffer bufferStr = new StringBuffer(reforma.getDescripcion());
            bufferStr.setLength(CARACTERES_DESC_DIRECCION);
            randomFile.writeChars(bufferStr.toString());

            bufferStr = new StringBuffer(reforma.getDireccion());
            bufferStr.setLength(CARACTERES_DESC_DIRECCION);
            randomFile.writeChars(bufferStr.toString());

            randomFile.writeDouble(reforma.getCoste());
            
            
         
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            
        
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void muestraEjercicio2(int id){
                
        // RamdomAccessFile para acceso a un fichero de forma aleatoria
        RandomAccessFile randomFile = null; 
        int posicion = 0;
        Reforma reforma = new Reforma();
        
        // Creamos un array de byte para la lectura de la direccion
        byte [] cadenaDesc = new byte [LONGITUD_DESC];
        //y otro para direccion
        byte [] cadenaDir = new byte [LONGITUD_DIRECCION];
        
        try {
            
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "r");

            posicion = (id-1) * LONGITUD_TOTAL;
            
            
            if (posicion < randomFile.length()) {       
                randomFile.seek(posicion);
                
              
                reforma.setId(randomFile.readLong());
               
                randomFile.read(cadenaDesc);                               
                reforma.setDescripcion(new String(cadenaDesc));
                System.out.println(reforma.getDescripcion());

                randomFile.read(cadenaDir);                               
                reforma.setDireccion(new String(cadenaDir));
                
                reforma.setCoste(randomFile.readDouble());
                System.out.println(reforma.getCoste());
                
            }      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }

}
