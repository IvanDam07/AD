/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio3examen.vista;

import com.mmc.ejercicio3examen.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class VistaTexto implements InterfazVista{
    
    private Controlador controlador;
    
    private final BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

   @Override
    public void arranca() {
        procesaNuevaOperacion();
    }
    
    private void solicitaOperacion() {
        System.out.println("=============================================================");
        System.out.println("0. Salir");
        System.out.println("1. Introducir datos");
        System.out.println("2. Mostrar datos");
        System.out.println("=============================================================");
        System.out.print("Introduzca la opcion: ");
    }
    
    
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                System.out.println("Ejecucion finalizada");
                System.exit(0);
            }
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, INTRODUCIR_DATOS));
            case 2 -> controlador.actionPerformed(new ActionEvent(this, operacion, MOSTRAR_DATOS));
        }
        procesaNuevaOperacion();
    }
    
    private int leeOpcion() {
        String s = null;
        try {
            s = br.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }
    
    @Override
    public String leeString(){
        String s = null;
        try {
            System.out.print("Introduce el nombre: ");
            s=br.readLine();
            return s;
        } catch (IOException e) {
            System.out.println("Error en la cadena introducida");
            return leeString();
        }
    }
    
    public int leeInt() {
        System.out.print("Introduce la cantidad: ");
        String s = null;
        try {
            s = br.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }
    
     private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

}
