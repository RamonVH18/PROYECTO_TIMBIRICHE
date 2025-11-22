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
import estructurasDatos.ListaLineas;
import estructurasDatos.MatrizPuntos;
import eventos.LineaPintadaEvent;
import java.util.List;
import manejadores.ManejadorTurnos;
import objetosModeloJuego.TamañoTablero;
import interfaces.ObservadorJuego;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Punto;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego
        implements IModeloJuegoInicio, IModeloJuegoIniciado, Mediador, MediadorEventos {

    private ManejadorPaquetes manejoPaquetes;
    private ManejadorTurnos manejoTurnos;
    private VerificadorEventos verificadorEventos;
    private Serializador serializador;
    private Deserializador deserializador;
    private ListaJugadores listaJugadores;
    private EstadoJuego estadoJuego;
    private Jugador jugadorLocal;
    private DireccionDTO direccionLocal;
    private ObservadorJuego observador;

    public ModeloJuego() {
        manejoTurnos = new ManejadorTurnos(listaJugadores);
        estadoJuego = new EstadoJuego();
        listaJugadores = new ListaJugadores();
        jugadorLocal = new Jugador();
        direccionLocal = new DireccionDTO("192.168.1.71", 8080);
        serializador = new Serializador();
        deserializador = new Deserializador(verificadorEventos);
    }

    public void inicializarModeloJuego(ManejadorPaquetes manejadorPaquetes) {
        manejoPaquetes = new ManejadorPaquetes(this);
        verificadorEventos = new VerificadorEventos(this);
    }

    public void suscribirObservador(ObservadorJuego observador) {
        this.observador = observador;
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
     */
    public void empezarJuego() {
        manejoTurnos.crearTurnos();
        manejoTurnos.iniciarTurno();
        Jugador jugador = manejoTurnos.mostrarJugadorActual();
        observador.cambiarTurno(
                manejoTurnos.esMiTurno(jugadorLocal));
    }

    @Override
    public List<Jugador> obtenerJugadores() {
        return listaJugadores.obtenerJugadores();
    }

    @Override
    public Jugador obtenerJugadorEnTurno() {
        return manejoTurnos.mostrarJugadorActual();
    }

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
    public Punto[][] obtenerMatriz() {
        return estadoJuego.getMatriz().obtenerMatriz();
    }

    @Override
    public List<Linea> obtenerLineas() {
        return estadoJuego.getLineas().obtenerListaLinea();
    }

    //
    @Override
    public List<Cuadro> obtenerCuadros() {
        return estadoJuego.getCuadros().obtenerListaCuadros();
    }
    //

    @Override
    public void actualizarLineasCuadros(Linea linea) {
        estadoJuego.getLineas().marcarLinea(linea);
        Jugador jugador = manejoTurnos.mostrarJugadorActual();
        verificarCuadrosCompletados(jugador);
        notificarCambioTurno();
    }

    @Override
    public void realizarJugada(Linea linea) {
        actualizarLineasCuadros(linea);
        transmitirNuevaJugada(linea);
        manejoTurnos.siguienteTurno();
        manejoTurnos.iniciarTurno();
        notificarCambioTurno();
    }

    public void notificarCambioTurno() {
        observador.cambiarTurno(
                manejoTurnos.esMiTurno(jugadorLocal));
    }

    private void transmitirNuevaJugada(Linea linea) {
        LineaPintadaEvent lpEvent = new LineaPintadaEvent(linea);
        PaqueteDTO paquete;
        try {
            paquete = serializador.serializarLineaPintadaEvent("nuevaLineaPintada", lpEvent);
            enviarPaqueteATodos(paquete);
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verificarCuadrosCompletados(Jugador j) {
        return estadoJuego.getCuadros().revisarCuadrosCompletos(j);
    }

    /*
     * INICIO DEL FLUJO PARA AGREGAR NUEVO JUGADOR
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
        NuevoJugadorEvent njEvent = new NuevoJugadorEvent(jugadorLocal, direccionLocal);
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
    }

    @Override
    public void guardarInformacionJugador(String idJugador, String nombreJugador, String imagenJugador,
            String colorJugador) {
        jugadorLocal = new Jugador(idJugador, nombreJugador, imagenJugador, colorJugador);
        listaJugadores.agregarJugador(jugadorLocal);
    }
    /*
     * FIN DEL FLUJO PARA AGREGAR NUEVO JUGADOR
     */

}
