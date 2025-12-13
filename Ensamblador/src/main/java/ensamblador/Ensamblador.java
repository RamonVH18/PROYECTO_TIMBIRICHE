/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ensamblador;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import static ensamblador.Ensamblador.servidor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejoRecepcionPaquetes;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoIniciado.vistas.MenuDeOpciones;
import mvcJuegoInicio.controlador.ControlJuegoInicio;
import mvcJuegoInicio.modelo.ModeloJuegoInicio;
import mvcJuegoInicio.vistas.PantallaInicio;
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
        servidor = new ServerTCP(9010);

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
            //modeloJuego.guardarInformacionJugador("0", "JANO", ImagenJugador.GATO, ColorJugador.NARANJA);
            //modeloJuego.guardarInformacionJugador("1", "ESKELER", ImagenJugador.MAOMAO, ColorJugador.VERDE);
            //modeloJuego.guardarInformacionJugador("2", "RODRIGO", ImagenJugador.PANDA, ColorJugador.TURQUESA);
            modeloJuego.guardarInformacionJugador("3", "SONIA", ImagenJugador.STUART, ColorJugador.AZUL);
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(Ensamblador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void iniciarPresentacion() {
        iniciarModelo();
        ModeloJuegoIniciado modeloJuegoIniciado = new ModeloJuegoIniciado(TamañosTablero.PEQUEÑO, modeloJuego);
        modeloJuego.suscribirObservador(modeloJuegoIniciado);
         
        ControlJuegoIniciado controlJuegoIniciado = new ControlJuegoIniciado(modeloJuegoIniciado);
        
        TableroJuego tablero = new TableroJuego(modeloJuegoIniciado, controlJuegoIniciado);
        PantallaDeJuego pantallaDeJuego = new PantallaDeJuego(modeloJuegoIniciado, controlJuegoIniciado, tablero);

        modeloJuegoIniciado.añadirObserver(tablero, ObserverType.TABLERO);
        modeloJuegoIniciado.añadirObserver(pantallaDeJuego, ObserverType.PANTALLA_JUEGO);
        modeloJuegoIniciado.añadirObserver(pantallaDeJuego, ObserverType.PANTALLAS);
        
        ModeloJuegoInicio modeloJuegoInicio = new ModeloJuegoInicio();
        ControlJuegoInicio controlJuegoInicio = new ControlJuegoInicio(modeloJuegoInicio);
        
        MenuDeOpciones menuDeOpciones = new MenuDeOpciones(modeloJuegoIniciado, controlJuegoIniciado, controlJuegoInicio);
        
        PantallaInicio pantallaInicio = new PantallaInicio(modeloJuegoInicio, controlJuegoInicio);
        
        modeloJuegoIniciado.añadirObserver(menuDeOpciones, ObserverType.MENU_OPCIONES);
        modeloJuegoInicio.añadirObserver(pantallaInicio, ObserverType.PANTALLA_INICIO);

        controlJuegoIniciado.mostrarVista(ObserverType.PANTALLA_JUEGO);
        modeloJuego.empezarJuego();
    }
}


//ESTOS OBJETOS ME SIRVEN PARA NOTIFICAR EL CIERRE DEL SERVIDOR TCP DEL CLIENTE
//QUE ABANDONA LA PARTIDA
/*
interface IObservadorApagarServidor{
    public boolean notificarCerrarServidor();
}

class ObservadorApagarServidor implements IObservadorApagarServidor{
    @Override
    public boolean notificarCerrarServidor(){
        return true;
    }
}

class ObservableApagarServidor{
    List<ObservadorApagarServidor> listaObservadores = new ArrayList<>();
    
    public void subscribir(ObservadorApagarServidor observador){
        listaObservadores.add(observador);
    }
    
    public void notificar(){
        for (ObservadorApagarServidor observador : listaObservadores) {
            observador.notificarCerrarServidor();
        }
    }
}*/

