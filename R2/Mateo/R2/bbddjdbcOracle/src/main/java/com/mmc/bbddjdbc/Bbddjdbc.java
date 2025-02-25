/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.bbddjdbc;

import com.mmc.bbddjdbc.bbdd.OperacionesBBDD;
import com.mmc.bbddjdbc.modelo.Departamento;
import com.mmc.bbddjdbc.modelo.Empleado;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Proyecto para unificar las conexiones de sqlite y de oracle
 * 
 * @author MMC by Mateo Molina Campos
 */
public class Bbddjdbc {
    
    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    private static Optional<ResultSet> rs;
    

    public static void main(String[] args) {
        
        bbdd.abrirConexion();
       
//        Departamento departamento = new Departamento(2,"Ventas","Logroño");
//        departamento.insertar(bbdd);
//        
//        departamento = new Departamento(3,"Logística","Cuenca");
//        departamento.insertar(bbdd);

//        Departamento d = new Departamento();
//        d.selectById(bbdd,2);
//        
//        System.out.println(d);
//        
//        d.setLoc("Logroño");
//        d.update(bbdd);
//        
//        d.selectById(bbdd, 2);
//        System.out.println(d);
//        
//        Departamento.delete(bbdd,1);

            //si nos dice en el examen que si ya esta que le digamos al usuario que esta duplicado lo hacemos capturando las excepciones como hasta ahora, no haciendo un select antes
            
            
//        rs = Departamento.selectAll(bbdd);
//        int nFilas  = bbdd.obtenerNumeroFilasDevueltas(rs);
//        System.out.println("Filas: "+nFilas);

//        Departamento d = new Departamento(78,"Departamento1","Daimiel");
        //d.update(rs.get());
        
//        d.insertar(rs.get());


        
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");
        java.util.Date fecha = null;
        try {
            fecha = s.parse("02/02/20");
        } catch (ParseException ex) {
            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
                
                
        Empleado e = new Empleado(6,null,"EMPLEADO",7902,fechaSql,1000,100,1);
        e.f_nomina(bbdd,20);
        
        bbdd.mostrarInfoTabla("EMPLEADOS");
//        
//        Departamento d = new Departamento(1,"DEPPRUEBA","DAIMIEL");
//        d.insertar(bbdd);
        
        
        
        
        
        bbdd.cerrarConexion();
        
        
    }
    
    
//    public static void insertarDatos(){
//        try {
//            
//            bbdd.insert("insert into departamentos values (?,?,?)", 1, "informatica", "Ciudad Real");
//            
//        } catch (SQLException ex) {
//            
//            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    /*

    INSERTAR UN REGISTRO:
        > Si existe nos lo dice y da error
        > Si no existe lo inserta
    
    - En vez de hacer un select para comprobar y hacer un insert despues, directamente hacemos el insert y controlamos la excepcion con el codigo de error, asi nos ahorramos un acceso a la bd

    */
        
    
    
}
