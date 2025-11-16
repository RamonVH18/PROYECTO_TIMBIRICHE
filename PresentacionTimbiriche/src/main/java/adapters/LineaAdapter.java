/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import java.awt.Point;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;
import objetosPresentacion.EstadoLinea;
import objetosPresentacion.LineaTablero;

/**
 *
 * @author Ximena
 */
public class LineaAdapter {
    
    public static LineaTablero toLineaTablero(Linea l) {
        Point a = new Point(
                l.getPuntoA().getCoordenadaX(),
                l.getPuntoA().getCoordenadaY()
        );
        
        Point b = new Point(
                l.getPuntoB().getCoordenadaX(),
                l.getPuntoB().getCoordenadaY()
        );
        
        if (l.estaOcupada()) {
            return new LineaTablero(a, b, EstadoLinea.OCUPADA);
        }
            return new LineaTablero(a, b, EstadoLinea.LIBRE);
        
    }
    
    public static Linea toLinea(LineaTablero lt) {
        return new Linea(
                new Punto(lt.puntoA.x,
                        lt.puntoA.y), 
                new Punto(lt.puntoB.x,
                        lt.puntoB.y)
        );
    }
}
