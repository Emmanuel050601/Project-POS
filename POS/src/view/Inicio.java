package view;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.Reloj;


/*
HACER LAS CONEXIONES NECESARIAS
*/
public class Inicio{
    private final JFrame ventana;
    private final JPanel panel;
    private final JMenuBar barra; 
    private final JLabel logo, fecha, tiempo;
    private final JLabel nombreNegocio, bienvenido, rol;
    private final Image image;
    private final Thread h1;    
    
    public Inicio(String nombre, String tipo, Statement conector){
        ventana = new JFrame ("POS Developers");
        panel = new JPanel();
        logo = new JLabel();
        fecha = new JLabel();
        tiempo = new JLabel();
        nombreNegocio = new JLabel("Dulceria Vicky");
        bienvenido = new JLabel("BIENVENIDO: " + nombre);
        rol = new JLabel("ROL: " + tipo);
        image = Toolkit.getDefaultToolkit().getImage("src/Images/logo.png");
        barra = new Menu(tipo, conector, ventana).menu();
        h1 = new Thread(new Reloj(tiempo, fecha));
        h1.start();
    }
    
    private void configuraVentana(){
        ventana.setSize(850,600);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setIconImage(image);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configuraPanel(){
        panel.setBounds(0,0,850,600);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
    }
    
    private void configuraEtiquetas(){
        logo.setIcon(resizeImage("src/images/logo.png", 300, 300));
        logo.setBounds(275, 0, 300, 300);
        nombreNegocio.setBounds(320,270,238,30);
        nombreNegocio.setFont(new Font("Microsoft YaHei", BOLD, 25));
        fecha.setBounds(275, 340, 200, 30);
        fecha.setFont(new Font("Microsoft YaHei", BOLD, 20));
        tiempo.setBounds(450, 340, 120, 30);
        tiempo.setFont(new Font("Microsoft YaHei", BOLD, 20));
        bienvenido.setBounds(275,390, 400,30 );
        bienvenido.setFont(new Font("Microsoft YaHei", BOLD, 20));
        rol.setBounds(275, 440, 260, 30 );
        rol.setFont(new Font("Microsoft YaHei", BOLD, 20));
    }
    
    private ImageIcon resizeImage(String filePath, int x, int y){
        return new ImageIcon(new ImageIcon(filePath).getImage()
                           .getScaledInstance(x,y, Image.SCALE_SMOOTH));
    }
    
    private void agregarVentana() {
        panel.add(logo);
        panel.add(fecha);
        panel.add(tiempo);
        panel.add(nombreNegocio);
        panel.add(bienvenido);
        panel.add(rol);
        ventana.add(panel);
        ventana.setJMenuBar(barra);
        ventana.setVisible(true);
    }

    public void principal(){
        configuraVentana();
        configuraPanel();
        configuraEtiquetas();
        agregarVentana();
    }
}
