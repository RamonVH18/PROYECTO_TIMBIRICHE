/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

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

/**
 *
 * @author Ramon Valencia
 */
public class TableroJuego extends JPanel implements IVista{

    Dimension dimensionTablero;

    Point[][] matriz;
    List<Linea> lineas = new ArrayList<>();
    Integer largo;
    Integer ancho;
    Integer distanciaPuntos;
    Integer tamañoPunto;

    public TableroJuego(Point[][] matrizPuntos, Integer distancia, Integer tamaño) {
        distanciaPuntos = distancia;
        tamañoPunto = tamaño;
        matriz = matrizPuntos;
        generarTablero();
        generarLineas();
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    }

    private void generarTablero() {
        largo = (matriz.length * distanciaPuntos) + distanciaPuntos;
        ancho = (matriz[0].length * distanciaPuntos) + distanciaPuntos;
        dimensionTablero = new Dimension(ancho, largo);
        setSize(dimensionTablero);
        setPreferredSize(dimensionTablero);
        setMaximumSize(dimensionTablero);

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
                lineas.add(new Linea(a, b, OrientacionLinea.HORIZONTAL));
            }
        }

        // Líneas verticales
        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < columnas; j++) {
                Point a = matriz[i][j];
                Point b = matriz[i + 1][j];
                lineas.add(new Linea(a, b, OrientacionLinea.VERTICAL));
            }
        }
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
