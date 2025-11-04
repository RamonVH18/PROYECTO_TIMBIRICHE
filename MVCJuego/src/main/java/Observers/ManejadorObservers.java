/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observers;

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
    
    private final Map<String, List<IVista>> vistas;

    public ManejadorObservers() {
        vistas = new HashMap<>();
    }
    
    
    
    public void agregarObserver(String lista, IVista v) {
        vistas.computeIfAbsent(lista, k -> new ArrayList<>()).add(v);
    }

    public void eliminarObserver(String lista, IVista v) {
        vistas.getOrDefault(lista, new ArrayList<>()).remove(v);
    }

    public void notificar(String lista) {
        List<IVista> listaObservers = vistas.get(lista);
        if (listaObservers != null) {
            for (IVista v : listaObservers) {
                v.actualizar();
            }
        }
    }
    
}
