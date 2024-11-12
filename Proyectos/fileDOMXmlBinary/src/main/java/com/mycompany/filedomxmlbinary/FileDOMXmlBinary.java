/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.filedomxmlbinary;

import com.mycompany.filedomxmlbinary.modelo.Conversor;
import com.mycompany.filedomxmlbinary.modelo.Empleado;
import java.util.List;

/**
 *
 * @author Ivan Sobrino
 */
public class FileDOMXmlBinary {

    public static void main(String[] args) {
        Conversor modeloConversor = new Conversor("./Resources/empleados.dat", "DatosEmpleados");
        List<Empleado> listaEmpleados = modeloConversor.lecturaEmpleados();
        modeloConversor.fromEmpleadoToXML(listaEmpleados);
    }
}
