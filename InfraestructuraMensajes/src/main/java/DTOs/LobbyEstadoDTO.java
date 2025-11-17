/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author multaslokas33
 */
public class LobbyEstadoDTO {
    private List<JugadorLobbyDTO> jugadores;
    private int maxJugadores;
    private boolean partidaIniciada;
    private String tamanoTablero;   

    public LobbyEstadoDTO(List<JugadorLobbyDTO> jugadores, int maxJugadores, boolean partidaIniciada, String tamanoTablero) {
        this.jugadores = jugadores;
        this.maxJugadores = maxJugadores;
        this.partidaIniciada = partidaIniciada;
        this.tamanoTablero = tamanoTablero;
    }

    public List<JugadorLobbyDTO> getJugadores() {
        return jugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public boolean isPartidaIniciada() {
        return partidaIniciada;
    }

    public String getTamanoTablero() {
        return tamanoTablero;
    }
}
