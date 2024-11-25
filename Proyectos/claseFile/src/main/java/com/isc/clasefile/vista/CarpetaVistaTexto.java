/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.clasefile.vista;

import com.isc.clasefile.controlador.ControlArchivo;
import com.isc.clasefile.controlador.ControlCarpeta;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 18 sept 2024
 */
public class CarpetaVistaTexto implements InterfazVista{
    
    private ControlCarpeta controladorCarpeta;
    private ControlArchivo controladorArchivo;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void setControlador(ControlCarpeta cC) {
        this.controladorCarpeta = cC;
    }

    @Override
    public void setControlador(ControlArchivo cA) {
        this.controladorArchivo = cA;
    }

    @Override
    public void arranca() {
        procesarNuevaOperacion();
    }

    @Override
    public String getRuta() {
        System.out.print("Introduce la ruta: ");
        return leeString();
    }

    @Override
    public String getNombre() {
        System.out.print("Introduce el nombre del archivo: ");
        return leeString();
    }
    
    @Override
    public String getNombreActual() {
        System.out.println("Introduce el nombre actual del archivo: ");
        return leeString();
    }
    
    @Override
    public String getNuevoNombre() {
        System.out.println("Introduce el nombre nuevo del archivo: ");
        return leeString();
    }
    
    @Override
    public String getRutaOrigen() {
        System.out.println("Introduce la ruta de origen: ");
        return leeString();
    }
    
    @Override
    public String getRutaDestino() {
        System.out.println("Introduce la ruta de destino: ");
        return leeString();
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
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTACOMPLETA));
            }
            case 2 -> {
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTAPADREYNOMBRE));
            }
            case 3 -> {
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONFILEPADREYNOMBRE));
            }
            case 4 -> {
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, CREARARCHIVOENCARPETA));
            }
            case 5 -> {
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, MOSTRARCONTENIDOCARPETA));  // Comando para mostrar contenido
            }
            case 6 -> {
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, ELIMINARARCHIVOODIRECTORIO)); //Comando para eliminar archivo o directorio
            }
            case 7 -> {
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, RENOMBRARARCHIVO));
            }
            case 8 -> {
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, COPIARARCHIVO));
            }
            case 9 -> {
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, MOVERARCHIVO));
            }
            default -> {
                procesarNuevaOperacion();
            }
        }
    }

    private void solicitaOperacion() {
        System.out.println("Elige la operación que deseas realizar:");
        System.out.println("1: Crear carpeta con ruta completa");
        System.out.println("2: Crear carpeta con ruta padre y nombre");
        System.out.println("3: Crear carpeta con un File y nombre");
        System.out.println("4: Crear archivo en una carpeta");
        System.out.println("5: Mostrar contenido de un directorio");
        System.out.println("6: Eliminar archivo o directorio");
        System.out.println("7: Renombrar archivo");
        System.out.println("8: Copiar archivo");
        System.out.println("9: Mover archivo");
        System.out.println("0: Salir");
    }
    
    private void opcionInvalida() {
        System.out.println("Opcion Invalida");
    }
    
    public void setControlador(Object cA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*
    @Override
    public void setControlador(ControlArchivo cA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    */
}
