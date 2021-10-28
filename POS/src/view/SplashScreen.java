package view;
/*
CLASE DE LA VENTANA DE PRESENTACION
*/
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ConexionDB;

public class SplashScreen {
    private final JFrame ventana;
    private final JPanel panel;
    private final JLabel etiqueta;
    private final JLabel icono;
    private final Image image;
    private Statement conector;
    private boolean union;

    public SplashScreen() {
        ventana = new JFrame();
        panel = new JPanel();
        etiqueta = new JLabel ("Cargando...");
        icono = new JLabel();
        image = Toolkit.getDefaultToolkit().getImage("src/Images/logo.png");
        conector = null;
        union = false;
    }
    
    private void configura(){
        ventana.setSize(250, 100);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setUndecorated(true);
        ventana.setIconImage(image);
        panel.setLayout(null);
        panel.setBounds(0, 0, 250, 100);
        icono.setIcon(resizeImage("src/images/logo.png",120,120));
        icono.setBounds(0, 0, 120, 120);
        etiqueta.setBounds(130, 40, 100, 25);
        etiqueta.setFont(new Font("CooperBlack", BOLD, 15));
    }
    
    private ImageIcon resizeImage(String filePath, int x, int y){
        return new ImageIcon(new ImageIcon(filePath).getImage()
                           .getScaledInstance(x,y, Image.SCALE_SMOOTH));
    }
    
    private void agregaVentana(){
        panel.add(icono);
        panel.add(etiqueta);
        ventana.add(panel);
        ventana.setVisible(true);
    }
    
    private Statement conexion(){
        try{
            conector = ConexionDB.getConexion().createStatement();
            System.out.println("CONEXION ESTABLECIDA");
            union = true;
            return conector;
        }catch(SQLException ex){
            System.out.println(ex.toString());
            System.exit(0);
        }
        return conector;
    }
    
    private void esperar(int i){
        try{
            Thread.sleep(i);
        }catch(InterruptedException e){}
    }
    
    public void run(){
        configura();
        agregaVentana();
        conexion();
        if(union == true){
            esperar(1000);
            ventana.dispose();
            new Login(conector).run();
        }else
            ventana.dispose();
    }
}
