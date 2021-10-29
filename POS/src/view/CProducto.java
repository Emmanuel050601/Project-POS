package view;
//CLASE CONSULTAR PRODUCTO
//ESTA SIRVE COMO BASE PARA LAS DEMAS DE CONSULTA
import controller.ControllerProducto;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Image;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ConsultasDB;


public class CProducto {
    private final JFrame ventana;
    private final JPanel panel;
    private final JMenuBar barra;
    private final JLabel codigoP, nombreP, imagen;
    private final JButton btnBuscar;
    private final JTextField txtCodigo, txtNombre;
    private DefaultTableModel modelo;
    private final JTable tabla;
    private final JScrollPane scroll;
    private final ControllerProducto controlador;
    private final ConsultasDB consultas;
    
    public CProducto(JFrame ventana, JMenuBar barra, Statement conector){
        this.barra = barra;
        this.ventana = ventana;
        panel = new JPanel();
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        codigoP = new JLabel("Codigo del Producto");
        nombreP = new JLabel("Nombre del Producto");
        imagen = new JLabel();
        btnBuscar = new JButton("Buscar");
        modelo = new DefaultTableModel(0,0);
        controlador = new ControllerProducto(txtCodigo,txtNombre,conector,modelo);
        consultas = new ConsultasDB(conector, modelo);
        tabla = new JTable(modelo)
        {@Override public boolean isCellEditable(int a, int b) {return false;} };
        scroll = new JScrollPane(tabla);
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
        codigoP.setBounds(60, 50, 200, 35);
        codigoP.setFont(new Font("Microsoft YaHei", BOLD, 17));
        nombreP.setBounds(60, 180, 200, 35);
        nombreP.setFont(new Font("Microsoft YaHei", BOLD, 17));
        imagen.setIcon(resizeImage("src/images/lupa.png", 30, 30));
        imagen.setBounds(270, 320, 30, 30);
        
    }
    
    private void configuraBotones(){
        btnBuscar.setBounds(60, 320, 200, 30);
        btnBuscar.addActionListener(controlador);
    }
    
    private void configuraTexto(){
        txtCodigo.setBounds(60, 100, 225, 30);
        txtNombre.setBounds(60, 230, 225, 30);
    }
    
    private void configuraTabla(){
        modelo.addColumn("Codigo Producto");
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("Precio de Venta");
        modelo.addColumn("Stock");
        scroll.setBounds(320,50,450,450);
        consultas.consultaPublica();
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
        panel.add(codigoP);
        panel.add(nombreP);
        panel.add(imagen);
        panel.add(btnBuscar);
        panel.add(txtCodigo);
        panel.add(txtNombre);
        panel.add(scroll);
        ventana.add(panel);
        ventana.setJMenuBar(barra);
        ventana.setVisible(true);
    }
    
    public void run(){
        configuraVentana();
        configuraPanel();
        configuraEtiquetas();
        configuraBotones();
        configuraTexto();
        configuraTabla();
        agregarVentana();
    }
}
