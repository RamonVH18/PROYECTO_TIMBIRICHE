/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ensamblador;

import excepciones.FalloCreacionServerException;
import interfaz.IEmisor;
import modeloJuego.ModeloJuego;
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
    
    public static void main(String[] args) throws FalloCreacionServerException {
        modeloJuego = ModeloJuego.getInstance();
        receptor.inyectarManejador(modeloJuego);
        
        
        modeloJuego.guardarInformacionJugador("PolloJalado", "1", "1");
        servidor.iniciarServidor();
        modeloJuego.conectarseAServidor();
        
        
    }
}
