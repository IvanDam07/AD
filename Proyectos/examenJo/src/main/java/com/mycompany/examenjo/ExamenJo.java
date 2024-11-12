/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examenjo;

import com.mycompany.examenjo.controlador.ControladorExamen;
import com.mycompany.examenjo.modelo.Examen;
import com.mycompany.examenjo.vista.ExamenVistaTexto;
import com.mycompany.examenjo.vista.InterfazVista;

/**
 *
 * @author Ivan Sobrino
 */
public class ExamenJo {

    public static void main(String[] args) {
        InterfazVista vista = new ExamenVistaTexto();
        Examen modelo = new Examen();
        
        ControladorExamen controlador = new ControladorExamen(vista, modelo);
        vista.arranca();
    }
}
