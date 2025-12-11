/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

/**
 *
 * @author Ramon Valencia
 */
public enum TamañoTablero {
    PEQUEÑO(10,10, 0),
    MEDIANO(20, 20, 1),
    GRANDE(30, 30, 2);
    
    private final Integer filas;
    private final Integer columnas;
    private final Integer size;

    TamañoTablero(int filas, int columnas, int size) {
        this.filas = filas;
        this.columnas = columnas;
        this.size = size;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getSize() {
        return size;
    }

    public static TamañoTablero getSizeByInt(int size) {
        for (TamañoTablero tamanio : TamañoTablero.values()) {
            if (tamanio.getSize() == size) {
                return tamanio;
            }
        }
        return null;
    }
}
