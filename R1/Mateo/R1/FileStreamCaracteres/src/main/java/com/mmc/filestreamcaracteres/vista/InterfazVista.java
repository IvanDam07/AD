/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mmc.filestreamcaracteres.vista;

import com.mmc.filestreamcaracteres.controlador.ControladorFileStream;

/**
 * @author MMC by Mateo Molina Campos
 */
public interface InterfazVista {
    
  //Constantes de lectura
  static final String LEER_CARACTER_A_CARACTER = "leer un fichero caracter a caracter";
  static final String LEER_FICHERO_ARRAY_CARACTERES = "leer un fichero a partir de un array de caracteres";
  static final String LEER_FICHERO_LINEA = "leer un fichero linea a linea";
  
  //Constantes de escritura
  static final String ESCRIBIR_CARACTER = "escribir un caracter en un fichero";
  static final String ESCRIBIR_ARRAY_CARACTERES = "escribir una cadena de caracteres en un fichero";
  static final String ESCRIBIR_LINEA = "escribir una linea en un fichero";
  
  
  void setControladorFileStreams(ControladorFileStream cFS);
  void arranca();
  void operacionTerminada();
  void escribeResultado (String outputResult);
  
  boolean sobreescribirFichero ();
  char leerChar();
  String leerString();
  char[] leerCaracteres();
  
  String getRuta();
    
}
