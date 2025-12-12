/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.controlador;

import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ControlJuegoInicio implements IControlJuegoInicio {

    private final IModeloModificableJInicio modelo;

    public ControlJuegoInicio(IModeloModificableJInicio modelo) {
        this.modelo = modelo;
    }

    @Override
    public void marcarListo() {
        modelo.marcarListo();
    }

    @Override
    public void marcarNoListo() {
        modelo.marcarNoListo();
    }

    @Override
    public void mostrarLobby() {
        modelo.mostrarLobby();
    }

}
