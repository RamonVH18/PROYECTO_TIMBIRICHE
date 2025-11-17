/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTOs.DireccionDTO;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;

/**
 *
 * @author Ramon Valencia
 */
public interface MediadorEventos {
    
    public void solicitarInfoNuevoJugador(DireccionDTO direccion);
    
    public void transmitirInfoANuevoJugador(DireccionDTO direccion);
    
    public void registrarNuevoJugador(Jugador jugador, DireccionDTO direccion);
    
    public void actualizarLineasCuadros(Linea linea);
    
}
