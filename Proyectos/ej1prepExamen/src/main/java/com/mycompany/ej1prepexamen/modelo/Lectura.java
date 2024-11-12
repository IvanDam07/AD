/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1prepexamen.modelo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Ivan Sobrino
 */
public class Lectura {
    private static final int TAMANIO_REGISTRO = 128; //Tamaño fijo para cada registro
    
    // Método para leer un producto desde una posición específica en el archivo
    public static Producto leerProducto(RandomAccessFile archivo, int posicion) throws IOException{
        archivo.seek(posicion * TAMANIO_REGISTRO);
        int id=archivo.readInt();
        String nombre = leerString(archivo, 50).trim(); //Leer y recorta el nombre
        double precio = archivo.readDouble();
        int stock = archivo.readInt();
        return new Producto(id, nombre, precio, stock);
    }
    
    // Método auxiliar para leer una cadena de longitud fija desde el archivo
    public static String leerString(RandomAccessFile archivo, int longitud) throws IOException{
        byte[] bytes = new byte[longitud];
        archivo.readFully(bytes); //Leer los bytes completos del archivo
        return new String(bytes); //Convertir los bytes en una cadena
    }
    
    public static Producto buscarProductoPorId(RandomAccessFile archivo, int id) throws IOException {
        archivo.seek(0); //Iniciar la búsqueda desde el inicio del archivo
        while(archivo.getFilePointer() < archivo.length()) { // Mientras no se alcance el final
            Producto producto = leerProducto(archivo, (int) archivo.getFilePointer() / TAMANIO_REGISTRO);
            if (producto.getId() == id) { //Si se encuentra el producto con el ID buscado
                return producto;
            }
            archivo.skipBytes(TAMANIO_REGISTRO - Integer.BYTES); //Saltar al siguiente registro
        }
        
        return null; //Producto no encontrado
    }
}
