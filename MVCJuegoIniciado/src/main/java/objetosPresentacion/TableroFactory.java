/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import interfaces.IControl;
import interfaces.IModeloLeible;
import java.awt.Point;
import vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public class TableroFactory {

    public static TableroJuego crearTablero(TamañosTablero tamaño) {
        Integer filas = tamaño.getFilas();
        Integer columnas = tamaño.getColumnas();
        Integer distancia = tamaño.getDistanciaPuntos();
        Integer tamañoPunto = tamaño.getTamañoPunto();
        Integer grosorLinea = tamaño.getGrosorLinea();

        Point[][] puntos = new Point[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puntos[i][j] = new Point((j+1) * distancia, (i+1) * distancia);
            }
        }

        return new TableroJuego(puntos, distancia, tamañoPunto, grosorLinea);
    }
    
    // hacer proporcional el tamaño del tablero a la pantalla
}
