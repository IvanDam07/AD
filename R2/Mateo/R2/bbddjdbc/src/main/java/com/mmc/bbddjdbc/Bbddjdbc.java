/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.bbddjdbc;

import com.mmc.bbddjdbc.bbdd.OperacionesBBDD;
import com.mmc.bbddjdbc.modelo.Departamento;
import com.mmc.bbddjdbc.modelo.Empleado;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
        
        
        /*
        
        COSAS PARA EL PROXIMO DIA
        
        -> Hacer lo mismo que hemos hecho con departamento para empleado
        -> Script de creaciones de tabla para que nos funcione tambien en oracle (pequeños ajustes de aparte de la conexion)
        meter la dependecia de classes12 y SIEMPRE por debajo de la de jdbc para que no de problemas

        -> desde el usuario system, ejecutamos en oracle: ALTER USER dam2 quota unlimited on Users;
        
        */
        
//        Empleado e = new Empleado(1,"Aguilar","Informático",678,"10/01/2000",2000.0,0.25,1);
//        
//        e.insertar(bbdd);
        
        //Pruebas ejercicios
//        Departamento d = new Departamento(15,"Informatica","MADRID");
//        d.insertar(bbdd);
//        
        Empleado e = new Empleado(4,"Herrera","ANALISTA",1,"23/11/2024",1400,100,15);
        e.selectApellidoOficioSalario(bbdd,1);
//        
//        e = new Empleado(2,"Aguilar","DISEÑADOR",7777,"12/12/1999",1500,0,15);
//        e.insertar(bbdd);

//        Empleado e = new Empleado();
        
//        e.actualizarSalarioEmpleadosDeUnDepartamento(bbdd, -10, 15);
        
        
        bbdd.cerrarConexion();
        
        
    }
    
    public static void insertarDatos(){
        try {
            
            bbdd.insert("insert into departamentos values (?,?,?)", 1, "informatica", "Ciudad Real");
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
