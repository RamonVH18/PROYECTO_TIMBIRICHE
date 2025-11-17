package mvcJuegoIniciado.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetosPresentacion.JugadorVisual;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;

/**
 *
 * @author Ramon Valencia
 */
public class TablaJugadores extends JPanel implements IVista {
    //Constantes
    private Integer MAX_JUGADORES = 4;
    private Dimension DIM_FLD_JUGADOR = new Dimension();

    //Atributos de la clase
    private IModeloLeibleJI modelo;
    
    //TODO: Hacer esto responsivo
    private Dimension dimension_tabla = new Dimension(350, 200);
    private static List<JugadorVisual> jugadoresActuales;
    private JLabel titulo;

    public TablaJugadores(IModeloLeibleJI modelo) {
        //Inicializar el modelo
        this.modelo = modelo;
//        modelo.añadirObserver(this);

        //Definir disposicion
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        crearTabla();
    }

    private void inicializar() {

    }

    private void crearTabla() {
        //Inicializar lista de jugadores
        titulo = new JLabel();
        jugadoresActuales = new ArrayList<>();

        //Diseño de la tabla de jugadores
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setSize(dimension_tabla);
        setPreferredSize(dimension_tabla);
        generarMarcosJugadores();
    }

    private void generarMarcosJugadores() {
        //Título
        titulo.setText("Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setOpaque(true);
        add(Box.createVerticalStrut(10));
        add(titulo);
        add(Box.createVerticalStrut(10));

        //Generar la vista de los jugadores
        jugadoresActuales = modelo.obtenerJugadores();
        for (JugadorVisual j : jugadoresActuales) {
            JPanel panelJugador = new JPanel();
            panelJugador.setLayout(new BoxLayout(panelJugador, BoxLayout.X_AXIS));
            panelJugador.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel nombre = new JLabel(j.getNombre());
            JLabel puntaje = new JLabel("  0"); // mockeado por ahora ya duespues pues lo vamos viendo lo de los puntos asi nomas

            panelJugador.add(nombre);
            panelJugador.add(Box.createHorizontalGlue());
            panelJugador.add(puntaje);

            add(panelJugador);
            add(Box.createVerticalStrut(5));
        }
        
    }

    @Override
    public void actualizar() {
        removeAll();
        generarMarcosJugadores();
        repaint();
        revalidate();
    }
    
    @Override
    public void mostrar(){
      
    }
}
