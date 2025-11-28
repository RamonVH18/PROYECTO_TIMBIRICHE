/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import DTOs.DireccionDTO;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import manejadores.ManejadorPaquetes;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
import objetosModeloJuego.Jugador;
import objetosPresentacion.TamañosTablero;
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
        
//        modeloJuego.conectarseAServidor();
        modeloJuego.guardarInformacionJugador("2", "Yizbin", "1", "1");
//        modeloJuego.registrarNuevoJugador(
//                new Jugador("1", "Pollo Jalado", "1", "1"),
//                new DireccionDTO("", )
//        );
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        ModeloJuegoIniciado modelo = new ModeloJuegoIniciado(TamañosTablero.PEQUEÑO, modeloJuego);
        modeloJuego.suscribirObservador(modelo);
        ControlJuegoIniciado control = new ControlJuegoIniciado(modelo);
        TableroJuego tablero = new TableroJuego(modelo, control);
        modelo.setObserverTablero(tablero);

        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control, tablero);
        MenuDeOpciones menuDeOpciones = new MenuDeOpciones(modelo, control);

        modelo.añadirObserverPantallaDeJuego(pantallaDeJuego);
        modelo.añadirObservadorPantallas(pantallaDeJuego);

        modelo.añadirObservadorPantallas(menuDeOpciones);

        control.mostrarPantallaDeJuego();
        modeloJuego.empezarJuego();
    }
}
