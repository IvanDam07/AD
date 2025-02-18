/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio3examen.modelo;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;



/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Modelo {
    
    Document documento;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    
    public Modelo(String nombre){
    
        try {
            
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();
            this.documento = (Document) implementation.createDocument(null, nombre, null);
            this.documento.setXmlVersion("1.0");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void añadirDatos(String nombreProducto, int cantidad, String nombreSuper) {
        
        cargarArchivoEnMemoria("./RESOURCES/listaCompra.xml");
        
        String cant = Integer.toString(cantidad);
       
        Element elem = addNodo("producto");
        addNodoYTexto("nombre",nombreProducto, elem);
        addNodoYTexto("cantidad",cant, elem);
        addNodoYTexto("supermercado",nombreSuper, elem);
        
        generarArchivoDelDOM("./RESOURCES/listaCompra.xml","yes");
        cargarArchivoEnMemoria("./RESOURCES/listaCompra.xml");
        
    }

    public void mostrarDatos(String indent) {
        
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(System.out);
            
            
            preProcess(indent).transform(source,salida);
            
            
        } catch (TransformerException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTagValue(String tag, Element element){
        
        //esto nos sirve para el caso que se da una vez, identificador apellido... si por ej apellido lo tiene 2 veces un empleado ya no vale
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes(); //busca solo dentro de ese elemento que yo le he pasado
        Node node = nodeList.item(0);
        
        if(node != null){
            
            return node.getNodeValue();
            
        } else {
            
            return null;
            
        }
    }
    
    public Element addNodo(String nombreNodo){
        
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
        
    }
    
    
    public void addNodoYTexto (String datoProducto, String texto, Element raiz){
        
        Element dato = this.documento.createElement(datoProducto);
        Text textoDato = this.documento.createTextNode(texto);
        dato.appendChild(textoDato);
        raiz.appendChild(dato);
 
    }
   
    
    private Transformer preProcess(String indent){
        
        Transformer transformer = null;
        
        try {
            
            transformer = TransformerFactory.newInstance().newTransformer(); 
            transformer.setOutputProperty(OutputKeys.INDENT, indent); //para colocarlo bien y que no salga todo en una linea
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transformer;
    }
    
    
    public void generarArchivoDelDOM(String nombreArchivo, String indent){ //le pasamos si lo queremos indentado o no
        
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(new File (nombreArchivo));//en vez de pasarle el system.out para que lo muestre por pantalla, le pasamos esto y lo manda a un archivo
            
            
            preProcess(indent).transform(source,salida);
            
            
        } catch (TransformerException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void mostrarPantalla(String indent){
        
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(System.out);
            
            
            preProcess(indent).transform(source,salida);
            
            
        } catch (TransformerException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void cargarArchivoEnMemoria(String nombreArchivo){
        
        try {
            //con esto ya tenemos el xml cargado
            this.documento = this.builder.parse(new File(nombreArchivo));
            
            //con esto lo normalizamos
            this.documento.getDocumentElement().normalize();
            
        } catch (SAXException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

}
