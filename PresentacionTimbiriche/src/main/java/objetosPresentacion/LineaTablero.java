/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    public LineaTablero(Point a, Point b, OrientacionLinea orientacion, Integer grosorLinea) {
        this.puntoA = a;
        this.puntoB = b;
        this.orientacion = orientacion;
        this.estado = EstadoLinea.LIBRE;
        this.grosorLinea = grosorLinea;
    }

    //revisar el desacoplo del estado de la línea en el constructor
    //setLibre, setOcupado, setSeleccionado
    @Override
    public void paintComponent(Graphics g) {
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
        g2.drawLine(puntoA.x, puntoA.y, puntoB.x, puntoB.y);
    }
    
}
