/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreambytes.modelo;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 4 oct 2024
 */
public class Escritura extends Fichero{
    
    public Escritura(String ruta) {
        super(ruta);
    }
    
    public void escribirDatosSimples() {
        FileOutputStream ficheroOut = null;
        DataOutputStream datosOut = null;
        
        try {
            
            
            ficheroOut = new FileOutputStream(getRuta());
            
            datosOut = new DataOutputStream(ficheroOut);
            
            datosOut.writeByte((byte) 123);
            datosOut.writeShort((short) 1234);
            datosOut.writeInt(1234567);
            datosOut.writeLong(1234567890123456L);
            datosOut.writeFloat((float) Math.E);
            datosOut.writeDouble(Math.PI);
            datosOut.writeBoolean(true);
            datosOut.writeChar('A');
            datosOut.writeUTF("Esto es una cadena");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosOut.close();
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public void escribirObjetos(Object objeto) {
        
        FileOutputStream ficheroOut = null;
        ObjectOutputStream datosOut = null;        
        
        try {
            if (super.existeFichero()) {
                //Por aquí entra cuando ya se creó el archivo
                //No crea cabeceras
                ficheroOut = new FileOutputStream(getRuta(), true);
                datosOut = new ObjectOutputStream(ficheroOut);
            } else {
                //Por aquí entra la primera vez que crea el archivo
                //Crea la cabecera
                ficheroOut = new FileOutputStream(getRuta());
                datosOut = new ObjectOutputStream(ficheroOut);
                
            }            
            
            datosOut.writeObject(objeto);
            
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
