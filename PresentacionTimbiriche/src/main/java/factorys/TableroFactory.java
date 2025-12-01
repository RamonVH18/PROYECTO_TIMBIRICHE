/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factorys;

import adapters.CuadroAdapter;
import adapters.LineaAdapter;
import enums.TamañosTablero;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;
import objetosPresentacion.CuadroTablero;
import objetosPresentacion.LineaTablero;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class TableroFactory {

    public static PuntoTablero[][] crearMatriz(Punto[][] matrizPuntos) {
        int filas = matrizPuntos.length;
        int columnas = matrizPuntos[0].length;

        PuntoTablero[][] matrizTablero = new PuntoTablero[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                Punto p = matrizPuntos[i][j];
                matrizTablero[i][j] = new PuntoTablero(p.getCoordenadaX(), p.getCoordenadaY());
            }
        }

        return matrizTablero;
    }

    public static List<LineaTablero> crearLineas(List<Linea> lineasOriginales, TamañosTablero tamañoTablero) {
        List<LineaTablero> lineasTablero = new ArrayList<>();
        for (Linea l : lineasOriginales) {
            LineaTablero linea = LineaAdapter.toLineaTablero(l);
            linea.setGrosorLinea(tamañoTablero.getGrosorLinea());
            lineasTablero.add(linea);
        }
        return lineasTablero;
    }
    
    public static List<CuadroTablero> crearCuadros(List<Cuadro> cuadrosOriginales, TamañosTablero tamañoTablero) {
        List<CuadroTablero> cuadrosTableros = new ArrayList<>();

        int distancia = tamañoTablero.getDistanciaPuntos();

        for (Cuadro c : cuadrosOriginales) {
            int x = c.getLineaIzquierda().getPuntoA().getCoordenadaX() * distancia;
            int y = c.getLineaSuperior().getPuntoA().getCoordenadaY() * distancia;

            CuadroTablero cuadro = CuadroAdapter.toCuadroTablero(c, new Point(x, y), distancia);

            cuadrosTableros.add(cuadro);
        }
        return cuadrosTableros;

    }
}
