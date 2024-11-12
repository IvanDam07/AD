/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej2prepexamen.Modelo;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class ExportadorXML {

    /**
     * Exporta una lista de libros a un archivo XML.
     *
     * @param libros Lista de libros a exportar.
     * @param archivoDestino Archivo de destino para el XML.
     * @throws Exception Si ocurre un error durante la exportación.
     */
    public void exportarLibrosXML(List<Libro> libros, File archivoDestino) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // Elemento raíz
        Element raiz = doc.createElement("Biblioteca");
        doc.appendChild(raiz);

        // Agregar libros
        for (Libro libro : libros) {
            Element libroElem = doc.createElement("Libro");
            raiz.appendChild(libroElem);

            Element titulo = doc.createElement("Titulo");
            titulo.appendChild(doc.createTextNode(libro.getTitulo()));
            libroElem.appendChild(titulo);

            Element autor = doc.createElement("Autor");
            autor.appendChild(doc.createTextNode(libro.getAutor()));
            libroElem.appendChild(autor);

            // Resto de atributos
        }

        // Guardar archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(archivoDestino);
        transformer.transform(source, result);
    }
}
