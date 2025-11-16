/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructurasDatos;

import java.util.ArrayList;
import java.util.List;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;

/**
 *
 * @author Ramon Valencia
 */
public class ListaLineas {
    
    private List<Linea> lineas;
    
    public ListaLineas(Punto[][] matriz) {
        lineas = generarLineas(matriz);
    }
    
    private List<Linea> generarLineas(Punto[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        List<Linea> nuevaLista = new ArrayList<>();
        
        // Líneas horizontales
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                Punto a = matriz[i][j];
                Punto b = matriz[i][j + 1];
                nuevaLista.add(new Linea(a, b));
            }
        }
        
        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < columnas; j++) {
                Punto a = matriz[i][j];
                Punto b = matriz[i + 1][j];
                nuevaLista.add(new Linea(a, b));
            }
        }
        return nuevaLista;
    }
    
    public List<Linea> obtenerListaLinea() {
        return lineas;
    }
    
    public void marcarLinea(Linea linea) {
        for (Linea l : lineas) {
            if (l.equals(linea)) {
                l.marcarComoOcupada();
                return;
            }
        }
    }
    
    
}
