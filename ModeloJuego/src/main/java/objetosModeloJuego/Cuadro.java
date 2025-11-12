/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

/**
 *
 * @author Ramon Valencia
 */
public class Cuadro {
    
    private Linea lineaSuperior;
    private Linea lineaInferior;
    private Linea lineaIzquierda;
    private Linea lineaDerecha;
    private String estado;
    private Jugador jugador;
    
    public Cuadro(Linea Sup, Linea Inf, Linea Izq, Linea Der) {
        this.lineaSuperior = Sup;
        this.lineaInferior = Inf;
        this.lineaIzquierda = Izq;
        this.lineaDerecha = Der;
    }
    
    public void asignarCuadro(Jugador jugador) {
        
    }
}
