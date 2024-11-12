/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1prepexamen.controlador;

import com.mycompany.ej1prepexamen.modelo.Escritura;
import com.mycompany.ej1prepexamen.modelo.Lectura;
import com.mycompany.ej1prepexamen.modelo.Producto;
import com.mycompany.ej1prepexamen.vista.VistaProducto;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Ivan Sobrino
 */
public class ControladorProducto {
    private VistaProducto vista;
    private RandomAccessFile archivo;
    
    public ControladorProducto(VistaProducto vista, RandomAccessFile archivo) {
        this.vista = vista;
        this.archivo = archivo;
    }
    
    public void iniciar() throws IOException {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            manejarOpcion(opcion);
        } while (opcion != 3);
    }
    
    /**
     * Maneja la opción seleccionada por el usuario.
     *
     * @param opcion La opción seleccionada en el menú.
     */
    private void manejarOpcion(int opcion) throws IOException {
        switch (opcion) {
            case 1:
                Producto producto = vista.crearProducto();
                if (Producto.validarProducto(producto)) {
                    Escritura.escribirProducto(archivo, producto);
                }
                break;
            case 2:
                int id = vista.leerProducto();
                Producto productoLeer = Lectura.buscarProductoPorId(archivo, id);
                if (productoLeer != null) {
                    System.out.println("Producto encontrado: " + productoLeer);
                } else {
                    System.out.println("Producto no encontrado.");
                }
                break;
            case 3:
                System.out.println("Saliendo del sistema.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
