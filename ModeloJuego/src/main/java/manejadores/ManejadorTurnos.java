/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import estructurasDatos.ListaJugadores;
import java.util.List;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ximena
 */
public class ManejadorTurnos {

    private Jugador jugadorEnTurno;
    private int indiceTurno;
    private ListaJugadores listaJugadores;

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
        indiceTurno++;

        if (indiceTurno >= listaJugadores.obtenerJugadores().size()) {
            indiceTurno = 0;
        }
    }
    
    public boolean esMiTurno(Jugador jugadorLocal) {
        return jugadorEnTurno.equals(jugadorLocal);
    }
    
    public void crearTurnos() {
        
        List<Jugador> jugadores = listaJugadores.obtenerJugadores();//.sort(Comparator.comparing(j -> j.getIdJugador()));
        int conta = 1;
        for (Jugador j : jugadores) {
            j.asignarIdJugador(String.valueOf(conta));
            conta++;
        }
    }
}
