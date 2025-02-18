/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.filestreambytes.modelo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 4 oct 2024
 *
 */
public class Lectura extends Fichero{
    
    public Lectura(String ruta){
        super(ruta);
    }
    
    public String leerDatosSimple(){
        
        FileInputStream ficheroIn = null;
        DataInputStream dataIn = null;
        StringBuffer texto = new StringBuffer();
        
        try {
            
            ficheroIn = new FileInputStream(getRuta());
            dataIn = new DataInputStream(ficheroIn);
            
            texto.append(dataIn.readByte());
            texto.append(" ");
            
            texto.append(dataIn.readShort());
            texto.append(" ");
            
            texto.append(dataIn.readInt());
            texto.append(" ");
            
            texto.append(dataIn.readLong());
            texto.append(" ");
            
            texto.append(dataIn.readFloat());
            texto.append(" ");
            
            texto.append(dataIn.readDouble());
            texto.append(" ");
            
            texto.append(dataIn.readBoolean());
            texto.append(" ");
            
            texto.append(dataIn.readChar());
            texto.append(" ");
            
            texto.append(dataIn.readUTF());
            texto.append(" ");
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            
            try {
                
                dataIn.close();
                ficheroIn.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
                
            }
         
            return texto.toString();
        }
        
    }
    
    
    public ArrayList <Object> lecturaObjetos(){
        
        FileInputStream ficheroIn = null;
        ObjectInputStream dataIn = null;
        
        ArrayList <Object> retornoList = new ArrayList();
        
        Object retornoObject = null;
        
        try {
            
            ficheroIn = new FileInputStream(getRuta());
            dataIn = new ObjectInputStream(ficheroIn);
            
            while(ficheroIn.available() > 0){
                
                retornoObject = dataIn.readObject();
                retornoList.add(retornoObject);
                
            }
           
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retornoList;
    }

}
