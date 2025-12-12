/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import manejadores.ManejoEnvioPaquetes;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.PantallaUnirsePartida;
import mvcJuegoIniciado.vistas.TableroJuego;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.modelo.ModeloJuegoInicio;
import enums.TamañosTablero;
import eventos.VerificadorEventos;
import excepciones.DatosJugadorInvalidosException;

import static enums.ObserverType.PANTALLA_UNIRSE_PARTIDA;

import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoRecepcionPaquetes;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
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
    public static IModeloLeibleJInicio modeloJuegoInicio;
    public static IControlJuegoInicio controlJuegoInicio;

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
            modeloJuego.guardarInformacionJugador("1", "Yizbin", ImagenJugador.GOLDEN, ColorJugador.AZUL);
//        modeloJuego.registrarNuevoJugador(
//                new Jugador("2", "Pollo Jalado", "2", "rojo"),
//                new DireccionDTO("192.168.1.70", 5000)
//        );
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void iniciarModeloinicio(){
        modeloJuegoInicio = new ModeloJuegoInicio();
        controlJuegoInicio = new controlJuegoInicio();
        PantallaUnirsePartida = new PantallaUnirsePartida(controlJuegoInicio, modeloJuegoInicio);
        modeloJuegoInicio.añadirObserver(PantallaUnirsePartida, PANTALLA_UNIRSE_PARTIDA);
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        iniciarModeloinicio();
        modeloJuego.suscribirObservador(modelo);
        ControlJuegoIniciado control = new ControlJuegoIniciado(modelo);
        TableroJuego tablero = new TableroJuego(modelo, control);
        MenuDeOpciones menuDeOpciones = new MenuDeOpciones(modelo, control);

        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control, tablero);

        modelo.añadirObserver(tablero, ObserverType.TABLERO);
        modelo.añadirObserver(pantallaDeJuego, ObserverType.PANTALLA_JUEGO);
        modelo.añadirObserver(pantallaDeJuego, ObserverType.PANTALLAS);
        modelo.añadirObserver(menuDeOpciones, ObserverType.MENU_OPCIONES);

        control.mostrarVista(ObserverType.PANTALLA_JUEGO);
        modeloJuego.empezarJuego();
    }
}
