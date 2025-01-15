/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ventanagrafica.vista;

import com.isc.ventanagrafica.Usuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 15 ene 2025
 */
public class VentanaLogin extends JFrame {
    
    private final JTextField campoUsuario;
    private final JPasswordField campoContrasena;
    private final JButton botonLogin;
    private final JButton botonCrearUsuario;
    
    private Usuario usuarioRegistrado;
    /**
     * Genera la interfaz gráfica
     */
    public VentanaLogin() {
        super("Iniciar sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10,10));
        
        // Panel para los campos de entrada
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelCampos.add(new JLabel("Usuario:"));
        campoUsuario = new JTextField(15);
        panelCampos.add(campoUsuario);

        panelCampos.add(new JLabel("Contraseña:"));
        campoContrasena = new JPasswordField(15);
        panelCampos.add(campoContrasena);
        
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        
        //Panel para el botón
        JPanel panelBoton = new JPanel();
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.setActionCommand("LOGIN");
        botonCrearUsuario = new JButton("Crear usuario");
        botonCrearUsuario.setActionCommand("CREAR_USUARIO");
        
        panelBoton.add(botonLogin);
        panelBoton.add(botonCrearUsuario);
        
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
        
        //Añadir el panel principal al contenido
        getContentPane().add(panelPrincipal);
        
        inicializarEventos();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        //Inicialmente no hay usuario registrado
        usuarioRegistrado = null;
    }
    
    /**
     * Método para inicializar los eventos de los botones
     */
    private void inicializarEventos() {
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioRegistrado == null) {
                    JOptionPane.showMessageDialog(null, "No hay ningún usuario registrado. Por favor, cree un usuario primero.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String nombre = campoUsuario.getText();
                    String contrasena = new String(campoContrasena.getPassword());

                    if (usuarioRegistrado.getNombre().equals(nombre) && usuarioRegistrado.getContrasena().equals(contrasena)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        abrirPantallaNueva();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                        campoUsuario.setText("");
                        campoContrasena.setText("");
                    }
                }
            }
        });
    }
    
    /**
     * Método para abrir una nueva pantalla tras un inicio de sesión exitoso
     */
    private void abrirPantallaNueva() {
        JFrame nuevaPantalla = new JFrame("Pantalla Principal");
        nuevaPantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaPantalla.setSize(300, 200);
        nuevaPantalla.add(new JLabel("Bienvenido, " + usuarioRegistrado.getNombre() + "!", SwingConstants.CENTER));
        nuevaPantalla.setLocationRelativeTo(null);
        nuevaPantalla.setVisible(true);
        this.dispose();
    }
        
    public String getUsuario() {
        return campoUsuario.getText();
    }
    
    public String getContrasena() {
        return new String(campoContrasena.getPassword());
    }
    
    public void setControladorLogin(java.awt.event.ActionListener controlador) {
        botonLogin.addActionListener(controlador);
    }
    
    public static void main(String[]args) {
        new VentanaLogin();
    }
}
