package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.Login;

public class ControllerMenu implements ActionListener{
    private String ex;
    private int opcion;
    private final JFrame ventana;
    private final Statement conector;
    
    public ControllerMenu(Statement conector, JFrame ventana){
        this.ventana = ventana;
        this.conector = conector;
    }
    
    private void salir(){
        opcion = JOptionPane.showConfirmDialog(null,"¿Estas seguro de Salir?",
                                "SALIR", JOptionPane.YES_NO_OPTION, 2);
        if(opcion == 0) {
            try {
                conector.close();
                System.exit(0);
            } catch (SQLException ex) {}
        }
    }
    
    private void cerrar(){
        opcion = JOptionPane.showConfirmDialog(null, 
                                               "¿Estas seguro de Cerrar Sesion?",
                                               "Cerrar Sesion", 
                                               JOptionPane.YES_NO_OPTION, 2);
        if(opcion == 0) {
            ventana.dispose();
            new Login(conector).run();
        }    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ex = e.getActionCommand();
        if(ex.equals("Cerrar Sesion")) cerrar();
        else if(ex.equals("Salir")) salir();
        
    }
    
}
