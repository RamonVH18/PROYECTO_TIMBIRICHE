/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

/**
 *
 * @author Ramon Valencia
 */
public enum TamañosTablero {
    PEQUEÑO(10,10, 50, 20),
    MEDIANO(20, 20, 40, 15),
    GRANDE(30, 30, 30, 10);
    
    private final Integer filas;
    private final Integer columnas;
    private final Integer distanciaPuntos; 
    private final Integer tamañoPunto;

    TamañosTablero(int filas, int columnas, int distanciaPuntos, int tamañoPunto) {
        this.filas = filas;
        this.columnas = columnas;
        this.distanciaPuntos = distanciaPuntos;
        this.tamañoPunto = tamañoPunto;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Integer getDistanciaPuntos() {
        return distanciaPuntos;
    }

    public Integer getTamañoPunto() {
        return tamañoPunto;
    }
}
