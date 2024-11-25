/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.ejercicio2examen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 13 nov 2024
 */
public class Modelo {

    public void insertaEjercicio2(Reforma reforma) {
        RandomAccessFile randomFile = null;        

        int posicion = reforma.getId();
        StringBuffer bufferStr = null;

        try {
            randomFile.seek(0);
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "r");
            int id = randomFile.readInt();

            while (randomFile.getFilePointer() < randomFile.length()) {
                long posicionActual = randomFile.getFilePointer();
                Reforma refor = utilidadLeerReforma(randomFile);
                if (refor.getId() == reforma.getId()) {
                    refor.setCoste(reforma.getCoste());
                } else {
                    utilidadEscribirReforma(randomFile, reforma);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Reforma utilidadLeerReforma(RandomAccessFile archivo) throws IOException {
        int id = archivo.readInt();
        String descripcion = archivo.readUTF();
        String direccion = archivo.readUTF();
        double coste = archivo.readDouble();
        return new Reforma(id, descripcion, direccion, coste);
    }

    public void utilidadEscribirReforma(RandomAccessFile archivo, Reforma reforma) throws IOException {
        archivo.writeInt(reforma.getId());
        archivo.writeUTF(reforma.getDescripcion());
        archivo.writeUTF(reforma.getDireccion());
        archivo.writeDouble(reforma.getCoste());
    }

    public void muestraEjercicio2(int id) {
        RandomAccessFile randomFile = null;
        int pos = id;
        Reforma reforma = new Reforma();

        try {
            randomFile = new RandomAccessFile("./ORIGEN/datosReformas.dat", "r");
            if (id < randomFile.length()) {
                randomFile.seek(pos);
                System.out.println("Reforma id=" + reforma.getId()
                        + "\nDescripción: " + reforma.getDescripcion()
                        + "\nCoste: " + reforma.getCoste());
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
