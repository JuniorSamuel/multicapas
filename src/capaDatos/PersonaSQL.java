/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samy
 */
public class PersonaSQL {

    private ArrayList<Persona> lista;
    private Conexion conexion;
    private PreparedStatement statement;
    private ResultSet result;

    public PersonaSQL() {
        lista = new ArrayList();
        conexion = new Conexion();
    }

public PreparedStatement getStatement(String sql) {
    statement = null;
    try {
        statement = conexion.getConexion().prepareStatement(sql);
    } catch (SQLException ex) {
        Logger.getLogger(PersonaSQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    return statement;
}

    public ArrayList<Persona> listar() {
        //Consulta SQL 
        String sql = "SELECT * FROM persona";
        result = null;
        try {
            result = getStatement(sql).executeQuery();
            while (result.next()) {
                Persona p = new Persona();
                p.setId(result.getInt(1));
                p.setNombre(result.getString(2));
                p.setCorreo(result.getString(3));
                p.setTel(result.getInt(4));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.close();
        return lista;
    }

    public int agregar(Persona persona) {
        //Consulta SQL 
        String sql;
        sql = "INSERT INTO persona(id, nombre, correo, telefono) VALUES (null,'" + persona.getNombre() + "', '" + persona.getCorreo() + "', " + persona.getTel() + ")";
        try {
            return getStatement(sql).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean eliminar(int id) {
        //Consulta SQL
        String sql = "DELETE FROM persona WHERE id = " + id;
        try {
            return getStatement(sql).execute();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean actualizar(Persona persona) {
        //Consulta SQL        
        String sql = "UPDATE persona SET nombre = '" + persona.getNombre() + "', correo = '" + persona.getCorreo() + "', telefono = " + persona.getTel() + " WHERE id = " + persona.getId();
        try {
            return getStatement(sql).execute();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
