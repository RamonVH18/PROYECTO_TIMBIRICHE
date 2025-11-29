/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import java.awt.Color;
import objetosModeloJuego.Jugador;
import objetosPresentacion.JugadorVisual;
import utils.ColorConverter;

/**
 *
 * @author Ramon Valencia
 */
public class JugadorAdapter {
    
    public static JugadorVisual toJVisual(Jugador j) {
        JugadorVisual jv = new JugadorVisual(
                j.getNombre(),
                j.getImagen(),
                ColorConverter.obtenerColorJugador(j.getColor())
        );
        jv.setIdentificador(j.getIdJugador());
        return jv;
    }
}
