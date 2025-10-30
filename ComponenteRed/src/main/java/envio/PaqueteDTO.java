/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import com.google.gson.JsonObject;


/**
 *
 * @author Ramon Valencia
 */public class PaqueteDTO {

    private final String tipoPaquete;
    private final JsonObject mensaje;
    
    public PaqueteDTO(String tipo, JsonObject paquete) {
        this.tipoPaquete = tipo;
        this.mensaje = paquete;
        
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public JsonObject getMensaje() {
        return mensaje;
    }
    
}
