/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.filexmlconversor;

import com.mmc.filexmlconversor.modelo.Conversor;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Filexmlconversor {

    public static void main(String[] args) {
        
        Conversor modelo = new Conversor("./recursos/empleados.xml","./recursos/empleadosPlantilla.xsl","./recursos/empleados.html");
        
        modelo.convertirAHTML();
        
    }
}
