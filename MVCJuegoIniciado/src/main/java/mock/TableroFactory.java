/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mock;

import java.awt.Point;
import vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public class TableroFactory {

    public static TableroJuego crearTablero(TamañosTablero tamaño) {
        int filas = 15;
        int columnas = 15;
        int distancia = 50;

        Point[][] puntos = new Point[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puntos[i][j] = new Point(j * distancia + 50, i * distancia + 50);
            }
        }

        return new TableroJuego(puntos);
    }
}
