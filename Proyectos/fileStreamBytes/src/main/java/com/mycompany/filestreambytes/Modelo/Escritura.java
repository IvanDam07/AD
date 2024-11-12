/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreambytes.Modelo;

import com.mycompany.filestreambytes.Modelo.objetos.MiObjectOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
     * Escribe un diferentes tipos simples
     * 
     */
    public void escribirDatosSimples(){
                   
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

        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosOut.close();
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Clase que escribe un objeto en un archivo
     * 
     * @param objeto Objeto a guardar en el fichero
     */
    public void escribirObjetos(Object objeto){
        
        FileOutputStream ficheroOut = null;
        ObjectOutputStream datosOut = null;
        
        try {
            // Si el fichero existe tenemos que crea nuestro MiObjectOutputStream para
            // que no escriba la cabecera
            if (super.existeFichero()){
              ficheroOut = new FileOutputStream(getRuta(), true);
                datosOut = new MiObjectOutputStream(ficheroOut);  
            } else {
                // Si el archivo es nuevo, debemos utilizar ObjectOutputStream
                // para que cree la primera cabecera.
                ficheroOut = new FileOutputStream(getRuta());
                datosOut = new ObjectOutputStream(ficheroOut);
            }
            datosOut.writeObject(objeto);        
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosOut.close();
                ficheroOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}