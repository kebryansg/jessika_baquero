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
import mvc.controlador.entidades.ip.Enfermedad;
import mvc.modelo.ipDao.EnfermedadDao;

/**
 *
 * @author kebryan
 */
public class EnfermedadDaoImp  implements EnfermedadDao{
    C_BD conn;

    @Override
    public List<Enfermedad> list() {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Enfermedad> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from Enfermedad");
        try {
            while (rs.next()) {
                Enfermedad value = new Enfermedad();
                value.setId(rs.getInt("id"));
                value.setNombres(rs.getNString("nombres"));
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
    public Enfermedad edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        Enfermedad value = new Enfermedad();
        ResultSet rs = this.conn.query("select * from Enfermedad where id = "+ id);
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setNombres(rs.getNString("nombres"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(Enfermedad value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
