/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import DTOs.DireccionDTO;
import interfaces.MediadorEventos;

/**
 *
 * @author Ximena
 */
public class VerificadorEventos {
    
    private final MediadorEventos modeloJuego;
    
    public VerificadorEventos(MediadorEventos modeloJuego) {
        this.modeloJuego = modeloJuego;
    }
    
    public void eventoNuevaDireccion(DireccionDTO direccion) {
        modeloJuego.solicitarInfoNuevoJugador(direccion);
    }
    
    public void eventoSolicitudNuevoJugador(DireccionDTO direccion) {
        modeloJuego.transmitirInfoANuevoJugador(direccion);
    }
    
    public void eventoNuevoJugador(NuevoJugadorEvent njEvent) {
        
    }
    
}
