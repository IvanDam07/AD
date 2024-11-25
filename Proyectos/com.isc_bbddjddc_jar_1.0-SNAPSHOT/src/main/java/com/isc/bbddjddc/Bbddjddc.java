/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.bbddjddc;

import com.isc.bbddjddc.Modelo.Departamento;
import com.isc.bbddjddc.Modelo.Empleado;
import com.isc.bbddjddc.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Bbddjddc {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    private static Optional<ResultSet> rs;
    

    public static void main(String[] args) throws SQLException {
        
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
        
        
        
        /* ESTO ES PARA: Sentencias de Manipulación Ejecutando DML (punto 2)
        int dep = 10;
        Optional<ResultSet> empleados = Empleado.obtenerEmpleadoPorDepartamentos(bbdd, dep);
        
        if (empleados.isPresent()) {
            try {
                ResultSet rs = empleados.get();
                while(rs.next()) {
                    System.out.println("Apellido: " + rs.getString("apellido") +
                            ", Oficio: " + rs.getString("oficio") +
                            ", Salario: " + rs.getDouble("salario"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Bbddjddc.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No se encontraron empleados en ese id de departamento.");
        }
        */
        
        Optional<ResultSet> empleados = Empleado.obtenerEmpleadoConMayorSalario(bbdd);
        if(empleados.isPresent()) {
            try {
                ResultSet rs = empleados.get();
                while(rs.next()) {
                    System.out.println("Apellido: " + rs.getString("apellido") + 
                            ", Salario: " + rs.getString("salario") +
                            ", Nombre departamento: " + rs.getString("dnom"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Bbddjddc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bbdd.cerrarConexion();
                
    }
    
}
