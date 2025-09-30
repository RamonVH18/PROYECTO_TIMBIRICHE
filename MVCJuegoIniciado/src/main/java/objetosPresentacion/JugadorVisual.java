/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import java.awt.image.BufferedImage;

/**
 *
 * @author Ramon Valencia
 */
public class JugadorVisual {
    
    private Integer identificador;
    private final String nombre; 
    private final String imagen;

    public JugadorVisual(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }
    
    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }
    public Integer getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        // Una vez que agregue las al paquete este get se encargara de procesarlas
        return imagen;
    }
    
    
}
