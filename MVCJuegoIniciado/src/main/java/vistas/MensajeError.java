/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import javax.swing.JOptionPane;

/**
 *
 * @author Ramon Valencia
 */
public class MensajeError extends JOptionPane {
    
    public static void mostrarError(String message) {
        showMessageDialog(null, message, "Error", ERROR_MESSAGE);
    }
    
}
