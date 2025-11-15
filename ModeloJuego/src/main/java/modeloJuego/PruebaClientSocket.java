/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloJuego;

import com.google.gson.JsonObject;
import envio.DispatcherFactory;
import DTOs.EnvioDTO;
import interfaz.IEmisor;
import DTOs.PaqueteDTO;
import java.io.IOException;

/**
 *
 * @author Ramon Valencia
 */
public class PruebaClientSocket {

    private static IEmisor emisor;

    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        emisor = DispatcherFactory.createDispatcher();
        
            JsonObject jason = new JsonObject();
            jason.addProperty("host", "localhost");
            jason.addProperty("port", 5000);
            PaqueteDTO paquete = new PaqueteDTO("registroPeer", jason);
            EnvioDTO envio = new EnvioDTO("localhost", 8000, paquete);
            emisor.enviarPaquete(envio);
            Thread.sleep(2000L);
        

    }

}
