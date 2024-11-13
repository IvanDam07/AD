/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio3examen.Controlador;

import com.isc.ejercicio3examen.Modelo.Producto;
import com.isc.ejercicio3examen.Vista.VistaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 13 nov 2024
 */
//public class ControladorProducto implements ActionListener{
//    private VistaProducto vista;
//    private RandomAccessFile archivo;
//    
//    public ControladorProducto(VistaProducto vista, RandomAccessFile archivo) {
//        this.vista = vista;
//        this.archivo = archivo;
//    }
//    
//    public void iniciar() throws IOException {
//        int opcion;
//        do {
//            opcion = vista.mostrarMenu();
//            manejarOpcion(opcion);
//        } while (opcion != 2);
//    }
//    
//    public void manejarOpcion(int opcion) throws IOException {
//        switch (opcion) {
//            case 1: 
//                Producto producto = vista.crearProducto();
//                if (Producto.validarProducto(producto)) {
//                    
//                }
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        switch (e.getActionCommand()) {
//            case VistaProducto.CREAR_PRODUCTO -> {
//                
//            }
//        }
//    }
//}
