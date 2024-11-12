/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectopracticaexamen;

import com.mycompany.proyectopracticaexamen.controlador.ControlUniversidades;
import com.mycompany.proyectopracticaexamen.modelo.FicheroUniversidades;
import com.mycompany.proyectopracticaexamen.modelo.Universidad;
import com.mycompany.proyectopracticaexamen.vista.InterfazVista;
import com.mycompany.proyectopracticaexamen.vista.VentanaTexto;

/**
 *
 * @author Ivan Sobrino
 */
public class ProyectoPracticaExamen {

    public static void main(String[] args) {
        InterfazVista vista = new VentanaTexto();
        Universidad modeloUni = new Universidad();
        FicheroUniversidades modeloFichero = new FicheroUniversidades("");

        ControlUniversidades controlUnis = new ControlUniversidades(vista, modeloUni, modeloFichero);
        vista.arranca();
    }
}
