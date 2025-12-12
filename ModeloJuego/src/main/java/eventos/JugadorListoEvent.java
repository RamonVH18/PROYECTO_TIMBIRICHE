/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

/**
 *
 * @author multaslokas33
 */
public class JugadorListoEvent {

    private String idJugador;
    private boolean listo;

    public JugadorListoEvent(String idJugador, boolean listo) {
        this.idJugador = idJugador;
        this.listo = listo;
    }

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }
}
