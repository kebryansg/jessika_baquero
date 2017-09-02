/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Consulta;
import mvc.controlador.entidades.sm.ConsultaEstudiosLabs;
import mvc.controlador.entidades.sm.DetalleEstudiosLabs;
import mvc.controlador.entidades.sm.EstudiosLaboratorio;
import mvc.modelo.smDao.ConsultaEstudiosLabsDao;

/**
 *
 * @author kebryan
 */
public class ConsultaEstudiosLabsDaoImp implements ConsultaEstudiosLabsDao {

    C_BD conn;

    @Override
    public boolean save(ConsultaEstudiosLabs value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try {
            if (value.getId() == 0) {
                sql = "INSERT INTO [dbo].[consultaEstudiosLabs]([idConsulta],[idDetalleEstudiosLabs],[valores]) VALUES(" + value.getIdConsulta().getId() + "," + value.getIdDetalleEstudiosLabs().getId() + ",'" + value.getValores() + "')";
            } else {
                sql = "UPDATE [dbo].[historialClinico]\n"
                        + "   SET [idConsulta] = '" + value.getIdConsulta().getId() + "'\n"
                        + "   SET [idDetalleEstudiosLabs] = '" + value.getIdDetalleEstudiosLabs().getId() + "'\n"
                        + "   SET [valores] = '" + value.getValores() + "'\n"
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
    public ConsultaEstudiosLabs edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select coEst.id, coEst.idConsulta,coEst.idDetalleEstudiosLabs,coEst.valores,detEst.descripcion from consultaEstudiosLabs coEst inner join detalleEstudiosLabs detEst on coEst.idDetalleEstudiosLabs = detEst.id where coEst.id = '" + id + "'");
        ConsultaEstudiosLabs value = new ConsultaEstudiosLabs();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdConsulta(new Consulta(rs.getInt("idConsulta")));
                value.setIdDetalleEstudiosLabs(new DetalleEstudiosLabs(rs.getInt("idDetalleEstudiosLabs"), rs.getString("descripcion")));
                value.setValores(rs.getString("valores"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public List<ConsultaEstudiosLabs> list(int idConsulta) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select el.id idEstudios,el.descripcion tipoEstudios, coEst.id, coEst.idConsulta,coEst.idDetalleEstudiosLabs,coEst.valores,detEst.descripcion from consultaEstudiosLabs coEst inner join detalleEstudiosLabs detEst on coEst.idDetalleEstudiosLabs = detEst.id inner join dbo.estudiosLaboratorio el on el.id = detEst.idEstudiosLab where coEst.idConsulta = '" + idConsulta + "'");
        List<ConsultaEstudiosLabs> list = new ArrayList<>();
        try {
            while (rs.next()) {
                ConsultaEstudiosLabs value = new ConsultaEstudiosLabs(rs.getInt("id"));
                value.setIdConsulta(new Consulta(rs.getInt("idConsulta")));
                DetalleEstudiosLabs det =new DetalleEstudiosLabs(rs.getInt("idDetalleEstudiosLabs"), rs.getString("descripcion").toUpperCase());
                det.setIdEstudiosLab(new EstudiosLaboratorio(rs.getInt("idEstudios"), rs.getString("tipoEstudios").toUpperCase()));
                value.setIdDetalleEstudiosLabs(det);
                value.setValores(rs.getString("valores"));
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
