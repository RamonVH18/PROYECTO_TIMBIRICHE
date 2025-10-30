/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloJuego;

import com.google.gson.JsonObject;
import envio.DispatcherFactory;
import envio.EnvioDTO;
import envio.IEmisor;
import envio.PaqueteDTO;
import java.io.IOException;

/**
 *
 * @author Ramon Valencia
 */
public class PruebaClientSocket {

    private static IEmisor emisor;

    public static void main(String[] args) throws IOException, InterruptedException {
        emisor = DispatcherFactory.createDispatcher();

        
        
        for (int i = 0; i < 3; i++) {

            JsonObject jason = new JsonObject();
            jason.addProperty("nombre", "Este es un paquete enviado usando Json " + i);
            PaqueteDTO paquete = new PaqueteDTO("Jason", jason);
            EnvioDTO envio = new EnvioDTO("localhost", 5000, paquete);
            emisor.enviarPaquete(envio);

            Thread.sleep(2000L);
        }

    }

}
