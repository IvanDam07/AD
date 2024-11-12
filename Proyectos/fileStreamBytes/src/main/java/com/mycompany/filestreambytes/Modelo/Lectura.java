/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreambytes.Modelo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Lectura extends Fichero{

    public Lectura(String ruta) {
        super(ruta);
    }
   
    /**
     * Lee el contenido de un archivo en el que se han almacenado datos simples
     * 
     * @return Cadena con el contenido del archivo
     */
    public String lecturaDatosSimples(){
        
        FileInputStream ficheroIn = null;
        DataInputStream datosIn = null;
        StringBuilder texto = new StringBuilder();
        
        try {
            
            ficheroIn = new FileInputStream(getRuta());
            datosIn = new DataInputStream(ficheroIn);

            texto.append(datosIn.readByte());
            texto.append(" ");
            texto.append(datosIn.readShort());
            texto.append(" ");
            texto.append(datosIn.readInt());
            texto.append(" ");
            texto.append(datosIn.readLong());
            texto.append(" ");
            texto.append(datosIn.readFloat());
            texto.append(" ");
            texto.append(datosIn.readDouble());
            texto.append(" ");
            texto.append(datosIn.readBoolean());
            texto.append(" ");
            texto.append(datosIn.readChar());
            texto.append(" ");
            texto.append(datosIn.readUTF());
            
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosIn.close();
                ficheroIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return texto.toString();
    }  

    /**
     * Lee todos los objetos contenidos en un archivo
     * 
     * @return Un ArrayList con todos los objetos leídos de un archivo
     * @throws ClassNotFoundException 
     */
    public ArrayList <Object> lecturaObjetos() throws ClassNotFoundException{
        FileInputStream ficheroIn = null;
        ArrayList <Object> retornoList = new ArrayList();
        Object retorno = null;
        ObjectInputStream datosIn = null;
                
        try {        
            ficheroIn = new FileInputStream(getRuta());
            datosIn = new ObjectInputStream(ficheroIn);
             
            while (ficheroIn.available()>0){
                retorno = datosIn.readObject();
                retornoList.add(retorno);
            }         
        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                datosIn.close();
                ficheroIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retornoList;
    }
}