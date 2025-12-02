/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import estructurasDatos.ListaJugadores;
import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import Enums.ColorJugador;
import Enums.ImagenJugador;
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
import Enums.TamañoTablero;
import interfaces.ObservadorJuego;
import manejadores.ManejadorPuntajes;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Puntaje;
import objetosModeloJuego.Punto;
import utilidades.Configuracion;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego
        implements IModeloJuegoInicio, IModeloJuegoIniciado, Mediador, MediadorEventos {

    private ManejadorPaquetes manejoPaquetes;
    private ManejadorTurnos manejoTurnos;
    private ManejadorPuntajes manejoPuntajes;
    private VerificadorEventos verificadorEventos;
    private Serializador serializador;
    private Deserializador deserializador;
    private ListaJugadores listaJugadores;
    private EstadoJuego estadoJuego;
    private Jugador jugadorLocal;
    private DireccionDTO direccionLocal;
    private ObservadorJuego observador;
    private boolean matrizVacia;

    public ModeloJuego() {

        listaJugadores = new ListaJugadores();
        manejoTurnos = new ManejadorTurnos(listaJugadores);
        manejoPuntajes = new ManejadorPuntajes();
        estadoJuego = new EstadoJuego();
        jugadorLocal = new Jugador();
        direccionLocal = new DireccionDTO(
                Configuracion.get("local.host"),
                Configuracion.getInt("local.port"));
        serializador = new Serializador();
    }

    public void inicializarModeloJuego(ManejadorPaquetes manejadorPaquetes) {
        matrizVacia = true;
        manejoPaquetes = manejadorPaquetes;
        verificadorEventos = new VerificadorEventos(this);
        deserializador = new Deserializador(verificadorEventos);
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
            enviarPaqueteA(paquete, new DireccionDTO(
                    Configuracion.get("server.host"),
                    Configuracion.getInt("server.port")));
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
        observador.cambiarTurno(
                manejoTurnos.esMiTurno(jugadorLocal));
    }

    @Override
    public List<Jugador> obtenerJugadores() {
        return listaJugadores.obtenerJugadores();
    }

    @Override
    public List<Puntaje> obtenerPuntajes() {
        return manejoPuntajes.mostrarPuntajes();
    }

    @Override
    public Jugador obtenerJugadorEnTurno() {
        return manejoTurnos.mostrarJugadorActual();
    }

    @Override
    public void crearMatriz(TamañoTablero tamaño) {
        if (matrizVacia) {
            MatrizPuntos matriz = new MatrizPuntos(tamaño);
            crearLineas(matriz);
            estadoJuego.setMatriz(matriz);
        }

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
        if (!verificarCuadrosCompletados(jugador)) {
            manejoTurnos.siguienteTurno();
            /*
            Este if es muy importante ya que gracias a este 
            esta toda la logica de lo de que un jugador tenga
            permitido seguir jugando luego de hacer un cuadro.
             */
        }
        manejoTurnos.iniciarTurno();
        notificarCambioTurno();
    }

    @Override
    public void realizarJugada(Linea linea) {
        actualizarLineasCuadros(linea);
        transmitirNuevaJugada(linea);
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
        int cuadrosCompletados = estadoJuego.getCuadros().revisarCuadrosCompletos(j);
        if (cuadrosCompletados > 0) {
            int puntosTotal = Configuracion.getInt("punto.cuadro") * cuadrosCompletados;
            manejoPuntajes.sumarPunto(puntosTotal, j.getIdJugador());
            manejoPuntajes.ordenarMayorAMenor();
            return true;
        }
        return false;
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
    public void registrarJugadores(List<DireccionDTO> direcciones) {
        for (DireccionDTO direccion : direcciones) {
            solicitarInfoNuevoJugador(direccion);
        }
    }

    @Override
    public void registrarNuevoJugador(Jugador jugador, DireccionDTO direccion) {
        if (!manejoPaquetes.isDireccionRegistrada(direccion)) {
            manejoPaquetes.agregarNuevaDireccion(jugador.getNombre(), direccion);
            listaJugadores.agregarJugador(jugador);
            manejoPuntajes.agregarNuevoPuntaje(
                    new Puntaje(jugador.getIdJugador())
            );
            transmitirInfoANuevoJugador(direccion);
        }
    }

    /*
     * FIN DEL FLUJO PARA AGREGAR NUEVO JUGADOR
     */
    
    /*
     *INICIO DE LOS METODOS DEL CASO DE USO REGISTRAR JUGADOR 
     */
    
    @Override
    public void guardarInformacionJugador(String idJugador, String nombreJugador, ImagenJugador imagenJugador,
            ColorJugador colorJugador) {
        jugadorLocal = new Jugador(idJugador, nombreJugador, imagenJugador, colorJugador);
        listaJugadores.agregarJugador(jugadorLocal);
        manejoPuntajes.agregarNuevoPuntaje(
                new Puntaje(idJugador)
        );
    }
    
    @Override
    public void editarInformacionJugador(String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador) {
        
        jugadorLocal.cambiarNombre(nombreJugador);
        jugadorLocal.cambiarImagen(imagenJugador);
        jugadorLocal.cambiarColor(colorJugador);
        
    }

}
