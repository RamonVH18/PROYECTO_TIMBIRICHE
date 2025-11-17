/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

import DTOs.DireccionDTO;
import java.util.Objects;

/**
 *
 * @author Ramon Valencia
 */
public class Jugador {

    private String idJugador;
    private String nombre;
    private String imagen;
    private String color;

    public Jugador() {

    }

    public Jugador(String nombre, String imagen, String color) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
    }

    public Jugador(String idJugador, String nombre, String imagen, String color) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
    }

    public void asignarIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public void cambiarImagen(String imagen) {
        this.imagen = imagen;
    }

    public void cambiarColor(String color) {
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

    public String getImagen() {
        return imagen;
    }

    public String getColor() {
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
