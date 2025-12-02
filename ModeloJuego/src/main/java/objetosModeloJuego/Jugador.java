/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import java.util.Objects;

/**
 *
 * @author Ramon Valencia
 */
public class Jugador {

    private String idJugador;
    private String nombre;
    private ImagenJugador imagen;
    private ColorJugador color;

    public Jugador() {

    }

    public Jugador(String nombre, ImagenJugador imagen, ColorJugador color) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
    }

    public Jugador(String idJugador, String nombre, ImagenJugador imagen, ColorJugador color) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
    }

    public void asignarIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public void cambiarImagen(ImagenJugador imagen) {
        this.imagen = imagen;
    }

    public void cambiarColor(ColorJugador color) {
        this.color = color;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public ImagenJugador getImagen() {
        return imagen;
    }

    public ColorJugador getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.idJugador, other.idJugador)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        return Objects.equals(this.color, other.color);
    }

}
