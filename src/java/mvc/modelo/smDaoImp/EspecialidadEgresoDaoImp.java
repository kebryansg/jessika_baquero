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
import mvc.controlador.entidades.sm.Especialidad;
import mvc.controlador.entidades.sm.EspecialidadEgreso;
import mvc.modelo.smDao.EspecialidadEgresoDao;

/**
 *
 * @author Deivi
 */
public class EspecialidadEgresoDaoImp implements EspecialidadEgresoDao {
    C_BD conn;

    @Override
    public List<EspecialidadEgreso> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<EspecialidadEgreso> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from especialidadEgreso");
        try {
            while (rs.next()) {
                EspecialidadEgreso value = new EspecialidadEgreso();
                value.setId(rs.getInt("id"));
                value.setDescripcion(rs.getNString("descripcion"));
                value.setCodigo(rs.getInt("codigo"));
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
