/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreamcaracteres.Vista;

import com.mycompany.filestreamcaracteres.Controlador.ControladorFileStream;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class VentanaTexto implements InterfazVista {
    
  private final BufferedReader consoleReader = new BufferedReader (new InputStreamReader (System.in));
  private ControladorFileStream controladorFileStream;
  private String path;
  
  
  public VentanaTexto () {
    super();
  }
  
  
  private int leerOpcion () {
    try {
        
      String tempInput = consoleReader.readLine();
      return Integer.parseInt(tempInput);
      
    } catch (IOException | NumberFormatException e) {
      opcionInvalida();
      return 0;
    }
  }
  
  
  private void mostrarMenuGeneral () {
    System.out.println("\n\n");
    
    System.out.println("Indica la operacion que quieres realizar: ");
    System.out.println(" 1. leer un fichero.");
    System.out.println(" 2. escribir un fichero.");
  }
  
  
  private void mostrarMenuLeer () {
    System.out.println("Indica como quieres leer el fichero: ");
    System.out.println(" 1. leer fichero (caracter a caracter).");
    System.out.println(" 2. leer fichero (cadena de caracteres).");
    System.out.println(" 3. leer fichero (linea a linea).");
  }
  
  
  private void mostrarMenuEscribir () {
    System.out.println("Indica que quieres escribir en el fichero: ");
    System.out.println(" 1. escribir un caracter.");
    System.out.println(" 2. escribir un conjunto de caracteres.");
    System.out.println(" 3. escribir una linea de texto.");
  }
  
  
  private void procesarNuevaOperacion () {
    mostrarMenuGeneral();
    int opcionGeneral = leerOpcion();
    
    switch (opcionGeneral) {
      case 0 -> {
        System.out.println("\n");
        System.exit(0);
      }
      
      case 1 -> {
        mostrarMenuLeer();
        int opcionLeer = leerOpcion();
        
        switch (opcionLeer) {
          case 0 -> {
            System.out.println("\n");
            System.exit(0);
          }
          
          case 1 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionLeer, LEER_CARACTER_A_CARACTER));
          }
          
          case 2 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionLeer, LEER_FICHERO_ARRAY_CARACTERES));
          }
          
          case 3 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionLeer, LEER_FICHERO_LINEA));
          }
        }
      }
      
      case 2 -> {
        mostrarMenuEscribir();
        int opcionEscribir = leerOpcion();
        
        switch (opcionEscribir) {
          case 0 -> {
            System.out.println("\n");
            System.exit(0);
          }
          
          case 1 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionEscribir, ESCRIBIR_CARACTER));
          }
          
          case 2 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionEscribir, ESCRIBIR_ARRAY_CARACTERES));
          }
          
          case 3 -> {
            this.controladorFileStream.actionPerformed(new ActionEvent(this, opcionEscribir, ESCRIBIR_LINEA));
          }
        }
      }
    }
    
    procesarNuevaOperacion();
  }
  
  
  private void opcionInvalida () {
    System.out.println("ERROR! Opcion Invalida.");
  }
  
  
  

    @Override
    public void setControladorFileStreams(ControladorFileStream cFS) {
        this.controladorFileStream = cFS;
    }

    @Override
    public void arranca() {
        procesarNuevaOperacion();
    }

    @Override
    public void operacionTerminada() {
        System.out.println(" > Operacion realizada con exito.");
    }

    @Override
    public void escribeResultado(String outputResult) {
        System.out.println("\n" + outputResult + "\n");
    }

    @Override
    public boolean sobreescribirFichero() {

        System.out.print("  > Quieres sobreescribir el fichero: ");
        String opcion = leerString().toLowerCase();
        boolean noSobreescribir = true;

        switch (opcion) {
          case "si" -> {
            noSobreescribir = false;
          }

          case "no" -> {
            noSobreescribir = true;
          }

          default -> {
            noSobreescribir = true;
          }
        }

        return noSobreescribir;
        
    }

    @Override
    public char leerChar() {
        try {
      System.out.println("  > Introduce el caracter: ");
      String inputTemp = consoleReader.readLine();
      
      if (inputTemp != null && !inputTemp.isEmpty()) {
        return inputTemp.charAt(0); // Retornar el primer carácter
      } else {
        throw new IllegalArgumentException("No se ingresó ningún carácter."); // Manejo de caso vacío
      }
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
    }

    @Override
    public String leerString() {
        try {
      return consoleReader.readLine();
    } catch (IOException e) {
      System.out.println("ERROR! Formato introducido invalido.");
      return null;
    }
    }

    @Override
    public String getRuta() {
        System.out.print(" > Introduce la ruta: ");
        return leerString();
    }
    
    @Override
    public char[] leerCaracteres() {
        
        String input = "";
      try {
          
          input = consoleReader.readLine();
          
          
      } catch (IOException ex) {
          
          Logger.getLogger(VentanaTexto.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        char[] caracteres = input.toCharArray();
        
        return caracteres;
        
    }
}