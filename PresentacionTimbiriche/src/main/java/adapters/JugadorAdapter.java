/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;


import java.util.List;
import objetosModeloJuego.Jugador;
import objetosPresentacion.JugadorVisual;
import utils.ColorConverter;

/**
 *
 * @author Ramon Valencia
 */
public class JugadorAdapter {
    
    private static JugadorVisual toJVisual(Jugador j) {
        JugadorVisual jv = new JugadorVisual(
                j.getNombre(),
                j.getImagen(),
                ColorConverter.obtenerColorJugador(j.getColor())
        );
        jv.setIdentificador(j.getIdJugador());
        return jv;
    }
    
    public static List<JugadorVisual> mapeoJugadores(List<Jugador> jugadores, List<JugadorVisual> listaJugadores) {
        listaJugadores.clear();
        for (Jugador j : jugadores) {
            listaJugadores.add(
                    JugadorAdapter.toJVisual(j)
            );
        }
        return listaJugadores;
    }
    
    
}
