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
        Jugador j = new Jugador("Jaime", "1", "1");
        Linea l = new Linea(new Punto(0, 0), new Punto(0, 1));
        Linea l2 = new Linea(new Punto(0, 0), new Punto(1, 0));
        Linea l3 = new Linea(new Punto(1, 0), new Punto(1, 1));
        Linea l4 = new Linea(new Punto(0, 1), new Punto(1, 1));
        
        modelo.crearMatriz(TamañoTablero.PEQUEÑO);
        modelo.realizarJugada(l, j);
        modelo.realizarJugada(l2, j);
        modelo.realizarJugada(l3, j);
        modelo.realizarJugada(l4, j);
    }
    
}
