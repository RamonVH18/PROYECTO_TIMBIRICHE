package DTOs;

public class PartidaDTO {
    private String nombrePartida;
    private int size;
    private int numJugadores;

    public PartidaDTO() {
    }
 
    public PartidaDTO(String nombrePartida, int size, int numJugadores) {
        this.nombrePartida = nombrePartida;
        this.size = size;
        this.numJugadores = numJugadores;
    }
  
    public PartidaDTO(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }
    
    
}
