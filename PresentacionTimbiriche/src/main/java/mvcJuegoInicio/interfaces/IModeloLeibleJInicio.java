/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import Enums.ColorJugador;
import Enums.ImagenJugador;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeibleJInicio {
    
    public ImagenJugador obtenerImagenAlmacenada();
    
    public ColorJugador obtenerColorAlmacenado();
    
    public boolean isMostrandoPantallaRegistrarJugador();
    
    public boolean isMostrandoPantallaSeleccionarImagen();
}
