/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public class CambioJugadorEvent {
    
    private String idJugador;
    private String nombreAnterior;
    private String nombreNuevo;
    private ImagenJugador imagenAnterior;
    private ImagenJugador imagenNueva;
    private ColorJugador colorAnterior;
    private ColorJugador colorNuevo;

    public CambioJugadorEvent(Jugador jugadorCambio, String nombreNuevo, ImagenJugador imagenNueva, ColorJugador colorNuevo) {
        this.idJugador = jugadorCambio.getIdJugador();
        this.nombreAnterior = jugadorCambio.getNombre();
        this.nombreNuevo = nombreNuevo;
        this.imagenAnterior = jugadorCambio.getImagen();
        this.imagenNueva = imagenNueva;
        this.colorAnterior = jugadorCambio.getColor();
        this.colorNuevo = colorNuevo;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }

    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public ImagenJugador getImagenAnterior() {
        return imagenAnterior;
    }

    public void setImagenAnterior(ImagenJugador imagenAnterior) {
        this.imagenAnterior = imagenAnterior;
    }

    public ImagenJugador getImagenNueva() {
        return imagenNueva;
    }

    public void setImagenNueva(ImagenJugador imagenNueva) {
        this.imagenNueva = imagenNueva;
    }

    public ColorJugador getColorAnterior() {
        return colorAnterior;
    }

    public void setColorAnterior(ColorJugador colorAnterior) {
        this.colorAnterior = colorAnterior;
    }

    public ColorJugador getColorNuevo() {
        return colorNuevo;
    }

    public void setColorNuevo(ColorJugador colorNuevo) {
        this.colorNuevo = colorNuevo;
    }
    
    
}
