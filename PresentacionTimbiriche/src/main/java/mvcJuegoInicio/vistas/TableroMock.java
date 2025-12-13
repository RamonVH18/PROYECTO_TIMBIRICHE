/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import objetosPresentacion.CuadroTablero;
import objetosPresentacion.LineaTablero;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ximena
 */
public class TableroMock extends JPanel {
    private Dimension dimensionTablero;
    private IModeloLeibleJInicio modelo;
    private Integer distanciaPunto;

    public TableroMock(IModeloLeibleJInicio modelo) {
        this.modelo = modelo;
        generarTablero();
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
    }
    
    private void generarTablero() {
        PuntoTablero[][] matriz = modelo.getMatriz();
        distanciaPunto = modelo.getTamañoTablero().getDistanciaPuntos();

        int largo = (matriz.length * distanciaPunto) + distanciaPunto;
        int ancho = (matriz[0].length * distanciaPunto) + distanciaPunto;

        dimensionTablero = new Dimension(ancho, largo);
        setPreferredSize(dimensionTablero);
        setSize(dimensionTablero);
        setMaximumSize(dimensionTablero);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<LineaTablero> lineas = modelo.getLineas();
        List<CuadroTablero> cuadros = modelo.getCuadros();
        PuntoTablero[][] matriz = modelo.getMatriz();
        int tamañoPunto = modelo.getTamañoTablero().getTamañoPunto();

        for (LineaTablero l : lineas) {
            l.paintComponent(g, distanciaPunto);
        }

        for (CuadroTablero c : cuadros) {
            if (c.estaCompleto()) {
                c.paintComponent(g);
            }
        }

        g.setColor(Color.BLACK);
        for (Point[] fila : matriz) {
            for (Point p : fila) {
                g.fillOval(
                        ((p.x + 1) * distanciaPunto) - (tamañoPunto / 2),
                        ((p.y + 1) * distanciaPunto) - (tamañoPunto / 2),
                        tamañoPunto, tamañoPunto
                );
            }
        }
    }
    
}
