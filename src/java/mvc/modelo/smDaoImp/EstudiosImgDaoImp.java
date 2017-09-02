
package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.DetallesEstudiosImg;
import mvc.controlador.entidades.sm.EstudioImagen;
import mvc.controlador.entidades.sm.TipoEstudioImg;
import mvc.modelo.smDao.EstudiosImgDao;
import test.list_count;

public class EstudiosImgDaoImp implements EstudiosImgDao{
    C_BD conn;

    @Override
    public EstudioImagen edit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public list_count list_det(int idTipoEstudiosImg, int idEstudiosImg,String filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<DetallesEstudiosImg> list = new ArrayList<>();
        list_count l = new list_count();
        CallableStatement call;
        try {
            call = this.conn.getConexion().prepareCall("{call dbo.viewEstudiosImg(?,?,?,?,?,?)}");
            call.setString("filtro", filter);
            call.setInt("tops", top);
            call.setInt("pag", pag);
            call.setInt("tipoEstudio", idTipoEstudiosImg);
            call.setInt("estImg", idEstudiosImg);
            call.registerOutParameter("registros", Types.INTEGER);
            call.execute();
            ResultSet rs = call.getResultSet();
            while (rs.next()) {
                DetallesEstudiosImg value = new DetallesEstudiosImg(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
                value.setExtremidades(rs.getString("extremidades"));
                EstudioImagen estI = new EstudioImagen(idEstudiosImg);
                estI.setIdTipoEstudioImg(new TipoEstudioImg(idTipoEstudiosImg, rs.getNString("t_descripcion")));
                value.setIdEstudiosImg(estI);
                
                list.add(value);
            }
            l.setList(list);
            l.setTotal(call.getInt("registros"));
            System.out.println("total :" + call.getInt("registros"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return l;
    }
    
}
