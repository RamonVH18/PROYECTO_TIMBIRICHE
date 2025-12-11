/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public interface ObservadorJuego {
    
    public void cambiarTurno(boolean turno);
    
    public void jugadorAbandonoPartida(Jugador jugador);
    
}
