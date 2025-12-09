/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.controlador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
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
    public void almacenarImagenYColor(ImagenJugador imagen, ColorJugador color) {
        modelo.almacenarImagenYColorJugador(imagen, color);
    }
    
    @Override
    public void registrarJugador(String nombre, ImagenJugador imagen, ColorJugador color) {
        modelo.registrarJugador(nombre, imagen, color);
    }
    
    @Override
    public void mostrarVista(ObserverType tipo) {
        modelo.mostrarVista(tipo);
    }
    
    @Override
    public void ocultarVista(ObserverType tipo) {
        modelo.ocultarVista(tipo);
    }
}
