/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloJuego;

import com.google.gson.JsonObject;
import envio.DispatcherFactory;
import envio.IEmisor;
import envio.PaqueteDTO;
import java.io.IOException;

/**
 *
 * @author Ramon Valencia
 */
public class PruebaClientSocket {

    private static IEmisor emisor;

    public static void main(String[] args) throws IOException {
        emisor = DispatcherFactory.createDispatcher("localhost", 5000);

        JsonObject jason = new JsonObject();
        jason.addProperty("nombre", "ABRAHAMA");
        PaqueteDTO paquete = new PaqueteDTO("abraham", jason);

        emisor.agregarAColaEnvio(paquete);
    }

}
