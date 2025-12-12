/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import estructurasDatos.ListaJugadores;
import java.util.Comparator;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ximena
 */
public class ManejadorTurnos {

    private Jugador jugadorEnTurno;
    private int indiceTurno;
    private ListaJugadores listaJugadores;
    private String palabraClaveJugadorAbandonoPartida ="Desconectado";

    public ManejadorTurnos(ListaJugadores listaJugadores) {
        this.listaJugadores = listaJugadores;
        this.indiceTurno = 0;
    }

    public Jugador mostrarJugadorActual() {
        return jugadorEnTurno;
    }

    public void iniciarTurno() {
        jugadorEnTurno = listaJugadores.obtenerJugadores().get(indiceTurno);
    }

    public void siguienteTurno() {
        int total = listaJugadores.obtenerJugadores().size();

        if (total == 0) return;

        do {
            indiceTurno++;

            if (indiceTurno >= total) {
                indiceTurno = 0;
            }

        } while (isJugadorDesconectado(
                    listaJugadores.obtenerJugadores()
                        .get(indiceTurno)
                        .getIdJugador()
                ));

        jugadorEnTurno = listaJugadores.obtenerJugadores().get(indiceTurno);
    }

    
    public boolean esMiTurno(Jugador jugadorLocal) {
        return jugadorEnTurno.equals(jugadorLocal);
    }
    
    public void crearTurnos() {
        listaJugadores.obtenerJugadores().sort(Comparator.comparing(j -> j.getIdJugador()));
    }
    
    public void quitarJugadorDeTurnero(String idJugador){
        listaJugadores.marcarComoDesconectadoJugador(idJugador);
    }
    
    public boolean isJugadorDesconectado(String idJugador){
        return listaJugadores.obtenerJugador(idJugador).getNombre().contains(palabraClaveJugadorAbandonoPartida);
    }
}
