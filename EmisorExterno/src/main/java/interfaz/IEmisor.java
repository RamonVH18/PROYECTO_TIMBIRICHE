/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaz;

import DTOs.EnvioDTO;

/**
 *
 * @author Ramon Valencia
 */
public interface IEmisor {
    
    public void enviarPaquete(EnvioDTO envio) throws Exception;
    
}
