/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.modelo.smDao.HistorialClinicoDao;

/**
 *
 * @author kebryan
 */
public class HistorialClinicoDaoImp implements HistorialClinicoDao {

    C_BD conn;

    @Override
    public List<HistorialClinico> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HistorialClinico edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select * from HistorialClinico where id = '" + id + "'");
        HistorialClinico value = new HistorialClinico();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdPaciente(rs.getInt("idPaciente"));
               value.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public HistorialClinico edit_Paciente(int idPaciente) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select * from HistorialClinico where idPaciente = '" + idPaciente + "'");
        HistorialClinico value = new HistorialClinico();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdPaciente(rs.getInt("idPaciente"));
                value.setMenarca(rs.getDate("menarca"));
                value.setFecha(rs.getDate("fecha"));
                value.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(HistorialClinico value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try {
            if (value.getId() == 0) {
                sql = "INSERT INTO [dbo].[historialClinico]([idPaciente],[fecha],[menarca],[estado]) VALUES('" + value.getIdPaciente() + "',GETDATE(),GETDATE(),'1')";
            } else {
                sql = "UPDATE [dbo].[historialClinico]\n"
                        + "   SET [idPaciente] = '" + value.getIdPaciente() + "'\n"
                        + "   SET [fecha] = '" + value.getFecha() + "'\n"
                        + "   SET [menarca] = '" + value.getMenarca() + "'\n"
                        + "   SET [estado] = '" + value.getEstado() + "'\n"
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
    public boolean delete(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try {
            sql = "UPDATE [dbo].[historialClinico]\n"
                    + "   SET [estado] = '0'\n"
                    + " WHERE [idPaciente] = '" + id + "'";
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
    public HistorialClinico edit_Paciente(String opcion,String param) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql_imp = opcion.equals("cedula") ? "where pac.cedula = '"+ param +"'" : "where hc.id = '"+ param +"'";
        ResultSet rs = this.conn.query("select hc.* from dbo.historialClinico hc INNER JOIN BD_IP.dbo.paciente pac on pac.id = hc.idPaciente "+ sql_imp);
        HistorialClinico value = new HistorialClinico();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdPaciente(rs.getInt("idPaciente"));
                value.setMenarca(rs.getDate("menarca"));
                value.setFecha(rs.getDate("fecha"));
                value.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

}
