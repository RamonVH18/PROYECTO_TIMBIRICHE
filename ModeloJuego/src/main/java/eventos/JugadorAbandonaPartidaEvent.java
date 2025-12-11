/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import DTOs.DireccionDTO;
import objetosModeloJuego.Jugador;

/**
 *
 * @author janot
 */
public class JugadorAbandonaPartidaEvent {
    Jugador jugador;
    DireccionDTO direccionDTO;

    public JugadorAbandonaPartidaEvent(Jugador jugador, DireccionDTO direccionDTO) {
        this.jugador = jugador;
        this.direccionDTO = direccionDTO;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public DireccionDTO getDireccionDTO() {
        return direccionDTO;
    }

}
