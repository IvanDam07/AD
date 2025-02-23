/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.examenr5;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;
import org.w3c.dom.Node;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class ExamenR5 {

    //Variables para la conexión a la bbdd
    private static XQDataSource server;
    private static XQConnection con;

    public static void main(String[] args) {
        conecta();
        mostrarClientes();
        //eliminarProductos();
        System.out.println();
        //consulta("/productos");
        desconecta();
    }

    private static void conecta() {
        try {
            server = new ExistXQDataSource();
            server.setProperty("serverName", "localhost");
            server.setProperty("port", "8080");
            server.setProperty("user", "dam2");
            server.setProperty("password", "dam2");

            con = server.getConnection();

        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void desconecta() {
        try {
            con.close();
        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void consulta(String textConsulta) {

        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;

            consulta = con.prepareExpression(textConsulta);
            resultado = consulta.executeQuery();

            XQResultItem r_item;
            while (resultado.next()) {
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(r_item.getItemAsString(null));
            }
        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modificacion(String textoDML) {
        try {
            XQExpression expresion;

            expresion = con.createExpression();

            expresion.executeCommand(textoDML);
        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    /**
     * EJERCICIO 3
     */
    private static void mostrarClientes() {
        consulta("string(/clientes)");
    }
    
    /**
     * EJERCICIO 4
     */
    private static void eliminarProductos() {
        modificacion("update delete /productos/product[@categoria='A' and @pvp<200]");
    }
}
