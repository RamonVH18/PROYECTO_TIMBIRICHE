/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.DireccionDTO;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public interface MediadorEventos {

    public void recibirNuevaDireccion(DireccionDTO direccion, Jugador jugador);
    
    public void solicitarInfoNuevoJugador(DireccionDTO direccion);
    
    public Jugador obtenerJugadorPorNuevaDireccion(DireccionDTO direcion);
    
    public void transmitirInfoANuevoJugador(DireccionDTO direccion);
    
    public void registrarNuevoJugador(Jugador jugador, DireccionDTO direccion);
    
    
}
