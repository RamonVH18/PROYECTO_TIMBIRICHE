/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import objetosModeloJuego.Jugador;
import objetosPresentacion.JugadorVisual;

/**
 *
 * @author Ramon Valencia
 */
public class JugadorAdapter {
    
    public static JugadorVisual toJVisual(Jugador j) {
        JugadorVisual jv = new JugadorVisual(
                j.getNombre(),
                j.getImagen(),
                j.getColor()
        );
        jv.setIdentificador(j.getIdJugador());
        return jv;
    }
    
}
