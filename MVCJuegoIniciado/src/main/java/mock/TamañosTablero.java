/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mock;

/**
 *
 * @author Ramon Valencia
 */
public enum TamañosTablero {
    PEQUEÑO(100,100),
    MEDIANO(20, 20),
    GRANDE(30, 30);
    
    private final int filas;
    private final int columnas;

    TamañosTablero(int filas, int columnas) {
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
