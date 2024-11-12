/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ej2prepexamen;

import com.mycompany.ej2prepexamen.Controlador.BibliotecaControlador;
import com.mycompany.ej2prepexamen.Modelo.Libro;
import com.mycompany.ej2prepexamen.Modelo.RecursoDigital;
import com.mycompany.ej2prepexamen.Modelo.Revista;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan Sobrino
 */
public class Ej2prepExamen {

    public static void main(String[] args) {
        // Crear una instancia del controlador de la biblioteca
        BibliotecaControlador controlador = new BibliotecaControlador();

        // 1. Añadir algunos recursos (libros, revistas, recursos digitales) al sistema
        System.out.println("Agregando recursos a la biblioteca...");
        Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, "Novela", "Santillana", 450);
        Revista revista1 = new Revista("National Geographic", "John Smith", 2023, 4, "Septiembre");
        RecursoDigital recurso1 = new RecursoDigital("Curso de Java", "Jane Doe", 2022, "PDF", 15.65);

        controlador.agregarLibro(libro1);
        controlador.agregarRecurso(revista1);
        controlador.agregarRecurso(recurso1);
        System.out.println("Recursos agregados con éxito.\n");

        // 2. Buscar un recurso por título
        System.out.println("Buscando recursos por título...");
        controlador.buscarRecursoPorTitulo("Cien Años de Soledad");
        controlador.buscarRecursoPorTitulo("National Geographic");
        controlador.buscarRecursoPorTitulo("Curso de Java");
        System.out.println("Búsqueda por título completada.\n");

        // 3. Buscar un recurso por autor
        System.out.println("Buscando recursos por autor...");
        controlador.buscarRecursoPorAutor("Gabriel García Márquez");
        controlador.buscarRecursoPorAutor("Jane Doe");
        System.out.println("Búsqueda por autor completada.\n");

        // 4. Registrar un préstamo
        System.out.println("Registrando un préstamo...");
        String idPrestamo = controlador.registrarPrestamo("Cien Años de Soledad", "Juan Pérez");
        System.out.println("Préstamo registrado con ID: " + idPrestamo + "\n");

        // 5. Calcular multa por devolución tardía
        System.out.println("Calculando multa por devolución tardía...");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date fechaDevolucion = sdf.parse("25-10-2024"); // Fecha de devolución simulada
            double multa = controlador.calcularMulta(idPrestamo, fechaDevolucion);
            System.out.println("Multa calculada: " + multa + " euros\n");
        } catch (Exception e) {
            System.err.println("Error al calcular la multa: " + e.getMessage());
        }

        // 6. Exportar los datos de la biblioteca a XML
        System.out.println("Exportando datos a formato XML...");
        controlador.exportarLibrosXML("biblioteca.xml");
        System.out.println("Datos exportados a biblioteca.xml con éxito.\n");

        // 7. Importar datos desde un archivo XML
        System.out.println("Importando datos desde un archivo XML...");
        controlador.importarDatosXML("biblioteca.xml");
        System.out.println("Datos importados con éxito.\n");

        // 8. Exportar los datos de la biblioteca a JSON
        System.out.println("Exportando datos a formato JSON...");
        controlador.exportarDatosJSON("biblioteca.json");
        System.out.println("Datos exportados a biblioteca.json con éxito.\n");

        // 9. Importar datos desde un archivo JSON
        System.out.println("Importando datos desde un archivo JSON...");
        controlador.importarDatosJSON("biblioteca.json");
        System.out.println("Datos importados con éxito.\n");

        // 10. Generar un reporte de los recursos más prestados
        System.out.println("Generando reporte de los recursos más prestados...");
        controlador.generarReporteRecursosMasPrestados();
        System.out.println("Reporte generado con éxito.\n");
    }
}
