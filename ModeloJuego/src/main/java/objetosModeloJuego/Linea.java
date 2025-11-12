/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

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
}
