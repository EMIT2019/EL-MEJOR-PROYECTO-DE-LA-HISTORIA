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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.Casa;
/**
 *
 * @author JJ
 */
public class TablaCasa extends Conexion {
    private final String SELECT_ALL = "SELECT * from dbo.Casa";
    private final String SELECT_BY_ID = "SELECT * FROM dbo.Casa WHERE codigo=?";
    private final String SELECT_COLUMN_NAME = "SELECT codigo, descripcion, direccion FROM dbo.Casa"; 
    private final String DELETE_BY_ID = "DELETE dbo.Casa WHERE codigo=?";
    private final String UPDATE = "UPDATE dbo.Casa SET descripcion = ?, direccion = ? WHERE codigo = ?";
    private final String INSERT_INTO = "INSERT INTO dbo.Casa(codigo, descripcion, direccion) values(?, ?, ?)";
    private final Connection conn;
    private PreparedStatement statement;
    
    public TablaCasa() {
        conn = this.obtenerConexion();
    }
    
    public int registrarCasa(int codigo, String descripcion, String direccion){
        //Retorna las filas afectadas por la consulta
        
        int result = 0;
        try {
            
            PreparedStatement statement = conn.prepareStatement(INSERT_INTO);
            statement.setInt(1, codigo);
            statement.setString(2, descripcion);
            statement.setString(3, direccion);
            result = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
    }
    
     public void actualizarCasa(int codigo, String descripcion, String direccion) {
        try {
            
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, descripcion);
            statement.setString(2, direccion);
            
            
            statement.setInt(3, codigo);

            statement.executeUpdate();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaResidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void borrarCasa(int id) {
       try {

            PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     
      public ResultSet obtenerCasa(int id) {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
      }
      
      public Casa obtenerCasaxCodigo(String codigo)
      {
          Casa casa = new Casa();
          try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, codigo);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                casa.setCodigo(Integer.parseInt(rs.getString("codigo")));
                casa.setDescripcion(rs.getString("descripcion"));
                casa.setDireccion(rs.getString("direccion"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
          return casa;
      }
        
        public ResultSet listarCasas() {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        }
        
        public ResultSet showColumnsNameC() {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_COLUMN_NAME);
            ResultSet rs = statement.executeQuery();
            
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        }
        
        
        public DefaultTableModel obtenerCasaTabla(String id) {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Codigo", "Descripcion", "Direccion"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[5];
                o[0] = rs.getString("Codigo");
                o[1] = rs.getString("Descripcion");
                o[2] = rs.getString("Direccion");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
        
        public DefaultTableModel obtenerCasaTabla() {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Codigo", "Descripcion", "Direccion"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[5];
                o[0] = rs.getString("Codigo");
                o[1] = rs.getString("Descripcion");
                o[2] = rs.getString("Direccion");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
}
