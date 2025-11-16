/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

import java.util.Objects;

/**
 *
 * @author Ramon Valencia
 */
public class Linea {
    
    private final Punto puntoA;
    private final Punto puntoB;
    private String estadoLinea;
    
    public Linea(Punto A, Punto B) {
        this.puntoA = A;
        this.puntoB = B;
        this.estadoLinea = "LIBRE";
    }
    
    public void marcarComoOcupada() {
        estadoLinea = "OCUPADA";
    }
    
    public boolean estaOcupada() {
        if (estadoLinea.equals("OCUPADA")) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(puntoA, puntoB);
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
        final Linea other = (Linea) obj;
        return (puntoA.equals(other.puntoA) && puntoB.equals(other.puntoB)) ||
               (puntoA.equals(other.puntoB) && puntoB.equals(other.puntoA));
    }
    
    public boolean conecta(Punto a, Punto b) {
    return (this.puntoA.equals(a) && this.puntoB.equals(b)) ||
           (this.puntoA.equals(b) && this.puntoB.equals(a));
}
    
}
