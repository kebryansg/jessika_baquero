/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.ip.Parroquia;
import mvc.modelo.ipDao.ParroquiaDao;

/**
 *
 * @author kebryan
 */
public class ParroquiaDaoImp implements ParroquiaDao {

    C_BD conn;

    @Override
    public List<Parroquia> list(int idCanton) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Parroquia> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from Parroquia where idCanton = " + idCanton);
        try {
            while (rs.next()) {
                Parroquia value = new Parroquia();
                value.setId(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public Parroquia edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        Parroquia value = new Parroquia();
        ResultSet rs = this.conn.query("select * from Parroquia where id = " + id);
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(Parroquia canton) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String detParroquia(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        String result = "" ,sql = "SELECT    idCanton, idProvincia    \n"
                + "FROM            canton INNER JOIN\n"
                + "                         parroquia ON canton.id = parroquia.idCanton INNER JOIN\n"
                + "                         provincia ON canton.idProvincia = provincia.id\n"
                + "						 where parroquia.id = '" + id + "'";
        ResultSet rs = this.conn.query(sql);
        try {            
            while (rs.next()) {
                result = "{ \"canton\": " + rs.getInt("idCanton") + " , \"provincia\": " + rs.getInt("idProvincia") + " }";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return result;
    }

}
