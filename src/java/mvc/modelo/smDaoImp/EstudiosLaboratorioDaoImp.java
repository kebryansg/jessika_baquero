package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.DetalleEstudiosLabs;
import mvc.controlador.entidades.sm.EstudiosLaboratorio;
import mvc.modelo.smDao.EstudiosLaboratorioDao;
import test.list_count;

public class EstudiosLaboratorioDaoImp implements EstudiosLaboratorioDao {

    C_BD conn;

    @Override
    public List<EstudiosLaboratorio> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<EstudiosLaboratorio> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from dbo.estudiosLaboratorio");
        try {
            while (rs.next()) {
                EstudiosLaboratorio value = new EstudiosLaboratorio(rs.getInt("id"));
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
    public EstudiosLaboratorio edit(int value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        EstudiosLaboratorio esLab = new EstudiosLaboratorio(0);
        ResultSet rs = this.conn.query("select * from dbo.estudiosLaboratorio where id = " + value);
        try {
            while (rs.next()) {
                esLab.setId(rs.getInt("id"));
                esLab.setDescripcion(rs.getNString("descripcion").toUpperCase());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return esLab;
    }

    @Override
    public list_count list_Det(int categoria, String filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<DetalleEstudiosLabs> list = new ArrayList<>();
        list_count l = new list_count();
        CallableStatement call;
        try {
            call = this.conn.getConexion().prepareCall("{call dbo.viewEstudiosLab(?,?,?,?,?)}");
            call.setString("filtro", filter);
            call.setInt("tops", top);
            call.setInt("pag", pag);
            call.setInt("categoria", categoria);
            call.registerOutParameter("registros", Types.INTEGER);
            call.execute();
            ResultSet rs = call.getResultSet();
            while (rs.next()) {
                DetalleEstudiosLabs detEst = new DetalleEstudiosLabs(rs.getInt("del_id"));
                detEst.setIdEstudiosLab(new EstudiosLaboratorio(rs.getInt("el_id"), rs.getNString("el_descripcion")));
                detEst.setDescripcion(rs.getNString("del_descripcion").toUpperCase());
                list.add(detEst);
            }
            l.setList(list);
            l.setTotal(call.getInt("registros"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }

    @Override
    public List<EstudiosLaboratorio> list(int idConsulta) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<EstudiosLaboratorio> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from dbo.estudiosLaboratorio");
        try {
            while (rs.next()) {
                EstudiosLaboratorio value = new EstudiosLaboratorio(rs.getInt("id"));
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

}
