/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clasefilenio.modelo;

import java.io.File;

/**
 *
 * @author Ivan Sobrino
 */
public class ModeloDirectorios {
    private String ruta;
  
  //------------------------------------------------>
  public ModeloDirectorios (String rutaInput) {
    this.ruta = rutaInput;
  }
  
  public ModeloDirectorios() {}
  
  //------------------------------------------------>
  public File getFileFromRuta() {
    File mainDirectory = new File(this.ruta);
    return mainDirectory;
  }
  
  //------------------------------------------------>
  public String getRuta() {
    return this.ruta;
  }
  
  public void setRuta (String rutaInput) {
    this.ruta = rutaInput;
  }
}
