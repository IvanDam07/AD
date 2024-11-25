/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.isc.clasefile.vista;

import com.isc.clasefile.controlador.ControlArchivo;
import com.isc.clasefile.controlador.ControlCarpeta;

/**
 * Interfaz con los métodos que deben implementar las vistas que se generen
 * para la aplicación
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 18 sept 2024
 */
public interface InterfazVista {
    
    void setControlador(ControlCarpeta cC);
    void setControlador(ControlArchivo cA);
    void arranca();
    
    String getRuta();
    String getNombre();
    
    String getNombreActual();
    String getNuevoNombre();
    
    String getRutaOrigen();
    String getRutaDestino();
    
    String getRutaDirectorio();
    
    static final String CREARCARPETACONRUTACOMPLETA = "Crea carpeta recibiendo "
            + "la ruta completa";
    static final String CREARCARPETACONRUTAPADREYNOMBRE = "Crea carpeta "
            + "recibiendo la ruta padre";
    static final String CREARCARPETACONFILEPADREYNOMBRE = "Crea carpeta "
            + "recibiendo un File y el nombre";
    
    //ARCHIVO
    static final String CREARARCHIVOENCARPETA = "Crea archivo en la carpeta"
            + " que se indica";
    
    // Nuevo comando para mostrar el contenido de una carpeta
    static final String MOSTRARCONTENIDOCARPETA = "Mostrar contenido de un directorio";
    
    //Nuevo comando para eliminar el archivo o directorio
    static final String ELIMINARARCHIVOODIRECTORIO = "Eliminar archivo o directorio";
    
    //Nuevo comando para renombrar archivo
    static final String RENOMBRARARCHIVO = "Renombrar archivo";
    
    //Nueva constante para copiar un archivo
    static final String COPIARARCHIVO = "Copiar archivo de una ruta a otra";
    
    //Nueva constante para mover un archivo
    static final String MOVERARCHIVO = "Mover archivo de una ruta a otra";
    
    static final String LISTARCONTENIDO = "Listar contenido de un directorio";
}
