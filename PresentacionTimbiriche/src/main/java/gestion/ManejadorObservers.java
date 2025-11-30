/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion;

import enums.ObserverType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mvcJuegoIniciado.interfaces.IVista;

/**
 *
 * @author Ramon Valencia
 */
public class ManejadorObservers {

    private final Map<ObserverType, List<IVista>> vistas;

    public ManejadorObservers() {
        vistas = new HashMap<>();
    }

    public void agregarObserver(ObserverType lista, IVista v) {
        vistas.computeIfAbsent(lista, k -> new ArrayList<>()).add(v);
    }

    public void eliminarObserver(ObserverType lista, IVista v) {
        vistas.getOrDefault(lista, new ArrayList<>()).remove(v);
    }

    public void notificar(ObserverType lista) {
        List<IVista> listaObservers = vistas.get(lista);
        if (listaObservers != null) {
            for (IVista v : listaObservers) {
                v.actualizar();
            }
        }
    }
    
    public void mostrarObservers(ObserverType lista) {
        List<IVista> listaObservers = vistas.get(lista);
        if (listaObservers != null) {
            for (IVista v : listaObservers) {
                v.mostrar();
            }
        }
    }

}
