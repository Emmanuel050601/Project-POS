package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.*;

public class ControllerMenu implements ActionListener{
    private String ex;
    private int opcion;
    private final JFrame ventana;
    private final Statement conector;
    private final JMenuBar barra;
    private final JPanel panel;
    private Ayuda ayuda;
    private CProducto cProducto;
    
    public ControllerMenu(Statement conector, JFrame ventana, 
            JMenuBar barra, JPanel panel){
        this.ventana = ventana;
        this.barra = barra;
        this.conector = conector;
        this.panel = panel;
        ayuda = new Ayuda(ventana, barra);
        cProducto = new CProducto(ventana, barra, conector);
    }
    
    private void reiniciarConstructores(){
        ayuda = new Ayuda(ventana, barra);
        cProducto = new CProducto(ventana, barra, conector);
    }
    
    private void apagarPaneles(){
        panel.setVisible(false);
        ayuda.apagarPanel();
        cProducto.apagarPanel();
        /*
        "Realizar Venta" APAGAR;
        Ventas y Devoluciones" APAGAR;
        "Consultar Devoluciones" APAGAR;
        "Consultar Proveedores"; APAGAR
        "Consultar Compras"; APAGAR
        "Consultar Usuarios"; APAGAR
        "Administracion"; APAGAR 
        */
        reiniciarConstructores();
    }
    
    private void encenderPanel(String ex){
        if(ex.equals("Ayuda")) ayuda.encenderPanel();
        else if(ex.equals("Consultar Productos")) cProducto.encenderPanel();
        /*
        "Realizar Venta" APAGAR;
        Ventas y Devoluciones" APAGAR;
        "Consultar Devoluciones" APAGAR;
        "Consultar Proveedores"; APAGAR
        "Consultar Compras"; APAGAR
        "Consultar Usuarios"; APAGAR
        "Administracion"; APAGAR 
        */   
    }
    
    private void manejarPaneles(String ex){
        apagarPaneles();
        encenderPanel(ex);
    }
////////////////////////////SUBMENU MENU FUNCIONES/////////////////////////////
    private void consultaP(){
        cProducto.run();
    }
/////////////////////////SUBMENU OPCIONES FUNCIONES////////////////////////////
    private void ayuda(){
        ayuda.run();
    }
    
    private void cerrar(){
        opcion = JOptionPane.showConfirmDialog(null, 
                    "¿Estas seguro de Cerrar Sesion?","Cerrar Sesion", 
                    JOptionPane.YES_NO_OPTION, 2);
        if(opcion == 0) {
            ventana.dispose();
            new Login(conector).run();
        }    
    }
    
    private void salir(){
        opcion = JOptionPane.showConfirmDialog(null,"¿Estas seguro de Salir?",
                     "SALIR", JOptionPane.YES_NO_OPTION, 2);
        if(opcion == 0) {
            try {
                conector.close();
                System.exit(0);
            } catch (SQLException exe) {}
        }
    }
///////////////////////////////////////////////////////////////////////////////    
    @Override
    public void actionPerformed(ActionEvent e) {
        ex = e.getActionCommand();
        if(ex.equals("Cerrar Sesion")) cerrar();
        else if(ex.equals("Salir")) salir();
        else if(ex.equals("Ayuda")) {
                manejarPaneles(ex);
                ayuda();
        }
        else if(ex.equals("Consultar Productos")) {
                manejarPaneles(ex);
                consultaP();
        }
    }
    
}
