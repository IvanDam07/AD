/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.examenju;

import com.mycompany.examenju.controlador.ControlUniversidades;
import com.mycompany.examenju.modelo.FicheroUniversidad;
import com.mycompany.examenju.vista.InterfazVista;
import com.mycompany.examenju.vista.VentanaTexto;

/**
 *
 * @author Ivan Sobrino
 */
public class ExamenJu {

    public static void main(String[] args) {
        InterfazVista vista = new VentanaTexto();
        FicheroUniversidad modelo = new FicheroUniversidad();

        ControlUniversidades controlador = new ControlUniversidades(vista, modelo);
        vista.arranca();
    }
}
