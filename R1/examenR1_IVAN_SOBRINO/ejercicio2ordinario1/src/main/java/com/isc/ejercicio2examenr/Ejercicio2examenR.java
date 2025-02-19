/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.ejercicio2examenr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio2examenR {

    private static int LONGITUD_INT = 4;
    private static int LONGITUD_CHAR = 2;

    private static int LONGITUD_ID = LONGITUD_INT;
    private static int LONGITUD_FECHA = 8 * LONGITUD_CHAR; //Fecha en String -> ddMMyyyy
    private static int LONGITUD_LUGAR = 40 * LONGITUD_CHAR;
    private static int LONGITUD_HORAS = LONGITUD_INT;

    private static int LONGITUD_TOTAL = LONGITUD_ID + LONGITUD_FECHA + LONGITUD_LUGAR + LONGITUD_HORAS;

    public static void main(String[] args) {
        Lanzamientos lanzamiento1 = new Lanzamientos(3, "01442025", "Guayana Francesa", 240);
        Lanzamientos lanzamiento2 = new Lanzamientos(6, "12042025", "Cabo Cañaveral", 360);

        insertaEjercicio2(lanzamiento1);
        insertaEjercicio2(lanzamiento2);
        
        muestraEjercicio2(3);
        
        lanzamiento1.setFecha_lanzamiento("01052025");
        
        insertaEjercicio2(lanzamiento1);
        muestraEjercicio2(3);
    }

    public static void insertaEjercicio2(Lanzamientos lanzamiento) {
        int id = lanzamiento.getId();
        String fecha = lanzamiento.getFecha_lanzamiento();
        String lugar = lanzamiento.getLugar_lanzamiento();
        int horas = lanzamiento.getHoras_vuelo_estimadas();

        RandomAccessFile randomFile = null;
        StringBuffer sb = null;
        long posicion = (id - 1) * Ejercicio2examenR.LONGITUD_TOTAL;

        try {
            randomFile = new RandomAccessFile(".LANZAMIENTOS/datosLanzamientos.dat", "rw");
            randomFile.seek(posicion);
            if (randomFile.length() != 0) {

                //Si el identificador que leo es el mismo que le paso por parámetro, ya existe
                if (id == randomFile.readInt()) {
                    //posiciono el cursor
                    posicion = posicion + Ejercicio2examenR.LONGITUD_ID;
                    randomFile.seek(posicion);
                    //Cambio la fecha
                    randomFile.writeUTF(fecha);
                }
            } else {
                randomFile.writeInt(id);

                StringBuffer bufferStr = new StringBuffer(fecha);
                bufferStr.setLength(Ejercicio2examenR.LONGITUD_FECHA);
                randomFile.writeChars(bufferStr.toString());

                bufferStr = new StringBuffer(lugar);
                bufferStr.setLength(Ejercicio2examenR.LONGITUD_LUGAR);
                randomFile.writeChars(bufferStr.toString());

                randomFile.writeInt(horas);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void muestraEjercicio2(int id) {
        RandomAccessFile randomFile = null;
        long posicion = (id - 1) * Ejercicio2examenR.LONGITUD_TOTAL;
        try {
            randomFile = new RandomAccessFile("./LANZAMIENTOS/datosLanzamientos", "r");
            randomFile.seek(posicion);

            int idLeido = randomFile.readInt();

            byte[] lugar = new byte[LONGITUD_LUGAR];
            randomFile.readFully(lugar);
            String lugarL = new String(lugar);

            byte[] horas = new byte[LONGITUD_HORAS];
            randomFile.readFully(horas);
            int horasL = randomFile.readInt();

            int horasLTotal = utilidadLecturaTodosLosLanzamientos();
            double porcentaje = (horasL * 100) / horasLTotal;

            System.out.println("Corresponde a un porcentaje del " + porcentaje + " %");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int utilidadLecturaTodosLosLanzamientos() {
        int totalHoras = 0;

        RandomAccessFile randomFile = null;
        int posicion = LONGITUD_ID + LONGITUD_FECHA + LONGITUD_LUGAR;

        try {
            randomFile = new RandomAccessFile("./LANZAMIENTOS/datosLanzamientos", "r");

            Lanzamientos lanzamiento = new Lanzamientos();

            randomFile.seek(posicion);

            totalHoras += randomFile.readInt();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2examenR.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalHoras;
    }
}
