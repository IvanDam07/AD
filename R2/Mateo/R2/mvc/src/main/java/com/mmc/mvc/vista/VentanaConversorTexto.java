/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mmc.mvc.vista;

import com.mmc.mvc.controlador.ControlConversor;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0 Created on 13 sept 2024
 *
 */
public class VentanaConversorTexto implements InterfazVista {

    private ControlConversor controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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
            operaciónIncorrecta();
            return 0;
        }
    }

    /**
     * Solicita una cantidad al usuario
     *
     * @return Devuelve un double con la cantidad introducida por el usuario
     * @exception IOException Si se produce un error en la entrada/salida
     * @exception NumberFormatException Si el formato del número no es correcto
     */
    private double leeCantidad() {
        String s = null;
        try {
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en formato del número, tiene que ser 99.99: ");
            return leeCantidad();
        }
    }
    
    /**
     * Solicita una comision al usuario
     *
     * @return Devuelve un int con la comision que el usuario ha introducido
     * @exception IOException Si se produce un error en la entrada/salida
     * @exception NumberFormatException Si el formato del número no es correcto
     */
    private double leeComision() {
        String s = null;
        try {
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en formato del número ");
            return leeComision();
        }
    }

    /**
     * Muestra el menú con las opciones
     */
    private void solicitaOperacion() {
        System.out.println("Indica la operación que quiere realizar:");
        System.out.println("1: convertir pesetas a euros");
        System.out.println("2: convertir euros a pesetas");
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
            case 1 ->
                controlador.actionPerformed(new ActionEvent(this, operacion, AEUROS));
            case 2 ->
                controlador.actionPerformed(new ActionEvent(this, operacion, APESETAS));
            default ->
                operacionIncorrecta();
        }
        procesaNuevaOperacion();
    }

    /**
     * Procesa el caso de que introduzcamos una opción que no sea una de las
     * indicadas
     */
    private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }

    @Override
    public void setControlador(ControlConversor c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.print("Cantidad a convertir (formato 99.99):");
        return leeCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        System.out.println();
        
        procesaNuevaOperacion();
    }

    private void operaciónIncorrecta() {
        System.out.println("Operación incorrecta");
    }

    @Override
    public double getComision() {
        System.out.print("Dame la comision en porcentaje (de 1 a 100): ");
        return leeComision();
        }

}
