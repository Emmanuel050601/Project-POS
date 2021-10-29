package controller;
//CLASE CON EL CONTROLADOR DEL BOTON BUSCAR DE LA CLASE PRODUCTO 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ConsultasDB;

public class ControllerProducto implements ActionListener{

    private final ConsultasDB consultas;
    private final JTextField txtCodigo, txtNombre;
    
    public ControllerProducto(JTextField txtCodigo, JTextField txtNombre,
            Statement conector, DefaultTableModel modelo){
        consultas = new ConsultasDB(conector, modelo);
        this.txtCodigo = txtCodigo;
        this.txtNombre = txtNombre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        consultas.consultaUnico(txtCodigo.getText(), txtNombre.getText());
        txtCodigo.setText("");
        txtNombre.setText("");
    }
    
}
