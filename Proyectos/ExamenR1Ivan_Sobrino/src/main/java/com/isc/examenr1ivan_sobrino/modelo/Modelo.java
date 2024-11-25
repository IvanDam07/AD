/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.examenr1ivan_sobrino.modelo;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 23 oct 2024
 */
public class Modelo {

    private String ruta = "./PRUEBAS";

    public Modelo(String ruta) {
        this.ruta = ruta;
    }

    public Modelo() {
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void CrearEstructuraDeCarpetas(String nombre1, String nombre2) {
        
        File elemento1 = new File(ruta); //La ruta debe ser la actual donde está este proyecto. Buscar en otros proyectos
        File elemento2 = new File(ruta);

        if (elemento1.exists() && elemento1.getName() == nombre1) {
            if (elemento1.isFile()) {
                if (elemento1.delete()) {
                    System.out.println("Archivo eliminado " + elemento1.getName());
                } else {
                    System.out.println("No se pudo eliminar el archivo");
                }
            } else if (elemento1.isDirectory()) {
                File[] archivos = elemento1.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            archivo.delete(); //Borramos cada archivo
                            System.out.println("Archivo eliminado: " + archivo.getName());
                        }
                    }
                }
                if (elemento1.delete()) {
                    System.out.println("Directorio eliminado: " + elemento1.getName());
                } else {
                    System.out.println("No se pudo eliminar el directorio.");
                }
            }
        }
        
        if (elemento2.exists() && elemento2.getName() == nombre2) {
            if (elemento2.isFile()) {
                if (elemento2.delete()) {
                    System.out.println("Archivo eliminado " + elemento2.getName());
                } else {
                    System.out.println("No se pudo eliminar el archivo");
                }
            } else if (elemento2.isDirectory()) {
                File[] archivos = elemento2.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            archivo.delete(); //Borramos cada archivo
                            System.out.println("Archivo eliminado: " + archivo.getName());
                        }
                    }
                }
                if (elemento2.delete()) {
                    System.out.println("Directorio eliminado: " + elemento2.getName());
                } else {
                    System.out.println("No se pudo eliminar el directorio.");
                }
            }
        }
        
        //Se crean las carpetas
        File nuevaCarpeta = new File (ruta, nombre1);
        nuevaCarpeta.mkdir();
        
        nuevaCarpeta = new File(ruta, nombre2);
        nuevaCarpeta.mkdir();
    }
    
    
    
//    public boolean AltaDatosCarrerasUniversitarias(int ID, String nombreCarrera, String ciudad, double notaCorte) {
//        String ruta = "./ORIGEN/datosUniversidades.dat";        
//        Universidad uni = new Universidad(ID, nombreCarrera, ciudad, notaCorte);
//        List<Universidad>universidades = buscarUniversidadesPorID(uni, ID);
//    }
//    
//    private void escribirCarrera(RandomAccessFile archivo, Universidad universidad) throws IOException {
//        archivo.writeU
//    }
    
    
        
    
    
    public void GeneraXMLCarrerasUniversitarias(RandomAccessFile universidad, double notaCorte) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        List<Universidad> universidades = buscarUniversidades(universidad, notaCorte);
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // Elemento raíz
        Element raiz = doc.createElement("Biblioteca");
        doc.appendChild(raiz);
        
        for (Universidad uni : universidades) {
            Element uniElem = doc.createElement("Universidad");
            raiz.appendChild(uniElem);

//            Element id = doc.createElement("ID");
//            id.appendChild(doc.createTextNode(uni.getID()));
//            uniElem.appendChild(id);

            Element nombreC = doc.createElement("Nombre carrera");
            nombreC.appendChild(doc.createTextNode(uni.getNombreCarrera()));
            uniElem.appendChild(nombreC);

            Element ciudad = doc.createElement("Ciudad");
            ciudad.appendChild(doc.createTextNode(uni.getCiudad()));
            uniElem.appendChild(ciudad);
            
//            Element notaC = doc.createElement("Nota Corte");
//            notaC.appendChild(doc.createTextNode(uni.getNotaCorte()));
//            uniElem.appendChild(notaC);
            // Resto de atributos
        }

        // Guardar archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(ruta);
        transformer.transform(source, result);
        
    }
    
    private List<Universidad> buscarUniversidades(RandomAccessFile universidad, double notaCorte) throws IOException{
        List<Universidad> uniEncontradas = new ArrayList<>();
        universidad.seek(0);
        
        while(universidad.getFilePointer() < universidad.length()) {
            long posicionActual = universidad.getFilePointer();
            Universidad uni = leerUniversidad(universidad);
            if (uni.getNotaCorte() >= 7) {
                uniEncontradas.add(uni);
            }
            universidad.seek(posicionActual + 1024); //Avanza al siguiente registro
        }
        return uniEncontradas;
    }
    
    private List<Universidad> buscarUniversidadesPorID(RandomAccessFile universidad, int ID) throws IOException{
        List<Universidad> uniEncontradas = new ArrayList<>();
        universidad.seek(0);
        
        while(universidad.getFilePointer() < universidad.length()) {
            long posicionActual = universidad.getFilePointer();
            Universidad uni = leerUniversidad(universidad);
            if (uni.getID() == ID) {
                uniEncontradas.add(uni);
            }
            universidad.seek(posicionActual + 1024); //Avanza al siguiente registro
        }
        return uniEncontradas;
    }
    private Universidad leerUniversidad(RandomAccessFile archivo) throws IOException {
        int ID = archivo.readInt();
        String nombreCarrera = archivo.readUTF();
        String ciudad = archivo.readUTF();
        double notaCorte = archivo.readDouble();
        
        return new Universidad(ID, nombreCarrera, ciudad, notaCorte);             
    }
    
    
    
//    public boolean ModificaCarreraUniversitaria(int ID, String ciudad) {
//        
//    }
//    
//    public void modificarUniversidad(RandomAccessFile archivo, int ID, String ciudad, Universidad universidad) throws IOException {
//        archivo.seek(posicion);
//        escribirLibro(archivo, libro);
//    }
}
