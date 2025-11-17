/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
import mvcJuegoIniciado.controlador.ControlJuegoIniciado;
import mvcJuegoIniciado.modelo.ModeloJuegoIniciado;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import mvcJuegoIniciado.vistas.PantallaDeJuego;
import mvcJuegoIniciado.vistas.TableroJuego;
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

    public static void main(String[] args) throws FalloCreacionServerException {
        modeloJuego = new ModeloJuego();
        modeloJuego.inicializarModeloJuego();
        
        
        receptor = new Receptor();
        colaRecepcion = ColaRecepcion.getInstancia();
        colaRecepcion.suscribirReceptor(receptor);
        receptor.inyectarManejador(modeloJuego);
        servidor = new ServerTCP(8080);

        modeloJuego.guardarInformacionJugador("PolloJalado", "1", "1");
        Thread hiloServidor = new Thread(() -> {
            try {
                servidor.iniciarServidor();  // método bloqueante que escucha sockets
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Hilo-Servidor");
        hiloServidor.start();
        modeloJuego.conectarseAServidor();
        iniciarPresentacion();

    }
    
    public static void iniciarPresentacion() {
        
        ModeloJuegoIniciado modelo = new ModeloJuegoIniciado(TamañosTablero.PEQUEÑO, modeloJuego);
        modeloJuego.suscribirObservador(modelo);
        ControlJuegoIniciado control = new ControlJuegoIniciado(modelo);
        TableroJuego tablero = new TableroJuego(modelo, control);
        modelo.setObserverTablero(tablero);
        
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modelo, control, tablero);
        MenuDeOpciones menuDeOpciones = new MenuDeOpciones(modelo,control);
        
        modelo.añadirObserverPantallaDeJuego(pantallaDeJuego);
        modelo.añadirObservadorPantallas(pantallaDeJuego);
        
        modelo.añadirObservadorPantallas(menuDeOpciones);
        
        control.mostrarPantallaDeJuego();
    }
}
