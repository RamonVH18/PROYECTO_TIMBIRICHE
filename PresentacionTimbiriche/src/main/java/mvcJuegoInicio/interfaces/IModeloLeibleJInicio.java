/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import java.util.List;
import objetosPresentacion.JugadorLobbyVisual;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeibleJInicio {

    public List<JugadorLobbyVisual> obtenerJugadoresLobby();

    public boolean isMostrandoLobby();

    public boolean isJugadorLocalListo();

}
