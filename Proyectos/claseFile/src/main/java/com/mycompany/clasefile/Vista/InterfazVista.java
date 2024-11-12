/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.clasefile.Vista;

import com.mycompany.clasefile.Controlador.ControlCarpeta;

/**
 *
 * @author Ivan Sobrino
 */
public interface InterfazVista {

    /**
     * Especifica el controlador que se va a encargar de procesar las acciones
     * realizadas en la vista.
     *
     * @param c Controlador que procesa las acciones
     */
    void setControlador(ControlCarpeta c);

    /**
     * Comienza la visualización de la vista
     */
    void arranca();

    /**
     * Obtiene por pantalla la ruta
     *
     * @return Ruta que ha introducido el usuario
     */
    String getRuta();

    /**
     * Obtiene por pantalla el nombre del nuevo directorio
     *
     * @return Nombre del directorio que ha introducido el usuario
     */
    String getNombre();

    /**
     * Constantes para las operaciones:
     */
    static final String CREARCARPETACONRUTACOMPLETA = "Crea carpeta recibiendo la ruta completa";
    static final String CREARCARPETACONRUTAPADREYNOMBRE = "Crea carpeta recibiendo la ruta del padre";
    static final String CREARCARPETACONFILERUTAPADREYNOMBRE = "Crea carpeta recibiendo un File";
}
