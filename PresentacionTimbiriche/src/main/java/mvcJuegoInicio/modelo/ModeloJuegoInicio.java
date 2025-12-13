package mvcJuegoInicio.modelo;

import enums.ObserverType;
import static enums.ObserverType.MENU_OPCIONES;
import static enums.ObserverType.PANTALLA_JUEGO;
import enums.TamañosTablero;
import gestion.ManejadorObservers;
import java.util.List;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.PuntajeVisual;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio{
    private boolean mostrandoPantallaInicio;
    private final ManejadorObservers manejoObservers;

    public ModeloJuegoInicio() {
        mostrandoPantallaInicio = false;
        this.manejoObservers = new ManejadorObservers();
    }
    
    @Override
    public void añadirObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }
    
    @Override
    public void mostrarPantalla(ObserverType tipo) {
        activarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }
    
    private void activarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_INICIO -> {
                this.mostrandoPantallaInicio = true;
                break;
            }
        }
    }
    
    @Override
    public void ocultarPantalla(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }
    
    private void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_INICIO -> {
                this.mostrandoPantallaInicio = false;
            }
        }
    }

    @Override
    public boolean isMostrandoPantallaDeInicio() {
        return mostrandoPantallaInicio;
    }
}
