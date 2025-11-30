/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import enums.OrientacionLinea;
import enums.EstadoLinea;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Ramon Valencia
 */
public class LineaTablero extends JComponent {

    public Point puntoA;
    public Point puntoB;
    public OrientacionLinea orientacion;
    public EstadoLinea estado;
    public Integer grosorLinea;

    public LineaTablero(Point a, Point b, EstadoLinea estado) {
        this.puntoA = a;
        this.puntoB = b;
        this.orientacion = generarOrientacion(a, b);
        this.estado = estado;
    }
    
    private OrientacionLinea generarOrientacion(Point a, Point b) {
        
        if(a.getX() == b.getX()) {
            return OrientacionLinea.VERTICAL;
        }
        return OrientacionLinea.HORIZONTAL;
    }
    //revisar el desacoplo del estado de la línea en el constructor
    //setLibre, setOcupado, setSeleccionado
    
    public void paintComponent(Graphics g, Integer distanciaEntrePuntos) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        switch (estado) {
            case LIBRE ->
                g.setColor(Color.LIGHT_GRAY);
            case SELECCIONADA ->
                g.setColor(Color.BLUE);
            case OCUPADA ->
                g.setColor(Color.BLACK);
            default -> {
            }
        }
        g2.setStroke(new BasicStroke(grosorLinea));
        g2.drawLine((puntoA.x + 1) * distanciaEntrePuntos ,
                (puntoA.y + 1) * distanciaEntrePuntos,
                (puntoB.x + 1) * distanciaEntrePuntos,
                (puntoB.y + 1) * distanciaEntrePuntos);
    }

    public OrientacionLinea getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionLinea orientacion) {
        this.orientacion = orientacion;
    }

    public EstadoLinea getEstado() {
        return estado;
    }

    public void setEstado(EstadoLinea estado) {
        this.estado = estado;
    }

    public Integer getGrosorLinea() {
        return grosorLinea;
    }

    public void setGrosorLinea(Integer grosorLinea) {
        this.grosorLinea = grosorLinea;
    }

    
    
}
