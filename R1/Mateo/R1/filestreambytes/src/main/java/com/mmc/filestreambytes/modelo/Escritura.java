/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.filestreambytes.modelo;

import com.mmc.filestreambytes.modelo.objetos.MiObjectOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 4 oct 2024
 *
 */
public class Escritura extends Fichero {
    
    public Escritura(String ruta){
        super(ruta);
    }
    
    
    public void escribirDatosSimples(){
        
        FileOutputStream ficheroOut = null;
        
        DataOutputStream dataOut = null;
        
        try {
            
            ficheroOut = new FileOutputStream(getRuta());
            
            dataOut = new DataOutputStream(ficheroOut);

            
            dataOut.writeByte((byte) 123);
            dataOut.writeShort((short) 1234);
            dataOut.writeInt(1234567);
            dataOut.writeLong(1234567890123456L);
            dataOut.writeFloat((float) Math.E);
            dataOut.writeDouble(Math.PI);
            dataOut.writeBoolean(true);
            dataOut.writeChar('A');
            dataOut.writeUTF("Esto es una cadena");
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            
            try {
                
                dataOut.close();
                ficheroOut.close();
                
            } catch (IOException ex) {
                
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void escribirObjetos(Object objeto){
        
        FileOutputStream ficheroOut = null;
        ObjectOutputStream dataOut = null;
        
        try {
            
            if(super.existeFichero()){
                
                /*
                Por aqí entra cuando ya se ha creado el archivo
                No crea cabeceras
                */
                ficheroOut = new FileOutputStream(getRuta(), true);
                dataOut = new MiObjectOutputStream(ficheroOut);
                
            } else {
                
                /*
                Por aquí entra la primera vez que crea el archivo
                Crea la cabecera
                */
                ficheroOut = new FileOutputStream(getRuta());
                dataOut = new ObjectOutputStream(ficheroOut);
                
            }
            
            dataOut.writeObject(objeto);
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Dados dos arrays con nombres de personas y numeros de telefono, se escribe el nombre y telefono para cada una de los elementos del array
     * 
     * @param nombres el array de nombres
     * @param telefonos el array de telefonos
     */
    public void escribirNombresYTelefonos(String[] nombres, String[] telefonos){
        
        if(nombres.length != telefonos.length) {
            
            //no tiene por qué controlarse con una excepcion
            throw new IllegalArgumentException("Los arrays de nombres y teléfonos deben tener la misma longitud");
            
        }
        
        FileOutputStream ficheroOut = null;
        PrintWriter printWriter = null;
        
        try {
            
            ficheroOut = new FileOutputStream(getRuta(),true); //con true no sobreescribimos
            printWriter = new PrintWriter(ficheroOut);
            
            for(int i=0; i < nombres.length; i++){
                
                printWriter.println(nombres[i]+": "+telefonos[i]);
                
            }
            
            printWriter.flush(); //asegura que todo el contenido que qede pendiente se vacie y se escriba
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                
                printWriter.close();
                ficheroOut.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    

}
