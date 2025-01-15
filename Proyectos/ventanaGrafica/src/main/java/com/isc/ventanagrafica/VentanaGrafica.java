/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.ventanagrafica;

import com.isc.ventanagrafica.vista.VentanaLogin;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class VentanaGrafica {

    public static void main(String[] args) {
        // Registrar un usuario inicial para pruebas
        Prueba.agregarUsuario(new Usuario("carla", "123abc"));

        // Crear y mostrar la ventana de login
        java.awt.EventQueue.invokeLater(() -> {
            new Prueba().setVisible(true);
        });
    }
}
