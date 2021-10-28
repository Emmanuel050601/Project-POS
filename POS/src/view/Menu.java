package view;

//CLASE DONDE SE ENCUENTRA EL MENU
import controller.ControllerMenu;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {
    private final JMenuBar barra; 
    private final JMenu menu, opciones;
    private final JMenuItem rVenta, ventaD, devoluciones, administracion;
    private final JMenuItem productos, proveedor, compras, usuarios;
    private final JMenuItem ayuda, cerrarS, salir;
    private final String rol;
    private final ControllerMenu controllerMenu;
    
    public Menu(String rol, Statement conector, JFrame ventana){
        barra = new JMenuBar();
        menu = new JMenu("Menu");
        opciones = new JMenu("Opciones");
        rVenta = new JMenuItem("Realizar Venta");
        ventaD = new JMenuItem("Ventas y Devoluciones");
        devoluciones = new JMenuItem("Consultar Devoluciones");
        productos = new JMenuItem("Consultar Productos");
        proveedor = new JMenuItem("Consultar Proveedores");
        compras = new JMenuItem("Consultar Compras");
        usuarios = new JMenuItem("Consultar Usuarios");
        administracion = new JMenuItem("Administracion");
        ayuda = new JMenuItem("Ayuda");
        cerrarS = new JMenuItem("Cerrar Sesion");
        salir = new JMenuItem("Salir");
        controllerMenu = new ControllerMenu(conector, ventana);
        this.rol = rol;
    }
    
    private void configuraOpciones(){
        salir.addActionListener(controllerMenu);
        cerrarS.addActionListener(controllerMenu);
        
    }
    
    private void agregarMenu(){
        menu.add(rVenta);
        menu.add(ventaD);
        menu.add(devoluciones);
        menu.add(productos);
        menu.add(proveedor);
        if(rol.equals("ADMINISTRADOR")){
            menu.add(compras);
            menu.add(usuarios);
            menu.add(administracion);
        } 
    }
    
    private void agregarOpciones(){
        configuraOpciones();
        opciones.add(ayuda);
        opciones.add(cerrarS);
        opciones.add(salir);
    }
    
    private void agregar(){
        barra.add(menu);
        barra.add(opciones);
        agregarMenu();
        agregarOpciones();
    }
    
    public JMenuBar menu(){
        agregar();
        return barra;
    }
}
