/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fileaccesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Lectura extends FicheroEmpleados {

    public Lectura(String ruta) {
        super(ruta);
    }

    /**
     * Devuelve un empleado con el identificador dado
     *
     * @param identificador Id del empleado que queremos mostrar
     * @return Empleado con sus datos
     */
    public Empleado lecturaEmpleado(int identificador) {

        // RamdomAccessFile para acceso a un fichero de forma aleatoria
        RandomAccessFile randomFile = null;
        int posicion = 0;
        Empleado empleado = new Empleado();
        // Creamos un array de byte para la lectura del apellido.
        // El tamaño en bytes será la longitud del apellido que hemos declarado en la clase FicheroEmpleados
        byte[] cadena = new byte[super.getLONGITUD_APELLIDO_EN_BYTES()];

        try {

            randomFile = new RandomAccessFile(getRuta(), "r"); //abrimos solo lectura, no hace falta que abramos en modo rw (lectura-escritura)

            // La posicion se calcula a partir del Id. 
            // Imagina que el Id es 3 y que la longitud del registro es 40 (nos lo da getLONGITUD_TOTAL) => (3-1) * 40 = 80.
            // Los datos del empleado 3 estarían a partir de la posición 80
            posicion = (identificador - 1) * super.getLONGITUD_TOTAL();

            // Verificamos que el id corresponde a una posición menor que la longitud del archivo
            if (posicion < randomFile.length()) {
                randomFile.seek(posicion);

                // Leemos el identificador del empleado
                empleado.setIdentificador(randomFile.readLong());
                // Leemos el apellido
                randomFile.read(cadena);
                empleado.setApellido(new String(cadena));
                // Leemos el número del departamento
                empleado.setDepartamento(randomFile.readInt());
                // Leemos el salario
                empleado.setSalario(randomFile.readDouble());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Devolvemos al empleado
        return empleado;
    }

    /**
     * Devuelve una lista con todos los empleados almacenados en el fichero
     *
     * @return Lista de empleados con sus datos
     */
    public List<Empleado> lecturaTodosLosEmpleados() {
        //creamos una lista para almacenar los empleados
        List<Empleado> empleados = new ArrayList<>();

        RandomAccessFile randomFile = null;
        int posicion = 0;

        byte[] cadena = new byte[super.getLONGITUD_APELLIDO_EN_BYTES()];

        try {

            randomFile = new RandomAccessFile(getRuta(), "r");//Entramos como solo lectura

            //Compruebo mientras que tenga suficientes bytes
            while (posicion + super.getLONGITUD_TOTAL() <= randomFile.length()) {

                Empleado empleado = new Empleado();

                //Vamos al siguiente regsitro
                randomFile.seek(posicion);

                // Leer los datos del empleado en la posición actual
                empleado.setIdentificador(randomFile.readLong());
                randomFile.read(cadena); //Aquí lo leemos y abajo lo compartimos
                // Convertir byte[] a String
                empleado.setApellido(new String(cadena));
                empleado.setDepartamento(randomFile.readInt()); // Departamento
                empleado.setSalario(randomFile.readDouble()); // Salario

                // Metemos el empleado en la lista
                empleados.add(empleado);

                // Saltamos a la siguiente posicion (longitud del registro)
                posicion += super.getLONGITUD_TOTAL();
            }
        } catch (FileNotFoundException ex) {

            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                //Recomendacion ChatGPT porque daba error si solo lo cerraba
                if (randomFile != null) {
                    randomFile.close();
                }

            } catch (IOException ex) {

                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Devolvemos la lista
        return empleados;
    }

    //realmente se puede hacer todo en un metodo, este es una adaptacion que usa el de arriba para que no muestre los ids a 0
    public void mostrarTodosLosRegistros() {
        List<Empleado> empleados = lecturaTodosLosEmpleados();

        if (empleados.isEmpty()) {
            System.out.println("No hay registros almacenados.");
        } else {
            System.out.println("Registros de empleados:");
            for (Empleado empleado : empleados) {
                // Solo mostramos empleados cuyo identificador es diferente de 0
                if (empleado.getIdentificador() != 0) {
                    System.out.println(empleado);
                }
            }
        }
    }

}
