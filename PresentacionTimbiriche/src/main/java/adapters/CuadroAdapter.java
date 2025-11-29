/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import java.awt.Color;
import java.awt.Point;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosPresentacion.CuadroTablero;
import utils.ColorConverter;

/**
 *
 * @author Ximena
 */
public class CuadroAdapter {
    public static CuadroTablero toCuadroTablero(Cuadro c, Point esquina, int distancia) {
        CuadroTablero ct = new CuadroTablero(esquina, distancia);
        
        if (c.estaCompleto()) {
            Color color = ColorConverter.obtenerColorJugador(c.getJugador().getColor());
            ct.actualizarEstado(color, true);
        }
        
        return ct;
    }
    
    public static Cuadro toCuadro(CuadroTablero ct, Linea sup, Linea inf, Linea izq, Linea der, Jugador jugador) {
        Cuadro c = new Cuadro(sup, inf, izq, der);
        if (ct != null && jugador != null) {
            c.asignarCuadro(jugador);
        }
        
        return c;
    }
    
}
