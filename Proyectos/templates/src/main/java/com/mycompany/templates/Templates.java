/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.templates;

/**
 *
 * @author Ivan Sobrino
 */
public class Templates {

    public static void main(String[] args) {
        
        //Implementar el controlador con mayor felicidad
        /*
        public class $ {name}implements ActionListener {
  private final InterfazVista vista;
  private final Modelo modelo;

  public ControladorRegistros (InterfazVista inputVista, Modelo inputModelo) {
  this.vista = inputVista;
  this.modelo = inputModelo;
    
  this.vista.setControlador(this);
}

  public void actionPerformed (ActionEvent evento) {
  switch (evento.getActionCommand()) {
  case InterfazVista.CONSTANTE_EJEMPLO -> {
    this.vista.operacionExitosa();
  }
}
}
}
        */
        
        
        
        
        //Implementar la interfaz vista con mayor facilidad
//        public interface InterfazVista {
//static final String CONSTANTE_EJEMPLO = "esto es una constante de ejemplo";
//
//public void setControlador(Controlador inputControlador);
//public void arranca();
//public void operacionExitosa();
//public void escribeResultado(String cadenaTexto);
//public String getRuta();
//}


//Implementar la ventanatexto con mayor facilidad
/*
public class ${name} implements InterfazVista {
private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
private Controlador controladorGeneral;
private String ruta;

public ${name} () {
  super();
}

private String leerString () {
try {
return in.readLine();
} catch (IOException e) {
System.out.println("ERROR! Introduce correctamente la cadena.");
return null;
}
}

private int leerOpcion () {
try {
String opcion = in.readLine();
return Integer.parseInt(opcion);
} catch (IOException | NumberFormatException e) {
opcionInvalida();
return 0;
}
}

private void procesarNuevaOperacion () {
  mostrarMenu();
  int opcion;
    
  opcion = leerOpcion();
    
  switch (opcion) {
  case 0 -> {
    System.out.println("\n");
    System.exit(0);
}
      
  case 1 -> {
    this.controlRegistros.actionPerformed(new ActionEvent(this, opcion, LEER_EMPLEADO));
}
    }

  procesarNuevaOperacion();
}

@Override
public void arranca() {
  procesarNuevaOperacion();
}

@Override
public void operacionExitosa () {
  System.out.println("Operacion realizada con Exito!");
}

@Override
public void escribeResultado (String inputCadena) {
  System.out.println(inputCadena);
}

@Override
public void setControladorRegistros (ControladorRegistros cR) {
  this.controlRegistros = cR;
}

@Override
public String getRuta () {
  System.out.println(" > Introduce la ruta: ");
  return leerString();
}
}
*/
    }
}
