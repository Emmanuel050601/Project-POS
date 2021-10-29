package view;

import controller.ControllerAyuda;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ayuda {
    private final JFrame ventana;
    private final JPanel panel;
    private final JMenuBar barra;
    private final JLabel pdf, youtube;
    private final JButton btnPdf, btnYoutube;
    private final JTextArea areaTxt;
    private final String ruta;
    
    public Ayuda(JFrame ventana, JMenuBar barra){
        this.barra = barra;
        this.ventana = ventana;
        panel = new JPanel();
        pdf = new JLabel();
        youtube = new JLabel();
        btnPdf = new JButton("Descargar Manuales");
        btnYoutube = new JButton("Visitar canal de Youtube");
        areaTxt = new JTextArea();
        ruta = "src/images/";
    }
    
    private void configuraVentana(){
        ventana.setSize(850,600);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configuraPanel(){
        panel.setBounds(0,0,850,600);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
    }
    
    private void configuraEtiquetas(){
        pdf.setIcon(resizeImage(ruta+"pdf.png", 150, 150));
        pdf.setBounds(85, 50, 150, 150);
        youtube.setIcon(resizeImage(ruta+"youtube.png", 150,150));
        youtube.setBounds(85, 280, 150, 150);
        
    }
    
    private void configuraBotones(){
        btnPdf.setBounds(60, 210, 200, 30);
        btnPdf.addActionListener(new ControllerAyuda());
        btnYoutube.setBounds(60, 440, 200, 30);
        btnYoutube.addActionListener(new ControllerAyuda());
    }
    
    private void configuraArea(){
        areaTxt.setBounds(320, 50, 450, 450);
        areaTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        areaTxt.setText("\n\n\n\n\n\n\nINFORMACION DE CONTACTO");//ESITAR POR LA INFO DE CONTACTO
        areaTxt.setEditable(false);
    }
    
    private ImageIcon resizeImage(String filePath, int x, int y){
        return new ImageIcon(new ImageIcon(filePath).getImage()
                           .getScaledInstance(x,y, Image.SCALE_SMOOTH));
    }
    
    public void apagarPanel(){
        panel.setVisible(false);
    }
    
    public void encenderPanel(){
        panel.setVisible(true);
    }
    
    private void agregarVentana() {
        panel.add(pdf);
        panel.add(btnPdf);
        panel.add(youtube);
        panel.add(btnYoutube);
        panel.add(areaTxt);
        ventana.add(panel);
        ventana.setJMenuBar(barra);
        ventana.setVisible(true);
    }
    
    public void run(){
        configuraVentana();
        configuraPanel();
        configuraEtiquetas();
        configuraBotones();
        configuraArea();
        agregarVentana();
    }
}
