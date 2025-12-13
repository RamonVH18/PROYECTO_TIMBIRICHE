/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloJuego;

import estructurasDatos.ListaCuadros;
import estructurasDatos.ListaLineas;
import estructurasDatos.MatrizPuntos;
import Enums.TamañoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class EstadoJuego {
    
    private String nombrePartida = "patito feo";
    private int numJugadores = 3;
    private TamañoTablero tamañoTablero = TamañoTablero.MEDIANO;
    private MatrizPuntos matriz;
    private ListaLineas lineas;
    private ListaCuadros cuadros;
    
    public EstadoJuego() {
    }
    
    public MatrizPuntos getMatriz() {
        return matriz;
    }
    
    public void setMatriz(MatrizPuntos matriz) {
        this.matriz = matriz;
    }

    public ListaLineas getLineas() {
        return lineas;
    }

    public void setLineas(ListaLineas lineas) {
        this.lineas = lineas;
    }

    public ListaCuadros getCuadros() {
        return cuadros;
    }

    public void setCuadros(ListaCuadros cuadros) {
        this.cuadros = cuadros;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }
    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public int getNumJugadores() {
        return numJugadores;
    }
    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public TamañoTablero getTamañoTablero() {
        return tamañoTablero;
    }

    public void setTamañoTablero(TamañoTablero tamañoTablero) {
        this.tamañoTablero = tamañoTablero;
    }
    
}
