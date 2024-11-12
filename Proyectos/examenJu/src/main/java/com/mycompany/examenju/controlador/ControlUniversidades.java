/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenju.controlador;

import com.mycompany.examenju.modelo.FicheroUniversidad;
import com.mycompany.examenju.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class ControlUniversidades implements ActionListener {
 // constantes & variables ->
  private InterfazVista vista;
  private FicheroUniversidad modelo;
  
 //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 // constructor ->
  public ControlUniversidades (InterfazVista inputVista, FicheroUniversidad inputModelo) {
    this.vista = inputVista;
    this.modelo = inputModelo;
    
    this.vista.setControlador(this);
  }
  
 //——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
 // metodos publicos ->
   // metodo | actionPerformed | controlar las opciones a realizar ->
  @Override
  public void actionPerformed (ActionEvent evento) {
    switch (evento.getActionCommand()) {
      case InterfazVista.CREAR_CARPETAS -> {
        this.modelo.crearArbolDirectorios();
      }
      
      case InterfazVista.DAR_ALTA_UNIVERSIDADES -> {
        this.modelo.AltaDatosCarrerasUniversitarias();
      }
      
      case InterfazVista.GENERAR_ARCHIVO_XML -> {
        try {
          this.modelo.generarXMLFromCarreras();
        } catch (FileNotFoundException ex) {
          Logger.getLogger(ControlUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      case InterfazVista.GENERAR_PLANTILLA_XSL -> {
        this.modelo.generarPlantillaXSL();
      }
      
      case InterfazVista.MODIFICAR_CARRERA_XML -> {
        this.modelo.modificarCarreraUniversitariaXML(this.vista.getIdUni(), this.vista.leerString());
      }
      
      case InterfazVista.GENERAR_PAGINA_WEB_HTML -> {
        this.modelo.generarPaginaWeb();
      }
    }
  }
}
