/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej2prepexamen.Vista;

import com.mycompany.ej2prepexamen.Controlador.BibliotecaControlador;
import java.util.Scanner;

/**
 *
 * @author Ivan Sobrino
 */
public class BibliotecaVistaTexto {
    private BibliotecaControlador controlador;
    public Scanner scanner;
    
    /**
     * Constructor de la clase BibliotecaVistaTexto.
     * 
     * @param controlador Controlador que maneja la lógica de la aplicación.
     */
    public BibliotecaVistaTexto (BibliotecaControlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }
    
    public void MostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Buscar recurso por título");
            System.out.println("2. Buscar recurso por autor");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    buscarPorAutor();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 3);
    }
    
    private void buscarPorTitulo() {
        System.out.println("Ingrese el título del recurso: ");
        String titulo = scanner.nextLine();
        controlador.buscarRecursoPorTitulo(titulo);
    }
    
    private void buscarPorAutor() {
        System.out.println("Ingrese el autor del recurso: ");
        String autor = scanner.nextLine();
        controlador.buscarRecursoPorAutor(autor);
    }
}
