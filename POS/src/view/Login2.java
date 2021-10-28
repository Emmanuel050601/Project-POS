package view;
/*CLASE DONDE SE CAMBIA LA CONTRASENA, CONECTA CON EL BOTON QUE SE ENCUENTRA
//EN LOGIN
*/
import controller.ControllerLogin;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login2 {
    private final JFrame ventana;
    private final JPanel panel;
    private final JLabel usuario, contrasena, nuevaContrasena, confirmaContrasena;
    private final JButton actualizar, cancelar;
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena,txtNcontrasena,txtCcontrasena ;
    private final ControllerLogin controller;    
    
    public Login2(JFrame ventana, Statement conector){
        this.ventana = ventana;
        panel = new JPanel();
        usuario = new JLabel("USUARIO");
        contrasena = new JLabel("CONTRASEÑA ACTUAL");
        nuevaContrasena = new JLabel("NUEVA CONTRASEÑA");
        confirmaContrasena = new JLabel("CONFIRMAR NUEVA CONTRASEÑA");
        actualizar = new JButton("Actualizar");
        cancelar = new JButton ("Cancelar");
        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();
        txtNcontrasena = new JPasswordField();
        txtCcontrasena = new JPasswordField();       
        controller = new ControllerLogin(conector, txtUsuario, txtContrasena, 
                            txtNcontrasena, txtCcontrasena, panel, ventana);
    }
    
    private void configuraPanel(){
        panel.setBounds(0,0,500,550);
        panel.setLayout(null);
        panel.setBackground(new Color(153,205,206));
    }
    
    private void configuraBotones(){
        actualizar.setBounds(90, 410, 120, 35);
        actualizar.addActionListener(controller);
        cancelar.setBounds(280, 410, 120, 35);
        cancelar.addActionListener(controller);
    }
    
    private void configuraEtiquetas(){
        usuario.setBounds(120, 40,100,40 );
        usuario.setFont(new Font("CooperBlack", BOLD, 15));
        contrasena.setBounds(120,130,200,40 );
        contrasena.setFont(new Font("CooperBlack", BOLD, 15));
        nuevaContrasena.setBounds(120, 210, 200, 40);
        nuevaContrasena.setFont(new Font("CooperBlack", BOLD, 15));
        confirmaContrasena.setBounds(120, 290, 200, 40);
        nuevaContrasena.setFont(new Font("CooperBlack", BOLD, 15));
    }
    
    private void configuraTexto(){
        txtUsuario.setBounds(120, 80, 250, 35);
        txtContrasena.setBounds(120, 170, 250, 35);
        txtNcontrasena.setBounds(120, 250, 250, 35);
        txtCcontrasena.setBounds(120, 330, 250, 35);
    }
    
    private void agregarVentana() {
        panel.add(usuario);
        panel.add(contrasena);
        panel.add(nuevaContrasena);
        panel.add(confirmaContrasena);
        panel.add(txtUsuario);
        panel.add(txtContrasena);
        panel.add(txtNcontrasena);
        panel.add(txtCcontrasena);
        panel.add(actualizar);
        panel.add(cancelar);
        ventana.add(panel);
    }
    
    public void run(){
        configuraPanel();
        configuraBotones();
        configuraEtiquetas();
        configuraTexto();
        agregarVentana();
    }
}
