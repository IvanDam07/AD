package com.mmc.mvc.vista;

import com.mmc.mvc.controlador.ControlConversor;
import static com.mmc.mvc.vista.InterfazVista.AEUROS;
import static com.mmc.mvc.vista.InterfazVista.APESETAS;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 *
 */
public class VentanaConversor extends JFrame implements InterfazVista{
    
 private final JButton convertirApesetas;
    private final JButton convertirAeuros;
    private final JTextField cantidad;
    private final JLabel resultado;
    private final JTextField comision;
    

    /**
     * Genera la interfaz gráfica
     */
    public VentanaConversor() {
        
        super("Conversor de Euros y Pesetas");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelPrincipal = new JPanel();
        
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        
        cantidad = new JTextField(8);
        JPanel panelaux = new JPanel();
        panelaux.add(cantidad);
        
        comision = new JTextField(8);
        JPanel panelaux3 = new JPanel();
        panelaux.add(comision);
        
        panelPrincipal.add(panelaux, BorderLayout.NORTH);
        
        resultado = new JLabel("Indique una cantidad y pulse uno de los botones");
        JPanel panelaux2 = new JPanel();
        panelaux2.add(resultado);
        
        panelPrincipal.add(panelaux2, BorderLayout.CENTER);
        
        convertirApesetas = new JButton("A pesetas");
        
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirApesetas.setActionCommand(APESETAS);
        
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirAeuros = new JButton("A euros");
        
        convertirAeuros.setActionCommand(AEUROS);
        
     
        JPanel botonera = new JPanel();
        botonera.add(convertirApesetas);
        botonera.add(convertirAeuros);
        panelPrincipal.add(botonera, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal);
    
    }

    @Override
    public void setControlador(ControlConversor c) {
        convertirApesetas.addActionListener(c);
        convertirAeuros.addActionListener(c);
    }

    @Override
    public void arranca() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public double getCantidad() {
        try{
            return Double.parseDouble(cantidad.getText());//cantidad es el JTextField donde escribe el usuario en la interfaz
        }catch(NumberFormatException e){ //por si acaso mete algo que no sea numérico
            return 0.0D;
        }
        
    }

    @Override
    public void escribeCambio(String s) {
        resultado.setText(s); //resultado es el JLabel donde irá el resultado
    }

    @Override
    public double getComision() {
        try{
            return Double.parseDouble(comision.getText());//cantidad es el JTextField donde escribe el usuario en la interfaz
        }catch(NumberFormatException e){ //por si acaso mete algo que no sea numérico
            return 0.0D;
        }
        }
}
