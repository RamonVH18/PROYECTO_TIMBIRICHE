/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;

/**
 *
 * @author Ramon Valencia
 */
public class ColorConverter {
    
    public static Color obtenerColorJugador(String color) {
        switch (color) {
            case "azul" -> {
                return new Color(180, 200, 255);
            }
            case "rojo" -> {
                return new Color(255, 180, 180);
            }
            case "verde" -> {
                return new Color(180, 255, 180);
            }
            case "amarillo" -> {
                return new Color(255, 245, 180);
            }
            case "lavanda" -> {
                return new Color(220, 180, 255);
            }
            case "rosa" -> {
                return new Color(255, 200, 220);
            }
            case "gris" -> {
                return new Color(230, 230, 230);
            }
            default -> throw new AssertionError();
        }
    }
}
