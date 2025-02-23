/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.break4learning.proyectojpa2024;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 8 dic 2024
 */
public class ProyectoJPA2024 {

    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamento;
    static Empleados empleado;
    
    static DepartamentosJpaController departamentosJpaController;
    
    public static void main(String[] args) {
        
           
    /**********************************************************************
     * A) EJEMPLOS QUE NO UTLIZAN JPQL 
     **********************************************************************/
        //inicializaFactory();    
    
        //insertarDatosDepart();
        //insertarDatosEmple();
        //modificarDatos();
        //borrarDatos();
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS LECTURA DE REGISTRO. EN LECTURAS SUCESIVAS LEE DESDE MEMORIA
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //leerUnRegistro();
        //esperar();
        
        // Modificar el nombre del departamento desde Developer y ver que la siguiente lectura no se hace desde
        // la bbdd sino desde el objeto cargado en memoria
        
        //leerUnRegistro();
           
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS LECTURA DE REGISTRO. EN LECTURAS SUCESIVAS REFRESCAMOS PARA QUE ACTUALICE EL OBJETO ANTES DESDE LA BBDD
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //leerUnRegistro();
        //esperar();
        // Modificar el nombre del departamento desde Developer y ver que la siguiente lectura no se hace desde
        // la bbdd sino desde el objeto cargado en memoria
        
        //leerUnRegistroRefrescandoDesdeBbdd();

        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS LECTURA DE REGISTRO BLOQUEANDO. Si llamamos a esperar() antes de hacer el commit en el siguiente método y luego intentamos 
        //modificar el registro desde Developer, no permitiría hacerlo ya que está bloqueado y daría el siguiente mensaje de error:
        //Se ha producido un error al guardar los cambios en la tabla "DAM2"."DEPARTAMENTOS":
        //Fila 1: Datos confirmados en otra/la misma sesión; no se puede actualizar la fila.
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //leerUnRegistroBloqueando();
        
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE LECTURA DE UN REGISTRO Y TODOS LOS RELACIONADOS CON ÉL
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //leerUnRegistroRelacionado();

        /*--------------------------------------------------------------------------------------------------------------------------*/
        

        //finalizaFactory();

    /**********************************************************************
     * B) EJEMPLOS UTILIZANDO LOS JPACONTROLLER 
     **********************************************************************/
        //inicializaFactoryController();
        
        //inicializaControllers();
                
        //insertaDepartamento();
        //modificarDepartamento(98); // Modificar un departamento sin empleados
        
        //insertaDepartamentoConEmpleados();
        //modificarDepartamento(99); // Modificar un departamento con empleados
        
        //borraDepartamento();
        //listarDepartamentos();
        //listarDepartamentosPorTramos();
        //contarNumeroDepartamentos();
        //listarUnDepartamento();
        
        //FALTA MOSTRAR UN REGISTRO RELACIONADO: Realizar como ejercicio alumnado

        //finalizaFactoryController();

       
    /**********************************************************************
     * C) EJEMPLOS QUE SI UTLIZAN JPQL
     **********************************************************************/
        //inicializaFactory();  
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //consultaSimple();
        //consultaVariosCampos();
        

        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL ALMACENADAS EN LAS CLASES DE PERSISTENCIA
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //consultaAlmacenada();        
        //consultaAlmacenadaConParametro(10);
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE LECTURA UTILIZANDO CONSULTAS Y CRITERIAQUERY
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //consultaConCriteriaQuery();
        //consultaConCriteriaQueryVariosCampos();
        
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE MODIFICACION UTILIZANDO JPQL
        /*--------------------------------------------------------------------------------------------------------------------------*/
       
        //insertarDatosDepart(); //Utilizamos la inserción sin JPQL
        //consultaAlmacenadaConParametro(99);
        //modificarDatosConJPQL();
        //consultaAlmacenadaConParametro(99);
            
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //PRUEBAS DE BORRADO UTILIZANDO JPQL
        /*--------------------------------------------------------------------------------------------------------------------------*/
        
        //insertarDatosDepart(); //Utilizamos la inserción sin JPQL
        //consultaAlmacenadaConParametro(99);
        //borrarDatosConJPQL();
        //consultaAlmacenadaConParametro(99);


        //finalizaFactory();
                
    }
    
