/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import manejadores.ManejadorPaquetes;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
import enums.TamañosTablero;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import recepcion.ColaRecepcion;
import recepcion.Receptor;
import recepcion.ServerTCP;

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
    public static ManejadorPaquetes manejoPaquetes;

    public static void main(String[] args) throws FalloCreacionServerException {
        iniciarPresentacion();
    }

    public static void iniciarComponenteRed() {
        receptor = new Receptor();
        colaRecepcion = ColaRecepcion.getInstancia();
        colaRecepcion.suscribirReceptor(receptor);
        receptor.inyectarManejador(manejoPaquetes);
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
        manejoPaquetes = new ManejadorPaquetes(modeloJuego);
        modeloJuego.inicializarModeloJuego(manejoPaquetes);
        iniciarComponenteRed();

        modeloJuego.conectarseAServidor();
        modeloJuego.guardarInformacionJugador("1", "Yizbin", ImagenJugador.IMAGEN, ColorJugador.AZUL);
//        modeloJuego.registrarNuevoJugador(
//                new Jugador("2", "Pollo Jalado", "2", "rojo"),
//                new DireccionDTO("192.168.1.70", 5000)
//        );
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        ModeloJuegoIniciado modelo = new ModeloJuegoIniciado(TamañosTablero.PEQUEÑO, modeloJuego);
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
