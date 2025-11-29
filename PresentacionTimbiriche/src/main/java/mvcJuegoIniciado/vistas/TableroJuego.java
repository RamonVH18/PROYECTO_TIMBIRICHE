/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IVista;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import objetosPresentacion.EstadoLinea;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import objetosModeloJuego.Linea;
import objetosPresentacion.CuadroTablero;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class TableroJuego extends JPanel implements IVista {

    private Dimension dimensionTablero;

    private Integer largo;
    private Integer ancho;
    private LineaTablero lineaSeleccionada;
    private Integer distanciaPunto;
    private IModeloLeibleJI modelo;
    private IControlJuegoIniciado control;

    public TableroJuego(IModeloLeibleJI modelo, IControlJuegoIniciado control) {
        this.modelo = modelo;
        this.control = control;
        generarTablero();

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    }

    // esto es lo que fija el tablero 
    private void generarTablero() {
        PuntoTablero[][] matriz = modelo.getMatriz();
        distanciaPunto = modelo.getTamañoTablero().getDistanciaPuntos();
        largo = (matriz.length * distanciaPunto) + distanciaPunto;
        ancho = (matriz[0].length * distanciaPunto) + distanciaPunto;
        dimensionTablero = new Dimension(ancho, largo);
        setSize(dimensionTablero);
        setPreferredSize(dimensionTablero);
        setMaximumSize(dimensionTablero);
        configurarMouseClicker();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpia el fondo

        List<LineaTablero> lineas = modelo.getLineas();
        List<CuadroTablero> cuadros = modelo.getCuadros();
        PuntoTablero[][] matriz = modelo.getMatriz();

        Integer tamañoPunto = modelo.getTamañoTablero().getTamañoPunto();

        for (LineaTablero l : lineas) {
            if (l.equals(lineaSeleccionada)) {
                l.setEstado(EstadoLinea.SELECCIONADA);
            }
            l.paintComponent(g, distanciaPunto); // usamos el paintComponent de cada línea
        }

        for (CuadroTablero c : cuadros) {
            if (c.estaCompleto()) {
                c.paintComponent(g);
            }
        }

        g.setColor(Color.BLACK); // color de los puntos
        for (Point[] matriz1 : matriz) {
            for (Point p : matriz1) {
                // Dibuja un círculo centrado en la coordenada del Point
                g.fillOval(
                        ((p.x + 1) * distanciaPunto) - (tamañoPunto / 2),
                        ((p.y + 1) * distanciaPunto) - (tamañoPunto / 2),
                        tamañoPunto,
                        tamañoPunto);
            }
        }

    }

    private void configurarMouseClicker() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Point click = e.getPoint();
                List<LineaTablero> lineas = modelo.getLineas();
                if (modelo.estoyJugando()) {
                    for (LineaTablero linea : lineas) {
                        if (estaSobreLinea(click, linea)) {
                            if (lineaSeleccionada != null) {
                                lineaSeleccionada.estado = EstadoLinea.LIBRE;
                            }
                            if (linea.estado == EstadoLinea.OCUPADA) {
                                return;
                            }
                            linea.setEstado(EstadoLinea.OCUPADA);
                            lineaSeleccionada = linea;

                            repaint();
                            break;
                        }
                    }
                }
            }
        });
    }

    private boolean estaSobreLinea(Point p, LineaTablero linea) {
        double distancia = distanciaPuntoALinea(p, linea.puntoA, linea.puntoB);
        return distancia <= (linea.grosorLinea + 3); // margen extra de clic
    }

    private double distanciaPuntoALinea(Point p, Point a, Point b) {
        double A = p.x - ((a.x + 1) * distanciaPunto);
        double B = p.y - ((a.y + 1) * distanciaPunto);
        double C = ((b.x + 1) * distanciaPunto) - ((a.x + 1) * distanciaPunto);
        double D = ((b.y + 1) * distanciaPunto) - ((a.y + 1) * distanciaPunto);

        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double param = (lenSq != 0) ? (dot / lenSq) : -1;

        double xx, yy;

        if (param < 0) {
            xx = ((a.x + 1) * distanciaPunto);
            yy = ((a.y + 1) * distanciaPunto);
        } else if (param > 1) {
            xx = ((b.x + 1) * distanciaPunto);
            yy = ((b.y + 1) * distanciaPunto);
        } else {
            xx = ((a.x + 1) * distanciaPunto) + param * C;
            yy = ((a.y + 1) * distanciaPunto) + param * D;
        }

        double dx = p.x - xx;
        double dy = p.y - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public LineaTablero getLineaSeleccionada() {
        return lineaSeleccionada;
    }
    
    public void desocuparLineaSeleccionada() {
        lineaSeleccionada = null;
    }

    @Override
    public void actualizar() {
//        if(lineaSeleccionada == null) {
//            return;
//        }
//        lineaSeleccionada.estado = EstadoLinea.OCUPADA;
//        lineaSeleccionada = null;
        repaint();
    }

    @Override
    public void mostrar() {

    }

}
