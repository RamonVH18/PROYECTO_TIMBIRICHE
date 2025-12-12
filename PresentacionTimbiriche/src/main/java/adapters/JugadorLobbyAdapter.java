/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import java.util.ArrayList;
import java.util.List;
import objetosModeloJuego.Jugador;
import objetosPresentacion.JugadorLobbyVisual;
import utils.ColorConverter;

/**
 *
 * @author multaslokas33
 */
public class JugadorLobbyAdapter {

    public static JugadorLobbyVisual toJugadorLobbyVisual(Jugador j) {
        return new JugadorLobbyVisual(
                j.getIdJugador(),
                j.getNombre(),
                j.getImagen().name(),
                ColorConverter.obtenerColorJugador(j.getColor()),
                j.isListo()
        );
    }

    public static List<JugadorLobbyVisual> mapeoJugadoresLobby(List<Jugador> jugadores) {
        List<JugadorLobbyVisual> listaJugadoresLobby = new ArrayList<>();
        for (Jugador j : jugadores) {
            listaJugadoresLobby.add(toJugadorLobbyVisual(j));
        }
        return listaJugadoresLobby;
    }

}
