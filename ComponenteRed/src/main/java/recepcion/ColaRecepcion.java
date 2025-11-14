/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recepcion;

import DTOs.PaqueteDTO;
import interfaces.IReceptor;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author Ramon Valencia
 */
public class ColaRecepcion {
    private final Queue<PaqueteDTO> colaRecepcion;
    private static ColaRecepcion instancia;
    private static IReceptor receptor;
    
    private ColaRecepcion() {
        this.colaRecepcion = new ArrayDeque<>();
    }
    
    public static ColaRecepcion getInstancia() {
        if (instancia == null) {
            instancia = new ColaRecepcion();
        }
        return instancia;
    }
    
    public void suscribirReceptor(IReceptor receptor) {
        this.receptor = receptor;
    }
    
    public void encolar(PaqueteDTO paquete) {
        colaRecepcion.add(paquete);
        notificarPaqueteNuevo();
    }
    
    public PaqueteDTO desencolar() {
        return colaRecepcion.remove();
    }
    
    private void notificarPaqueteNuevo() {
        
    }
}
