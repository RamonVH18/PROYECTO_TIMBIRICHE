/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructurasDatos;

import objetosModeloJuego.Punto;
import Enums.TamañoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class MatrizPuntos {
    
    private Punto[][] matrizPuntos;
    
    public MatrizPuntos(TamañoTablero tamaño) {
        matrizPuntos = generarMatriz(tamaño);
    }
    
    private Punto[][] generarMatriz(TamañoTablero tamaño) {
        Integer filas = tamaño.getFilas();
        Integer columnas = tamaño.getColumnas();
        Punto[][] matriz = new Punto[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new Punto(i, j);
            }
        }
        return matriz;
    }
    
    public Punto[][] obtenerMatriz() {
        return matrizPuntos;
    }
    
}
