package controller;
/*
Controlador de los botones de login
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.Inicio;
import view.Login;
import view.Login2;

public class ControllerLogin implements ActionListener{
    
    private final JTextField usuario;
    private final JPasswordField contrasena, nuevaContrasena, confirmaContrasena;
    private final JFrame ventana;
    private final JPanel panel;
    private final Statement conector;
    private ResultSet resultado;
    private String user, pass, consulta, tipo, nombre, ex;

    public ControllerLogin(Statement conector , JTextField usuario, 
                     JPasswordField contrasena, JPanel panel, JFrame ventana) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.panel = panel;
        this.ventana = ventana;
        this.conector = conector;
        user = "#";
        pass = "#";
        nuevaContrasena = null;
        confirmaContrasena = null;
    }
    
    public ControllerLogin(Statement conector ,JTextField usuario, 
            JPasswordField contrasena, JPasswordField nuevaContrasena, 
            JPasswordField confirmaContrasena, JPanel panel,
            JFrame ventana) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.panel = panel;
        this.ventana = ventana;
        user = "#";
        pass = "#";
        this.nuevaContrasena = nuevaContrasena;
        this.confirmaContrasena = confirmaContrasena;
        this.conector = conector;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private void consulta(){
        consulta = "SELECT * "
                 + "FROM usuarios "
                 + "WHERE id_usuario= '"+usuario.getText()+"'";
        try {
            resultado = conector.executeQuery(consulta);
            while(resultado.next()){
                user = resultado.getString(1);
                nombre = resultado.getString(2);
                pass = resultado.getString(3);
                tipo = resultado.getString(4).toUpperCase();
            }
        } catch (SQLException exe) {}
    }
    
    private void consultaActualiza(){
        consulta = "UPDATE usuarios "
                + "SET contra='"+confirmaContrasena.getText()+"'"
                + "WHERE id_usuario= '"+usuario.getText()+"'";
        try {
            resultado = conector.executeQuery(consulta);
        } catch (SQLException exe) {}        
    }
    /////////////REVISAR SI LAS 2 CONSULTAS SE PUEDEN MANDAR A MODELO///////////
    
    private void sesion(){
        consulta();
        if(!usuario.getText().equals(user) || !contrasena.getText().equals(pass)){
                JOptionPane.showMessageDialog(null,
                                            "Usuario o Contraseña Incorrectos",
                                            "POS Developers", 0, null);
                usuario.setText("");
                contrasena.setText("");
            }
            else {
                ventana.dispose();
                JOptionPane.showMessageDialog(null, "Inicio de Sesion Exitoso",
                                              "POS Developers", -1, null);
                new Inicio(nombre, tipo, conector).principal();//REVISAR LA CONEXION CON LAS OTRAS INTERFACES
            }
    }
    
    private void actualizar(){
        consulta();
        if(!usuario.getText().equals(user) || !contrasena.getText().equals(pass))
            JOptionPane.showMessageDialog(null,
                                          "Usuario o Contraseña Incorrectos",
                                          "POS Developers", 0, null);
        else if(!confirmaContrasena.getText().equals(nuevaContrasena.getText()))
                JOptionPane.showMessageDialog(null, 
                                              "La contraseña nueva no coincide",
                                              "POS Developers", 2, null);
            else {
                  consultaActualiza();
                  JOptionPane.showMessageDialog(null, "Actualizacion exitosa",
                                                  "POS Developers", -1, null);
                  cancelar();
            }
        }
    
    private void cambio(){
         panel.setVisible(false);
         new Login2(ventana, conector).run();
    }
    
    private void cancelar(){
        ventana.dispose();
        new Login(conector).run();
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        ex = e.getActionCommand();
        if(ex.equals("Entrar")) sesion();
        else if(ex.equals("Cambiar Contraseña"))cambio();
        else if(ex.equals("Cancelar"))cancelar();
        else if(ex.equals("Actualizar"))actualizar();
        else {
            try {
                conector.close();
                System.exit(0);
            } catch (SQLException ex) {}
        }
    }
}

