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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JJ
 */
public class TablaVisita extends Conexion {
    private final String SELECT_ALL = "SELECT * from dbo.Visita";
    private final String SELECT_COLUMN_NAME = "SELECT id, usuario, casa, visitante, fechaHora, razonVisita, transporte, matricula  FROM dbo.Visita"; 
    private final String SELECT_BY_ID = "SELECT * FROM dbo.Visita WHERE id=?";
    private final String DELETE_BY_ID = "DELETE dbo.Visita WHERE id=?";
    private final String UPDATE = "UPDATE dbo.Visita SET usuario = ?, casa = ?, visitante = ?, fechaHora = ?, razonVisita = ?, transporte = ?, matricula = ? WHERE id = ?";
    private final String INSERT_INTO = "INSERT INTO dbo.Visita(usuario, casa, visitante, fechaHora, razonVisita, transporte, matricula) values(?, ?, ?, ?, ?, ?, ?)";
    private final Connection conn;
    private PreparedStatement statement;
    
    public TablaVisita() {
        conn = this.obtenerConexion();
    }
    
     public int registrarVisita(String usuario, int numeroCasa, String visitante, String fechaHora, String razonVisita, String transporte, String matricula){
        //Retorna las filas afectadas por la consulta
        
        int result = 0;
        try {
            
            PreparedStatement statement = conn.prepareStatement(INSERT_INTO);
            statement.setString(1, usuario);
            statement.setInt(2, numeroCasa);
            statement.setString(3, visitante);
            statement.setString(4, fechaHora);
            statement.setString(5, razonVisita);
            statement.setString(6, transporte);
            statement.setString(7, matricula);
            
            result = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return result;
    }
     
     public ResultSet obtenerVisita(String id) {
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
     
     public ResultSet showColumnsNameVisitas() {
        try {
            
            PreparedStatement statement = conn.prepareStatement(SELECT_COLUMN_NAME);
            ResultSet rs = statement.executeQuery();
            return rs;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        }
     
     public DefaultTableModel obtenerVisitaTabla(String id) {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"ID", "Usuario", "Casa", "Visitante", "FechaHora", "RazonVisita", "Transporte", "Matricula"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("ID");
                o[1] = rs.getString("Usuario");
                o[2] = rs.getString("Casa");
                o[3] = rs.getString("Visitante");
                o[4] = rs.getString("FechaHora");
                o[5] = rs.getString("RazonVisita");
                o[6] = rs.getString("Transporte");
                o[7] = rs.getString("Matricula");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
     
     
     public DefaultTableModel obtenerVisitaTabla() {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"ID", "Usuario", "Casa", "Visitante", "FechaHora", "RazonVisita", "Transporte", "Matricula"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[8];
                o[0] = rs.getString("ID");
                o[1] = rs.getString("Usuario");
                o[2] = rs.getString("Casa");
                o[3] = rs.getString("Visitante");
                o[4] = rs.getString("FechaHora");
                o[5] = rs.getString("RazonVisita");
                o[6] = rs.getString("Transporte");
                o[7] = rs.getString("Matricula");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
    
}
    
   