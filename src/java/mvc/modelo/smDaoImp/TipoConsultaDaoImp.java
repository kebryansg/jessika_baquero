package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.DetallesMetodos;
import mvc.controlador.entidades.sm.Metodos;
import mvc.controlador.entidades.sm.TipoConsulta;
import mvc.modelo.smDao.TipoConsultaDao;

public class TipoConsultaDaoImp implements TipoConsultaDao {

    C_BD conn;

    @Override
    public List<TipoConsulta> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<TipoConsulta> list = new ArrayList<>();
        String sql = "select * from tipoConsulta";
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                TipoConsulta value = new TipoConsulta(rs.getInt("id"), rs.getNString("descripcion"));
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
    public List<Metodos> list_Metodos(int idTipoConsulta) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Metodos> list = new ArrayList<>();

        String sql = "select * from metodos where idTipoConsulta = " + idTipoConsulta;
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                List<DetallesMetodos> list_detalles = new ArrayList<>();
                Metodos value = new Metodos(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
                value.setIdTipoConsulta(idTipoConsulta);

                sql = "select * from dbo.detallesMetodos where idMetodo = " + value.getId();
                ResultSet rs2 = this.conn.query(sql);
                try {

                    while (rs2.next()) {
                        DetallesMetodos detMetodo = new DetallesMetodos(rs2.getInt("id"));
                        detMetodo.setDescripcion(rs2.getString("descripcion"));
                        detMetodo.setIdMetodo(value.getId());
                        detMetodo.setSexo(rs2.getString("sexo"));
                        list_detalles.add(detMetodo);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                value.setList(list_detalles);

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
    public TipoConsulta edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "select * from tipoConsulta where id = "+ id;
        TipoConsulta value = null;
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                value = new TipoConsulta(rs.getInt("id"), rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        try {
            CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.insertTipoConsulta()}");
            call.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }finally{
            this.conn.close();
        }
        
        return false;
    }

}
