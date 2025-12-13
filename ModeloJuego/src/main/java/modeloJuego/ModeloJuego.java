/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import estructurasDatos.ListaJugadores;
import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import DTOs.PartidaDTO;
import Enums.ColorJugador;
import Enums.ImagenJugador;
import estructurasDatos.ListaCuadros;
import eventos.NuevoJugadorEvent;
import excepciones.ErrorAlEnviarPaqueteException;
import excepciones.PaqueteVacioAlSerializarException;
import interfaces.MediadorEventos;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoEnvioPaquetes;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import serializador.Serializador;
import interfaces.IModeloJuegoIniciado;
import interfaces.IModeloJuegoInicio;
import estructurasDatos.ListaLineas;
import estructurasDatos.MatrizPuntos;
import eventos.LineaPintadaEvent;
import java.util.List;
import manejadores.ManejadorTurnos;
import Enums.TamañoTablero;
import eventos.CambioJugadorEvent;
import excepciones.DatosIncompletosPartidaException;
import excepciones.DatosJugadorInvalidosException;
import interfaces.ObservadorJuego;
import manejadores.ManejadorPuntajes;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Puntaje;
import objetosModeloJuego.Punto;
import utilidades.Configuracion;
import validaciones.ValidacionesJugador;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego
        implements IModeloJuegoInicio, IModeloJuegoIniciado, MediadorEventos {

    private ManejoEnvioPaquetes manejoPaquetes;
    private ManejadorTurnos manejoTurnos;
    private ManejadorPuntajes manejoPuntajes;
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
        manejoPaquetes = new ManejoEnvioPaquetes();
        matrizVacia = true;
    }

    public void suscribirObservador(ObservadorJuego observador) {
        this.observador = observador;
    }

    /**
     * INICIO METODOS PARA FLUJO DE ENVIO Y RECEPCION DE PAQUETES
     */
    public void conectarseAServidor() {
        try {
            manejoPaquetes.conectarseAServidor(direccionLocal, new DireccionDTO(
                    Configuracion.get("server.host"),
                    Configuracion.getInt("server.port")));
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
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
        try {
            manejoPaquetes.transmitirNuevaJugada(lpEvent);
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
        
        try {
            manejoPaquetes.solicitarInfoNuevoJugador(direccionLocal, direccion);
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
        
        try {
            manejoPaquetes.transmitirInfoANuevoJugador(direccion, njEvent);
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
            ColorJugador colorJugador) throws DatosJugadorInvalidosException{
        ValidacionesJugador.validarCreacionJugador(nombreJugador, imagenJugador, colorJugador);
        jugadorLocal = new Jugador(idJugador, nombreJugador, imagenJugador, colorJugador);
        listaJugadores.agregarJugador(jugadorLocal);
        manejoPuntajes.agregarNuevoPuntaje(
                new Puntaje(idJugador)
        );
    }
    
    @Override
    public void editarInformacionJugador(String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador) 
            throws DatosJugadorInvalidosException {
        ValidacionesJugador.validarCambiosJugador(jugadorLocal, nombreJugador, imagenJugador, colorJugador);
        transmitirCambioDatosJugador(nombreJugador, imagenJugador, colorJugador);
        jugadorLocal.cambiarNombre(nombreJugador);
        jugadorLocal.cambiarImagen(imagenJugador);
        jugadorLocal.cambiarColor(colorJugador);
        
    }
    
    private void transmitirCambioDatosJugador(String nombreNuevo, ImagenJugador imagenNueva, ColorJugador colorNuevo) {
        CambioJugadorEvent cjEvent = new CambioJugadorEvent(jugadorLocal, nombreNuevo, imagenNueva, colorNuevo);
        try {
            manejoPaquetes.transmitirCambioDatosJugador(cjEvent);
        } catch (PaqueteVacioAlSerializarException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorAlEnviarPaqueteException ex) {
            Logger.getLogger(ModeloJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearPartida(String nombrePartida, int numJugadores, TamañoTablero tamaño) throws DatosIncompletosPartidaException {
        // Validar datos
        if (nombrePartida == null || nombrePartida.isBlank()
                || tamaño == null
                || numJugadores < 2) {
            throw new DatosIncompletosPartidaException("Datos incompletos");
        }
        
        // Guardar datos en estado de juego
        estadoJuego.setNombrePartida(nombrePartida);
        estadoJuego.setNumJugadores(numJugadores);
        estadoJuego.setTamañoTablero(tamaño);
        
        // Crear matriz
        crearMatriz(tamaño);
        matrizVacia = false;
        
        // Mandarlo al servidor
        try {
            DireccionDTO direccionServer = new DireccionDTO(Configuracion.get("server.host"), Configuracion.getInt("server.port"));
            manejoPaquetes.registrarPartidaEnServidor(nombrePartida, numJugadores, direccionLocal, direccionServer);
            System.out.println("La partida se registro en el servidor");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    @Override
    public TamañoTablero obtenerTamañoTablero() {
        return estadoJuego.getTamañoTablero();
    }

    @Override
    public String obtenerNombrePartida() {
        return estadoJuego.getNombrePartida();
    }

    @Override
    public int obtenerNumJugadores() {
        return estadoJuego.getNumJugadores();
    }

}
