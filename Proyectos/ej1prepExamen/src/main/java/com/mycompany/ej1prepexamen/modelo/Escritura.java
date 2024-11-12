/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1prepexamen.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Ivan Sobrino
 */
public class Escritura {

    private static final int TAMANIO_REGISTRO = 128;

    /**
     * Escribe un objeto Producto en el archivo de acceso aleatorio.
     *
     * @param archivo El archivo de acceso aleatorio.
     * @param producto El objeto Producto a escribir en el archivo.
     * @throws IOException Si ocurre un error durante la escritura en el
     * archivo.
     */
    public static void escribirProducto(RandomAccessFile archivo, Producto producto) throws IOException {
        archivo.seek(archivo.length()); // Mover el puntero al final del archivo
        archivo.writeInt(producto.getId()); // Escribir el ID del producto
        escribirString(archivo, producto.getNombre(), 50); // Escribir el nombre del producto con longitud fija
        archivo.writeDouble(producto.getPrecio()); // Escribir el precio del producto
        archivo.writeInt(producto.getStock()); // Escribir el stock del producto
    }

    /**
     * Método auxiliar para escribir una cadena de longitud fija en el archivo.
     *
     * @param archivo El archivo de acceso aleatorio.
     * @param texto El texto a escribir.
     * @param longitud La longitud máxima de la cadena.
     * @throws IOException Si ocurre un error durante la escritura.
     */
    private static void escribirString(RandomAccessFile archivo, String texto, int longitud) throws IOException {
        StringBuilder builder = new StringBuilder(texto);
        builder.setLength(longitud); // Ajustar la longitud del texto al tamaño fijo
        archivo.writeBytes(builder.toString()); // Escribir los bytes en el archivo
    }

    /**
     * Actualiza la información de un Producto en el archivo de acceso
     * aleatorio.
     *
     * @param archivo El archivo de acceso aleatorio.
     * @param producto El producto con la información actualizada.
     * @throws IOException Si ocurre un error durante la actualización.
     */
    public static void actualizarProducto(RandomAccessFile archivo, Producto producto) throws IOException {
        archivo.seek(0); // Iniciar desde el principio del archivo
        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Producto productoActual = Lectura.leerProducto(archivo, (int) (posicionActual / TAMANIO_REGISTRO));
            if (productoActual.getId() == producto.getId()) {
                archivo.seek(posicionActual); // Volver a la posición del producto encontrado
                escribirProducto(archivo, producto); // Sobrescribir el producto con la nueva información
                return; // Salir del método después de actualizar
            }
            archivo.skipBytes(TAMANIO_REGISTRO); // Saltar al siguiente registro
        }
        throw new IOException("Producto con ID " + producto.getId() + " no encontrado.");
    }

    /**
     * Elimina un Producto del archivo de acceso aleatorio.
     *
     * @param archivo El archivo de acceso aleatorio.
     * @param id El ID del producto a eliminar.
     * @throws IOException Si ocurre un error durante la eliminación.
     */
    public static void eliminarProducto(RandomAccessFile archivo, int id) throws IOException {
        archivo.seek(0); // Iniciar desde el principio del archivo
        while (archivo.getFilePointer() < archivo.length()) {
            long posicionActual = archivo.getFilePointer();
            Producto productoActual = Lectura.leerProducto(archivo, (int) (posicionActual / TAMANIO_REGISTRO));
            if (productoActual.getId() == id) {
                archivo.seek(posicionActual); // Volver a la posición del producto encontrado
                archivo.writeInt(-1); // Marcar el producto como eliminado
                return; // Salir del método después de eliminar
            }
            archivo.skipBytes(TAMANIO_REGISTRO); // Saltar al siguiente registro
        }
        throw new IOException("Producto con ID " + id + " no encontrado.");
    }

    /**
     * Exporta una lista de productos a un archivo XML.
     *
     * @param productos Lista de productos a exportar.
     * @param nombreArchivo Nombre del archivo XML de destino.
     * @throws Exception Si ocurre un error durante la exportación.
     */
    public static void exportarProductosXML(List<Producto> productos, String nombreArchivo) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("productos");
        doc.appendChild(rootElement);

        for (Producto producto : productos) {
            Element prodElement = doc.createElement("producto");
            rootElement.appendChild(prodElement);

            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(producto.getId())));
            prodElement.appendChild(idElement);

            Element nombreElement = doc.createElement("nombre");
            nombreElement.appendChild(doc.createTextNode(producto.getNombre()));
            prodElement.appendChild(nombreElement);

            Element precioElement = doc.createElement("precio");
            precioElement.appendChild(doc.createTextNode(String.valueOf(producto.getPrecio())));
            prodElement.appendChild(precioElement);

            Element stockElement = doc.createElement("stock");
            stockElement.appendChild(doc.createTextNode(String.valueOf(producto.getStock())));
            prodElement.appendChild(stockElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileWriter(nombreArchivo));

        transformer.transform(source, result);
    }

}
