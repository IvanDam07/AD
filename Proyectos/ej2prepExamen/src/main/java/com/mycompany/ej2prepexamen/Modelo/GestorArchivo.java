/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej2prepexamen.Modelo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Sobrino
 */
public class GestorArchivo {

    /**
     * Lee un libro del archivo de acceso aleatorio.
     *
     * @param archivo Archivo de acceso aleatorio donde se encuentra el libro.
     * @return Libro leído del archivo.
     * @throws IOException Si ocurre un error de E/S.
     */
    public Libro leerLibro(RandomAccessFile archivo) throws IOException {
        String titulo = archivo.readUTF();
        String autor = archivo.readUTF();
        int anioPublicacion = archivo.readInt();
        String ISBN = archivo.readUTF();
        String editorial = archivo.readUTF();
        int numPaginas = archivo.readInt();
        return new Libro(titulo, autor, anioPublicacion, ISBN, editorial, numPaginas);
    }

    /**
     * Lee una revista del archivo de acceso aleatorio.
     *
     * @param archivo Archivo de acceso aleatorio donde se encuentra la revista.
     * @return Revista leída del archivo.
     * @throws IOException Si ocurre un error de E/S.
     */
    public Revista leerRevista(RandomAccessFile archivo) throws IOException {
        String titulo = archivo.readUTF();
        String autor = archivo.readUTF();
        int anioPublicacion = archivo.readInt();
        int numEdicion = archivo.readInt();
        String mesPublicacion = archivo.readUTF();
        return new Revista(titulo, autor, anioPublicacion, numEdicion, mesPublicacion);
    }

    /**
     * Lee un recurso digital del archivo de acceso aleatorio.
     *
     * @param archivo Archivo de acceso aleatorio donde se encuentra el recurso
     * digital.
     * @return RecursoDigital leído del archivo.
     * @throws IOException Si ocurre un error de E/S.
     */
    public RecursoDigital leerRecursoDigital(RandomAccessFile archivo) throws IOException {
        String titulo = archivo.readUTF();
        String autor = archivo.readUTF();
        int anioPublicacion = archivo.readInt();
        String formato = archivo.readUTF();
        double tamanioMB = archivo.readDouble();
        return new RecursoDigital(titulo, autor, anioPublicacion, formato, tamanioMB);
    }

    /**
     * Busca libros en el archivo por su título.
     *
     * @param archivo Archivo de acceso aleatorio.
     * @param titulo Título del libro a buscar.
     * @return Lista de libros con el título especificado.
     * @throws IOException Si ocurre un error de E/S.
     */
    public List<Libro> buscarLibrosPorTitulo(RandomAccessFile archivo, String titulo) throws IOException {
        List<Libro> librosEncontrados = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Libro libro = leerLibro(archivo);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                librosEncontrados.add(libro);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return librosEncontrados;
    }

    /**
     * Busca revistas en el archivo por su título.
     *
     * @param archivo Archivo de acceso aleatorio.
     * @param titulo Título de la revista a buscar.
     * @return Lista de revistas con el título especificado.
     * @throws IOException Si ocurre un error de E/S.
     */
    public List<Revista> buscarRevistasPorTitulo(RandomAccessFile archivo, String titulo) throws IOException {
        List<Revista> revistasEncontradas = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Revista revista = leerRevista(archivo);
            if (revista.getTitulo().equalsIgnoreCase(titulo)) {
                revistasEncontradas.add(revista);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return revistasEncontradas;
    }

    public List<RecursoDigital> buscarRecursosDigitalesPorTitulo(RandomAccessFile archivo, String titulo) throws IOException {
        List<RecursoDigital> recursosEncontrados = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            RecursoDigital recurso = leerRecursoDigital(archivo);
            if (recurso.getTitulo().equalsIgnoreCase(titulo)) {
                recursosEncontrados.add(recurso);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return recursosEncontrados;
    }

    public List<Libro> buscarLibrosPorAutor(RandomAccessFile archivo, String autor) throws IOException {
        List<Libro> librosEncontrados = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Libro libro = leerLibro(archivo);
            if (libro.getTitulo().equalsIgnoreCase(autor)) {
                librosEncontrados.add(libro);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return librosEncontrados;
    }

    public List<Revista> buscarRevistasPorAutor(RandomAccessFile archivo, String autor) throws IOException {
        List<Revista> revistasEncontradas = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Revista revista = leerRevista(archivo);
            if (revista.getTitulo().equalsIgnoreCase(autor)) {
                revistasEncontradas.add(revista);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return revistasEncontradas;
    }

    public List<RecursoDigital> buscarRecursosDigitalesPorAutor(RandomAccessFile archivo, String autor) throws IOException {
        List<RecursoDigital> recursosEncontrados = new ArrayList<>();
        archivo.seek(0);

        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            RecursoDigital recurso = leerRecursoDigital(archivo);
            if (recurso.getTitulo().equalsIgnoreCase(autor)) {
                recursosEncontrados.add(recurso);
            }
            archivo.seek(posicionActual + 1024); //Avanzar al siguiente registro
        }
        return recursosEncontrados;
    }

    /**
     * Escribe un libro en el archivo de acceso aleatorio.
     *
     * @param archivo Archivo de acceso aleatorio.
     * @param libro Libro a escribir.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void escribirLibro(RandomAccessFile archivo, Libro libro) throws IOException {
        archivo.writeUTF(libro.getTitulo());
        archivo.writeUTF(libro.getAutor());
        archivo.writeInt(libro.getAnioPublicacion());
        archivo.writeUTF(libro.getISBN());
        archivo.writeUTF(libro.getEditorial());
        archivo.writeInt(libro.getNumPaginas());
    }

    /**
     * Actualiza un libro en una posición específica del archivo.
     *
     * @param archivo Archivo de acceso aleatorio.
     * @param posicion Posición en el archivo donde actualizar.
     * @param libro Libro con los datos actualizados.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void actualizarLibro(RandomAccessFile archivo, long posicion, Libro libro) throws IOException {
        archivo.seek(posicion);
        escribirLibro(archivo, libro);
    }

    /**
     * Elimina un libro en una posición específica del archivo, marcándolo como
     * borrado.
     *
     * @param archivo Archivo de acceso aleatorio.
     * @param posicion Posición en el archivo donde se encuentra el libro.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void eliminarLibro(RandomAccessFile archivo, long posicion) throws IOException {
        archivo.seek(posicion);
        archivo.writeUTF(""); // Borrar título para marcarlo como eliminado
    }
    
    //Lo mismo para revista y RecursoDigital
    
    
    /**
 * Busca libros por año de publicación en el archivo.
 * 
 * @param archivo Archivo de acceso aleatorio.
 * @param año Año de publicación a buscar.
 * @return Lista de libros publicados en el año especificado.
 * @throws IOException Si ocurre un error de E/S.
 */
public List<Libro> buscarLibrosPorAño(RandomAccessFile archivo, int año) throws IOException {
    List<Libro> librosEncontrados = new ArrayList<>();
    archivo.seek(0);

    while (archivo.getFilePointer() < archivo.length()) {
        long posicionActual = archivo.getFilePointer();
        Libro libro = leerLibro(archivo);
        if (libro.getAnioPublicacion() == año) {
            librosEncontrados.add(libro);
        }
        archivo.seek(posicionActual + 1024); // Suponiendo un tamaño fijo por registro
    }
    return librosEncontrados;
}
}
