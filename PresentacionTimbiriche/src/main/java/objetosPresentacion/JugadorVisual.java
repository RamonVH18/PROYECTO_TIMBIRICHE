/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

/**
 *
 * @author Ramon Valencia
 */
public class JugadorVisual {

    private String identificador;
    private String nombre;
    private String imagen;
    private String color;

    public JugadorVisual(String nombre, String imagen, String color) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
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
        // Una vez que agregue las al paquete este get se encargara de procesarlas
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

}
