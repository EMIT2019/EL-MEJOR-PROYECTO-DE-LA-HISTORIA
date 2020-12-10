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
import models.Residente;
import models.Usuario;

/**
 *
 * @author JJ
 */
public class TablaResidente extends Conexion {

    private final String SELECT_ALL = "SELECT * from dbo.Residente";
    private final String SELECT_BY_ID = "SELECT * FROM dbo.Residente WHERE cedula=?";
    private final String SELECT_COLUMN_NAME = "SELECT cedula, nombres, apellidos, dependientes, casa FROM dbo.Residente";
    private final String DELETE_BY_ID = "DELETE dbo.Residente WHERE cedula=?";
    private final String UPDATE = "UPDATE dbo.Residente SET nombres=?,apellidos=?,dependientes=?,casa=? WHERE Cedula =?";
    private final String INSERT_INTO = "INSERT INTO dbo.Residente(cedula, nombres, apellidos, dependientes, casa) values(?, ?, ?, ?, ?)";
    private final Connection conn;
    private PreparedStatement statement;

    public TablaResidente() {
        conn = this.obtenerConexion();
    }

    public int registrarResidente(String cedula, String nombre, String apellidos, int dependientes, int casa) {
        //Retorna las filas afectadas por la consulta

        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement(INSERT_INTO);
            statement.setString(1, cedula);
            statement.setString(2, nombre);
            statement.setString(3, apellidos);
            statement.setInt(4, dependientes);
            statement.setInt(5, casa);
            result = statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public void actualizarResidente(String cedula, String nombre, String apellidos, int dependientes, int casa) {
        try {

            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, nombre);
            statement.setString(2, apellidos);
            statement.setInt(3, dependientes);
            statement.setInt(4, casa);

            statement.setString(5, cedula);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(TablaResidente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarResidente(String id) {
        try {

            PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID);
            statement.setString(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerResidente(String id) {
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet listarResidentes() {
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public DefaultTableModel obtenerResidenteTabla(String id) {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Cedula", "Nombres", "Apellidos", "Dependientes", "Casa"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[5];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("Dependientes");
                o[4] = rs.getString("Casa");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }

    public DefaultTableModel obtenerResidenteTabla() {
        DefaultTableModel tbl = new DefaultTableModel();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            String Titulos[] = {"Cedula", "Nombres", "Apellidos", "Dependientes", "Casa"};
            tbl.setColumnIdentifiers(Titulos);
            while (rs.next()) {
                Object[] o = new Object[5];
                o[0] = rs.getString("Cedula");
                o[1] = rs.getString("nombres");
                o[2] = rs.getString("Apellidos");
                o[3] = rs.getString("Dependientes");
                o[4] = rs.getString("Casa");
                tbl.addRow(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return tbl;
    }
    
    
    public Residente obtenerResidentexCedula(String id) {
        Residente r = new Residente();
        try {

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                r.setCedula(rs.getString("cedula"));
                r.setNombres(rs.getString("nombres"));
                r.setApellidos(rs.getString(("apellidos")));
                r.setDependientes(rs.getInt("dependientes"));
                r.setNumeroCasa(rs.getInt("casa"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return r;
    }

} //fin class

//    public void listarResidente(){
//        Connection conn = this.obtenerConexion();
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(SELECT_ALL);
//            
//            ResultSetMetaData metaData = rs.getMetaData();
//            int numDeColumna = metaData.getColumnCount();
//            
//            System.out.println("Tabla Residente");
//            for(int i =1; i <= numDeColumna; i++){
//                System.out.printf("%-8s\t", metaData.getColumnName(i));
//                
//            }
//            System.out.println("");
//            while (rs.next()){
//                for(int i= 1; i<= numDeColumna; i++){
//                    System.out.printf("%-8s \t", rs.getObject(i));
//                }
//                System.out.println("");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }      
//    }

