/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;

import mvc.controlador.entidades.sm.Establecimiento;
import mvc.modelo.smDao.EstablecimientoDao;

/**
 *
 * @author Deivi
 */
public class EstablecimientoDaoImp implements EstablecimientoDao {
C_BD conn;
String excepcion="";
    @Override
    public boolean save(Establecimiento value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try 
        {
            if (value.getId() == 0) 
            {
                sql = "INSERT INTO establecimiento (nombre,encargado,parroquia,direccion,telefono,email,logo)\n" +
                "values ('"+value.getNombre()+"','"+value.getEncargado()+"','"+value.getParroquia()+"','"+value.getDireccion()+"',\n" +
                "'"+value.getTelefono()+"','"+value.getEmail()+"','"+value.getLogo()+"');";
            } else
            {
                sql = "update establecimiento set nombre='"+value.getNombre()+"', encargado='"+value.getEncargado()+"', parroquia='"+value.getParroquia()+"', direccion='"+value.getDireccion()+"',\n" +
                "telefono='"+value.getTelefono()+"', email='"+value.getEmail()+"', logo='"+value.getLogo()+"'";
            }
            conn.execute(sql);
            System.out.println(sql);
            return true;
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            excepcion=ex.getMessage();
            return false;
        } 
        finally 
        {
            this.conn.close();
        }
    }

    @Override
    public boolean updateLogo(String name) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try 
        {
            sql = "update establecimiento set logo='"+name+"'";           
            conn.execute(sql);
            System.out.println(sql);
            return true;
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            excepcion=ex.getMessage();
            return false;
        } 
        finally 
        {
            this.conn.close();
        }
    }

    @Override
    public List<Establecimiento> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Establecimiento> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from establecimiento");
        try {
            while (rs.next()) {
                Establecimiento value = new Establecimiento();                
                value.setNombre(rs.getString("nombre"));
                value.setEncargado(rs.getString("encargado"));                
                value.setParroquia(rs.getInt("parroquia"));
                value.setDireccion(rs.getString("direccion"));
                value.setTelefono(rs.getString("telefono"));
                value.setEmail(rs.getString("email"));
                value.setLogo(rs.getString("logo"));
                list.add(value);
            }
        } catch (SQLException ex) {
            excepcion=ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    
}
