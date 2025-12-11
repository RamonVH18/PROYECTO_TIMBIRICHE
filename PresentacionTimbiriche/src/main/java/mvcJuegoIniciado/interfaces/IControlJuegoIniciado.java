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
public interface IControlJuegoIniciado {

    public void realizarJugada(LineaTablero lineaSeleccionada);
    
    public void mostrarVista(ObserverType tipo);
    
    public void ocultarVista(ObserverType tipo);
    
    public void abandonarPartida();
}