    /**
     * CONTROL DEL GESTOR DE PERSISTENCIA 
     */
    
    public static void inicializaFactory(){
        emfactory = Persistence.createEntityManagerFactory( "com.break4learning_proyectoJPA2024_jar_1.0-SNAPSHOTPU" );
        entitymanager = emfactory.createEntityManager();
    } 
    
    public static void finalizaFactory(){
        entitymanager.close();
        emfactory.close();
    }
    
    public static void inicializaControllers(){
            departamentosJpaController = new DepartamentosJpaController(emfactory);

    }
    
    
    /**
     * A) EJEMPLOS QUE NO UTLIZAN JPQL 
     */
        
    public static void insertarDatosDepart(){      
       departamento = new Departamentos();
       departamento.setDeptNo((short)(99));
       departamento.setDnombre("Prueba");
       departamento.setLoc("LocPrueba");

       entitymanager.getTransaction().begin();
       entitymanager.persist(departamento);
       entitymanager.getTransaction().commit();
   }
    
    public static void insertarDatosEmple(){
       Empleados empleado;
       empleado = new Empleados();
       empleado.setEmpNo((short)(99));
       empleado.setApellido("García");
       empleado.setOficio("Marchante");
       empleado.setSalario(BigDecimal.valueOf(2000));
       empleado.setDeptNo(departamento);

       entitymanager.getTransaction().begin();
       entitymanager.persist(empleado);
       entitymanager.getTransaction().commit();
   }
   
    public static void modificarDatos(){
        departamento = entitymanager.find(Departamentos.class, (short)(99));

        entitymanager.getTransaction().begin();
        departamento.setLoc("Daimiel");
        entitymanager.getTransaction().commit();
     }
       
    public static void borrarDatos(){
        
        departamento = entitymanager.find(Departamentos.class, (short)(99));

        entitymanager.getTransaction().begin();
        entitymanager.remove(departamento);
        entitymanager.getTransaction().commit();
    }
    
    public static void leerUnRegistro(){

       departamento = entitymanager.find(Departamentos.class, (short)(10));
       
       if (departamento != null) {
            System.out.println("Dept NAME :" + departamento.getDnombre());
       } else{
            System.out.println("NO existe el registro");
       }
    }
      
    public static void recargarDepartamentodesdeBbdd(Departamentos departamento){
        entitymanager.getTransaction().begin();
        entitymanager.refresh(departamento);
        entitymanager.getTransaction().commit();
    }
    
    public static void leerUnRegistroRefrescandoDesdeBbdd(){
    
       departamento = entitymanager.find(Departamentos.class, (short)(10));
       
       recargarDepartamentodesdeBbdd(departamento);
       
       if (departamento != null) {
            System.out.println("Dept NAME :" + departamento.getDnombre());
       } else{
            System.out.println("NO existe el registro");
       }
    }
    
    public static void leerUnRegistroBloqueando(){
       
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, (short)(10),LockModeType.PESSIMISTIC_READ);

        if (departamento != null) {
             System.out.println("Dept NAME :" + departamento.getDnombre());
        } else{
             System.out.println("NO existe el registro");
        }
                
