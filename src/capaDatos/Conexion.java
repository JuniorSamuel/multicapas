/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Samy
 */
public class Conexion {
    
    private Connection connect;
    private boolean conectado;
    
    public Conexion(){
        
    }
    public Connection getConexion(){
        //Configura de motor de base de datos
        String className = "org.sqlite.JDBC";
        String driver = "";
        
        //Configura de conexion
        //El motor de base de dato debe ser 'mysql' o 'sqlite'
        String motor = "sqlite";
        //La direccion, es la ubicacion de la base de datos
        String direccion = "src//capaDatos";
        //baseDatos en el nombre de la base de datos
        String baseDatos = "databadse.db";
        
        //Credenciales
        String usuario = "root";
        String contrasena = "";
        
        String url = "jdbc:"+motor+":"+direccion+"//"+baseDatos;
        connect = null;
        
        try {
            Class.forName(className);
            connect = DriverManager.getConnection(url,usuario,contrasena);
            if (connect != null){
           
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        return connect;
    }
    
    public void close(){
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConectado() {
        return conectado;
    }
}
