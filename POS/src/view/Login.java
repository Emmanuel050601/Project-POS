package view;
//CLASE DE LOGIN
import controller.ControllerLogin;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
    private final JFrame ventana;
    private final JPanel panel;
    private final JLabel logo, usuarioImg, contrasenaImg;
    private final JLabel usuario, contrasena;
    private final JButton iniciar, salir, actualizar;
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final Image image;
    private final ControllerLogin controller;
    
    public Login(Statement conector){
        ventana = new JFrame ("POS Developers");
        panel = new JPanel();
        logo = new JLabel();
        usuarioImg = new JLabel();
        contrasenaImg = new JLabel();
        usuario = new JLabel("USUARIO");
        contrasena = new JLabel("CONTRASEÑA");
        iniciar = new JButton("Entrar"); 
        salir = new JButton("Salir");
        actualizar = new JButton("Cambiar Contraseña");
        txtUsuario = new JTextField(); 
        txtContrasena = new JPasswordField();
        image = Toolkit.getDefaultToolkit().getImage("src/Images/logo.png");
        controller = new ControllerLogin(conector, txtUsuario, txtContrasena, 
                                         panel, ventana);
    }
    
    private void configuraVentana(){
        ventana.setSize(500,550);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setIconImage(image);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configuraPanel(){
        panel.setBounds(0,0,500,550);
        panel.setLayout(null);
        panel.setBackground(new Color(153,205,206));
    }
    
    private void configuraBotones(){
        iniciar.setBounds(50, 410, 80, 35);
        iniciar.addActionListener(controller);
        salir.setBounds(160, 410, 80, 35);
        salir.addActionListener(controller);
        actualizar.setBounds(270,410,160,35);
        actualizar.addActionListener(controller);
    }
    
    private void configuraEtiquetas(){
        logo.setIcon(resizeImage("src/images/logo.png", 250, 250));
        logo.setBounds(120, 0, 250, 250);
        usuarioImg.setIcon(resizeImage("src/images/login.png", 70,70));
        usuarioImg.setBounds(35, 215, 70, 70);
        contrasenaImg.setIcon(resizeImage("src/images/pass.png", 70,70));
        contrasenaImg.setBounds(35, 300, 70, 70);
        usuario.setBounds(120,200,100,100 );
        usuario.setFont(new Font("Microsoft YaHei", BOLD, 15));
        contrasena.setBounds(120,290,130,100 );
        contrasena.setFont(new Font("Microsoft YaHei", BOLD, 15));
    }
    
    private ImageIcon resizeImage(String filePath, int x, int y){
        return new ImageIcon(new ImageIcon(filePath).getImage()
                           .getScaledInstance(x,y, Image.SCALE_SMOOTH));
    }
    
    private void configuraTexto(){
        txtUsuario.setBounds(250, 235, 180, 25);
        txtContrasena.setBounds(250, 325, 180, 25);
    }
    
    private void agregarVentana() {
        panel.add(logo);
        panel.add(usuarioImg);
        panel.add(contrasenaImg);
        panel.add(contrasena);
        panel.add(usuario);
        panel.add(txtUsuario);
        panel.add(txtContrasena);
        panel.add(iniciar);
        panel.add(salir);
        panel.add(actualizar);
        ventana.add(panel);
        ventana.setVisible(true);
    }
    
    public void run(){
        configuraVentana();
        configuraPanel();
        configuraBotones();
        configuraEtiquetas();
        configuraTexto();
        agregarVentana();
    }
}
