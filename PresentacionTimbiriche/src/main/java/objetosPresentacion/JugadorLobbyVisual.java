/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import java.awt.Color;

/**
 *
 * @author multaslokas33
 */
public class JugadorLobbyVisual {

    private String identificador;
    private String nombre;
    private String imagen;
    private Color color;
    private boolean listo;

    public JugadorLobbyVisual(String identificador, String nombre, String imagen, Color color, boolean listo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.listo = listo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public String getEstadoTexto() {
        return listo ? "Listo" : "Esperando...";
    }

}
