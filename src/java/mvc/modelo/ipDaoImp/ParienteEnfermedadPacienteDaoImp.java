/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.ip.Enfermedad;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.ip.ParienteEnfermedadPaciente;
import mvc.controlador.entidades.ip.Parientes;
import mvc.modelo.ipDao.ParienteEnfermedadPacienteDao;

/**
 *
 * @author kebryan
 */
public class ParienteEnfermedadPacienteDaoImp implements ParienteEnfermedadPacienteDao {

    C_BD conn;

    @Override
    public List<ParienteEnfermedadPaciente> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ParienteEnfermedadPaciente edit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(ParienteEnfermedadPaciente value) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        String sql = "";
        try {
            if (value.getId() == 0) {
                sql = "INSERT INTO [dbo].[pariente_enfermedad_paciente]([idPariente],[idEnfermedad],[idPaciente])\n"
                        + "     VALUES\n"
                        + "           (" + value.getIdPariente().getId() + "\n"
                        + "           ," + value.getIdEnfermedad().getId() + "\n"
                        + "           ," + value.getIdPaciente().getId() + ")";
            } else {
                sql = "UPDATE [dbo].[pariente_enfermedad_paciente]\n"
                        + "   SET [idPariente] = " + value.getIdPariente().getId() + "\n"
                        + "      ,[idEnfermedad] = " + value.getIdEnfermedad().getId() + "\n"
                        + "      ,[idPaciente] = " + value.getIdPaciente().getId() + "\n"
                        + " WHERE id = " + value.getId();
            }
            conn.execute(sql);
            //System.out.println(sql);
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
        this.conn = con_db.open(con_db.MSSQL_IP);
        boolean bandera = false;
        try {
            bandera = this.conn.execute("delete from pariente_enfermedad_paciente where id = " + id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }

        return bandera;
    }

    @Override
    public List<ParienteEnfermedadPaciente> list_Paciente(int idPaciente) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<ParienteEnfermedadPaciente> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from pariente_enfermedad_paciente where idPaciente = '" + idPaciente + "'");
        try {
            while (rs.next()) {
                ParienteEnfermedadPaciente value = new ParienteEnfermedadPaciente(rs.getInt("id"));
                value.setIdEnfermedad(new Enfermedad(rs.getInt("idEnfermedad")));
                value.setIdPariente(new Parientes(rs.getInt("idPariente")));
                value.setIdPaciente(new Paciente(rs.getInt("idPaciente")));
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
