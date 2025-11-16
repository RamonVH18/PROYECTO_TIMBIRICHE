/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import estructurasDatos.ListaJugadores;
import interfaces.Mediador;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ximena
 */
public class ManejadorTurnos {

    private Jugador jugadorActual;
    private int indiceTurno;
    private Mediador modelo;
    private ListaJugadores listaJugadores;

    public ManejadorTurnos(Mediador modelo, ListaJugadores listaJugadores) {
        this.listaJugadores = listaJugadores;
        this.indiceTurno = 0;
    }

    public Jugador mostrarJugadorActual() {
        return jugadorActual;
    }

    public void iniciarTurno() {
        jugadorActual = listaJugadores.obtenerJugadores().get(indiceTurno);
    }

    public void siguienteTurno() {
        indiceTurno++;

        if (indiceTurno >= listaJugadores.obtenerJugadores().size()) {
            indiceTurno = 0;
        }
    }

}
