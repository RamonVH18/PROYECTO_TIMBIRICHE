/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author Ramon Valencia
 */
public class DireccionDTO {
    
    private final String host;
    private final int port;
    private final boolean esHost;

    public DireccionDTO(String host, int port) {
        this.host = host;
        this.port = port;  
        this.esHost = false;
    }    
    
    public DireccionDTO(String host, int port, boolean esHost) {
        this.host = host;
        this.port = port;
        this.esHost = esHost;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isEsHost() {
        return esHost;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DireccionDTO other = (DireccionDTO) obj;
        if (this.port != other.port) {
            return false;
        }
        return Objects.equals(this.host, other.host);
    }
    
    
}
