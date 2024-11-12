/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej2prepexamen.Controlador;

import com.mycompany.ej2prepexamen.Modelo.GestorArchivo;
import com.mycompany.ej2prepexamen.Modelo.Libro;
import com.mycompany.ej2prepexamen.Modelo.RecursoDigital;
import com.mycompany.ej2prepexamen.Modelo.Revista;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ivan Sobrino
 */
public class BibliotecaControlador {

    private GestorArchivo gestorArchivo;

    public BibliotecaControlador() {
        this.gestorArchivo = new GestorArchivo();
    }

    /**
     * Busca un recurso por su título y muestra el resultado.
     *
     * @param titulo Título del recurso a buscar.
     */
    public void buscarRecursoPorTitulo(String titulo) {
        List<Libro> librosEncontrados = new ArrayList<>();
        List<Revista> revistasEncontradas = new ArrayList<>();
        List<RecursoDigital> recursosDigitalesEncontrados = new ArrayList<>();

        try {
            //Buscar en archivo de libros
            RandomAccessFile archivoLibros = new RandomAccessFile("libros.dat", "r");
            librosEncontrados = gestorArchivo.buscarLibrosPorTitulo(archivoLibros, titulo);
            archivoLibros.close();

            //Buscar en archivo de revistas
            RandomAccessFile archivoRevistas = new RandomAccessFile("revistas.dat", "r");
            revistasEncontradas = gestorArchivo.buscarRevistasPorTitulo(archivoRevistas, titulo);
            archivoRevistas.close();

            //Buscar en archivo de recursos digitales
            RandomAccessFile archivoRecursos = new RandomAccessFile("recursosDigitales.dat", "r");
            recursosDigitalesEncontrados = gestorArchivo.buscarRecursosDigitalesPorTitulo(archivoRecursos, titulo);
            archivoRecursos.close();

            //Mostrar resultados
            System.out.println("Resultados de la búsqueda por título: " + titulo);
            for (Libro libro : librosEncontrados) {
                System.out.println("Libro: " + libro);
            }
            for (Revista revista : revistasEncontradas) {
                System.out.println("Revista: " + revista);
            }
            for (RecursoDigital recurso : recursosDigitalesEncontrados) {
                System.out.println("Recurso digital: " + recurso);
            }
        } catch (IOException e) {
            System.err.println("Error al buscar el recurso por título: " + e.getMessage());
        }
    }

    /**
     * Busca un recurso por su autor y muestra el resultado.
     *
     * @param autor Autor del recurso a buscar.
     */
    public void buscarRecursoPorAutor(String autor) {
        List<Libro> librosEncontrados = new ArrayList<>();
        List<Revista> revistasEncontradas = new ArrayList<>();
        List<RecursoDigital> recursosDigitalesEncontrados = new ArrayList<>();

        try {
            // Buscar en archivo de libros
            RandomAccessFile archivoLibros = new RandomAccessFile("libros.dat", "r");
            librosEncontrados = gestorArchivo.buscarLibrosPorAutor(archivoLibros, autor);
            archivoLibros.close();

            // Buscar en archivo de revistas
            RandomAccessFile archivoRevistas = new RandomAccessFile("revistas.dat", "r");
            revistasEncontradas = gestorArchivo.buscarRevistasPorAutor(archivoRevistas, autor);
            archivoRevistas.close();

            // Buscar en archivo de recursos digitales
            RandomAccessFile archivoRecursos = new RandomAccessFile("recursosDigitales.dat", "r");
            recursosDigitalesEncontrados = gestorArchivo.buscarRecursosDigitalesPorAutor(archivoRecursos, autor);
            archivoRecursos.close();

            // Mostrar resultados
            System.out.println("Resultados de la búsqueda por autor: " + autor);
            for (Libro libro : librosEncontrados) {
                System.out.println("Libro: " + libro);
            }
            for (Revista revista : revistasEncontradas) {
                System.out.println("Revista: " + revista);
            }
            for (RecursoDigital recurso : recursosDigitalesEncontrados) {
                System.out.println("Recurso Digital: " + recurso);
            }
        } catch (IOException e) {
            System.err.println("Error al buscar el recurso por autor: " + e.getMessage());
        }
    }

    /**
     * Valida si una cadena de texto no está vacía.
     *
     * @param input Cadena de texto a validar.
     * @return true si la cadena no está vacía, false en caso contrario.
     */
    public boolean validarEntrada(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Calcula la fecha de vencimiento de un préstamo a partir de la fecha
     * actual.
     *
     * @param diasPrestamo Número de días del préstamo.
     * @return Fecha de vencimiento.
     */
    public LocalDate calcularFechaVencimiento(int diasPrestamo) {
        return LocalDate.now().plus(diasPrestamo, ChronoUnit.DAYS);
    }

    /**
     * Registra un préstamo en el sistema.
     *
     * @param recurso Título del recurso a prestar.
     * @param usuario Usuario que realiza el préstamo.
     * @param diasPrestamo Días que durará el préstamo.
     */
//    public void registrarPrestamo(String recurso, String usuario, int diasPrestamo) {
//        LocalDate fechaVencimiento = calcularFechaVencimiento(diasPrestamo);
//        System.out.println("Préstamo registrado para " + usuario + " con vencimiento el " + fechaVencimiento);
//    }
    /**
     * Calcula la multa por retraso en la devolución del recurso.
     *
     * @param diasRetraso Días de retraso.
     * @param tasaMulta Monto de la multa por día de retraso.
     * @return Monto total de la multa.
     */
    public double calcularMulta(int diasRetraso, double tasaMulta) {
        return diasRetraso * tasaMulta;
    }

    private Map<String, LocalDate> prestamosActivos = new HashMap<>();

    /**
     * Registra un préstamo en el sistema.
     *
     * @param titulo Título del recurso a prestar.
     * @param usuario Usuario que realiza el préstamo.
     * @param diasPrestamo Días que durará el préstamo.
     */
    public void registrarPrestamo(String titulo, String usuario, int diasPrestamo) {
        LocalDate fechaVencimiento = LocalDate.now().plusDays(diasPrestamo);
        prestamosActivos.put(titulo, fechaVencimiento);
        System.out.println("Préstamo registrado para " + usuario + " con vencimiento el " + fechaVencimiento);
    }

    /**
     * Calcula la multa por retraso en la devolución del recurso.
     *
     * @param titulo Título del recurso.
     * @param tasaMulta Monto de la multa por día de retraso.
     * @return Monto total de la multa.
     */
    public double calcularMulta(String titulo, double tasaMulta) {
        if (!prestamosActivos.containsKey(titulo)) {
            System.out.println("No hay registro de préstamo para el recurso.");
            return 0;
        }
        LocalDate fechaVencimiento = prestamosActivos.get(titulo);
        long diasRetraso = ChronoUnit.DAYS.between(fechaVencimiento, LocalDate.now());
        if (diasRetraso > 0) {
            return diasRetraso * tasaMulta;
        }
        return 0;
    }
}
