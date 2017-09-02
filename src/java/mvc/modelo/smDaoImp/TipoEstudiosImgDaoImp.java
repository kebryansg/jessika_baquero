package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.EstudioImagen;
import mvc.controlador.entidades.sm.TipoEstudioImg;
import mvc.modelo.smDao.TipoEstudiosImgDao;

public class TipoEstudiosImgDaoImp implements TipoEstudiosImgDao {

    C_BD conn;

    @Override
    public TipoEstudioImg edit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EstudioImagen> list_estI(int idTipoEstudiosImg) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<EstudioImagen> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select *  from dbo.estudioImagen where idTipoEstudioImg = '"+ idTipoEstudiosImg +"'");
        try {
            while (rs.next()) {
                EstudioImagen value = new EstudioImagen(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
                value.setIdTipoEstudioImg(new TipoEstudioImg(idTipoEstudiosImg));
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
    public List<TipoEstudioImg> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<TipoEstudioImg> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from dbo.tipoEstudioImg");
        try {
            while (rs.next()) {
                TipoEstudioImg value = new TipoEstudioImg(rs.getInt("id"), rs.getString("descripcion"));
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }
}
