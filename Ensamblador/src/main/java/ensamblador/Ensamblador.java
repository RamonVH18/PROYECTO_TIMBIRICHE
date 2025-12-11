/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
import eventos.VerificadorEventos;
import excepciones.DatosJugadorInvalidosException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoRecepcionPaquetes;
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
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        
        mvcJuegoInicio.modelo.ModeloJuegoInicio modeloInicio = new mvcJuegoInicio.modelo.ModeloJuegoInicio(modeloJuego);
        mvcJuegoInicio.controlador.ControlJuegoInicio controlInicio = new mvcJuegoInicio.controlador.ControlJuegoInicio(modeloInicio);
        
        // Creamos la pantalla que queremos ver primero: Crear partida
        mvcJuegoInicio.vistas.PantallaCrearPartida pantallaCrear = new mvcJuegoInicio.vistas.PantallaCrearPartida(controlInicio);
        
        // Suscribir observer
        modeloInicio.añadirObserver(pantallaCrear, ObserverType.CREAR_PARTIDA);
        
        pantallaCrear.setVisible(true);

    }
    
}
