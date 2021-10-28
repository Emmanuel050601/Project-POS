package model;
//CLASE QUE REALIZA LA CONEXION CON LA BASE DE DATOS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    public static Connection getConexion(){
        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=punto_venta;"//Se modifica dependiendo del nombre de la base de datos 
                + "user=sa;"//Se modifica depediendo del nombre de usuario para conectar a la base de datos
                + "password= batman2505;"//Se modifica dependiendo de la contrasena que se tiene para el usuario
                + "loginTimeout=30;";//Es el tiempo de espera en lo que se conecta la base de datos
        try{
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
