/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;
import java.sql.*;

/**
 *
 * @author kebryan
 */
public abstract  class C_BD {
    protected String[] params;
    protected Connection conexion;
    abstract Connection open();
     public Connection getConexion() {
        return conexion;
    }
    public ResultSet query(String query)
    {
        Statement st;
        ResultSet res = null;
        try {
            st = conexion.createStatement();
            res = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet Procedure(String query)
    {
        CallableStatement call;
        ResultSet res = null;
        try {
            call = conexion.prepareCall(query);
            res = call.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    
    public boolean execute(String query)
    {
        Statement st;
        boolean save = true;
        ResultSet res = null;
        try {
            st = conexion.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            save = false;
            e.printStackTrace();
            
        }
        return save;
    }
    public boolean close(){
        boolean ok= true;
        try {
            conexion.close();
        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        }
        return ok;
    }
}
