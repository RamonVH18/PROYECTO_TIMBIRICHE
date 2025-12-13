/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaces.IModeloJuegoInicio;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.PantallaUnirsePartida;
import mvcJuegoIniciado.vistas.TableroJuego;
import mvcJuegoInicio.controlador.ControlJuegoInicio;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.modelo.ModeloJuegoInicio;
import eventos.VerificadorEventos;
import excepciones.DatosJugadorInvalidosException;

import static enums.ObserverType.PANTALLA_UNIRSE_PARTIDA;

import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoRecepcionPaquetes;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import mvcJuegoIniciado.vistas.PantallaUnirsePartida;
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
    public static ModeloJuegoInicio modeloJuegoInicio;
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
                servidor.iniciarServidor(); // método bloqueante que escucha sockets
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
            // modeloJuego.registrarNuevoJugador(
            // new Jugador("2", "Pollo Jalado", "2", "rojo"),
            // new DireccionDTO("192.168.1.70", 5000)
            // );
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void iniciarModeloinicio(IModeloJuegoInicio modelo) {
        modeloJuegoInicio = new ModeloJuegoInicio(modelo);
        controlJuegoInicio = new ControlJuegoInicio(modeloJuegoInicio);
        PantallaUnirsePartida pantallaUnirsePartida = new PantallaUnirsePartida(controlJuegoInicio, modeloJuegoInicio);
        modeloJuegoInicio.añadirObserver(pantallaUnirsePartida, PANTALLA_UNIRSE_PARTIDA);
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        iniciarModeloinicio(modeloJuego);
        modeloJuego.suscribirObservadorInicio(modeloJuegoInicio);
        
        ControlJuegoInicio control = new ControlJuegoInicio(modeloJuegoInicio);

        control.mostrarPantallaUnirsePartida();

    }
}
