/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import enums.ObserverType;
import enums.TamañosTablero;
import mvcJuegoIniciado.interfaces.IVista;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificableJInicio {
    
    public void crearPartida(String nombrePartida, int numJugadores, TamañosTablero tamaño);
    
    public void añadirObserver(IVista v, ObserverType tipo);
    
    public void mostrarPantalla(ObserverType tipo);
    
    public void ocultarPantalla(ObserverType tipo);
    
}
