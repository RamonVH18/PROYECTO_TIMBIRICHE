/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Enums.ImagenJugador;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Ramon Valencia
 */
public class GeneradorImagen {

    public static ImageIcon obtenerImagenJugador(ImagenJugador imagen, int ancho, int alto) {
        String ruta;
        switch (imagen) {
            case ARDILLA -> {
                ruta = "img/ARDILLA.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case CAFE -> {
                ruta = "img/CAFE.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case CHEVES -> {
                ruta = "img/CHEVES.JPG";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case DIESEL -> {
                ruta = "img/DIESEL.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case GATO -> {
                ruta = "img/GATO.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case GOLDEN -> {
                ruta = "img/GOLDEN.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case MAOMAO -> {
                ruta = "img/MAOMAO.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case PELON -> {
                ruta = "img/PELON.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case PERRO -> {
                ruta = "img/PERRO.JPG";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            case STUART -> {
                ruta = "img/STUART.jpg";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
            default -> {
                ruta = "img/DEFECTO.JPG";
                return cargarIconoDesdeResources(ruta, ancho, alto);
            }
        }
    }

    public static ImageIcon cargarIconoDesdeResources(String ruta, int ancho, int alto) {

        URL url = GeneradorImagen.class.getResource("/" + ruta);
        ImageIcon iconoOriginal = new ImageIcon(url);
        
        Image imagenOriginal = iconoOriginal.getImage();
        
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(
                ancho,
                alto,
                Image.SCALE_SMOOTH
        );

        // 3. Crear y devolver el nuevo ImageIcon redimensionado
        return new ImageIcon(imagenRedimensionada);
    }
}
