/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clasefilenio;

import com.mycompany.clasefilenio.controlador.ControladorArchivo;
import com.mycompany.clasefilenio.controlador.ControladorCarpeta;
import com.mycompany.clasefilenio.modelo.Archivo;
import com.mycompany.clasefilenio.modelo.Carpeta;
import com.mycompany.clasefilenio.vista.InterfazVista;
import com.mycompany.clasefilenio.vista.VentanaGrafica;

/**
 *
 * @author Ivan Sobrino
 */
public class ClaseFileNIO {

    public static void main(String[] args) {
        InterfazVista vista = new VentanaGrafica();
        Archivo modeloArchivo = new Archivo();
        Carpeta modeloCarpeta = new Carpeta();
        ControladorArchivo controladorArchivo = new ControladorArchivo(vista, modeloArchivo);
        ControladorCarpeta controladorCarpeta = new ControladorCarpeta(vista, modeloCarpeta);

        vista.arranca();
    }
}
