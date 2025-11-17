/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author multaslokas33
 */
import DTOs.JugadorLobbyDTO;
import java.util.List;

public interface ObservadorLobby {

    void actualizarLobby(List<JugadorLobbyDTO> jugadores,
            int maxJugadores,
            boolean partidaIniciada,
            String tamanoTablero);
    
    void iniciarPartida();
}
