/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import java.util.List;

import DTOs.DireccionDTO;
import interfaces.MediadorEventos;
import objetosModeloJuego.Jugador;

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
        modeloJuego.registrarNuevoJugador(njEvent.getJugador(), njEvent.getDireccion());
    }
    
    public void eventoLineaPintada(LineaPintadaEvent lpEvent) {
        modeloJuego.actualizarLineasCuadros(lpEvent.getLinea());
        
    }

    public void eventoListaDirecciones(List<DireccionDTO> direcciones) {
       for (DireccionDTO direccion : direcciones) {
          // Jugador jugador = modeloJuego.solicitarInfoNuevoJugadorJugador(direccion);
           //modeloJuego.registrarNuevoJugador(jugador, direccion);
       }
    }


    
}
