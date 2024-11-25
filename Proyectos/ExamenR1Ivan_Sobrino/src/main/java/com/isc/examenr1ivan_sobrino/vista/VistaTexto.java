/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.examenr1ivan_sobrino.vista;

import com.isc.examenr1ivan_sobrino.controlador.ControlModelo;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 23 oct 2024
 */
public class VistaTexto implements InterfazVista{
    
    private ControlModelo controladorModelo;
    
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public void setControlador(ControlModelo cM) {
        this.controladorModelo = cM;
    }
    
    public void arranca() {
        procesarNuevaOperacion();
    }
    
    private String leeString() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.out.println("Error en la cadena introducida.");
            return "";
        }
    }
    
    private int leerOperacion() {
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Operación no válida.");
            return 0;
        }
    }
    
    private void procesarNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leerOperacion();

        switch (operacion) {
            case 0 -> {
                System.out.println("Saliendo del programa.");
                System.exit(0);
            }
            case 1 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, CREARESTRUCTURADECARPETAS));
            }
            case 2 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, ALTADATOSCARRERA));
            }
            case 3 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, GENERARXML));
            }
            case 4 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, GENERARXLS));
            }
            case 5 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, MODIFICARCARRERA));
            }
            case 6 -> {
                controladorModelo.actionPerformed(new ActionEvent(this, operacion, GENERARPAGINAWEB));
            }
            default -> {
                procesarNuevaOperacion();
            }
        }
    }
    private void solicitaOperacion() {
        System.out.println("Elige la operación que deseas realizar:");
        System.out.println("0: Salir");
        System.out.println("1: Crear estructura de carpetas");
        System.out.println("2: Alta datos Carreras Universitarias");
        System.out.println("3: Generar archivo XXML con Carreras Universitarias");
        System.out.println("4: Generación de plantilla XLS");
        System.out.println("5: Modificar Carrera Universitaria");
        System.out.println("6: Generación de página web con Carreras Universitarias");
    }
    
    private void opcionInvalida() {
        System.out.println("Opcion Invalida");
    }
    
    public void setControlador(Object cM) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
