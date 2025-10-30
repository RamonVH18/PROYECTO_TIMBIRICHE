/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

/**
 *
 * @author Ramon Valencia
 */
public class DireccionDTO {

    private final String host;
    private final int port;
    
    public DireccionDTO(String HOST, int PORT) {
        this.host = HOST;
        this.port = PORT;
    }
    
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    
}
