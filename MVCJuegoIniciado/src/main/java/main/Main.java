package main;

import controlador.Control;
import modelo.Modelo;
import objetosPresentacion.TamañosTablero;
import vistas.PantallaDeJuego;
import vistas.TableroJuego;

/**
 *
 * @author janot
 */
public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo(TamañosTablero.PEQUEÑO);
        Control control = new Control(modelo);
        TableroJuego tablero = new TableroJuego(modelo, control);
        modelo.setObserverTablero(tablero);
        
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control, tablero);
        
        modelo.añadirObserverPantallaDeJuego(pantallaDeJuego);
        modelo.añadirObservadorPantallas(pantallaDeJuego);
        
        control.mostrarPantallaDeJuego();
        
        
    }
}
