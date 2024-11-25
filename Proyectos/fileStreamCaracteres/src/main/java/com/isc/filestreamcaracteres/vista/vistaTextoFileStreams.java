/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreamcaracteres.vista;

import com.isc.filestreamcaracteres.controlador.controlFileStreams;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public class vistaTextoFileStreams implements interfazVista{
    private controlFileStreams controlador;
    private Scanner scanner;

    public vistaTextoFileStreams(controlFileStreams controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mostrarMenu() throws IOException {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione una operación:");
            System.out.println("1. Leer archivo completo");
            System.out.println("2. Leer archivo por array de caracteres");
            System.out.println("3. Leer archivo con BufferedReader");
            System.out.println("4. Escribir un carácter en el archivo");
            System.out.println("5. Escribir un array de caracteres en el archivo");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    leerArchivo();
                    break;

                case 2:
                    leerArrayCaracteres();
                    break;

                case 3:
                    leerBuffered();
                    break;

                case 4:
                    escribirCaracter();
                    break;

                case 5:
                    escribirArrayCaracteres();
                    break;

                case 6:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        System.out.println("Programa finalizado.");
    }

    @Override
    public void leerArchivo() throws IOException {
        controlador.leerArchivo();
    }

    @Override
    public void leerArrayCaracteres() throws IOException {
        controlador.leerArrayCaracteres();
    }

    @Override
    public void leerBuffered() throws IOException {
        controlador.leerBuffered();
    }

    @Override
    public void escribirCaracter() throws IOException {
        System.out.println("Ingrese el carácter que desea escribir:");
        char caracter = scanner.nextLine().charAt(0);
        System.out.println("¿Desea sobreescribir el archivo? (true/false)");
        boolean sobreescribe = scanner.nextBoolean();
        controlador.escribirCaracter(caracter, sobreescribe);
    }

    @Override
    public void escribirArrayCaracteres() throws IOException {
        System.out.println("Ingrese el texto que desea escribir:");
        String texto = scanner.nextLine();
        char[] caracteres = texto.toCharArray();
        System.out.println("¿Desea sobreescribir el archivo? (true/false)");
        boolean sobreescribe = scanner.nextBoolean();
        controlador.escribirArrayCaracteres(caracteres, sobreescribe);
    }
}
