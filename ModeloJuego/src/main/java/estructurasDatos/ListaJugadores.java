/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructurasDatos;

import java.util.ArrayList;
import java.util.Comparator;
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
    
    public void crearOrdenJugadores() {
        jugadores.sort(Comparator.comparing(j -> j.getIdJugador()));
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

    public void actualizarDireccionesPeers(List<Jugador> jugadoresActualizados) {
        jugadores = new ArrayList<>(jugadoresActualizados);
    }
}
