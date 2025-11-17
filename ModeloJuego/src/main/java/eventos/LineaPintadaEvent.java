/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import objetosModeloJuego.Linea;

/**
 *
 * @author Ximena
 */
public class LineaPintadaEvent {
    private Linea linea;

    public LineaPintadaEvent(Linea linea) {
        this.linea = linea;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
    
    
}
