/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.Usuario;

/**
 *
 * @author JJ
 */
public class TablaUsuario extends Conexion {

    private final String SELECT_ALL = "SELECT * from dbo.Usuario";
    private final String SELECT_USER = "Select * from dbo.Usuario where cedula = ? and pw = ?";
    private final String SELECT_COLUMN_NAME = "SELECT cedula, nombres, apellidos, carnet FROM dbo.Usuario";
    private final String SELECT_BY_ID = "SELECT * FROM dbo.Usuario WHERE cedula=?";
    private final String DELETE_BY_ID = "DELETE dbo.Usuario WHERE cedula=?";
    private final String UPDATE = "UPDATE dbo.Usuario SET nombres = ?, apellidos = ?, carnet = ?, pw = ? WHERE cedula = ?";
    private final String INSERT_INTO = "INSERT INTO dbo.Usuario(cedula, nombres, apellidos, carnet, pw) values(?, ?, ?, ?, ?)";
    private Connection conn;
    private PreparedStatement statement;

    public TablaUsuario() {
        conn = this.obtenerConexion();
    }

    public int registrarUsuario(String cedula, String nombre, String apellidos, String carnet, String pw) {
        //Retorna las filas afectadas por la consulta

        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement(INSERT_INTO);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            statement.setString(4, carnet);
            statement.setString(5, pw);
            result = statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public void actualizarUsuario(String cedula, String nombre, String apellidos, String carnet, String pw) {
        try {

            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, nombre);
            statement.setString(2, apellidos);
            statement.setString(3, carnet);
            statement.setString(4, pw);

            statement.setString(5, cedula);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(TablaResidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarUsuario(String id) {
        try {

            PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID);
            statement.setString(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuarioxCedula(String id) {
        Usuario u = new Usuario();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                u.setCedula(rs.getString("cedula"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString(("apellidos")));
                u.setCarnet(rs.getString("carnet"));
                u.setPw(rs.getString("pw"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return u;
    }
    
    public DefaultTableModel obtenerUsuario(String id) {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            String Titulos[]={"Cedula", "Nombres", "Apellidos", "Carnet"};
            tbl.setColumnIdentifiers(Titulos);
            while(rs.next()){
                Object[] o =new Object[4];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("carnet");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return tbl;
    }
    
    
    public DefaultTableModel obtenerUsuario() {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            String Titulos[]={"Cedula", "Nombres", "Apellidos", "Carnet"};
            tbl.setColumnIdentifiers(Titulos);
            while(rs.next()){
                Object[] o =new Object[4];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("carnet");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return tbl;
    }
    

    public ArrayList listarCedulasUsuarios() {
        ArrayList<String> cedulas = new ArrayList<>();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                cedulas.add(rs.getString("cedula"));
            }
            
            
            return cedulas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet showColumnsName() {
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_COLUMN_NAME);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean autenticarUsuario(String id, String pw) {
        boolean x = false;
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_USER);
            statement.setString(1, id);
            statement.setString(2, pw);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                x= true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return x;
    }

}
