/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreamcaracteres.Controlador;

import com.mycompany.filestreamcaracteres.Modelo.Escritura;
import com.mycompany.filestreamcaracteres.Modelo.Fichero;
import com.mycompany.filestreamcaracteres.Modelo.Lectura;
import com.mycompany.filestreamcaracteres.Vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ivan Sobrino
 */
public class ControladorFileStream implements ActionListener {

    private InterfazVista vista;
    private Escritura escritura;
    private Lectura lectura;
    private Fichero fichero;

    private String path;
    private boolean noSobreescribir;

    public ControladorFileStream(InterfazVista inputVista, Fichero fichero, Escritura escritura, Lectura lectura) {
        this.vista = inputVista;
        this.escritura = escritura;
        this.lectura = lectura;
        this.fichero = fichero;

        this.vista.setControladorFileStreams(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        switch (evento.getActionCommand()) {

            case InterfazVista.LEER_CARACTER_A_CARACTER -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.lectura.leerCaracterACaracter();
                this.vista.operacionTerminada();
            }

            case InterfazVista.LEER_FICHERO_ARRAY_CARACTERES -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.lectura.leerArrayCaracteres();
                this.vista.operacionTerminada();
            }

            case InterfazVista.LEER_FICHERO_LINEA -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.lectura.leerCaracteresBufferReader();
                this.vista.operacionTerminada();
            }

            case InterfazVista.ESCRIBIR_CARACTER -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.noSobreescribir = this.vista.sobreescribirFichero();
                char inputChar = this.vista.leerChar();

                this.escritura.escribirCaracter(inputChar, noSobreescribir);
                this.vista.operacionTerminada();
            }

            case InterfazVista.ESCRIBIR_ARRAY_CARACTERES -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.noSobreescribir = this.vista.sobreescribirFichero();
                char[] inputChars = this.vista.leerCaracteres();

                this.escritura.escribirArrayCaracteres(inputChars, noSobreescribir);
                this.vista.operacionTerminada();
            }

            case InterfazVista.ESCRIBIR_LINEA -> {
                this.path = this.vista.getRuta();
                this.fichero.setRuta(this.path);

                this.noSobreescribir = this.vista.sobreescribirFichero();
                String stringLine = this.vista.leerString();

                this.escritura.escribirStreamBufferedCaracteres(stringLine, noSobreescribir);
                this.vista.operacionTerminada();
            }
        }
    }
}
