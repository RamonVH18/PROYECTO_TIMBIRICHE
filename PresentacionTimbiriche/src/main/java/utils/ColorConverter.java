/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Enums.ColorJugador;
import java.awt.Color;

/**
 *
 * @author Ramon Valencia
 */
public class ColorConverter {
    
    public static Color obtenerColorJugador(ColorJugador color) {
        switch (color) {
            case AZUL -> {
                return new Color(180, 200, 255);
            }
            case ROJO -> {
                return new Color(255, 180, 180);
            }
            case VERDE -> {
                return new Color(180, 255, 180);
            }
            case AMARILLO -> {
                return new Color(255, 245, 180);
            }
            case LAVANDA -> {
                return new Color(220, 180, 255);
            }
            case ROSA -> {
                return new Color(255, 200, 220);
            }
            case GRIS -> {
                return new Color(230, 230, 230);
            }
            default -> throw new AssertionError();
        }
    }
}
