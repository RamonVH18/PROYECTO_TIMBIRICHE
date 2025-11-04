package main;

import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
import objetosPresentacion.TamañosTablero;



/**
 *
 * @author janot
 */
public class Main {
    public static void main(String[] args) {
        ModeloJuegoIniciado modelo = new ModeloJuegoIniciado(TamañosTablero.PEQUEÑO);
        ControlJuegoIniciado control = new ControlJuegoIniciado(modelo);
        TableroJuego tablero = new TableroJuego(modelo, control);
        modelo.setObserverTablero(tablero);
        
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control, tablero);
        
        modelo.añadirObserverPantallaDeJuego(pantallaDeJuego);
        modelo.añadirObservadorPantallas(pantallaDeJuego);
        
        control.mostrarPantallaDeJuego();
    }
}
