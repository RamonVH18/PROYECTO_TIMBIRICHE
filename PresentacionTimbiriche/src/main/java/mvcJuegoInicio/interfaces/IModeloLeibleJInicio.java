/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import enums.ObserverType;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeibleJInicio {
    public boolean isMostrandoUnirsePartida();
    public boolean isMostrandoMainMenu();
    public boolean isMostrandoPantallaMock();
    public boolean isPartidaCargada();
    public String obtenerNombrePartida();
    public int obtenerMaxJugadores();
    public String obtenerBoardSize();
    public void activarPantallas(ObserverType tipo);
    public void ocultarPantallas(ObserverType tipo);
}
