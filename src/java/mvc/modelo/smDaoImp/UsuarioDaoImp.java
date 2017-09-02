/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.SQLException;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.modelo.smDao.UsuarioDao;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.controlador.entidades.sm.Medico;
import mvc.controlador.entidades.sm.Rol;
import mvc.controlador.entidades.sm.Usuario;

/**
 *
 * @author Deivi
 */
public class UsuarioDaoImp implements UsuarioDao {

    C_BD conn;
    Medico medico;
    int idRol = -1, id = 0;

    @Override
    public Usuario Login(String usuario, String clave, int rol) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        Usuario value = new Usuario();
        try {
            CallableStatement call = this.conn.getConexion().prepareCall("{ call dbo.login(?,?,?,?,?)}");
            call.setString("nick", usuario);
            call.setString("pass", clave);
            call.setInt("rol", rol);
            call.registerOutParameter("user_name", Types.NVARCHAR);
            call.registerOutParameter("idOut", Types.INTEGER);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                value.setNick(rs.getString("nick"));
                value.setRol(new Rol(rs.getInt("idRol"), "", rs.getInt("val")));
            }
            value.setUser_name(call.getString("user_name"));
            value.setId(call.getInt("idOut"));
            return value;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            this.conn.close();
        }
    }

    @Override
    public int getIdRol() {
        return idRol;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Medico getMedico() {
        return medico;
    }

    @Override
    public List<Rol> list_rol() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Rol> list = new ArrayList<>();
        String sql = ("select * from rol;");
        try {
            ResultSet rs = this.conn.query(sql);
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setDescripcion(rs.getString("descripcion").toUpperCase());
                rol.setVal(rs.getInt("val"));
                list.add(rol);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            this.conn.close();
        }
        return list;
    }

}
