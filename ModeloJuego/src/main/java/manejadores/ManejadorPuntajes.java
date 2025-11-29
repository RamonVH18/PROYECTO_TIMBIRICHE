/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import objetosModeloJuego.Puntaje;


/**
 *
 * @author Ximena
 */
public class ManejadorPuntajes {
    private List<Puntaje> puntajes = new ArrayList<>();
    
    public void agregarNuevoPuntaje(Puntaje p) {
        this.puntajes.add(p);
    }
    
    public List<Puntaje> mostrarPuntajes() {
        return puntajes;
    }
    
    public void ordenarMayorAMenor() {
        int n = puntajes.size();
        
        for (int i = 0; i < n - 1; i++) {
            int indiceDelMayor = i;
            for (int j = i + 1; j < n; j++) {
                if (puntajes.get(j).getPuntuacion() > puntajes.get(indiceDelMayor).getPuntuacion()) {
                    indiceDelMayor = j;
                }
            }
            if (indiceDelMayor != i) {
                Collections.swap(puntajes, i, indiceDelMayor);
            }
        }
    }
    
    public void sumarPunto(int punto, String idJugador) {
        for (Puntaje p : puntajes) {
            if (p.getIdJugador().equals(idJugador)) {
                p.sumarPuntos(punto);
            }
        }
    }
    
}
