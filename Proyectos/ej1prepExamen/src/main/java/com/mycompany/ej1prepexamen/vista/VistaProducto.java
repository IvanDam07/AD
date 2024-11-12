/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1prepexamen.vista;

import com.mycompany.ej1prepexamen.modelo.Producto;
import java.util.Scanner;

/**
 *
 * @author Ivan Sobrino
 */
public class VistaProducto {
    private Scanner scanner = new Scanner(System.in);
    
    //Método para mostrar el menú y retornar la opción seleccionada
    public int mostrarMenu() {
        System.out.println("Gestión de Inventario:" );
        System.out.println("1. Crear Producto");
        System.out.println("2. Leer Producto");
        System.out.println("3. Salir");
        return scanner.nextInt();
    }
    
    //Método para solicitar información de un nuevo producto al usuario
    public Producto crearProducto() {
        System.out.println("Ingrese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el precio: ");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese stock: ");
        int stock = scanner.nextInt();
        return new Producto(id, nombre, precio, stock);
    }
    
    //Método para solicitar al usuario el ID del producto a buscar
    public int leerProducto() {
        System.out.println("Ingrese el ID del producto a buscar: ");
        return scanner.nextInt();
    }
}
