/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.controlador;

import java.util.ResourceBundle.Control;

import enums.ObserverType;
import gestion.ManejadorObservers;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ControlJuegoInicio implements IControlJuegoInicio {
    
    private IModeloModificableJInicio modelo;

    public ControlJuegoInicio(IModeloModificableJInicio modelo) {
        this.modelo = modelo;
    }

    @Override
    public void seleccionarPartida() {
        modelo.seleccionarPartida();
    }

    public void mostrarPantallaUnirsePartida(){
        modelo.mostrarPantallaUnirsePartida();
    }

}
