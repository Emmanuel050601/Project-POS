package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasDB {
    
    private final Statement conector;
    private String consulta;
    private ResultSet resultado;
    private final DefaultTableModel modelo;
    private int indice, tamanoTabla;
    
    //247
    public ConsultasDB(Statement conector, DefaultTableModel modelo){
        this.conector = conector;
        this.modelo = modelo;
        indice = 0;
    }
    
    private void consultaTodos(){ 
        consulta = "SELECT * "
                 + "FROM producto ";
        try{
            resultado = conector.executeQuery(consulta);
            while(resultado.next()){
                modelo.setNumRows(indice+1);
                modelo.setValueAt(resultado.getString(1), indice, 0);
                modelo.setValueAt(resultado.getString(2), indice, 1);
                modelo.setValueAt(resultado.getDouble(3), indice, 3);
                modelo.setValueAt(resultado.getDouble(4), indice, 2);
                modelo.setValueAt(resultado.getInt(5), indice, 4);
                indice++;
            }
        }catch(SQLException ex){}
    }
    
    private void consultaUno(String codigo, String nombre){
        if(codigo.equals("") && nombre.equals(""))
            JOptionPane.showMessageDialog(null,
                "Ingresa el codigo o el nombre", "POS Developers", 0, null);
        else{
            reiniciarTabla();
            if(codigo.equals(""))  consulta = "SELECT * FROM producto " +
                                              "WHERE nombre_p= '"+nombre+"'";
            else consulta = "SELECT * FROM producto " +
                            "WHERE id_producto= '"+codigo+"'";
            try{
                modelo.setNumRows(2);
                resultado = conector.executeQuery(consulta);
                while(resultado.next()){
                    modelo.setValueAt(resultado.getString(1), 0, 0);
                    modelo.setValueAt(resultado.getString(2), 0, 1);
                    modelo.setValueAt(resultado.getDouble(3), 0, 3);
                    modelo.setValueAt(resultado.getDouble(4), 0, 2);
                    modelo.setValueAt(resultado.getInt(5), 0, 4);
                }
                existeProducto();
            }catch(SQLException ex){}
        }
    }
    
    private void reiniciarTabla(){
        indice = 0;
        tamanoTabla = modelo.getRowCount();
        if(tamanoTabla > 0){
            for (int i = 0; i < tamanoTabla; i++) modelo.removeRow(0);
            modelo.fireTableRowsDeleted(0, tamanoTabla-1);
        }    
    }
    
    private void existeProducto(){
        if(modelo.getValueAt(0, 0) != null){}
        else {
            JOptionPane.showMessageDialog(null,
                "El producto no esta registrado", "POS Developers", 2, null);
            consultaTodos();
        }    
    }
    
    public void consultaUnico(String codigo, String nombre){
        consultaUno(codigo, nombre);
    }
    
    public void consultaPublica(){
        consultaTodos();
    }
}
