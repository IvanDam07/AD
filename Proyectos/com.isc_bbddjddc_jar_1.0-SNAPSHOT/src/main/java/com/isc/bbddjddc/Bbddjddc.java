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

//        Departamento dep = new Departamento();
//        dep.insertarDepartamento(bbdd, 15,"INFORMÁTICA","MADRID");
        // Creamos dos empleados en el departamento 15
//        Empleado emp1 = new Empleado();
//        emp1.insertarEmpleadoEnDepartamento(bbdd, 101, "PEREZ", "EMPLEADO", 0, "2024-11-26", 3000, 500, 15);
//
//        Empleado emp2 = new Empleado();
//        emp2.insertarEmpleadoEnDepartamento(bbdd, 102, "GARCIA", "EMPLEADO", 101, "2024-11-26", 3500, 0, 15);
        Empleado emp = new Empleado();
        //emp.actualizarSalario(bbdd, 15, 100);

        //emp.llamarPSubidaSal(bbdd, 15, 100);
//        int numEmpleados = Departamento.llamarFNEmpleado(bbdd, 15);
//        System.out.println("Número de empleados en el departamento 15: " +numEmpleados);
        //Empleado.mostrarNominaEmpleados(bbdd);
        
//        Optional<ResultSet> rs = Empleado.selectAll(bbdd);
//        if (rs.isPresent()) {
//            int totalFilas = Empleado.obtenerNumeroFilas(rs.get());
//            System.out.println("Total de filas: " + totalFilas);
//        }

        Departamento.mostrarInformacionTabla(bbdd, "EMPLEADOS");
        bbdd.cerrarConexion();

    }

}
