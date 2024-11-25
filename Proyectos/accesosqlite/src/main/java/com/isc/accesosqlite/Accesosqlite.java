/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.accesosqlite;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Accesosqlite {

    public static void main(String[] args) {
        ConexionOracle conecta = new ConexionOracle();
        conecta.conectar();
        conecta.cerrarConexion();
    }
}
