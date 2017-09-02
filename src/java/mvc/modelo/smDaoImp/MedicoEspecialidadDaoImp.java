/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Medico;
import mvc.controlador.entidades.sm.MedicoEspecialidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.modelo.smDao.MedicoEspecialidadDao;

/**
 *
 * @author Deivi
 */
public class MedicoEspecialidadDaoImp implements MedicoEspecialidadDao {
    C_BD conn;
    String excepcion="";
    @Override
    public boolean save(MedicoEspecialidad value) {
        String sql="";
        this.conn= con_db.open(con_db.MSSQL_SM);
        try 
        {
            sql="INSERT INTO medico_especialidad (idMedico,idEspecialidad) values ('"+value.getIdMedico().getId()+"','"+value.getIdEspecialidad().getId()+"');";
            this.conn.execute(sql);
            return true;
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return false;
        }
    }
    
   
    @Override
    public List<MedicoEspecialidad> list(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<MedicoEspecialidad> list = new ArrayList<>();
        ResultSet rs = this.conn.query("SELECT medico_especialidad.id me_id , especialidad.id,  especialidad.descripcion from medico_especialidad inner join especialidad on especialidad.id=medico_especialidad.idEspecialidad inner join medico on medico_especialidad.idMedico=medico.id WHERE medico.id="+id+"");
        System.out.println("SELECT medico_especialidad.id me_id , especialidad.id,  especialidad.descripcion from medico_especialidad inner join especialidad on especialidad.id=medico_especialidad.idEspecialidad inner join medico on medico_especialidad.idMedico=medico.id WHERE medico.id="+id+"");
        try {
            while (rs.next()) {
                MedicoEspecialidad value = new MedicoEspecialidad(rs.getInt("me_id"));                 
                value.setIdEspecialidad(new Especialidad(rs.getInt("id"),rs.getNString("descripcion")));                
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             excepcion=ex.getMessage();
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public MedicoEspecialidad edit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        String sql="delete from medico_especialidad where idMedico="+id;
        this.conn= con_db.open(con_db.MSSQL_SM);
        try 
        {                   
            this.conn.execute(sql);
            return true;
        }
        catch(Exception ex)
        { 
             excepcion=ex.getMessage();
            return false;
        }
        
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    
}
