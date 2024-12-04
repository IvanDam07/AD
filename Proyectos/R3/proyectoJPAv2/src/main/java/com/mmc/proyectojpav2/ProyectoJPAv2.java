/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mmc.proyectojpav2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class ProyectoJPAv2 {

    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamento;
    static DepartamentosJpaController departamentosJpaController;
    static Empleados empleado;
    Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {

        try {

            inicializarFactory();

//            leerUnRegistro();
//            
//            esperar();
//            
//            leerUnRegistro();
//            
//            recargardesdeBbdd();
//            
//            esperar();
//            
//            leerUnRegistro();
//            leerUnRegistro();
            //el emfactory es como una especie de fabrcia de creacion de entidades y demas
            //ente las comillas le tenemos que pasar el nombre de nuestra unidad de persistencia (abrimos el archivo persistence.xml y copiamos el persistence unit name)
//        emfactory = Persistence.createEntityManagerFactory("com.mmc_proyectoJPAv2_jar_1.0-SNAPSHOTPU");
//        
//        //parecido a cuando le pasábamos la conexion
//        departamentosJpaController = new DepartamentosJpaController(emfactory);
//        
//        //creamos un departamento y, depsues, llamando al jpacontroller.create hacemos el insert pasandole el objeto
//        departamentos = new Departamentos();
//        
//        //
//        departamentos.setDeptNo((short)99);
//        departamentos.setDnombre("Pruebas");
//        departamentos.setLoc("Madrid");
            //insertarDatos((short)99,"Prueba","LocPrueba");
            //modificarDatos();           
//            borrarDatos((short)99);

            
            cierraFactory();

//            departamentosJpaController.create(departamentos);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void inicializarFactory() {
        emfactory = Persistence.createEntityManagerFactory("com.mmc_proyectoJPAv2_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
    }

    public static void cierraFactory() {
        entitymanager.close();

        emfactory.close();
    }

    /**
     * Deja cambiiar datos mientras está ejecutándose, pero no lo detecta hasta
     * que ha acabado la ejecución
     */
    public static void leerUnRegistro() {
        short id = 10;
        departamento = entitymanager.find(Departamentos.class, id);

        if (departamento != null) {
            System.out.println("Dept NAME :" + departamento.getDnombre());
        } else {
            System.out.println("NO existe el registro");
        }
    }

    public static void esperar() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Pulsa Enter para continuar...");

            String sTexto = br.readLine();

        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * No deja cambiar datos mientras está ejecutándose (da error en el SQL
     * Developer si intentas cambiar un dato en mitad del proceso)
     */
    public static void leerUnRegistroBloqueando() {
        entitymanager.getTransaction().begin();

        short id = 10;
        departamento = entitymanager.find(Departamentos.class, id, LockModeType.PESSIMISTIC_READ);

        if (departamento != null) {
            System.out.println("Dept NAME :" + departamento.getDnombre());
        } else {
            System.out.println("NO existe el registro");
        }

        entitymanager.getTransaction().commit();
    }

    /**
     * Permite que leerUnRegistro lea el cambio que haces manualmente en el SQL
     * Developer
     */
    public static void recargardesdeBbdd() {
        entitymanager.getTransaction().begin();
        entitymanager.refresh(departamento);
        entitymanager.getTransaction().commit();
    }

    public static void insertarDatos(Short deptNo, String nombreDept, String loc) {
        Departamentos departamento;

        departamento = new Departamentos();
        departamento.setDeptNo(deptNo);
        departamento.setDnombre(nombreDept);
        departamento.setLoc(loc);

        entitymanager.getTransaction().begin();
        entitymanager.persist(departamento);
        entitymanager.getTransaction().commit();
    }

    public static void insertarEmpDatosPedidos() {
        Scanner scanner = new Scanner(System.in);
        
        Empleados empleado;
        empleado = new Empleados();
        
        System.out.println("Ingresa los siguientes datos: ");
        System.out.print("Número empleado: ");       
        int empNo = scanner.nextInt();        
        empleado.setEmpNo((short)empNo);
        
        System.out.println("Apellido: ");
        String apellido = scanner.next();
        empleado.setApellido(apellido);
        
        System.out.println("Oficio: ");
        String oficio = scanner.next();
        empleado.setOficio(oficio);
        
        System.out.println("Dir: ");
        int dir = scanner.nextInt();
        empleado.setDir((short)dir);
        
        System.out.println("Fecha alta: ");
        String fechaAlt = scanner.next();
        empleado.setFechaAlt(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaAlt).getTime()));
        
    }
    public static void modificarDatos() {
        short id = 99;
        departamento = entitymanager.find(Departamentos.class, id);

        entitymanager.getTransaction().begin();
        departamento.setLoc("Daimiel");
        entitymanager.getTransaction().commit();
    }

    public static void modificarDatosBloqueando() {
        entitymanager.getTransaction().begin();

        short id = 99;
        departamento = entitymanager.find(Departamentos.class, id, LockModeType.PESSIMISTIC_WRITE);

        if (departamento != null) {
            departamento.setLoc("Daimiel");
            esperar();
            System.out.println("Departamento actualizado: " + departamento.getDnombre());
        } else {
            System.out.println("No existe el registro con ID: " + id);
        }
        
        entitymanager.getTransaction().commit();
    }

    public static void borrarDatos(Short idDepart) {
        
        departamento = entitymanager.find(Departamentos.class, idDepart);

        entitymanager.getTransaction().begin();
        entitymanager.remove(departamento);
        //esperar();
        entitymanager.getTransaction().commit();
    }
    
    /**
     * Método Practica en casa punto 3
     * @param idEmp id del empleado
     * @param salario salario nuevo del empleado
     * @param deptNo departamento nuevo del empleado
     */
    public static void modificarSalarioDepart(int idEmp, BigDecimal salario, Departamentos deptNo) {
        empleado = entitymanager.find(Empleados.class, idEmp);
        
        entitymanager.getTransaction().begin();
        empleado.setSalario(salario);
        empleado.setDeptNo(deptNo);
        
        entitymanager.getTransaction().commit();
    }
    
    
}
