/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

/**
 *
 * @author Ximena
 */
public class Puntaje {

    private String idJugador;
    private int puntuacion;

    public Puntaje(String idJugador) {
        this.idJugador = idJugador;
        this.puntuacion = 0;
    }

    public void sumarPuntos(int puntos) {
        puntuacion = puntuacion + puntos;
    }

    public int mostrarPuntaje() {
        return puntuacion;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

}
