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
    PEQUEÑO(10,10),
    MEDIANO(20, 20),
    GRANDE(30, 30);
    
    private final Integer filas;
    private final Integer columnas;

    TamañoTablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
