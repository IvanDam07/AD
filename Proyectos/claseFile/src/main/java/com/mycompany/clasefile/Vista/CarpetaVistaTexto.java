/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clasefile.Vista;

import com.mycompany.clasefile.Controlador.ControlCarpeta;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ivan Sobrino
 */
public class CarpetaVistaTexto implements InterfazVista {

    private ControlCarpeta controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void setControlador(ControlCarpeta c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public String getRuta() {
        System.out.print("Ruta a seleccionar: ");
        return leeString();
    }

    @Override
    public String getNombre() {
        System.out.print("Introduce el nombre de la carpeta: ");
        return leeString();
    }

    /**
     * Solicita una opción al usuario
     *
     * @return Devuelve un entero con la opción elegida por el usuario
     * @exception IOException Si se produce un error en la entrada/salida
     * @exception NumberFormatException Si el formato del número no es correcto
     */
    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }

    /**
     * Solicita una ruta al usuario
     *
     * @return Devuelve un string con la ruta introducida por el usuario
     * @exception IOException Si se produce un error en la entrada/salida
     */
    private String leeString() {
        String s = null;
        try {
            s = in.readLine();
            return s;
        } catch (IOException e) {
            System.out.println("Error en la cadena introducida ");
            return leeString();
        }
    }

    /**
     * Muestra el menú con las opciones
     */
    private void solicitaOperacion() {
        System.out.println("Indica la operación que quiere realizar:");
        System.out.println("1: Crear carpeta pasando la ruta completa");
        System.out.println("2: Crear carpeta pasando la ruta padre y el nombre de la carpeta");
        System.out.println("3: Crear carpeta pasando un File que representa a la carpeta padre y el nombre de la carpeta");
        System.out.println("0: salir");
    }

    /**
     * Procesa la opción elegida por el usuario
     */
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                System.out.println("Adiós.");
                System.exit(0);
            }
            case 1 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTACOMPLETA));
            }
            case 2 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTAPADREYNOMBRE));
            }
            case 3 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONFILERUTAPADREYNOMBRE));
            }
        }
    }

    /**
     * Procesa el caso de que introduzcamos una opción que no sea una de las
     * indicadas
     */
    private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }
}
