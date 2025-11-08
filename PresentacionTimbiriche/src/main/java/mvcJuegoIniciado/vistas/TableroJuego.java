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
import objetosPresentacion.Linea;
import mvcJuegoIniciado.interfaces.IVista;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import objetosPresentacion.EstadoLinea;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;

/**
 *
 * @author Ramon Valencia
 */
public class TableroJuego extends JPanel implements IVista {
    private Dimension dimensionTablero;

    private Integer largo;  
    private Integer ancho;
    private Linea lineaSeleccionada;
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
        Point[][] matriz = modelo.getMatriz(); 
        Integer distanciaPuntos = modelo.getTamañoTablero().getDistanciaPuntos();
        largo = (matriz.length * distanciaPuntos) + distanciaPuntos;
        ancho = (matriz[0].length * distanciaPuntos) + distanciaPuntos;
        dimensionTablero = new Dimension(ancho, largo);
        setSize(dimensionTablero);
        setPreferredSize(dimensionTablero);
        setMaximumSize(dimensionTablero);
        configurarMouseClicker();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpia el fondo
        
        ArrayList <Linea> lineas = (ArrayList <Linea>) modelo.getLineas();
        Point[][] matriz = modelo.getMatriz();
        Integer tamañoPunto = modelo.getTamañoTablero().getTamañoPunto();
        
        for (Linea l : lineas) {
            l.paintComponent(g); // usamos el paintComponent de cada línea
        }

        g.setColor(Color.BLACK); // color de los puntos
        for (Point[] matriz1 : matriz) {
            for (Point p : matriz1) {
                // Dibuja un círculo centrado en la coordenada del Point
                g.fillOval(p.x - (tamañoPunto / 2), p.y - (tamañoPunto / 2), tamañoPunto, tamañoPunto);
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
                ArrayList <Linea> lineas = (ArrayList <Linea>) modelo.getLineas();
                for (Linea linea : lineas) {
                    if (estaSobreLinea(click, linea)) {
                        if (lineaSeleccionada != null) {
                            lineaSeleccionada.estado = EstadoLinea.LIBRE;
                        }
                        if (linea.estado == EstadoLinea.OCUPADA) {
                            return;
                        }
                        lineaSeleccionada = linea;
                        lineaSeleccionada.estado = EstadoLinea.SELECCIONADA;
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    private boolean estaSobreLinea(Point p, Linea linea) {
        double distancia = distanciaPuntoALinea(p, linea.puntoA, linea.puntoB);
        return distancia <= (linea.grosorLinea + 3); // margen extra de clic
    }

    private double distanciaPuntoALinea(Point p, Point a, Point b) {
        double A = p.x - a.x;
        double B = p.y - a.y;
        double C = b.x - a.x;
        double D = b.y - a.y;

        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double param = (lenSq != 0) ? (dot / lenSq) : -1;

        double xx, yy;

        if (param < 0) {
            xx = a.x;
            yy = a.y;
        } else if (param > 1) {
            xx = b.x;
            yy = b.y;
        } else {
            xx = a.x + param * C;
            yy = a.y + param * D;
        }

        double dx = p.x - xx;
        double dy = p.y - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Linea getLineaSeleccionada() {
        return lineaSeleccionada;
    }

    @Override
    public void actualizar() {
        if(lineaSeleccionada == null) {
            return;
        }
        lineaSeleccionada.estado = EstadoLinea.OCUPADA;
        lineaSeleccionada = null;
        repaint();
    }

    @Override
    public void mostrar() {

    }

}
