/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import Enums.TamañoTablero;
import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import manejadores.ManejoEnvioPaquetes;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
import enums.TamañosTablero;
import eventos.VerificadorEventos;
import excepciones.DatosJugadorInvalidosException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoRecepcionPaquetes;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import mvcJuegoInicio.vistas.PantallaMockJuego;
import recepcion.ColaRecepcion;
import recepcion.Receptor;
import recepcion.ServerTCP;
import serializador.Deserializador;

/**
 *
 * @author Ramon Valencia
 */
public class Ensamblador {

    public static ModeloJuego modeloJuego;
    public static IEmisor emisor;
    public static Receptor receptor;
    public static ServerTCP servidor;
    public static ColaRecepcion colaRecepcion;
    public static VerificadorEventos verificador;
    public static Deserializador deserializador;
    public static ManejoRecepcionPaquetes manejoRecepcionPaquetes;

    public static void main(String[] args) throws FalloCreacionServerException {
        iniciarPresentacion();
    }

    public static void iniciarComponenteRed() {
        receptor = new Receptor();
        colaRecepcion = ColaRecepcion.getInstancia();
        colaRecepcion.suscribirReceptor(receptor);
        receptor.inyectarManejador(manejoRecepcionPaquetes);
        servidor = new ServerTCP(8080);

        Thread hiloServidor = new Thread(() -> {
            try {
                servidor.iniciarServidor();  // método bloqueante que escucha sockets
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Hilo-Servidor");
        hiloServidor.start();
    }

    public static void iniciarModelo() {
        modeloJuego = new ModeloJuego();
        verificador = new VerificadorEventos(modeloJuego);
        deserializador = new Deserializador(verificador);
        manejoRecepcionPaquetes = new ManejoRecepcionPaquetes(deserializador);
        iniciarComponenteRed();

        modeloJuego.conectarseAServidor();
        try {
            modeloJuego.guardarInformacionJugador("1", "ximegaymer", ImagenJugador.GOLDEN, ColorJugador.ROSA);
//        modeloJuego.registrarNuevoJugador(
//                new Jugador("2", "Pollo Jalado", "2", "rojo"),
//                new DireccionDTO("192.168.1.70", 5000)
//        );
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void mostrarPantallaJuego(TamañoTablero tamañoSeleccionado) {
        // Crear la pantalla de juego cuando se pida mostrarse
        // TamañoTablero tamModelo = modeloJuego.obtenerTamañoTablero();
        TamañosTablero tamVista = TamañosTablero.valueOf(tamañoSeleccionado.name());
        ModeloJuegoIniciado modeloJuegoIniciado = new ModeloJuegoIniciado(tamVista, modeloJuego);
        modeloJuego.suscribirObservador(modeloJuegoIniciado);
            
        ControlJuegoIniciado controlJuego = new ControlJuegoIniciado(modeloJuegoIniciado);
            
        TableroJuego tablero = new TableroJuego(modeloJuegoIniciado, controlJuego);
        MenuDeOpciones menuDeOpciones = new MenuDeOpciones(modeloJuegoIniciado, controlJuego);
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modeloJuegoIniciado, controlJuego, tablero);
            
        modeloJuegoIniciado.añadirObserver(tablero, ObserverType.TABLERO);
        modeloJuegoIniciado.añadirObserver(menuDeOpciones, ObserverType.MENU_OPCIONES);
        modeloJuegoIniciado.añadirObserver(pantallaDeJuego, ObserverType.PANTALLA_JUEGO);
            
        pantallaDeJuego.setVisible(true);
        
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        
        mvcJuegoInicio.modelo.ModeloJuegoInicio modeloInicio = new mvcJuegoInicio.modelo.ModeloJuegoInicio(modeloJuego);
        mvcJuegoInicio.controlador.ControlJuegoInicio controlInicio = new mvcJuegoInicio.controlador.ControlJuegoInicio(modeloInicio);
        
        // Creamos la pantalla que queremos ver primero: Crear partida
        mvcJuegoInicio.vistas.PantallaCrearPartida pantallaCrear = new mvcJuegoInicio.vistas.PantallaCrearPartida(controlInicio);
        // Crear pantalla a ver despues
        // PantallaMockJuego mockJuego = new PantallaMockJuego(modeloInicio, controlInicio);
        
        modeloInicio.añadirObserver(pantallaCrear, ObserverType.CREAR_PARTIDA);
        // modeloInicio.añadirObserver(mockJuego, ObserverType.PANTALLA_MOCK);
       
        pantallaCrear.setVisible(true);

    }
    
}
