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
import mvc.controlador.entidades.ip.Canton;
import mvc.modelo.ipDao.CantonDao;

/**
 *
 * @author kebryan
 */
public class CantonDaoImp  implements CantonDao{
    C_BD conn;

    @Override
    public List<Canton> list(int idProvincia) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Canton> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from canton where idProvincia = "+ idProvincia);
        try {
            while (rs.next()) {
                Canton value = new Canton();
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
    public Canton edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        ResultSet rs = this.conn.query("select * from canton where id = '" + id + "'");
        Canton value = new Canton();
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
    public boolean save(Canton canton) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
