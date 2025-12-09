/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
import eventos.VerificadorEventos;
import manejadores.ManejoRecepcionPaquetes;
import mvcJuegoInicio.controlador.ControlJuegoInicio;
import mvcJuegoInicio.modelo.ModeloJuegoInicio;
import mvcJuegoInicio.vistas.PantallaRegistrarJugador;
import mvcJuegoInicio.vistas.PantallaSeleccionImagen;
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

//        modeloJuego.conectarseAServidor();
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        ModeloJuegoInicio modeloInicio = new ModeloJuegoInicio(modeloJuego);
        modeloJuego.suscribirObservadorInicio(modeloInicio);
        ControlJuegoInicio control = new ControlJuegoInicio(modeloInicio);
        PantallaRegistrarJugador registarJugador = new PantallaRegistrarJugador(modeloInicio, control);
        PantallaSeleccionImagen seleccionImagen = new PantallaSeleccionImagen(registarJugador, modeloInicio, control);
        modeloInicio.añadirObserver(registarJugador, ObserverType.REGISTRAR_JUGADOR);
        modeloInicio.añadirObserver(seleccionImagen, ObserverType.SELECCION_IMAGEN);

        control.mostrarVista(ObserverType.REGISTRAR_JUGADOR);
    }
}
