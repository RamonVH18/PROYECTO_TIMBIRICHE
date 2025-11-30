/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author Ramon Valencia
 */
public enum TamañosTablero {
    PEQUEÑO(10,10, 50, 20, 10),
    MEDIANO(20, 20, 40, 15, 8),
    GRANDE(30, 30, 30, 10, 6);
    
    private final Integer filas;
    private final Integer columnas;
    private final Integer distanciaPuntos; 
    private final Integer tamañoPunto;
    private final Integer grosorLinea;

    TamañosTablero(int filas, int columnas, int distanciaPuntos, int tamañoPunto, int grosorLinea) {
        this.filas = filas;
        this.columnas = columnas;
        this.distanciaPuntos = distanciaPuntos;
        this.tamañoPunto = tamañoPunto;
        this.grosorLinea = grosorLinea;
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
    
    public Integer getGrosorLinea() {
        return grosorLinea;
    }
}
