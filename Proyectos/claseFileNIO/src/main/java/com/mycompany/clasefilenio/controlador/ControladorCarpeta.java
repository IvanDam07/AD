/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clasefilenio.controlador;

import com.mycompany.clasefilenio.modelo.Carpeta;
import com.mycompany.clasefilenio.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Ivan Sobrino
 */
public class ControladorCarpeta implements ActionListener {
  private final InterfazVista vista;
  private final Carpeta modelo;
  
  private String ruta;
  private String nombre;
  
  public ControladorCarpeta (InterfazVista vistaInput, Carpeta modeloInput) {
    this.vista = vistaInput;
    this.modelo = modeloInput;
    this.vista.setControladorCarpeta (this);
  }
  
  //------------------------------------------------>
  @Override
  public void actionPerformed (ActionEvent evento) {
    switch (evento.getActionCommand()) {
      case InterfazVista.CREAR_CARPETA_CON_RUTA_COMPLETA -> {
        this.modelo.setRuta(this.vista.getRuta());
        this.modelo.crearCarpeta();
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.CREAR_CARPETA_CON_RUTA_PADRE_Y_NOMBRE -> {
        this.modelo.setRuta(this.vista.getRuta());
        this.nombre = this.vista.getNombre();
        
        this.modelo.crearCarpeta(nombre);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.CREAR_CARPETA_CON_FILE_Y_NOMBRE -> {
        this.modelo.setRuta(this.vista.getRuta());
        this.nombre = this.vista.getNombre();
        
        this.modelo.crearCarpeta(modelo.getFileFromRuta(), nombre);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.OBTENER_CONTENIDO_CARPETA -> {
        File archivo = new File(this.vista.getRuta());
        ArrayList<String> informacionCarpeta = this.modelo.customDir(archivo);
        String resultadoInformacion = informacionCarpeta.toString();
        this.vista.escribeResultado(resultadoInformacion);
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.BORRAR_FICHEROS_CARPETA -> {
        File archivo = new File(this.vista.getRuta());
        this.modelo.customDelete(archivo);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.BORRAR_FICHEROS_CARPETA_RECURSIVO -> {
        File archivo = new File(this.vista.getRuta());
        this.modelo.customDeleteRecursive(archivo);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
    }
  }
}