/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

import java.util.Set;

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
        this.estado = "VACIO";
    }
    
    public String obtenerEstado() {
        return estado;
    }

    public void asignarCuadro(Jugador jugador) {
        this.estado = "LLENO";
        this.jugador = jugador;
    }

    public boolean estaCompleto() {
        return lineaSuperior.estaOcupada()
                && lineaInferior.estaOcupada()
                && lineaIzquierda.estaOcupada()
                && lineaDerecha.estaOcupada();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cuadro other = (Cuadro) obj;

        // Crear sets porque el orden no importa
        Set<Linea> thisSet = Set.of(lineaSuperior, lineaInferior, lineaIzquierda, lineaDerecha);
        Set<Linea> otherSet = Set.of(other.lineaSuperior, other.lineaInferior, other.lineaIzquierda, other.lineaDerecha);

        return thisSet.equals(otherSet);
    }

    @Override
    public int hashCode() {
        return Set.of(lineaSuperior, lineaInferior, lineaIzquierda, lineaDerecha).hashCode();
    }
}
