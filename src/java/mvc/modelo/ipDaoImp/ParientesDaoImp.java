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
import mvc.controlador.entidades.ip.Parientes;
import mvc.modelo.ipDao.ParientesDao;

/**
 *
 * @author kebryan
 */
public class ParientesDaoImp implements ParientesDao{
    C_BD conn;

    @Override
    public List<Parientes> list() {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Parientes> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from parientes");
        try {
            while (rs.next()) {
                Parientes value = new Parientes();
                value.setId(rs.getInt("id"));
                value.setParentesco(rs.getNString("parentesco"));
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
    public Parientes edit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Parientes canton) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
