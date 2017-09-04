package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Caso;
import mvc.controlador.entidades.sm.Consulta;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.modelo.smDao.CasoDao;
import test.list_count;

public class CasoDaoImp implements CasoDao {

    C_BD conn;

    @Override
    public boolean save(Caso value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try {
            if (value.getId() == 0) {
                CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.saveCaso(?, ?)}");
                call.setInt("hc", value.getIdHistorialClinico().getId());
                call.registerOutParameter("id", Types.INTEGER);
                call.execute();
                value.setId(call.getInt("id"));
            } else {
                sql = "UPDATE [dbo].[caso]\n"
                        + "   SET [idHistorialClinico] = '" + value.getIdHistorialClinico() + "'\n"
                        + " WHERE id = '" + value.getId() + "'";
            }
            conn.execute(sql);
            System.out.println(sql);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            this.conn.close();
        }
    }

    @Override
    public Caso edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select * from caso where id = '" + id + "'");
        Caso value = new Caso();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdHistorialClinico(new HistorialClinico(rs.getInt("idHistorialClinico")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public list_count listConsulta(int idHistoriaC, String filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        list_count l = new list_count();
        List<String> list = new ArrayList<>();
        String sql = "{call dbo.viewCaso(?,?,?,?)}";
        try {
            CallableStatement call = this.conn.getConexion().prepareCall(sql);
            call.setInt("trows", top);
            call.setInt("inicio", pag);
            call.setInt("hc", idHistoriaC);
            System.out.println("\"" + (filter.equals("") ? "*" : filter + "*") + "\"");
            call.setString("buscar", "\"" + (filter.equals("") ? "*" : filter + "*") + "\"");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                l.setTotal(rs.getInt("full_count"));
                list.add("{"
                        + "\"caso\" : \" " + rs.getInt("caso") + " \", "
                        + "\"fecha\" : \" " + test.test.SQLSave(rs.getDate("fecha")) + " \", "
                        + "\"diagnostico\" : \" " + rs.getString("diagnostico") + " \", "
                        + "\"especialidad\" : \" " + rs.getString("especialidad") + " \", "
                        + "\"tipoConsulta\" : \" " + rs.getString("tipoConsulta") + " \", "
                        + "\"accion\" : \" <button data-toggle='tooltip' title='Mostrar Historial del Caso' data-placement='left' name='btnHCaso' data-id='"+ rs.getInt("caso") +"' class='btn btn-info'><i class='fa fa-table'></i></button> <button name='btnNewConsulta' data-id='"+ rs.getInt("caso") +"' data-toggle='tooltip' title='Agregar consulta al caso' data-placement='left' class='btn btn-info'><i class='fa fa-plus'></i></button>\" "
                        + "}");
            }
            l.setList(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return l;
    }

    @Override
    public List<Consulta> listDetConsulta(int idCaso) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Consulta> list = new ArrayList<>();
        try {
            CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.listConsultaCaso(?)}");
            call.setInt("idCaso", idCaso);
            call.execute();
            ResultSet rs = call.getResultSet();
            while (rs.next()) {
                Consulta value = new Consulta(rs.getInt("id"));
                value.setIdCaso(new Caso(rs.getInt("caso")));
                value.setFecha(rs.getDate("fecha"));
                value.setMotivo(rs.getString("motivo"));
                value.setSintoma(rs.getString("tipo"));
                value.setPrescripcion(rs.getString("Especialidad"));
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
