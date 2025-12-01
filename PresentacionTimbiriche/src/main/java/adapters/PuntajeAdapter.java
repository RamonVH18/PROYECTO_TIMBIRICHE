/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import java.util.List;
import objetosModeloJuego.Puntaje;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.PuntajeVisual;

/**
 *
 * @author Ramon Valencia
 */
public class PuntajeAdapter {
    public static List<PuntajeVisual> mapeoPuntajes(List<PuntajeVisual> listaPuntajes, List<JugadorVisual> listaJugadores, List<Puntaje> puntajes) {
        listaPuntajes.clear();
        for (Puntaje p : puntajes) {
            for (JugadorVisual j : listaJugadores) {
                if (p.getIdJugador().equals(j.getIdentificador())) {
                    listaPuntajes.add(
                            new PuntajeVisual(j.getNombre(), p.getPuntuacion(), j.getColor())
                    );
                }
            }
        }
        return listaPuntajes;
    }
}
