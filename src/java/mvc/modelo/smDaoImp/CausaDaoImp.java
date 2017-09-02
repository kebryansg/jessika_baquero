package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Causa;
import mvc.modelo.smDao.CausaDao;

public class CausaDaoImp implements CausaDao {

    C_BD conn;

    @Override
    public boolean save(Causa value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        try {
            if (value.getId() == 0) {
                CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.saveCausa(?, ?)}");
                call.setString(1, value.getDescripcion());
                call.registerOutParameter("id", Types.INTEGER);
                call.execute();
                value.setId(call.getInt("id"));
            } else {
                /*sql = "UPDATE [dbo].[caso]\n"
                        + "   SET [idHistorialClinico] = '" + value.getIdHistorialClinico() + "'\n"
                        + " WHERE id = '" + value.getId() + "'";*/
            }
            //conn.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            this.conn.close();
        }
    }

    @Override
    public List<Causa> list_filter(String value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Causa> list = new ArrayList<>();
        String sql = ("SELECT * FROM CAUSA WHERE (descripcion) LIKE '%" + value + "%'");
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                Causa causa = new Causa(rs.getInt("id"));
                causa.setDescripcion(rs.getString("descripcion").toUpperCase());
                list.add(causa);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public Causa edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = ("SELECT * FROM CAUSA WHERE id = '" + id + "'");
        Causa causa = new Causa();
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                causa.setId(rs.getInt("id"));
                causa.setDescripcion(rs.getString("descripcion").toUpperCase());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return causa;
    }

}
