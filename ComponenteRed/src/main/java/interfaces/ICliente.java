/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.FalloConexionSocketException;

/**
 *
 * @author Ramon Valencia
 */
public interface ICliente {
    
    public void enviarPaquete() throws FalloConexionSocketException;
}
