/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.vistas;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import utils.ColorConverter;
import utils.GeneradorImagen;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaSeleccionImagen extends JDialog implements IVista {
    
    private IModeloLeibleJInicio modelo;
    private IControlJuegoInicio control;
    private ImagenJugador imagenSeleccionada;
    private ColorJugador colorSeleccionado;

    public PantallaSeleccionImagen(JFrame parent, IModeloLeibleJInicio modelo, IControlJuegoInicio control) {

        super(parent, "Mi Diálogo Personalizado", true);
        
        imagenSeleccionada = null;
        colorSeleccionado = null;
        this.modelo = modelo;
        this.control = control;
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 0));

        JPanel panelImagenes = new JPanel(new GridLayout(0, 3, 10, 10));
        panelImagenes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        generarPanelImagenes(panelImagenes);

        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setForeground(Color.GRAY); // Color de la línea

        JPanel panelColores = new JPanel(new GridLayout(0, 3, 10, 10));
        panelColores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        generarPanelColores(panelColores);

        add(panelImagenes, BorderLayout.WEST);
        add(separador, BorderLayout.CENTER);
        add(panelColores, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(parent);
    }

    private void generarPanelImagenes(JPanel panelImagenes) {

        ImagenJugador[] imagenes = ImagenJugador.values();
        for (ImagenJugador imagen : imagenes) {
            ImageIcon icon = GeneradorImagen.obtenerImagenJugador(imagen, 80, 80);
            JLabel labelImagen = new JLabel(icon);
            labelImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            labelImagen.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    imagenSeleccionada = imagen;
                }
            });
            panelImagenes.add(labelImagen);
        }
    }

    private void generarPanelColores(JPanel panelColores) {
        ColorJugador[] colores = ColorJugador.values();
        for (ColorJugador color : colores) {
            JPanel cuadradoColor = new JPanel();
            cuadradoColor.setBackground(
                    ColorConverter.obtenerColorJugador(color)
            );
            cuadradoColor.setPreferredSize(new Dimension(80, 80));
            cuadradoColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cuadradoColor.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    colorSeleccionado = color;
                }
            });
            panelColores.add(cuadradoColor);
        }
    }

    @Override
    public void actualizar() {
        
    }

    @Override
    public void mostrar() {
        this.setVisible(modelo.isMostrandoPantallaRegistrarJugador());
    }
}
