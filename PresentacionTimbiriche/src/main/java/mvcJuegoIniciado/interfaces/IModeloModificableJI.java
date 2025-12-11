/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoIniciado.interfaces;

import enums.ObserverType;
import objetosPresentacion.LineaTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificableJI {

    public void añadirObserver(IVista v, ObserverType tipo);
    
    public void realizarJugada(LineaTablero lineaSelecionada);
    
    public void mostrarPantalla(ObserverType tipo);
    
    public void ocultarPantalla(ObserverType tipo);
    
    public void abandonarPartida();
}
