package main;

import controlador.Control;
import modelo.Modelo;
import vistas.PantallaDeJuego;

/**
 *
 * @author janot
 */
public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Control control = new Control(modelo);
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control);
        
        modelo.añadirObservadorPantallas(pantallaDeJuego);
        
        control.mostrarPantallaDeJuego();
        
        
    }
}
