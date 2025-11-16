/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import estructurasDatos.ListaJugadores;
import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import estructurasDatos.ListaCuadros;
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
import estructurasDatos.ListaLineas;
import estructurasDatos.MatrizPuntos;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.TamañoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego implements IReceptorPaquetes, IModeloJuegoInicio, IModeloJuegoIniciado, Mediador, MediadorEventos {
 
    private ManejadorPaquetes manejoPaquetes;
    private VerificadorEventos verificadorEventos;
    private Serializador serializador;
    private Deserializador deserializador;
    private ListaJugadores listaJugadores;
    private EstadoJuego estadoJuego;
    private Jugador jugadorLocal;
    private DireccionDTO direccionLocal;

    public ModeloJuego() {
        estadoJuego = new EstadoJuego();
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
    /**
     * INICIO METODOS PARA FLUJO DE ENVIO Y RECEPCION DE PAQUETES
     */
    
    
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

    private void enviarPaqueteA(PaqueteDTO paquete, DireccionDTO direccion) throws ErrorAlEnviarPaqueteException {
        manejoPaquetes.enviarPaqueteDireccion(paquete, direccion);
    }

    private void enviarPaqueteATodos(PaqueteDTO paquete) throws ErrorAlEnviarPaqueteException {
        manejoPaquetes.enviarPaqueteDTO(paquete);
    }

    @Override
    public void recibirPaquete(PaqueteDTO paquete) {
        revisarPaqueteRecibido(paquete);
    }

    @Override
    public void revisarPaqueteRecibido(PaqueteDTO paquete) {
        try {
            deserializador.deserializarPaquete(paquete);
        } catch (PaqueteVacioAlDeserializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * FIN METODOS PARA FLUJO DE ENVIO Y RECEPCION DE PAQUETES
     */
    
    /**
     * 
     * @param tamaño 
     */
    @Override
    public void crearMatriz(TamañoTablero tamaño) {
        MatrizPuntos matriz = new MatrizPuntos(tamaño);
        crearLineas(matriz);
        estadoJuego.setMatriz(matriz);
    }
    
    private void crearLineas(MatrizPuntos matriz) {
        ListaLineas lineas = new ListaLineas(matriz.obtenerMatriz());
        crearCuadros(matriz, lineas);
        estadoJuego.setLineas(lineas);
    }
    private void crearCuadros(MatrizPuntos matriz, ListaLineas lineas) {
        ListaCuadros cuadros = new ListaCuadros(matriz.obtenerMatriz(), lineas.obtenerListaLinea());
        estadoJuego.setCuadros(cuadros);
    }
    
    @Override
    public MatrizPuntos obtenerMatriz() {
        return estadoJuego.getMatriz();
    }
    
    private void actualizarLineas(Linea linea) {
        estadoJuego.getLineas().marcarLinea(linea);
    }

    @Override
    public void realizarJugada(Linea linea, Jugador jugador) {
        actualizarLineas(linea);
        verificarCuadrosCompletados(jugador);
        estadoJuego.getCuadros();
    }

    public void notificarCambioLinea() {

    }

    public boolean verificarCuadrosCompletados(Jugador j) {
        return estadoJuego.getCuadros().revisarCuadrosCompletos(j);
    }

    /*
    INICIO DEL FLUJO PARA AGREGAR NUEVO JUGADOR
     */
    
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
    public void registrarNuevoJugador(Jugador jugador, DireccionDTO direccion) {
        manejoPaquetes.agregarNuevaDireccion(jugador.getNombre(), direccion);
        listaJugadores.agregarJugador(jugador);
        System.out.println("Eso jugadorcin: " + jugador.getNombre());
    }

    @Override
    public void guardarInformacionJugador(String nombreJugador, String imagenJugador, String colorJugador) {
        jugadorLocal = new Jugador("0", nombreJugador, imagenJugador, colorJugador);

    }
    /*
    FIN DEL FLUJO PARA AGREGAR NUEVO JUGADOR
     */
}
