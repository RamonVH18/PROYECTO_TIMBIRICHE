/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import DTOs.PaqueteDTO;
import excepciones.PaqueteVacioAlDeserializarException;
import interfaz.IReceptorPaquetes;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializador.Deserializador;

/**
 *
 * @author Ramon Valencia
 */
public class ManejoRecepcionPaquetes implements IReceptorPaquetes {

    private Deserializador deserializador;

    public ManejoRecepcionPaquetes(Deserializador deserializador) {
        this.deserializador = deserializador;
    }

    @Override
    public void recibirPaquete(PaqueteDTO paquete) {
        if (paquete == null || paquete.getTipoPaquete().isBlank()) {
            return;
        }
        revisarPaqueteRecibido(paquete);
    }
    
    private void revisarPaqueteRecibido(PaqueteDTO paquete) {
        try {
            deserializador.deserializarPaquete(paquete);
        } catch (PaqueteVacioAlDeserializarException ex) {
            Logger.getLogger(ManejoRecepcionPaquetes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
