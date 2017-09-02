/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.ip.Obstetricos;
import mvc.controlador.entidades.ip.Paciente;
import mvc.modelo.ipDao.ObstetricosDao;

/**
 *
 * @author kebryan
 */
public class ObstetricosDaoImp implements ObstetricosDao {

    C_BD conn;

    @Override
    public List<Obstetricos> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Obstetricos edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        ResultSet rs = this.conn.query("select * from obstetricos where id = '" + id + "'");
        Obstetricos value = new Obstetricos();
        try {
            while (rs.next()) {
                value.setId(Integer.parseInt(rs.getNString("id")));
                value.setFpp(test.test.fechaSQL(rs.getNString("fpp")));
                value.setAbortos(Integer.parseInt(rs.getNString("abortos")));
                value.setGestas(Integer.parseInt(rs.getNString("gestas")));
                value.setPartos(Integer.parseInt(rs.getNString("partos")));
                value.setCesareas(Integer.parseInt(rs.getNString("cesareas")));
                value.setNacidosVivos(Integer.parseInt(rs.getNString("nacidosVivos")));
                value.setNacidosMuertos(Integer.parseInt(rs.getNString("nacidosMuertos")));
                value.setHijosVivos(Integer.parseInt(rs.getNString("hijosVivos")));
                value.setMuertos(Integer.parseInt(rs.getNString("muertos")));
                value.setObservaciones(rs.getNString("observaciones"));
                value.setIdPaciente(new Paciente(Integer.parseInt(rs.getNString("idPaciente"))));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(Obstetricos value) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        String sql = "";
        try {
            if (value.getId() == 0) {
                sql = "INSERT INTO [dbo].[obstetricos]([fpp],[gestas],[abortos],[partos],[cesareas],[nacidosVivos],[nacidosMuertos],[hijosVivos],[muertos],[observaciones],[idPaciente])\n"
                        + "     VALUES\n"
                        + "           ('" + test.test.SQLSave(value.getFpp()) + "'\n"
                        + "           ," + value.getGestas() + "\n"
                        + "           ," + value.getAbortos() + "\n"
                        + "           ," + value.getPartos() + "\n"
                        + "           ," + value.getCesareas() + "\n"
                        + "           ," + value.getNacidosVivos() + "\n"
                        + "           ," + value.getNacidosMuertos() + "\n"
                        + "           ," + value.getHijosVivos() + "\n"
                        + "           ," + value.getMuertos() + "\n"
                        + "           ,'" + value.getObservaciones() + "'\n"
                        + "           ," + value.getIdPaciente().getId() + ")";
            } else {
                sql = "UPDATE [dbo].[obstetricos]\n"
                        + "   SET [fpp] = '" + test.test.SQLSave(value.getFpp()) + "'\n"
                        + "      ,[gestas] = " + value.getGestas() + "\n"
                        + "      ,[abortos] = " + value.getAbortos() + "\n"
                        + "      ,[partos] = " + value.getPartos() + "\n"
                        + "      ,[cesareas] = " + value.getCesareas() + "\n"
                        + "      ,[nacidosVivos] = " + value.getNacidosVivos() + "\n"
                        + "      ,[nacidosMuertos] = " + value.getNacidosMuertos() + "\n"
                        + "      ,[hijosVivos] = " + value.getHijosVivos() + "\n"
                        + "      ,[muertos] = " + value.getMuertos() + "\n"
                        + "      ,[observaciones] = " + value.getObservaciones() + "\n"
                        + "      ,[idPaciente] = " + value.getIdPaciente().getId() + "\n"
                        + " WHERE id = " + value.getId();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Obstetricos edit_idPaciente(int idPaciente) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        ResultSet rs = this.conn.query("select * from obstetricos where idPaciente = '" + idPaciente + "'");
        Obstetricos value = new Obstetricos();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setFpp(rs.getDate("fpp"));
                value.setAbortos(rs.getInt("abortos"));
                value.setGestas(rs.getInt("gestas"));
                value.setPartos(rs.getInt("partos"));
                value.setCesareas(rs.getInt("cesareas"));
                value.setNacidosVivos(rs.getInt("nacidosVivos"));
                value.setNacidosMuertos(rs.getInt("nacidosMuertos"));
                value.setHijosVivos(rs.getInt("hijosVivos"));
                value.setMuertos(rs.getInt("muertos"));
                value.setObservaciones(rs.getString("observaciones"));
                value.setIdPaciente(new Paciente(rs.getInt("idPaciente")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

}
