/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructurasDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;

/**
 *
 * @author Ramon Valencia
 */
public class ListaCuadros {
    
    private List<Cuadro> cuadros;
    
    public ListaCuadros(Punto[][] matriz, List<Linea> lineas) {
        cuadros = generarCuadros(matriz, lineas);
    }
    
    private List<Cuadro> generarCuadros(Punto[][] matriz, List<Linea> lineas) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        List<Cuadro> nuevaLista = new ArrayList<>();

        // Necesitamos buscar líneas por sus dos puntos
        BiFunction<Punto, Punto, Linea> buscarLinea = (a, b) -> {
            for (Linea l : lineas) {
                if (l.conecta(a, b)) {
                    return l;
                }
            }
            return null;
        };

        // Generar cuadros
        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < columnas - 1; j++) {

                Punto supIzq = matriz[i][j];
                Punto supDer = matriz[i][j + 1];
                Punto infIzq = matriz[i + 1][j];
                Punto infDer = matriz[i + 1][j + 1];

                Linea lineaSuperior = buscarLinea.apply(supIzq, supDer);
                Linea lineaInferior = buscarLinea.apply(infIzq, infDer);
                Linea lineaIzquierda = buscarLinea.apply(supIzq, infIzq);
                Linea lineaDerecha = buscarLinea.apply(supDer, infDer);

                Cuadro cuadro = new Cuadro(lineaSuperior, lineaInferior, lineaIzquierda, lineaDerecha);
                nuevaLista.add(cuadro);
            }
        }
        return nuevaLista;
    }
    
    public int revisarCuadrosCompletos(Jugador j) {
        int cuadrosCompletados = 0;
        for (Cuadro c : cuadros) {
            if (c.estaCompleto() && c.obtenerEstado().equals("VACIO")) {
                c.asignarCuadro(j);
                cuadrosCompletados++;
            }
        }
        return cuadrosCompletados;
    }
    
    public boolean revisarTodosLosCuadrosCompletos() {
        for (Cuadro c: cuadros) {
            if (c.obtenerEstado().equals("VACIO")) {
                return false;
            }
        }
        return true;
    }
    
    public List<Cuadro> obtenerListaCuadros() {
        return cuadros;
    }
    
}
