/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosModeloJuego;

/**
 *
 * @author Ramon Valencia
 */
public class Jugador {
    private String nombre;
    private String imagen;
    private String color;
    private String host;
    
    public Jugador(String nombre, String imagen, String color, String host) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.host = host;
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
    
    
}
