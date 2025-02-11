/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mmc.proyectojpav2;

import com.mmc.proyectojpav2.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

            //inicializarFactory();
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
            //cierraFactory();
//            DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
//            
//            Departamentos departamento = new Departamentos();
//            
//            departamento.setDeptNo((short) 77);
//            departamento.setDnombre("BIG DATA");
//            departamento.setLoc("TALAVERA");
//            departamento.setEmpleadosCollection(null);
//            
//            Empleados empleado = new Empleados();
//            Collection<Empleados> empleadosCollection = new ArrayList<Empleados>();
//            
//            empleado.setEmpNo((short) 7777);
//            empleado.setApellido("ROBLES");
//            empleado.setSalario(BigDecimal.valueOf(2000));
//            empleado.setOficio("ANALISTA");
//            empleado.setDir((short) 7839);
//
//            empleadosCollection.add(empleado);
//            
//            departamento.setEmpleadosCollection(empleadosCollection);
//            departamentosJpaController.create(departamento);    
            
             /**************************************************************
             * B) EJEMPLOS UTILIZANDO JPACONTROLLER *
             **************************************************************/
             
            //inicializaFactoryController();

            //IinsertaDepartamento()
            //insertaDepartamentoConEmpleado();
            //borrarDepartamento()
            //borrarDepartamento();
            //listarDepartamento();
            //listarDepartamentos();
            //listarDepartamentosPorTramos();
            //contarNumeroDepartamentos();
            //modificarDepartamento(10);
            //cierraFactoryController();

            /***************************************************************
            * C) EJEMPLOS SI UTILIZAN JPQL                        *
            ***************************************************************/
            inicializarFactory();
            
                /*---------------------------------------------------------------------------
                    PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL
                ----------------------------------------------------------------------------*/
            //consultaSimple();
            
                /*----------------------------------------------------------------------------------------------------------------------------------------------
                    PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL AMACENADAS EN LAS CLASES DE PERSISTENCIA
                -----------------------------------------------------------------------------------------------------------------------------------------------*/
                //Aquí van los otros métodos de este apartado
                
                /*--------------------------------------------------------------------------------------------
                    PRUEBAS DE LECTURA UTILIZANDO CONSULTAS Y CRITERIAQUERY
                ---------------------------------------------------------------------------------------------*/
                //consultaConCriteriaQuery();
                //consultaConCriteriaQueryVariosCampos();          
                //modificarDatosConJPQL();
                //borrarDatosConJPQL();
            cierraFactory();
