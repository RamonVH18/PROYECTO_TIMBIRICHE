/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import DTOs.DireccionDTO;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public class NuevoJugadorEvent {
    private Jugador jugador;
    private DireccionDTO direccion;
    
    public NuevoJugadorEvent(Jugador jugador, DireccionDTO direccion) {
        this.jugador = jugador;
        this.direccion = direccion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }
    
    
}
