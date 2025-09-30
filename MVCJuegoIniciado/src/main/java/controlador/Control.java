/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import interfaces.IControl;
import interfaces.IModeloModificable;
import modelo.Modelo;

/**
 *
 * @author Ramon Valencia
 */
public class Control implements IControl{
    private static Control instanciaControl;
    private IModeloModificable modelo = Modelo.getInstaciaModelo(); 
    
    private Control() {
    }
    
    public static Control getInstanciaControl() {
        if (instanciaControl == null) {
            instanciaControl = new Control();
        }
        return instanciaControl;
    }

    @Override
    public void agregarJugador() {
        modelo.agregarJugador();
    }
    
}
