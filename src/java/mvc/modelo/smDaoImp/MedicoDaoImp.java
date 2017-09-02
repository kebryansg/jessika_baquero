/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.controlador.entidades.sm.Medico;
import mvc.modelo.smDao.MedicoDao;

/**
 *
 * @author Byron
 */
public class MedicoDaoImp implements MedicoDao{
C_BD conn;
String excepcion="";
    @Override
    //numero de paginas hace referencia a la paginacion
    // total de registros los que se muestran en esta consulta
    // ejemplo nPagina 2 total de registros 5 muestra desde el registro 6 hasta el 10
    public List<Medico> list(int numeroPaginas, int totalRegistro,int bandera, String buscar) {
        //this.conn = con_db.open(con_db.MSSQL_SM);
        List<Medico> list = new ArrayList<>();
       
           try {  
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=null;
            //con el txt buscar
            if(bandera==1)
            {   
                cStmt = conexion.prepareCall("{call obtenerMedicosBuscar(?, ?, ?)}");  
                cStmt.setString(3, buscar);  
            }
            // sin el txt buscar
            else
            {
                cStmt = conexion.prepareCall("{call obtener_medicos(?, ?)}");  
            }
             cStmt.setInt(1, numeroPaginas);    
             cStmt.setInt(2, totalRegistro);  
            // cStmt.registerOutParameter("inOutParam", Types.INTEGER);    
               
             cStmt.execute();    
             final ResultSet rs = cStmt.getResultSet(); 
        //ResultSet rs = this.conn.query("SELECT * FROM MEDICO");
        
            while (rs.next()) {
                Medico value = new Medico();
                value.setId(rs.getInt("id"));
                value.setNombre1(rs.getNString("nombre1"));
                value.setNombre2(rs.getNString("nombre2"));
                value.setApellidos1(rs.getNString("apellidos1"));
                value.setApellidos2(rs.getNString("apellidos2"));
                //(paciente1.getCedula() != null ? paciente1.getCedula() : "-")
                value.setDomicilio(rs.getNString("domicilio")!=null?rs.getNString("domicilio"):"-");
                value.setCiudad(rs.getNString("ciudad"));
                value.setTelefonoDomicilio(rs.getNString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getNString("telefonoOficina"));
                value.setTelefonoMovil(rs.getNString("telefonoMovil")!=null?rs.getNString("telefonoMovil"):"-");
                value.setEmail(rs.getNString("email"));
                value.setCedula(rs.getNString("cedula"));                
                value.setVisible(rs.getInt("visible"));
                value.setRegistros(rs.getInt("registros"));
                list.add(value);
                
            }
        } catch (SQLException ex) {
            excepcion=ex.getMessage();
        } finally {
            //this.conn.close();
        }       
        return list; 
        
    }

    @Override
    public Medico edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select * from MEDICO  where id = '" + id + "'");
        Medico value = new Medico();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));                
                value.setNombre1(rs.getNString("nombre1"));
                value.setNombre2(rs.getNString("nombre2"));
                value.setApellidos1(rs.getNString("apellido1"));
                value.setApellidos2(rs.getNString("apellido2"));
                value.setDomicilio(rs.getNString("domicilio"));
                value.setCiudad(rs.getNString("ciudad"));
                value.setTelefonoDomicilio(rs.getNString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getNString("telefonoOficina"));
                value.setTelefonoMovil(rs.getNString("telefonoMovil"));
                value.setEmail(rs.getNString("email"));
                value.setCedula(rs.getNString("cedula"));               
                
            }
        } catch (SQLException ex) {
            excepcion=ex.getMessage();
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(Medico value) {
        String sql="", sql2="";
        this.conn= con_db.open(con_db.MSSQL_SM);
        try 
        {
            //Insert
            if(value.getId()==0)
            {
                sql="INSERT INTO Medico (nombre1,nombre2,apellidos1,apellidos2,domicilio,ciudad,telefonoDomicilio,telefonoOficina, telefonoMovil,email, cedula,visible)\n" +
"			values ('"+value.getNombre1()+"','"+value.getNombre2()+"','"+value.getApellidos1()+"','"+value.getApellidos2()+"','"+value.getDomicilio()+"','"+value.getCiudad()+"','"+value.getTelefonoDomicilio()+"','"+value.getTelefonoOficina()+"','"+value.getTelefonoMovil()+"','"+value.getEmail()+"','"+value.getCedula()+"','1');";
                sql2="INSERT INTO usuario(nick, clave,idRol) VALUES ('"+value.getCedula()+"', ENCRYPTBYPASSPHRASE('encriptarClave','"+value.getCedula()+"'),1);";
            }
            //Update
            else
            {
                sql="UPDATE medico SET nombre1='"+value.getNombre1()+"', nombre2='"+value.getNombre2()+"', apellidos1='"+value.getApellidos1()+"', apellidos2='"+value.getApellidos2()+"', domicilio='"+value.getDomicilio()+"', ciudad='"+value.getCiudad()+"', telefonoDomicilio='"+value.getTelefonoDomicilio()+"', telefonoOficina='"+value.getTelefonoOficina()+"', telefonoMovil='"+value.getTelefonoMovil()+"',email='"+value.getEmail()+"',cedula='"+value.getCedula()+"', visible='"+value.getVisible()+"' where id='"+value.getId()+"'";                
                
            }
           
            
            this.conn.execute(sql2);
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
    public boolean delete(int id) {
        this.conn= con_db.open(con_db.MSSQL_SM);        
        try
        {
            this.conn.execute("UPDATE MEDICO SET visible='0' where id='"+id+"';");
            return true;
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return false;            
        }
    }
    @Override
    public int id()
    {
        int idMedico=0;
        this.conn= con_db.open(con_db.MSSQL_SM);        
        try
        {            
             ResultSet rs = this.conn.query("SELECT  TOP 1 id from medico order by id  desc");
             while(rs.next())
             {
                 idMedico=rs.getInt("id");
             }
             return idMedico;
        }
        catch(SQLException ex)
        {
            excepcion=ex.getMessage();
            return idMedico;
        }
    }
    @Override
    public int totalRegistros(int flag, String buscar )
    {
        int totalRegistros=0;
        this.conn= con_db.open(con_db.MSSQL_SM);        
        try
        {            
            ResultSet rs =null;
            if(flag==1)
            {
                String sql="SELECT  COUNT(id) as totalRegistros FROM medico WHERE cedula like '%"+buscar+"%' or nombre1 +' '+ nombre2 +' '+apellidos1  +' ' +apellidos2  like  '%"+buscar+"%' or domicilio like '%"+buscar+"%' or email like  '%"+buscar+"%' and visible=1";
                rs = this.conn.query(sql);
            }
            else
            {
                rs = this.conn.query("SELECT COUNT(id) as totalRegistros from medico where visible=1");
            }
             
             while(rs.next())
             {
                 totalRegistros=rs.getInt("totalRegistros");
             }
             return totalRegistros;
        }
        catch(SQLException ex)
        {
            excepcion=ex.getMessage();
            return totalRegistros;
        }
    }

    @Override
    public int validarCedula(String cedula) {
        int total=0;
        this.conn= con_db.open(con_db.MSSQL_SM);        
        try
        {            
             ResultSet rs = this.conn.query("select count(id) as total from medico where cedula='"+cedula+"' ");
             while(rs.next())
             {
                 total=rs.getInt("total");
             }
             return total;
        }
        catch(SQLException ex)
        {
            excepcion=ex.getMessage();
            return total;
        }
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    

    
    
    
    
}
