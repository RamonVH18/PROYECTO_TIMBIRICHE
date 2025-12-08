/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author Ximena
 */
public class PartidaDTO {
    private String nombrePartida;
    private int numJugadores;
    private DireccionDTO host;
    private String tamañoTablero;

    public PartidaDTO() {
    }

    public PartidaDTO(String nombrePartida, int numJugadores, DireccionDTO host, String tamañoTablero) {
        this.nombrePartida = nombrePartida;
        this.numJugadores = numJugadores;
        this.host = host;
        this.tamañoTablero = tamañoTablero;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public DireccionDTO getHost() {
        return host;
    }

    public void setHost(DireccionDTO host) {
        this.host = host;
    }

    public String getTamañoTablero() {
        return tamañoTablero;
    }

    public void setTamañoTablero(String tamañoTablero) {
        this.tamañoTablero = tamañoTablero;
    }
    
    
    
}
