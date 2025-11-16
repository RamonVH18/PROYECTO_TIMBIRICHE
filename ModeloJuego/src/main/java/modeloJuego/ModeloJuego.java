/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import eventos.NuevoJugadorEvent;
import eventos.VerificadorEventos;
import excepciones.ErrorAlEnviarPaqueteException;
import excepciones.PaqueteVacioAlDeserializarException;
import excepciones.PaqueteVacioAlSerializarException;
import interfaces.Mediador;
import interfaces.MediadorEventos;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejadorPaquetes;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import serializador.Deserializador;
import serializador.Serializador;
import interfaces.IModeloJuegoIniciado;
import interfaces.IModeloJuegoInicio;
import interfaz.IReceptorPaquetes;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego implements IReceptorPaquetes, IModeloJuegoInicio, IModeloJuegoIniciado, Mediador, MediadorEventos {

    private static ModeloJuego instanciaModelo;
    private ManejadorPaquetes manejoPaquetes;
    private VerificadorEventos verificadorEventos;
    private Serializador serializador;
    private Deserializador deserializador;
    private ListaJugadores listaJugadores;
    private Jugador jugadorLocal;
    private DireccionDTO direccionLocal;
    
    
    
    public ModeloJuego() {
        listaJugadores = new ListaJugadores();
        jugadorLocal = new Jugador();
        direccionLocal = new DireccionDTO("192.168.1.71", 5000);
    }
    public void inicializarModeloJuego() {
        manejoPaquetes = new ManejadorPaquetes(this);
        verificadorEventos = new VerificadorEventos(this);
        serializador = new Serializador(this, verificadorEventos);
        deserializador = new Deserializador(this, verificadorEventos);
        
    }

    public static ModeloJuego getInstance() {
        if (instanciaModelo == null) {
            instanciaModelo = new ModeloJuego();
        }

        return instanciaModelo;
    }

    private void enviarPaqueteA(PaqueteDTO paquete, DireccionDTO direccion) throws ErrorAlEnviarPaqueteException {
        manejoPaquetes.enviarPaqueteDireccion(paquete, direccion);
    }

    private void enviarPaqueteATodos(PaqueteDTO paquete) throws ErrorAlEnviarPaqueteException {
        manejoPaquetes.enviarPaqueteDTO(paquete);
    }

    @Override
    public void realizarJugada(Linea linea) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void notificarCambioLinea() {

    }

    public void verificarCuadrosCompletados() {

    }

    @Override
    public void revisarPaqueteRecibido(PaqueteDTO paquete) {
        try {
            deserializador.deserializarPaquete(paquete);
        } catch (PaqueteVacioAlDeserializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void recibirNuevaDireccion(DireccionDTO direccion, Jugador jugador) {

//        manejoPaquetes.agregarNuevaDireccion(nombreJugador, direccion);
    }

    @Override
    public Jugador obtenerJugadorPorNuevaDireccion(DireccionDTO direcion) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void solicitarInfoNuevoJugador(DireccionDTO direccion) {
        PaqueteDTO paquete;
        try {
            paquete = serializador.serializarDireccionAPaquete("solicitudInfoJugador", direccionLocal);
            enviarPaqueteA(paquete, direccion);
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void transmitirInfoANuevoJugador(DireccionDTO direccion) {
        Jugador j = jugadorLocal;
        NuevoJugadorEvent njEvent = new NuevoJugadorEvent(jugadorLocal, direccion);
        PaqueteDTO paquete;
        try {
            paquete = serializador.serializarNuevoJugadorEvent("nuevaInfoJugador", njEvent);
            enviarPaqueteA(paquete, direccion);
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarInformacionJugador(String nombreJugador, String imagenJugador, String colorJugador) {
        jugadorLocal = new Jugador("0", nombreJugador, imagenJugador, colorJugador);

    }

    @Override
    public void registrarNuevoJugador(Jugador jugador, DireccionDTO direccion) {
        manejoPaquetes.agregarNuevaDireccion(jugador.getNombre(), direccion);
        listaJugadores.agregarJugador(jugador);
        System.out.println("Eso jugadorcin: " + jugador.getNombre());
    }

    @Override
    public void recibirPaquete(PaqueteDTO paquete) {
        revisarPaqueteRecibido(paquete);
    }

    public void conectarseAServidor() {
        PaqueteDTO paquete;
        try {
            paquete = serializador.serializarDireccionAPaquete("registroPeer", direccionLocal);
            enviarPaqueteA(paquete, new DireccionDTO("192.168.1.71", 8000));
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
