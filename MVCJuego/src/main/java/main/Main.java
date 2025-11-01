package main;

import mvcJuegoIniciado.controlador.Control;
import mvcJuegoIniciado.modelo.Modelo;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
import objetosPresentacion.TamañosTablero;



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
