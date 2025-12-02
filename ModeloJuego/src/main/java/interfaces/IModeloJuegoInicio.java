/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Enums.ColorJugador;
import Enums.ImagenJugador;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloJuegoInicio {
    
    public void guardarInformacionJugador(String idJugador, String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador);
    
    public void editarInformacionJugador(String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador);

}
