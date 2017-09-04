/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.SignosVitales;
import mvc.modelo.smDao.SignosVitalesDao;

/**
 *
 * @author kebryan
 */
public class SignosVitalesDaoImp implements SignosVitalesDao {

    C_BD conn;

    @Override
    public boolean save(SignosVitales value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try {
            CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.saveSignosVitales(?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt("id", value.getId());
            call.setInt("idConsulta", value.getIdConsulta());
            call.setNString("peso", value.getPeso());
            call.setNString("temperatura", value.getTemperatura());
            call.setNString("presion", value.getPresion());
            call.setNString("talla", value.getTalla());
            call.setNString("frecuencia", value.getFrecuenciaC());
            if (value.getFum() == null) {
                call.setNull("fum", Types.DATE);
            } else {
                call.setDate("fum", new java.sql.Date(value.getFuc().getTime()));
            }
            if (value.getFuc() == null) {
                call.setNull("fuc", Types.DATE);
            } else {
                call.setDate("fuc", new java.sql.Date(value.getFuc().getTime()));
            }
            call.setString("periodo", value.getPeriodo());
            call.registerOutParameter("idOut", Types.INTEGER);
            call.execute();
            value.setId(call.getInt("idOut"));
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            this.conn.close();
        }
    }

    @Override
    public SignosVitales editar(int idConsulta) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select signosVitales.* from signosVitales inner join dbo.consulta on consulta.id = signosVitales.idConsulta where consulta.id = '" + idConsulta + "'");
        SignosVitales value = new SignosVitales();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setPeso(rs.getString("peso"));
                value.setTalla(rs.getString("talla"));
                value.setPresion(rs.getString("presion"));
                value.setPeriodo(rs.getString("periodo"));
                value.setTemperatura(rs.getString("temperatura"));
                value.setFum(rs.getDate("fum"));
                value.setFuc(rs.getDate("fuc"));
                value.setFrecuenciaC(rs.getString("frecuenciaC"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

}