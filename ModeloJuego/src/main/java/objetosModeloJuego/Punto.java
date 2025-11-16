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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.coordenadaX;
        hash = 47 * hash + this.coordenadaY;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Punto other = (Punto) obj;
        if (this.coordenadaX != other.coordenadaX) {
            return false;
        }
        return this.coordenadaY == other.coordenadaY;
    }
    
    
}
