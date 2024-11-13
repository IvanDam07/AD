/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio3examen.Vista;

import com.isc.ejercicio3examen.Modelo.Producto;
import java.util.Scanner;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 13 nov 2024
 */
public class VistaProducto {
    private Scanner scanner = new Scanner(System.in);
    
    public int mostrarMenu() {
        System.out.println("Gestión lista de la compra");
        System.out.println("1. Crear producto.");
        System.out.println("2. Salir.");
        return scanner.nextInt();
    }
    
    public Producto crearProducto() {
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = scanner.next();
        scanner.nextLine();
        
        System.out.println("Ingresa la cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Ingresa el supermercado donde quiere comprar: ");
        String supermercado = scanner.next();
        return new Producto(nombre, cantidad, supermercado);
    }
}
