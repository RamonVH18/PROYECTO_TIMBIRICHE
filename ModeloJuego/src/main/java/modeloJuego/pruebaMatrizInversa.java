/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modeloJuego;

import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;
import objetosModeloJuego.TamañoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class pruebaMatrizInversa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ModeloJuego modelo = new ModeloJuego();
        modelo.inicializarModeloJuego();
        
        Jugador j = new Jugador("Jaime", "1", "1");
        modelo.guardarInformacionJugador("Jaime", "1", "1");
        modelo.guardarInformacionJugador("PolloJalado", "2", "2");
        Linea l = new Linea(new Punto(0, 0), new Punto(0, 1));
        Linea la = new Linea(new Punto(2, 2), new Punto(2, 3));
        
        Linea l2 = new Linea(new Punto(0, 0), new Punto(1, 0));
        Linea lb = new Linea(new Punto(2, 2), new Punto(3, 2));
        
        Linea l3 = new Linea(new Punto(1, 0), new Punto(1, 1));
        Linea lc = new Linea(new Punto(3, 2), new Punto(3, 3));
        
        Linea l4 = new Linea(new Punto(0, 1), new Punto(1, 1));
        Linea ld = new Linea(new Punto(2, 3), new Punto(3, 3));
        
        modelo.crearMatriz(TamañoTablero.PEQUEÑO);
        modelo.realizarJugada(l);
        modelo.realizarJugada(la);
        modelo.realizarJugada(l2);
        modelo.realizarJugada(lb);
        modelo.realizarJugada(l3);
        modelo.realizarJugada(lc);
        modelo.realizarJugada(l4);
        modelo.realizarJugada(ld);
    }
    
}
