/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;
import conexionSQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JJ
 */
public class TablaVisitante extends Conexion {
    private final String SELECT_ALL = "SELECT * from dbo.Visitante";
    private final String SELECT_COLUMN_NAME = "SELECT cedula, nombres, apellidos, vetado FROM dbo.Visitante"; 
    private final String UPDATE = "UPDATE dbo.Visitante SET nombres = ?, apellidos = ?, vetado = ? WHERE cedula = ?";
    private final String SELECT_BY_ID = "SELECT * FROM dbo.Visitante WHERE cedula=?";
    private final String DELETE_BY_ID = "DELETE dbo.Visitante WHERE cedula=?";
    private final String INSERT_INTO = "INSERT INTO dbo.Visitante(cedula, nombres, apellidos, vetado) values(?, ?, ?, ?)";
    private Connection conn;
    private PreparedStatement statement;
    
    public TablaVisitante() {
           conn= this.obtenerConexion();
    }
    
    public int registrarVisitante(String cedula, String nombre, String apellidos, boolean vetado){
        //Retorna las filas afectadas por la consulta
        
        int result = 0;
        try {
            
            PreparedStatement statement = conn.prepareStatement(INSERT_INTO);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            statement.setBoolean(4, vetado);
            result = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
    }
    
     public void actualizarVisitante(String cedula,String nombre, String apellidos, boolean vetado) {
        try {
            
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, nombre);
            statement.setString(2, apellidos);
            statement.setBoolean(3, vetado);
            
            statement.setString(4, cedula);

            statement.executeUpdate();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaResidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void borrarVisitante(String id) {
       try {

            PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID);
            statement.setString(1, id);
            
            statement.executeUpdate();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     
      public ResultSet obtenerVisitante(String id) {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
      }
        
        public ResultSet listarVisitantes() {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        }
    
        public ResultSet showColumnsNameV() {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_COLUMN_NAME);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        }
        
        public DefaultTableModel obtenerVisitanteTabla(String id) {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Cedula", "Nombres", "Apellidos", "Vetado"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[4];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("Vetado");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
        
    
        public DefaultTableModel obtenerVisitanteTabla() {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Cedula", "Nombres", "Apellidos", "Vetado"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[4];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("Vetado");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
    
}
