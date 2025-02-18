package com.mmc.clasefile.vista;

import com.mmc.clasefile.controlador.ControlArchivo;
import com.mmc.clasefile.controlador.ControlCarpeta;

/**
 * Interfaz con los métodos que deben implementar las vistas que se generen para la aplicación
 * 
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public interface InterfazVista {
    
    void setControladorCarpeta(ControlCarpeta c);
    void setControladorArchivo(ControlArchivo c);
    
    void arranca();
    
    String getRuta();
    
    String getNombre();
    
    static final String CREARCARPETACONRUTACOMPLETA = "Crea carpeta recibiendo la ruta completa";
    static final String CREARCARPETACONRUTAPADREYNOMBRE = "Crea carpeta recibiendo la ruta del padre";
    static final String CREARCARPETACONFILEPADREYNOMBRE = "Crea carpeta recibiendo un File y el nombre";
    static final String CREARARCHIVO = "Crea un archivo en la ruta";
    static final String MOSTRARCONTENIDO = "Mostrar el contenido de la carpeta";
    static final String ELIMINAR = "Elimina un archivo o carpeta";
    static final String RENOMBRAR = "Renombrar un archivo";
}
