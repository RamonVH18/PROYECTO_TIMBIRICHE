/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosPresentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author Ximena
 */
public class CuadroTablero extends JComponent {
    private Point esquinaSuperiorIzquierda;
    private int tamanio;
    private Color colorJugador;
    private boolean completado;

    public CuadroTablero(Point esquina, int tamanio) {
        this.esquinaSuperiorIzquierda = esquina;
        this.tamanio = tamanio;
        this.colorJugador = null;
        this.completado = false;
        
        setOpaque(false);
        setBounds(esquina.x, esquina.y, tamanio, tamanio);
    }
    
    public void actualizarEstado(Color color, boolean completado) {
        this.colorJugador = color;
        this.completado = completado;
    
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!completado || colorJugador == null) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        
        Color color = new Color(
                colorJugador.getRed(),
                colorJugador.getGreen(),
                colorJugador.getBlue(),
                120  
        );
        
        g2d.setColor(color);
        g2d.fillRect(esquinaSuperiorIzquierda.x + tamanio, esquinaSuperiorIzquierda.y + tamanio, tamanio, tamanio);
    }
    
    public boolean estaCompleto() {
        return completado;
    }
    
}
