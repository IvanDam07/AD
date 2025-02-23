/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.existdb;

import java.io.StringWriter;
import static java.lang.String.format;
import static java.text.MessageFormat.format;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class ExistDb {

    //Variables para la conexión a la bbdd
    private static XQDataSource server;
    private static XQConnection con;

    public static void main(String[] args) {
        conecta();

        //consulta("/EMPLEADOS");
        //modificacion("update rename /EMPLEADOS/fila_emple as 'EMP_ROW");
        //addEmpleadoPos2();
        //updateSalarioMAT1();
        //mostrarEmpleadosDepartamento();
        //insertDep();
        //borraDep();
        //modificarDep();
        //consulta("/departamentos");
        //verApellidosEmpleados();
        //verNumEmpleados();
        //verApellidoSinComision();
        //verNombreMadBcn();
        //verEmpNoOficio();
        //funcionesAgregacion();
        //verEmpleadoEmpApeAnio();
        //verEmpMA();
        //apeMayus();
        //titularesAhorro();
        //numTalavera();
        //nodoCompleto();
        //empProfesor();
        //ultEmp();
        directorZona30();
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
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void desconecta() {
        try {
            con.close();
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve true si existe lo que consultamos
     *
     * @param textoConsulta
     * @return
     */
    private static boolean consultaB(String textoConsulta) {
        boolean resultadoBool = false;
        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;
            consulta = con.prepareExpression(textoConsulta);
            resultado = consulta.executeQuery();

            XQResultItem r_item;

            while (resultado.next()) {
                if (!resultadoBool) {
                    resultadoBool = true;
                }
            }
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoBool;
    }

    private static void modificacion(String textoDML) {
        try {
            XQExpression expresion;

            expresion = con.createExpression();

            expresion.executeCommand(textoDML);
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 1) A partir del documento universidad.xml, crea un programa en Java que:
     */
    // - Añade un empleado al departamento que ocupa la posición 2. Los datos son: 
    //	Salario: 2340
    //	Puesto: Técnico
    //	Nombre: Pedro Fraile
    private static void addEmpleadoPos2() {
        modificacion(
                "update insert "
                + "<empleado salario='2340'>"
                + "<puesto>Técnico</puesto>"
                + "<nombre>Pedro Fraile</nombre>"
                + "</empleado> "
                + "into /universidad/departamento[2]");
    }

    // - Actualiza el salario de los empleados del departamento con código MAT1 sumándoles 100€.
    private static void updateSalarioMAT1() {
        modificacion(
                "for $empleado in /universidad/departamento[codigo='MAT1']/empleado/@salario "
                + "return update value $empleado with string(number($empleado) + 100)");
    }

    //- Crea un método que lea de teclado un departamento y visualice sus empleados.
    private static void mostrarEmpleadosDepartamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el código del departamento: ");
        String codigoDept = scanner.nextLine();

        String consulta = "/universidad/departamento[codigo='" + codigoDept + "']/empleado";
        System.out.println("Empleados del departamento " + codigoDept + ": ");
        consulta(consulta);
    }

    //2. Realiza un programa en Java que inserte, elimine y modifique departamentos del documento departamentos.xml
    // Utiliza las sentencias de actualización de eXist. Haer que la función main() llame y ejecute los siguientes métodos (no devuelven nada):
    // Insertdep(): Leerá de teclado un departamento, su nombre y su localidad, y deberá añadirlo al documento. Si el código de departamento existe visualiza
    //que no se puede insertar porque ya existe.
    private static void insertDep() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime el número de departamento: ");
        int dept_no = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Dime el nombre del departamento: ");
        String nomDept = teclado.nextLine();
        System.out.println("Dime la localidad donde se encuentra el departamento: ");
        String localidad = teclado.nextLine();

        //Verificar si el departamento ya existe
        String consultaExiste = "/departamentos/DEP_ROW[DEPT_NO='" + dept_no + "']";
        if (consultaB(consultaExiste)) {
            System.out.println("El departamento ya existe. No se puede insertar");
            return;
        }

        //Insertar el departamento
        String insertarDept = "update insert"
                + "<DEP_ROW><DEPT_NO>" + dept_no + "</DEPT_NO><DNOMBRE>" + nomDept + "</DNOMBRE><LOC>" + localidad + "</LOC></DEP_ROW> "
                + " into /departamentos";

        modificacion(insertarDept);
        System.out.println("Departamento insertado con éxito.");
    }

    private static void borraDep() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Dime el número del departamento que quieres eliminar: ");
        int deptNo = teclado.nextInt();

        //Verificar si existe
        String consultaExiste = "/departamentos/DEP_ROW[DEPT_NO='" + deptNo + "']";
        if (!consultaB(consultaExiste)) {
            System.out.println("No se puede borrar el departamento porque no existe.");
            return;
        } else {
            //Borrar el departamento
            String deleteQuery = "update delete /departamentos/DEP_ROW[DEPT_NO='" + deptNo + "']";
            modificacion(deleteQuery);
            System.out.println("Se ha eliminado con éxito el departamento.");
        }

    }

    private static void modificarDep() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime el número del departamento a modificar: ");
        int deptNo = teclado.nextInt();
        teclado.nextLine();

        //Verificar si existe
        String consultaExiste = "/departamentos/DEP_ROW[DEPT_NO='" + deptNo + "']";
        if (!consultaB(consultaExiste)) {
            System.out.println("No se puede modificar el departamento porque no existe.");
            return;
        } else {
            System.out.println("Dime el nuevo nombre del departamento: ");
            String nombre = teclado.nextLine();
            System.out.println("Dime la nueva localidad del departamento: ");
            String localidad = teclado.nextLine();

            //Modificar el departamento
            // Modificar el nombre del departamento
            String updateNombre = "update value /departamentos/DEP_ROW[DEPT_NO='" + deptNo + "']/DNOMBRE with '" + nombre + "'";
            modificacion(updateNombre);

            // Modificar la localidad del departamento
            String updateLocalidad = "update value /departamentos/DEP_ROW[DEPT_NO='" + deptNo + "']/LOC with '" + localidad + "'";
            modificacion(updateLocalidad);

            System.out.println("Departamento modificado con éxito.");
        }
    }

    /*
    
    
    EJERCICIOS GPT
    
    
    
     */
    /**
     * Archivo: empleados.xml Obtén todos los apellidos de los empleados que
     * aparecen en el documento
     */
    private static void verApellidosEmpleados() {
        consulta("/EMPLEADOS/EMP_ROW/APELLIDO");
    }

    /**
     * empleados.xml Muestra los números de empleado (EMP_NO) de aquellos cuyo
     * salario (SALARIO) sea mayor o igual que 2000
     */
    private static void verNumEmpleados() {
        consulta("/EMPLEADOS/EMP_ROW[SALARIO >=2000]/EMP_NO");
    }

    /**
     * EMPLEADOS.XML Selecciona el apellido de todos aquellos que no tengan
     * comision (COMISION)
     */
    private static void verApellidoSinComision() {
        consulta("/EMPLEADOS/EMP_ROW[not(COMISION)]/APELLIDO");
    }

    /**
     * departamentos.xml Muestra el nombre (DNOMBRE) de los departamentos que
     * están en la localidad (LOC) "MADRID" o "BARCELONA"
     */
    private static void verNombreMadBcn() {
        consulta("/departamentos/DEP_ROW[LOC='MADRID' or LOC='BARCELONA']/DNOMBRE");
    }

    /**
     * empleados.xml Muestra el EMP_NO y el OFICIO de los empleados cuyo salario
     * está entre 1500 y 2000 (ambos incluidos)
     */
    private static void verEmpNoOficio() {
        consulta("/EMPLEADOS/EMP_ROW[SALARIO>=1500 and SALARIO<=2000]/(EMP_NO,OFICIO)");
    }

    /**
     * empleados.xml Utilizando funciones de agregación: El número total de
     * empleados El salario promedio (avg()) de todos los empleados que tengan
     * la etiqueta <SALARIO>
     * El salario mínimo y máximo
     */
    private static void funcionesAgregacion() {
        consulta("count(/EMPLEADOS/EMP_ROW)");
        System.out.println();
        consulta("avg(/EMPLEADOS/EMP_ROW/SALARIO)");
        /**
         * // Obtener la media de los salarios String media =
         * consulta("avg(/EMPLEADOS/EMP_ROW/SALARIO)"); if (media != null &&
         * !media.isEmpty()) { double mediaSalario = Double.parseDouble(media);
         * DecimalFormat df = new DecimalFormat("0.00");
         * System.out.println(df.format(mediaSalario)); }
         */
        System.out.println();
        consulta("min(/EMPLEADOS/EMP_ROW/SALARIO)");
        System.out.println();
        consulta("/max(/EMPLEADOS/EMP_ROW/SALARIO)");
    }

    /**
     * empleados.xml Para cada empleado que tenga fecha de alta (FECHA_ALT),
     * muestra su EMP_NO, el APELLIDO y el año de la fecha de alta
     */
    private static void verEmpleadoEmpApeAnio() {
//        consulta("""
//        for $e in /EMPLEADOS/EMP_ROW
//        where $e/FECHA_ALT
//        return (
//          data($e/EMP_NO),
//          data($e/APELLIDO),
//          year-from-date($e/FECHA_ALT)
//        )
//        """);

        //Todo en la misma linea
        consulta("""
        for $e in /EMPLEADOS/EMP_ROW
        where $e/FECHA_ALT
        return string-join((
            data($e/EMP_NO),
            data($e/APELLIDO),
            year-from-date(xs:date($e/FECHA_ALT))
        ), " - ")
    """);
    }

    /**
     * empleados.xml Muestra el APELLIDO de aquellos empleados cuyo nombre
     * empiece por "MA"
     */
    private static void verEmpMA() {
        consulta("/EMPLEADOS/EMP_ROW[starts-with(APELLIDO,'MA')]/APELLIDO");
    }

    /**
     * empleados.xml De todos los empleados que tengan salario superior a 1400,
     * transforma su apellido a mayúsculas
     */
    private static void apeMayus() {
        consulta("""
                 for $e in /EMPLEADOS/EMP_ROW[SALARIO>1400]
                 return upper-case($e/APELLIDO)
                 """);
    }

    /**
     * sucursales.xml Selecciona los nombres de los titulares de cuenta de tipo
     * 'AHORRO' cuyo saldohaber sea mayor que 10.000 y cuyo saldodebe sea igual
     * a 0
     */
    private static void titularesAhorro() {
        consulta("/sucursales/sucursal/cuenta[@tipo='AHORRO' and saldohaber>10000 and saldodebe=0]/nombre");
    }

    /**
     * sucursales.xml Obtén el número de teléfono (@telefono) de las sucursales
     * que estén en la población de 'Talavera'
     */
    private static void numTalavera() {
        //consulta("/sucursales/sucursal[poblacion='Talavera']/@telefono");
        consulta("""
        for $sucursal in /sucursales/sucursal[poblacion='Talavera']
        return string($sucursal/@telefono)
    """);
    }

    /**
     * sucursales.xml Muestra el nodo completo de todas las cuentas (tipo
     * "AHORRO" y tipo "PENSIONES") que correspondan a un titular cuyo nombre
     * contenga el texto "María"
     */
    private static void nodoCompleto() {
        consulta("/sucursales/sucursal/cuenta[(@tipo='PENSIONES' or @tipo='AHORRO') and contains(nombre,'María')]");
    }

    /**
     * universidad.xml Muestra los nombres y puestos de los empleados que tengan
     * el puesto "Profesor" en cualquiera de los departamentos
     */
    private static void empProfesor() {
        consulta("/universidad/departamento/empleado[puesto='Profesor']/(nombre,puesto)");
    }

    /**
     * EJERCIO 14 PREGUNTAR universidad.xml Selecciona el nombre y el salario
     * del empleado que aparezca en último lugar dentro de cada <departamento>
     */
    private static void ultEmp() {
        consulta("""
                 for $d in /universidad/departamento
                 return $d/empleado[last()]/(nombre,string(@salario))
                 """);
    }

    /**
     * zonas.xml Selecciona el director de la zona cuyo cod_zona sea 30, y
     * concatena el texto "DIRECTOR: " antes de su nombre
     */
    private static void directorZona30() {
        consulta("""
                 let $dir := /zonas/zona[cod_zona=30]/director
                 return concat('DIRECTOR: ',data($dir))
                 """);
    }

    /**
     * Uso de ejes empleados.xml a) Selecciona el padre (parent) del nodo
     * <OFICIO> de cualquier empleado. b) Selecciona los descendientes
     * (descendant) del nodo raíz que sean <EMP_ROW>. c) Selecciona todos los
     * antepasados (ancestor) del último <EMP_ROW>.
     */
    private static void ejes() {
        consulta("/EMPLEADOS/EMP_ROW/OFICIO/parent::*");
        consulta("/EMPLEADOS/descendant::EMP_ROW");
        consulta("(/EMPLEADOS/EMP_ROW[last()])/ancestor::*");
    }

    /**
     * (Actualización: insert) Archivo: empleados.xml Enunciado: Inserta un
     * nuevo empleado al final del documento, con la siguiente estructura
     * (puedes inventarte datos):
     */
    private static void insertarFinalDocumento() {
        consulta("update insert \n"
                + "  <EMP_ROW>\n"
                + "    <EMP_NO>9999</EMP_NO>\n"
                + "    <APELLIDO>NUEVO</APELLIDO>\n"
                + "    <OFICIO>PRACTICAS</OFICIO>\n"
                + "    <DIR>7839</DIR>\n"
                + "    <FECHA_ALT>1992-05-22</FECHA_ALT>\n"
                + "    <SALARIO>1100</SALARIO>\n"
                + "    <DEPT_NO>10</DEPT_NO>\n"
                + "  </EMP_ROW>\n"
                + "into /EMPLEADOS");
    }

    /**
     * (Actualización: replace) Archivo: departamentos.xml Enunciado: Reemplaza
     * por completo un nodo <DEP_ROW> cuyo <DEPT_NO> sea 40, cambiándolo a:
     */
    private static void reemplazaNodoCompleto() {
        consulta("update replace \n"
                + "  /departamentos/DEP_ROW[DEPT_NO=40]\n"
                + "with\n"
                + "  <DEP_ROW>\n"
                + "    <DEPT_NO>40</DEPT_NO>\n"
                + "    <DNOMBRE>MARKETING</DNOMBRE>\n"
                + "    <LOC>PAMPLONA</LOC>\n"
                + "  </DEP_ROW>");
    }

    /**
     * (Actualización: value) Archivo: empleados.xml Enunciado: Cambia el
     * SALARIO de aquel empleado con <EMP_NO> = 7499 a un nuevo valor de 1800.
     */
    private static void cambiarSalarioEmpleado() {
        consulta("update value\n"
                + "  /EMPLEADOS/EMP_ROW[EMP_NO=7499]/SALARIO\n"
                + "with '1800'");
    }

    /**
     * (Actualización: delete y rename) Archivo: sucursales.xml Enunciado:
     * Elimina (con update delete) todas las cuentas de tipo “PENSIONES” de la
     * sucursal con código SUC1. Renombra (con update rename) el nodo <director>
     * de la sucursal SUC2 para que pase a llamarse <responsable>. Pista (2):
     * update rename /sucursales/sucursal[@codigo='SUC2']/director as
     * 'responsable'.
     */
    private static void deleteRenamePensiones() {
        consulta("update delete\n"
                + "  /sucursales/sucursal[@codigo='SUC1']/cuenta[@tipo='PENSIONES']");
        consulta("update rename\n"
                + "  /sucursales/sucursal[@codigo='SUC2']/director\n"
                + "as 'responsable'");
    }
    
    /**
     * MÉTODOS JUAN
     */
    private boolean existeNodo(String inputConsulta) {
    try {
      XQExpression xqConsulta = connection.createExpression();
      String cadenaConsulta = "exists(" + inputConsulta + ")";
      XQResultSequence xqResultado = xqConsulta.executeQuery(cadenaConsulta);
        
      return xqResultado.next() && xqResultado.getBoolean();
    } catch (XQException ex) {
      ex.printStackTrace();
      return false;
    }
  }
    
    /**
     * Si es un atributo
     * @param inputConsulta 
     */
    public void realizarConsulta (String inputConsulta) {
    try {
      XQPreparedExpression xqConsulta = connection.prepareExpression(inputConsulta);
      XQResultSequence xqResultado = xqConsulta.executeQuery();
      
      XQResultItem resultItem;
      while (xqResultado.next()) {
        resultItem = (XQResultItem) xqResultado.getItem();
        
        if (resultItem.getItemType().getBaseType() == XQItemType.XQBASETYPE_STRING) {
          System.out.println(resultItem.getAtomicValue());
        } else {
          System.out.println(eliminarNamespace(resultItem));
        }
      }
    } catch (XQException ex) {
      ex.printStackTrace();
    }
  }
    
     // metodo eliminar cabeceras del xml =>
  private String eliminarNamespace (XQResultItem inputItem) {
    StringWriter writer = new StringWriter();
    
    try {
      Node node = (Node) inputItem.getNode();
      
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.transform(new DOMSource(node), new StreamResult(writer));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    
    return writer.toString();
  }
}
