/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

/**
 *
 * @author Ramon Valencia
 */
public class Punto {
    private final int coordenadaX;
    private final int coordenadaY;
    
    public Punto(int X, int Y) {
        this.coordenadaX = X;
        this.coordenadaY = Y;
    }
    
    public int getCoordenadaX() {
        return coordenadaX;
    }
    
    public int getCoordenadaY() {
        return coordenadaY;
    }
}
