/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificableJInicio {
    
    public void almacenarImagenYColorJugador(ImagenJugador imagen, ColorJugador color);
    
    public boolean registrarJugador(String nombre, ImagenJugador imagen, ColorJugador color);
    
    public void mostrarVista(ObserverType tipo);
    
    public void ocultarVista(ObserverType tipo);
}
