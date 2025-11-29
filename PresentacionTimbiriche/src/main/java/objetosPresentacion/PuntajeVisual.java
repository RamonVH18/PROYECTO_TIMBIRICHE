/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import java.awt.Color;

/**
 *
 * @author Ramon Valencia
 */
public class PuntajeVisual {
    private String nombreJugador;
    private int puntuacion;
    private Color colorJugador;

    public PuntajeVisual(String nombreJugador, int puntuacion, Color color) {
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
        this.colorJugador = color;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    
    public Color getColorJugador() {
        return colorJugador;
    }
    
}
