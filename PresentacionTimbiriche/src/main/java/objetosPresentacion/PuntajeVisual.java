/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

/**
 *
 * @author Ramon Valencia
 */
public class PuntajeVisual {
    private String nombreJugador;
    private int puntuacion;

    public PuntajeVisual(String nombreJugador, int puntuacion) {
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    
}