//            departamentosJpaController.create(departamentos);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void inicializarFactory() {
        emfactory = Persistence.createEntityManagerFactory("objectdb://localhost/proyecto.odb;user=admin;password=admin");
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
        empleado.setEmpNo((short) empNo);

        System.out.println("Apellido: ");
        String apellido = scanner.next();
        empleado.setApellido(apellido);

        System.out.println("Oficio: ");
        String oficio = scanner.next();
        empleado.setOficio(oficio);

        System.out.println("Dir: ");
        int dir = scanner.nextInt();
        empleado.setDir((short) dir);

        System.out.println("Fecha alta (dd/MM/yyyy): ");
        String fechaAlt = scanner.next();
        try {
            empleado.setFechaAlt(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaAlt).getTime()));
        } catch (ParseException e) {
            System.err.println("Formato de fecha inválido. Debe ser dd/MM/yyyy.");
            return; // Salir del método
        }

        System.out.println("Salario: ");
        BigDecimal salario = scanner.nextBigDecimal();
        empleado.setSalario(salario);

        System.out.println("Comisión: ");
        BigDecimal comision = scanner.nextBigDecimal();
        empleado.setComision(comision);

        System.out.println("Dept número: ");
        short numDept = scanner.nextShort();
        Departamentos dept = new Departamentos(); // Asume que tienes una clase Departamentos
        dept.setDeptNo(numDept);
        empleado.setDeptNo(dept);

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
     * B) EJEMPLOS UTILIZANDO LOS JPACONTROLLER
     */
    public static void inicializaFactoryController() {
        emfactory = Persistence.createEntityManagerFactory("com.mmc_proyectoJPAv2_jar_1.0-SNAPSHOTPU");
    }

    public static void cierraFactoryController() {
        emfactory.close();
    }

    public static void insertaDepartamento() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        Departamentos departamento = new Departamentos();

        departamento.setDeptNo((short) 98);
        departamento.setDnombre("BIG DATA");
        departamento.setLoc("DAIMIEL");

        try {
            departamentosJpaController.create(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertaDepartamentoConEmpleado() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        Departamentos departamento = new Departamentos();

        departamento.setDeptNo((short) 99);
        departamento.setDnombre("BIG DATA");
        departamento.setLoc("TOLEDO");

        empleado = new Empleados((short) 7521);

        List<Empleados> empleadosCollection = new ArrayList<Empleados>();

        empleadosCollection.add(empleado);

        departamento.setEmpleadosCollection(empleadosCollection);

        try {
            departamentosJpaController.create(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrarDepartamento() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        try {
            departamentosJpaController.destroy((short) 99);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarDepartamentos() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        List<Departamentos> departamentosListado;

        departamentosListado = departamentosJpaController.findDepartamentosEntities();

        for (Departamentos departamento : departamentosListado) {
            System.out.println("Nombre dpto: " + departamento.getDnombre());
        }
    }

    public static void listarDepartamentosPorTramos() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        List<Departamentos> departamentosListado;

        System.out.println("TODOS LOS DEPARTAMENTOS");
        listarDepartamentos();
        /**
         * ****************************************
         */
        System.out.println("Trae 3 registros empezando en la posición 0");

        departamentosListado = departamentosJpaController.findDepartamentosEntities(3, 0);

        for (Departamentos departamento : departamentosListado) {
            System.out.println("Nombre dpto: " + departamento.getDnombre());
        }
        /**
         * ****************************************
         */
        System.out.println("Trae 3 registros empezando en la posición 0");

        departamentosListado = departamentosJpaController.findDepartamentosEntities(3, 1);

        for (Departamentos departamento : departamentosListado) {
            System.out.println("Nombre dpto: " + departamento.getDnombre());
        }
    }

    public static void contarNumeroDepartamentos() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        int nElementos = departamentosJpaController.getDepartamentosCount();

        System.out.println("Nº de departamentos: " + nElementos);
    }

    public static void listarUnDepartamento() {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        Departamentos departamento = departamentosJpaController.findDepartamentos((short) 10);

        System.out.println(departamento.getDnombre());
    }

    public static void modificarDepartamento(int id) {
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        Departamentos departamento = departamentosJpaController.findDepartamentos((short) id);

        departamento.setDeptNo((short) id);
        departamento.setLoc("MADRID");

        try {
            departamentosJpaController.edit(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPAv2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * C) EJEMPLOS QUE SI UTILIZAN JPQL
     * 
     * https://www.objectdb.com/java/jpa/query
     */
    
        /*---------------------------------------------------------------------------
           PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL
        ----------------------------------------------------------------------------*/
   public static void consultaSimple() {
       Query query = entitymanager.createQuery("Select UPPER(d.dnombre) from Departamentos d");
       
       List<String> list = query.getResultList();
       
       for (String e: list) {
           System.out.println("Nombre departamento: " + e);
       }
   }
    
    public static void consultarVariosCampos() {
        TypedQuery<Object[]> query = entitymanager.createQuery("Select UPPER(d.dnombre) from Departamentos d", Object[].class);
       
       List<Object[]> list = query.getResultList();
       
       for (Object[] e: list) {
           System.out.println("Nombre departamento: " + e[0]);
           System.out.println("Localidad: " +e[1]);
       }
    }
    
        /*----------------------------------------------------------------------------------------------------------------------------------------------
           PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL ALMACENADAS EN LAS CLASES DE PERSISTENCIA
        -----------------------------------------------------------------------------------------------------------------------------------------------*/
    
    public static void consultaAlmacenada() {
        Query query = entitymanager.createNamedQuery("Departamentos.findAll");
       
       List<Departamentos> list = query.getResultList();
       
       for (Departamentos e: list) {
           System.out.println("Nombre departamento: " + e.getDnombre());
       }
    }
    
    public static void consultaAlmacenadaConParametro(int deptNoP) {
        Query query = entitymanager.createNamedQuery("Departamentos.findByDeptNo");
       
        query.setParameter("deptNo", deptNoP);
        
       List<Departamentos> list = query.getResultList();
       
       for (Departamentos e: list) {
           System.out.println("Nombre departamento: " + e.getDnombre());
       }
    }
    
         /*--------------------------------------------------------------------------------------------
            PRUEBAS DE LECTURA UTILIZANDO JPQL CON CRITERIAQUERY
         ---------------------------------------------------------------------------------------------*/
    /**
     * Select d from departamentos d
     */
    public static void consultaConCriteriaQuery() {
        
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
        
        CriteriaQuery<Departamentos> query = cb.createQuery(Departamentos.class);
        
        Root<Departamentos> c = query.from(Departamentos.class); //Especificamos el from 
                                                   
        query.select(c); //Indicamos los campos a seleccionar. C = todo el departamento
                                                   
        List<Departamentos> list = entitymanager.createQuery(query).getResultList();
        
        for (Departamentos e:list) {
            System.out.println("Nombre del departamento: " +e.getDnombre());
        }
    }
    
    /**
     * Select d.nombre, d.loc from Departamentos d
     */
    public static void consultaConCriteriaQueryVariosCampos() {
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
        
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        
        Root<Departamentos> c = query.from(Departamentos.class); //Especificamos el from 
        
        //Se ponen los nombres que hay en la clase DEPARTAMENTOS, no los de las tablas
        query.select(cb.array(c.get("dnombre"),c.get("loc"))); //Indicamos los campos a seleccionar. C = todo el departamento
                                                   
        List<Object[]> list = entitymanager.createQuery(query).getResultList();
        
        for (Object[] e:list) {
            System.out.println("Nombre del departamento: " +e[0]);
            System.out.println("Nombre de la localidad: " + e[1]);
        }
    }
    
        /*--------------------------------------------------------------------------------------------
            PRUEBAS DE MODIFICACIÓN, BORRADO UTILIZANDO JPQL
         ---------------------------------------------------------------------------------------------*/
    public static void modificarDatosConJPQL() {
        Query query = entitymanager.createQuery("UPDATE Departamentos d SET d.dnombre= :valorNuevo "
                + "WHERE d.deptNo = :deptNoV");
        
        query.setParameter("valorNuevo", "PRUEBAS");
        query.setParameter("deptNoV", (short)77);
        
        entitymanager.getTransaction().begin();
        int updateCount = query.executeUpdate();
        entitymanager.getTransaction().commit();
                
    }
    
    public static void borrarDatosConJPQL() {
        Query query = entitymanager.createQuery("DELETE from Departamentos d WHERE d.deptNo = :deptNoV");
                
        query.setParameter("deptNoV", (short)77);
        
        entitymanager.getTransaction().begin();
        int deleteCount = query.executeUpdate();
        entitymanager.getTransaction().commit();
    }
    /**
     * Método Practica en casa punto 3
     *
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
