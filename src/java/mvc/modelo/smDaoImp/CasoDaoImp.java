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
    public List<Consulta> listConsulta(int idHistoriaC, String fechaInicial, String fechaFinal, String filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Consulta> list = new ArrayList<>();
        String paginacion = (top != -1) ? "OFFSET " + pag + " ROWS FETCH NEXT " + top + " ROWS ONLY;" : "";
        String fecha = (!fechaFinal.equals("")) ? "(co.fecha < '" + fechaFinal + "' and co.fecha > '" + fechaInicial + "') and" : "";
        String sql = ("select co.idCaso casoId, min(co.fecha) fecha,(select top 1 con.diagnostico from consulta con where con.idCaso = co.idCaso ORDER by fecha asc) motivo from consulta co inner join dbo.caso ca on co.idCaso = ca.id inner join dbo.historialClinico hc on hc.id = ca.idHistorialClinico where hc.id = '" + idHistoriaC + "' AND "
                + fecha
                + " (co.motivo like '%" + filter + "%' or co.diagnostico like '%" + filter + "%' or co.prescripcion like '%" + filter + "%' or co.sintomas like '%" + filter + "%')"
                + " GROUP by co.idCaso "
                + " ORDER BY fecha desc " + paginacion);
        System.out.println(sql);
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                Consulta value = new Consulta(0);
                value.setMotivo(rs.getString("motivo"));
                value.setFecha(rs.getDate("fecha"));
                value.setIdCaso(new Caso(rs.getInt("casoId"), idHistoriaC));
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
