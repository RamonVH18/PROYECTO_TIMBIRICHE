/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import DTOs.PaqueteDTO;

/**
 *
 * @author Ramon Valencia
 */
public class EnvioDTO {

    private final String host;
    private final int port;
    private final PaqueteDTO paquete;

    public EnvioDTO(String Host, int port, PaqueteDTO paquete) {
        this.host = Host;
        this.port = port;
        this.paquete = paquete;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public PaqueteDTO getPaquete() {
        return paquete;
    }

}
