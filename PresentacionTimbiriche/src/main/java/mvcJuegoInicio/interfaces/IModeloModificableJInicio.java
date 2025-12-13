/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import enums.ObserverType;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificableJInicio {
    public void añadirObserver(mvcJuegoIniciado.interfaces.IVista v, ObserverType tipo);
    public void mostrarPantalla(ObserverType tipo);
    public void ocultarPantalla(ObserverType tipo);
}
