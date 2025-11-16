/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloJuego;

import estructurasDatos.ListaCuadros;
import estructurasDatos.ListaLineas;
import estructurasDatos.MatrizPuntos;
import objetosModeloJuego.TamañoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class EstadoJuego {
    

    private int numJugadores;
    private TamañoTablero tamañoTablero;
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
    
}
