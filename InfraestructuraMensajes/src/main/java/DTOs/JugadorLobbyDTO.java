/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author multaslokas33
 */
public class JugadorLobbyDTO {

    private String idJugador;
    private String nombre;
    private String imagen;
    private String color;
    private boolean listo;

    public JugadorLobbyDTO(String idJugador, String nombre, String imagen, String color, boolean listo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.listo = listo;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }    
    
}
