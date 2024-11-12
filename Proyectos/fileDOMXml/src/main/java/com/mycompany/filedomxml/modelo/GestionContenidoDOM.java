/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filedomxml.modelo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

//En caso de que salgan caracteres raros en el fichero xml al escribir una cadena, se puede solucionar usando el metodo trim() de esta manera:
//Text text = document.createTextNode(valor.trim());


//En caso de que aparezcan caracteres extraños de una cadena al crear el xml de un archivo binario, utilizaremos:
//
//byte[] cadena = new byte[20];
//fichero.read(cadena);
//String apellido = new String(cadena, "UTF-16");

/**
 *
 * @author Ivan Sobrino
 */
public class GestionContenidoDOM {
    Document documento;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    
    /**
     * 
     * @param nombre Nombre del nodo principal
     */
    public GestionContenidoDOM (String nombre){
        
        try {
            
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();
            this.documento = (Document) implementation.createDocument(null, nombre, null);
            this.documento.setXmlVersion("1.0");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public Element addNodo(String nombreNodo){
        
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
        
    }
    
    
    public Element addNodo (String datoEmple, Element elementoPadre){
        
        Element dato = this.documento.createElement(datoEmple);
        elementoPadre.appendChild(dato);
        
        return dato;
        
    }
    
    /**
     * 
     * @param datoEmple
     * @param texto
     * @param raiz la raiz donde lo quiero añadir
     */
    public void addNodoYTexto (String datoEmple, String texto, Element raiz){
        
        Element dato = this.documento.createElement(datoEmple);
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
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transformer;
    }
    
    
    public void generarArchivoDelDOM(String nombreArchivo, String indent){ //le pasamos si lo queremos indentado o no
        
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(new File (nombreArchivo));//en vez de pasarle el system.out para que lo muestre por pantalla, le pasamos esto y lo manda a un archivo
            
            
            preProcess(indent).transform(source,salida);
            
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void mostrarPantalla(String indent){
        
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(System.out);
            
            
            preProcess(indent).transform(source,salida);
            
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void cargarArchivoEnMemoria(String nombreArchivo){
        
        try {
            //con esto ya tenemos el xml cargado
            this.documento = this.builder.parse(new File(nombreArchivo));
            
            //con esto lo normalizamos
            this.documento.getDocumentElement().normalize();
            
        } catch (SAXException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public String getElementPrincipal(){
        
        //esto lo hacemos porque yo se que getdocumentelement me devuelve un nodo
        return this.documento.getDocumentElement().getNodeName();
        
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
    
    
    public Empleado getEmpleado(Node node){
        
        Empleado emple  = new Empleado();

        if(node.getNodeType() == Node.ELEMENT_NODE){
            
            Element element = (Element) node;
            
            //lo parseamos a long porque el identificador es de tipo long
            emple.setIdentificador(Long.parseLong(getTagValue("identificador",element)));
            
            
        }
        
        return emple;
        
    }
    
    
    public List<Empleado> getEmpleados(){
        
        List<Empleado> empleList = new ArrayList<Empleado>();
        
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        for (int i = 0; i < nodeList.getLength(); i++){
            
            empleList.add(getEmpleado(nodeList.item(i)));
            
        }
        return empleList;
    }
    
    
    /**
     * Método que nos añade un elemento cargo a empleados
     */
    public void addCargoAEmpleados(){
        
        //Hacemos lo mismo que en el método de arriba recorriendo cada empleado en el XML
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        for(int i = 0; i < nodeList.getLength(); i++){
            
            Element empleElemento = (Element) nodeList.item(i);
            
            addNodoYTexto("Cargo","Por especificar",empleElemento);//utilizamos el método que ya tenemos para esto
            
        }
        
        //guardo el XML con las cosas nuevas. Le pasamos el indent a mano en este caso
        generarArchivoDelDOM("empleados_actualizados.xml","yes");
        
    }
    
    
    public void quitarElementoDeEmpleados(String nombreElemento){
        
        //Hacemos lo mismo que antes, sacamos la lista
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        //recorro todos los nodos y cojo el nodo actual
        for(int i=0; i < nodeList.getLength(); i++){
            
            Element empleElemento = (Element) nodeList.item(i);
            
            //busco el hijo que quiero eliminar
            NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
            
            
            if(elementos.getLength() > 0){ //si existe, lo borro
            
                Node elementoABorrar = elementos.item(0);
                empleElemento.removeChild(elementoABorrar);
                
            }
            
        }
        
        //actualizo el archivo
        generarArchivoDelDOM("empleados_actualizados.xml","yes");
        
    }
    
    
    public void modificarSalarioEmpleado(long identificador, String nSalario){
        
        //Como siempre,saco la lista de empleados
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");

        //mejoer meter esto en un while para que cuando lo encuentre salga, no siga recorriendo
        for(int i=0; i < nodeList.getLength(); i++){
            
            Element empleElemento = (Element) nodeList.item(i);
            
            //Saco el valor del id del emple act
            String idEmple = getTagValue("identificador", empleElemento);
            
            //compruebo id
            if(idEmple != null && Long.parseLong(idEmple) == identificador){
                
                //busco salario en el empleado
                NodeList salarioList = empleElemento.getElementsByTagName("Salario");
                
                if(salarioList.getLength() > 0){
                    
                    //si el nodo existe, lo actualizo
                    salarioList.item(0).setTextContent(nSalario);
                    
                } else {
                    
                    //si no existe el nodo, lo añado y el meto el valor
                    addNodoYTexto("Salario",nSalario,empleElemento);
                    
                }
                
                //lo guardo en el archivo todo
                generarArchivoDelDOM("empleados_actualizados.xml","yes");
                
                //termino
                return;
                
            }
            
        }
        
        //Aqui no ha encontrado el empleado
        System.out.println("No encuentro al empleado");
        
    }
    
    
    /**
     * Método que lee un archivo binario de empleados y genera un archivo XML con los datos.
     * 
     * @param archivoBinario El nombre del archivo binario de empleados.
     * @param archivoXML El nombre del archivo XML a generar.
     * @param nombreRaiz El nombre de la raíz del XML.
     */
    public static void generarXMLDesdeBinario(String archivoBinario, String archivoXML, String nombreRaiz) {
        // Crear instancia de GestionContenidoDOM
        GestionContenidoDOM gestionContenido = new GestionContenidoDOM(nombreRaiz);

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoBinario))) {
            
            while (dis.available() > 0) {
                
                long identificador = dis.readLong();
                String apellido = dis.readUTF();
                int departamento = dis.readInt();
                double salario = dis.readDouble();

                // Crear un objeto Empleado
                Empleado empleado = new Empleado(identificador, apellido, departamento, salario);

                // Agregar el empleado al XML
                Element nodoEmpleado = gestionContenido.addNodo("Empleado");
                gestionContenido.addNodoYTexto("identificador", String.valueOf(empleado.getIdentificador()), nodoEmpleado);
                gestionContenido.addNodoYTexto("apellido", empleado.getApellido(), nodoEmpleado);
                gestionContenido.addNodoYTexto("departamento", Integer.toString(empleado.getDepartamento()), nodoEmpleado); //error porque es un int con el casteo nos vale
                gestionContenido.addNodoYTexto("salario", String.valueOf(empleado.getSalario()), nodoEmpleado);
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Generar el archivo XML
        gestionContenido.generarArchivoDelDOM(archivoXML, "yes");

        System.out.println("Archivo XML generado: " + archivoXML);
    }

    public static void main(String[] args) {
        String archivoBinario = "empleados.dat"; // Nombre del archivo binario
        String archivoXML = "empleados.xml"; // Nombre del archivo XML a generar
        String nombreRaiz = "Empleados"; // Nombre de la raíz del XML

        // Llamar al método para generar el XML
        generarXMLDesdeBinario(archivoBinario, archivoXML, nombreRaiz);
    }
    
    
//    import java.io.File;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Result;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.DOMImplementation;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Text;
//
///**
// *
// * @author pokem
// */
//public class ManejoConDOMEjemplo {
//
//    public static void main(String[] args) {
//       
//       
//       
//        try {
//            Document archivoEmpleados = inicializar("Empleados");
//           
//            Element nodo = crearNodoPrincipal("Empleado", archivoEmpleados);
//            // nodo = añadirNodo("Empleado", nodo, archivoEmpleados);
//            añadirNodo("id", "1", nodo, archivoEmpleados);
//            añadirNodo("id", "2", nodo, archivoEmpleados);
//           
//            nodo = crearNodoPrincipal("Empleado", archivoEmpleados);
//            añadirNodo("id", "2", nodo, archivoEmpleados);
//           
//
//            mostrarPantalla(archivoEmpleados);
//            //generarArchivo(archivoEmpleados, "Empleados.xml");
//           
//        } catch (ParserConfigurationException pce) {
//            System.out.println("Excepción.");
//        }  
//    }
//   
//    static void mostrarPantalla (Document archivo) {
//        Source source = new DOMSource(archivo);
//        Result salida = new StreamResult(System.out);
//        try {
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.transform(source, salida);
//        } catch (TransformerException te) {
//            System.out.println("Excepción del transformer.");
//        }
//    }
//   
//    static void generarArchivo (Document archivo, String nombre) {
//        Source source = new DOMSource(archivo);
//        Result salida = new StreamResult(new File(nombre));
//        try {
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.transform(source, salida);
//        } catch (TransformerException te) {
//            System.out.println("Excepción del transformer.");
//        }
//    }
//   
//    static void añadirNodo (String datoEmple, String texto, Element raíz, Document documento) {
//        Element dato = documento.createElement(datoEmple);
//        Text textoDato = documento.createTextNode(texto);
//        dato.appendChild(textoDato);
//        raíz.appendChild(dato);
//    }
//   
//    static Element añadirNodo (String datoEmple, Element raíz, Document documento) {
//        Element dato = documento.createElement(datoEmple);
//        raíz.appendChild(dato);
//        return dato;
//    }
//   
//    static Element crearNodoPrincipal(String nombreNodo, Document documento) {
//        Element nodoPrincipal = documento.createElement(nombreNodo);
//        documento.getDocumentElement().appendChild(nodoPrincipal);
//        return nodoPrincipal;
//    }
//   
//    static Document inicializar (String nombre) throws ParserConfigurationException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        DOMImplementation implementation = builder.getDOMImplementation();
//        Document archivo = (Document) implementation.createDocument(null, nombre, null);
//        archivo.setXmlVersion("1.0");
//        return archivo;
//    }
//}

    
    
    public void modificarSalarioEmpleado_2(long identificador, String nSalario){
        
        //Como siempre,saco la lista de empleados
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        boolean encontrado = false;
        int i = 0;

        //mejoer meter esto en un while para que cuando lo encuentre salga, no siga recorriendo
        while(!encontrado && i < nodeList.getLength()){
            
            Element empleElemento = (Element) nodeList.item(i);
            i++;
                
            
            //Saco el valor del id del emple act
            String idEmple = getTagValue("identificador", empleElemento);
            
            //compruebo id
            if(idEmple != null && Long.parseLong(idEmple) == identificador){
                
                encontrado = true;
                
                //busco salario en el empleado
                NodeList salarioList = empleElemento.getElementsByTagName("Salario");
                
                if(salarioList.getLength() > 0){
                    
                    //si el nodo existe, lo actualizo
                    salarioList.item(0).setTextContent(nSalario);
                    
                } else {
                    
                    //si no existe el nodo, lo añado y el meto el valor
                    addNodoYTexto("Salario",nSalario,empleElemento);
                    
                }
                
                //lo guardo en el archivo todo
                generarArchivoDelDOM("empleados_actualizados.xml","yes");
                
                
            }
            
        }
        
        //Aqui no ha encontrado el empleado
        System.out.println("No encuentro al empleado");
        
    }
}
