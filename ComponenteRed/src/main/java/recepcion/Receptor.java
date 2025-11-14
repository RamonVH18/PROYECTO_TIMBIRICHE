/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recepcion;

import DTOs.PaqueteDTO;
import interfaces.IReceptor;
import interfaces.ReceptorPaqueteInjector;
import interfaz.IReceptorPaquetes;

/**
 *
 * @author Ramon Valencia
 */
public class Receptor implements IReceptor, ReceptorPaqueteInjector{
    
    private IReceptorPaquetes modelo;
    private final ColaRecepcion cola;
    
    public Receptor() {
        this.cola = ColaRecepcion.getInstancia();
    }
    @Override
    public void recibirPaquete() {
        PaqueteDTO paquete = cola.desencolar();
        modelo.recibirPaquete(paquete);
    }

    @Override
    public void inyectarManejador(IReceptorPaquetes receptor) {
        this.modelo = receptor;
    }
    
}