        entitymanager.getTransaction().commit();
    }
    
    public static void leerUnRegistroRelacionado(){
                  
       departamento = entitymanager.find(Departamentos.class, (short)(10));
       
       if (departamento != null) {
            System.out.println("Dept NAME :" + departamento.getDnombre());
            
            Collection<Empleados> list = departamento.getEmpleadosCollection();

            for (Empleados emple: list){
                
                System.out.println("Emple NAME :" + emple.getApellido());
            }
        
       } else{
            System.out.println("NO existe el registro");
       }
    }
    
    
        
    
    /**
     * B) EJEMPLOS UTILIZANDO LOS JPACONTROLLER
     */
        
    public static void inicializaFactoryController(){
        emfactory = Persistence.createEntityManagerFactory( "com.break4learning_proyectoJPA2024_jar_1.0-SNAPSHOTPU" );
    } 
    
    public static void finalizaFactoryController(){
        emfactory.close();
    }
    
    public static void insertaDepartamento(){
                
        Departamentos departamento = new Departamentos();
        
        departamento.setDeptNo((short)98);
        departamento.setDnombre("BIG DATA");
        departamento.setLoc("SEVILLA");
        
        try {
            departamentosJpaController.create(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insertaDepartamentoConEmpleados(){
        
        departamento = new Departamentos();
        
        departamento.setDeptNo((short)99);
        departamento.setDnombre("BIG DATA");
        departamento.setLoc("TOLEDO");
        
        empleado = new Empleados((short)7521);
         
        Collection<Empleados> empleadosCollection = new ArrayList<Empleados>();
        
        empleadosCollection.add(empleado);
        
        departamento.setEmpleadosCollection(empleadosCollection);
        
        try {
            departamentosJpaController.create(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void borraDepartamento(){
        
        try {
            departamentosJpaController.destroy((short)99); //Cuidado porque si el departamento tiene empleados, dichos empleados tendrían el departamento a NULL
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listarDepartamentos(){
        
        List<Departamentos> departamentosListado;
        
        try {
            
            departamentosListado = departamentosJpaController.findDepartamentosEntities();
            
            for(Departamentos departamento: departamentosListado) {
                System.out.println("Nombre dpto:" + departamento.getDnombre());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void listarDepartamentosPorTramos(){
        
        List<Departamentos> departamentosListado;
        
        try {
            departamentosListado = departamentosJpaController.findDepartamentosEntities();
            
            System.out.println("**TODOS LOS DEPARTAMENTOS**");
            for(Departamentos departamento: departamentosListado) {
                System.out.println("Nombre dpto:" + departamento.getDnombre());
            }
            
            System.out.println("***Trae 3 registros empezando en la posición 0***");
            departamentosListado = departamentosJpaController.findDepartamentosEntities(3,0);
            
            for(Departamentos departamento: departamentosListado) {
                System.out.println("Nombre dpto:" + departamento.getDnombre());
            }
            
            System.out.println("***Trae 3 registros empezando en la posición 1***");
            
            departamentosListado = departamentosJpaController.findDepartamentosEntities(3,1);
            
            for(Departamentos departamento: departamentosListado) {
                System.out.println("Nombre dpto:" + departamento.getDnombre());
            }
            

            
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void contarNumeroDepartamentos(){
              
        try {
            int nElementos = departamentosJpaController.getDepartamentosCount();
            System.out.println("Nº departamentos:" + nElementos);
                      
            
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void listarUnDepartamento(){
              
        try {
            Departamentos departamento = departamentosJpaController.findDepartamentos((short)10);
            System.out.println(departamento.getDnombre());
                      
            
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modificarDepartamento(int id){
        
        Departamentos departamento = departamentosJpaController.findDepartamentos((short) id);  // Si no cargamos el departamento a modificar, dará error con los 
                                                                                                // empleados relacionados.
        
        departamento.setDnombre("IA");
        departamento.setLoc("SEVILLA");
        
        
        try {
            departamentosJpaController.edit(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * C) EJEMPLOS QUE SI UTILIZAN JPQL 
     * 
     * https://www.objectdb.com/java/jpa/query
     */
    
    public static void modificarDatosConJPQL(){
        Query query = entitymanager.createQuery("UPDATE Departamentos d SET d.dnombre= :valorNuevo  WHERE d.deptNo = :deptNoV");
        query.setParameter("valorNuevo", "PRUEBAS");
        query.setParameter("deptNoV", (short)(99));

        entitymanager.getTransaction().begin();
        int updateCount = query.executeUpdate();        
        entitymanager.getTransaction().commit();           
    }   
    
    public static void borrarDatosConJPQL(){
        Query query = entitymanager.createQuery("DELETE FROM Departamentos d WHERE d.deptNo = :deptNoV");
        query.setParameter("deptNoV", (short)(99));

        entitymanager.getTransaction().begin();
        int deletedCount = query.executeUpdate();        
        entitymanager.getTransaction().commit();           
    }
    
    public static void consultaAlmacenada(){
        Query query = entitymanager.createNamedQuery("Departamentos.findAll");

        List<Departamentos> list = query.getResultList( );

        for( Departamentos e:list ){
           System.out.println("Dept NAME :" + e.getDnombre());
        }
    }
               
    public static void consultaAlmacenadaConParametro(int deptNoP){
        Query query = entitymanager.createNamedQuery("Departamentos.findByDeptNo");

        query.setParameter("deptNo", deptNoP);
        List<Departamentos> list = query.getResultList( );

        for( Departamentos e:list ){
           System.out.println("Dept NAME :" + e.getDnombre());
        }
   }
    
    public static void consultaSimple(){
        Query query = entitymanager.createQuery("Select UPPER(d.dnombre) from Departamentos d");

        List<String> list = query.getResultList();

        for(String e:list) {
           System.out.println("Dept NAME :"+e);
        }
    }

    public static void consultaVariosCampos(){
        TypedQuery<Object[]> query = entitymanager.createQuery("Select d.dnombre,d.loc from Departamentos d", Object[].class);

        List<Object[]> list = query.getResultList();

        for(Object[] e:list) {
           System.out.println("Dept NAME :"+e[0]);
           System.out.println("Dept LOC :"+e[1]);
        }
    }
    
    /**
     * Vamos a hacer la siguiente consulta con CriteriaQuery: 
     * 
     * Select d from Departamentos d
     */
    public static void consultaConCriteriaQuery(){
                
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder(); //Fábrica de criterios

        //https://docs.oracle.com/javaee/7/api/javax/persistence/criteria/CriteriaQuery.html
        //CriteriaQUery<Departamentos>: Representa el tipo del resultado que se obtendrá de la consulta
        // cb.createQuery(Departamentos.class): Método que crea un objeto para una consulta  
        CriteriaQuery<Departamentos> query = cb.createQuery(Departamentos.class); //Representa la consulta 
        
        Root<Departamentos> c = 
                query.from(Departamentos.class); //Especificamos el from  
                query.select(c); //Indicamos los campos a seleccionar
   
        
        List<Departamentos> list = entitymanager.createQuery(query).getResultList();

        for( Departamentos e:list ){
           System.out.println("Dept NAME :" + e.getDnombre());
        }
    }
    
    /**
     * Vamos a hacer la siguiente consulta con CriteriaQuery: 
     * 
     * Select d.dnombre,d.loc from Departamentos d
     */
    public static void consultaConCriteriaQueryVariosCampos(){
                
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder(); //Fábrica de criterios

        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class); //Representa la consulta 
        
        Root<Departamentos> c = 
                query.from(Departamentos.class); //Especificamos el from  
                query.select(cb.array(c.get("dnombre"),c.get("loc"))); //Indicamos los campos a seleccionar
  
            
        List<Object[]> list = entitymanager.createQuery(query).getResultList();

        for( Object[] e:list ){
           System.out.println("Dept NAME :"+e[0]);
           System.out.println("Dept LOC :"+e[1]);
        }
    }
     
    
    /**
     * OTROS MÉTODOS 
     */
    
    public static void esperar(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pulsa Enter para continuar...");
        try {
            String sTexto = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2024.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
