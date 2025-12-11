/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructurasDatos;

import java.util.ArrayList;
import java.util.List;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ximena
 */
public class ListaJugadores {
    private static List<Jugador> jugadores;
    
    public ListaJugadores() {
        jugadores = new ArrayList<>();
    }
    
    public void agregarJugador(Jugador jugador) {
       if (!jugadores.contains(jugador)) {
           jugadores.add(jugador);
       }
    }
    
    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }
    
    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public Jugador obtenerJugador(String idJugador) {
        for (Jugador jugador : jugadores) {
            if(jugador.getIdJugador().equals(idJugador)){
                return jugador;
            }
        }
        return null;
    }
    
}
