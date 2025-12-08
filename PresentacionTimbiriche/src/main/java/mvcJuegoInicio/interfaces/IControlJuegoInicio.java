/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import enums.ObserverType;
import enums.TamañosTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IControlJuegoInicio {
    public void crearPartida(String nombrePartida, int numJugadores, TamañosTablero tamaño);
    
    public void mostrarVista(ObserverType tipo);
    
    public void ocultarVista(ObserverType tipo);
}
