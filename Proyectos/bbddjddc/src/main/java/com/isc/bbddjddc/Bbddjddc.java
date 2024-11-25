/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.bbddjddc;

import com.isc.bbddjddc.Modelo.Departamento;
import com.isc.bbddjddc.Modelo.Empleado;
import com.isc.bbddjddc.bbdd.OperacionesBBDD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Bbddjddc {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    

    public static void main(String[] args) {
        
        bbdd.abrirConexion();
        
//        Departamento d1 = new Departamento(2,"Ventas","Logroño");        
//        d1.insertar(bbdd);
//        
//        Departamento d2 = new Departamento(3,"Logistica","Cuenca");        
//        d2.insertar(bbdd);
         
//        Departamento.mostrarResultSet(Departamento.selectAll(bbdd));

//        Departamento departamento = new Departamento();
//        departamento.selectById(bbdd, 2);
//        System.out.println(departamento);
//        
//        departamento.setLoc("Albacete");
//        departamento.update(bbdd);
//        
//        departamento.selectById(bbdd, 2);
//        System.out.println(departamento);

        //Departamento.delete(bbdd,1);
        
        
//        Empleado e1 = new Empleado(1,"Garcia","Cocinero",2,"1 noviembre 2024",850.88,10,5);
//        e1.insertar(bbdd);
        Empleado empleado = new Empleado();
        empleado.selectById(bbdd, 5);
        System.out.println(empleado);
        
        bbdd.cerrarConexion();
        
        
    }
    
    public static void insertarDatos(){
        try {
            
            bbdd.insert("insert into departamentos values (?,?,?)", 1, "informatica", "Ciudad Real");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Bbddjddc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private static void insertarDatosObjetos(Departamento d) {
        try {
            
            bbdd.insert("insert into departamentos values (?,?,?)",d.getDept_no(),d.getDnombre(),d.getLoc());
            
        } catch (SQLException ex) {
            Logger.getLogger(Bbddjddc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
