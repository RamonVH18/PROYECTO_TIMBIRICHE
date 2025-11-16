/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
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
        modeloJuego = ModeloJuego.getInstance();
        modeloJuego.inicializarModeloJuego();
        
        receptor = new Receptor();
        colaRecepcion = ColaRecepcion.getInstancia();
        colaRecepcion.suscribirReceptor(receptor);
        receptor.inyectarManejador(modeloJuego);
        servidor = new ServerTCP(5000);

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

    }
}
