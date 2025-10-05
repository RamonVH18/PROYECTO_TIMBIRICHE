/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import controlador.Control;
import interfaces.IControl;
import interfaces.IModeloLeible;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import objetosPresentacion.Linea;
import objetosPresentacion.OrientacionLinea;
import interfaces.IVista;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.Modelo;
import objetosPresentacion.EstadoLinea;

/**
 *
 * @author Ramon Valencia
 */
public class TableroJuego extends JPanel implements IVista {

    private IModeloLeible modelo;
    private IControl control;
    private Dimension dimensionTablero;

    private final Point[][] matriz;
    private final List<Linea> lineas = new ArrayList<>();
    private Integer largo;
    private Integer ancho;
    private final Integer distanciaPuntos;
    private final Integer tamañoPunto;
    private final Integer grosorLinea;
    private Linea lineaSeleccionada;

    public TableroJuego(Point[][] matrizPuntos, Integer distancia, Integer tamaño, Integer grosor) {
        modelo = Modelo.getInstaciaModelo();
        control = Control.getInstanciaControl();

        distanciaPuntos = distancia;
        tamañoPunto = tamaño;
        matriz = matrizPuntos;
        grosorLinea = grosor;
        generarTablero();
        generarLineas();
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    }

    // esto es lo que fija el tablero 
    private void generarTablero() {
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

    private void generarLineas() {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Líneas horizontales
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                Point a = matriz[i][j];
                Point b = matriz[i][j + 1];
                lineas.add(new Linea(a, b, OrientacionLinea.HORIZONTAL, grosorLinea));
            }
        }

        // Líneas verticales
        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < columnas; j++) {
                Point a = matriz[i][j];
                Point b = matriz[i + 1][j];
                lineas.add(new Linea(a, b, OrientacionLinea.VERTICAL, grosorLinea));
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
