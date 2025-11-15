/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import interfaces.ICliente;
import DTOs.EnvioDTO;
import excepciones.FalloConexionSocketException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author Ramon Valencia
 */
public class ColaEnvio {
    private static Queue<EnvioDTO> cola;
    private static ICliente cliente;
    private static ColaEnvio instancia;
    
    private ColaEnvio() {
        cola = new ArrayDeque<>();
    }
    
    public static ColaEnvio getInstancia() {
        if (instancia == null) {
            instancia = new ColaEnvio();
        }
        return instancia;
    }
    
    public void subscribirCliente(ICliente cliente) {
        this.cliente = cliente;
    }
    
    public void encolar(EnvioDTO paquete) throws FalloConexionSocketException {
        cola.add(paquete);
        notificarPaqueteNuevo();
    }
    
    public EnvioDTO desencolar() {
        return cola.remove();
    }
    
    private void notificarPaqueteNuevo() throws FalloConexionSocketException {
        cliente.enviarPaquete();
    }
    
}
