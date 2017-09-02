/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.sm.Caso;
import mvc.controlador.entidades.sm.EspecialidadEgreso;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.controlador.entidades.sm.Ingresos;
import mvc.controlador.entidades.sm.Medicamentos;
import mvc.controlador.entidades.sm.Medico;
import mvc.controlador.entidades.sm.TipoIngreso;
import mvc.modelo.smDao.IngresosDao;

/**
 *
 * @author Deivi
 */
public class IngresosDaoImp implements IngresosDao {
C_BD conn;
String excepcion="";

    @Override
    public List<Ingresos> listIngresos(int numeroPaginas, int totalRegistro, Date fechaIngreso, Date fechaSalida) {
         List<Ingresos> list = new ArrayList<>();
       
          try {  
              
        Connection conexion;          
        conexion = con_db.open(con_db.MSSQL_SM).getConexion();
        CallableStatement cStmt=null;
        //con el txt buscar
        cStmt = conexion.prepareCall("{call obtenerIngresos(?, ?, ?, ?)}"); 
        
        cStmt.setInt(1, numeroPaginas);    
        cStmt.setInt(2, totalRegistro);  
        cStmt.setDate(3,new java.sql.Date(fechaIngreso.getTime()));  
        cStmt.setDate(4, new java.sql.Date(fechaSalida.getTime()));  
        cStmt.execute();    
        final ResultSet rs = cStmt.getResultSet(); 
            while (rs.next()) {
                Ingresos value = new Ingresos();
                value.setId(rs.getInt("id"));
                Paciente unPaciente= new Paciente(rs.getString("cedula")!=null?rs.getString("cedula"):"-",rs.getString("nombre1"),rs.getString("nombre2"),rs.getString("apellido1"),rs.getString("apellido2"));
                value.setUnPaciente(unPaciente);
                value.setIdTipoIngreso(new TipoIngreso(rs.getInt("idTipoIngreso")));
                value.setIdCaso(new Caso(rs.getInt("idCaso")));
                value.setIdEspecialidadEgreso(new EspecialidadEgreso(rs.getInt("idEspecialidadEgreso"),rs.getString("descripcion")));
                value.setFechaEntrada(rs.getDate("fechaEntrada"));
                value.setFechaSalida(rs.getDate("fechaSalida"));
                value.setHora(rs.getTime("hora"));
                //value.setCondicionEgreso(rs.getObject("condicionEgreso", Types.BIT));
                value.setCondicionEgreso(rs.getInt("condicionEgreso"));
                value.setDefinitivoEgreso(rs.getString("definitivoEgreso"));
                value.setSecundarioEgreso(rs.getString("secundarioEgreso"));
                value.setSecundarioEgreso2(rs.getString("secundarioEgreso2"));
                value.setCausaExterna(rs.getString("causaExterna"));
                value.setCodigoDiagnosticoDefinitivo(rs.getString("codigoDiagnosticoDefinitivo"));
                value.setRegistros(rs.getInt("registros"));
                
                /*.setIdPaciente(rs.getInt("idPaciente"));
                value.setNombre1(rs.getString("nombre1"));
                value.setNombre2(rs.getString("nombre2"));
                value.setApellido1(rs.getString("apellido1"));
                value.setApellido2(rs.getString("apellido2"));               
                value.setCedula(rs.getString("cedula"));   */            
                list.add(value);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            excepcion=ex.getMessage();
        } finally {
            //this.conn.close();
        }
       
        return list;
    }

    @Override
    public int totalIngresos(Date fechaIngreso, Date fechaSalida) {
        int totalRegistros=0;
        this.conn= con_db.open(con_db.MSSQL_IP);        
        try
        {            
            ResultSet rs =null;
            rs = this.conn.query("SELECT count(BD_SM.dbo.ingresos.id) as totalRegistros\n" +
"from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_IP.dbo.paciente.id= BD_SM.dbo.historialClinico.idPaciente inner join\n" +
"BD_SM.dbo.caso on BD_SM.dbo.caso.idHistorialClinico= BD_SM.dbo.historialClinico.id inner join BD_SM.dbo.ingresos on\n" +
"BD_SM.dbo.caso.id= BD_SM.dbo.ingresos.idCaso INNER JOIN BD_SM.dbo.especialidadEgreso on BD_SM.dbo.especialidadEgreso.id= BD_SM.dbo.ingresos.idEspecialidadEgreso\n" +
"where (BD_SM.dbo.ingresos.fechaEntrada  BETWEEN '"+fechaIngreso+"' and '"+fechaSalida+"' \n" +
"or BD_SM.dbo.ingresos.fechaSalida BETWEEN '"+fechaIngreso+"' and '"+fechaSalida+"') and estado=1 ");
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
    public int totalRegistros(int flag, String buscar ) {
        int totalRegistros=0;
        this.conn= con_db.open(con_db.MSSQL_IP);        
        try
        {            
            ResultSet rs =null;
            if(flag==1)
            {
                rs = this.conn.query("select count(BD_SM.dbo.historialClinico.idPaciente) as totalRegistros from  BD_IP.dbo.paciente inner join\n" +
"        BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id \n" +
"        WHERE cedula like '%"+buscar+"%' or nombre1 +' '+ nombre2 +' '+apellido1  +' ' +apellido2  like  '%"+buscar+"%' or domicilio like '%"+buscar+"%' or email like  '%"+buscar+"%'");
            }
            else
            {
                rs = this.conn.query("select count(BD_SM.dbo.historialClinico.idPaciente) as totalRegistros from  BD_IP.dbo.paciente inner join\n" +
"        BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id ");
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
    public List<HistorialClinico> list(int numeroPaginas, int totalRegistro,int bandera, String buscar) {
            List<HistorialClinico> list = new ArrayList<>();

              try {  
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=null;
            //con el txt buscar
            if(bandera==1)
            {   
                cStmt = conexion.prepareCall("{call obtenerPacientesBuscar(?, ?, ?)}");  
                cStmt.setString(3, buscar);  
            }
            // sin el txt buscar
            else
            {
                cStmt = conexion.prepareCall("{call obtenerPacientes(?, ?)}");  
            }
            cStmt.setInt(1, numeroPaginas);    
            cStmt.setInt(2, totalRegistro);  
            cStmt.execute();    
                 final ResultSet rs = cStmt.getResultSet(); 
            //ResultSet rs = this.conn.query("SELECT * FROM MEDICO");

                while (rs.next()) {
                    HistorialClinico value = new HistorialClinico();
                    value.setId(rs.getInt("id"));
                    value.setIdPaciente(rs.getInt("idPaciente"));
                    value.setNombre1(rs.getString("nombre1"));
                    value.setNombre2(rs.getString("nombre2"));
                    value.setApellido1(rs.getString("apellido1"));
                    value.setApellido2(rs.getString("apellido2"));               
                    value.setCedula(rs.getString("cedula"));  
                    value.setRegistros(rs.getInt("registros"));   
                    list.add(value);

                }
            } catch (SQLException ex) {
                excepcion=ex.getMessage();
                System.out.println(ex.getMessage());
            } finally {
                //this.conn.close();
            }

            return list;
    }

    @Override
    public boolean Save(Ingresos value, Integer idHistoria) {
        try {  
            //0 guardar
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=null;
                
            if(value.getId()==0)
            {
                 cStmt= conexion.prepareCall("{call Insert_IngresosHospital(?, ?, ?, ? , ?, ?, ? , ?, ?, ? , ?, ?, ?, ?)}");    
                 cStmt.setInt(1, idHistoria);
            }
            //editar
            else
            {
                cStmt= conexion.prepareCall("{call UpdateIngresos(?, ?, ?, ? , ?, ?, ? , ?, ?, ? , ?, ?, ?, ?, ?)}");    
                cStmt.setInt(1, value.getId());
                cStmt.setInt(15, value.getIdCaso().getId());
            }
            cStmt.setDate(2,  new java.sql.Date(value.getFechaEntrada().getTime()));
                cStmt.setInt(3, value.getIdTipoIngreso().getId());
                cStmt.setInt(4,value.getIdEspecialidadEgreso().getId());
                cStmt.setDate(5, new java.sql.Date(value.getFechaEntrada().getTime()));
                cStmt.setDate(6, new java.sql.Date(value.getFechaSalida().getTime()));
                cStmt.setTime(7,new java.sql.Time(value.getHora().getTime()));
                cStmt.setObject(8, value.getSos(),Types.BIT );
                cStmt.setInt(9, value.getCondicionEgreso());
                cStmt.setString(10, value.getDefinitivoEgreso());
                cStmt.setString(11, value.getSecundarioEgreso());
                cStmt.setString(12, value.getSecundarioEgreso2());
                cStmt.setString(13, value.getCausaExterna());
                cStmt.setString(14, value.getCodigoDiagnosticoDefinitivo());
                cStmt.execute();    
        //final ResultSet rs = cStmt.getResultSet(); 
        //ResultSet rs = this.conn.query("SELECT * FROM MEDICO");
        return true;
        } catch (SQLException ex) {
            excepcion=ex.getMessage();
            System.out.println(ex.getMessage());
            return false; 
        } 
    }

    
    public HistorialClinico historia(String cedula) {
             this.conn = con_db.open(con_db.MSSQL_SM);
        ResultSet rs = this.conn.query("select BD_SM.dbo.historialClinico.id, BD_SM.dbo.historialClinico.idPaciente, BD_IP.dbo.paciente.nombre1+ ' '+BD_IP.dbo.paciente.nombre2 + ' '+BD_IP.dbo.paciente.apellido1+' '+BD_IP.dbo.paciente.apellido2   as Paciente from  BD_IP.dbo.paciente inner join \n" +
        "BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id where BD_IP.dbo.paciente.cedula='"+cedula+"'");
        HistorialClinico value = new HistorialClinico();
        try {
            while (rs.next()) {
                value.setId(rs.getInt("id"));
                value.setIdPaciente(rs.getInt("idPaciente"));
                value.setPaciente(rs.getString("Paciente"));
                               
            }
        } catch (SQLException ex) {
            excepcion=ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean Delete(int id) {
        try
        {
        
        this.conn= con_db.open(con_db.MSSQL_SM);    
         
         this.conn.query("update ingresos set estado=0 where id="+id+"");
         return true;
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return false;
        }

    }

    @Override
    public boolean DeleteMedicamento(int id) {
        try
        {
        
        this.conn= con_db.open(con_db.MSSQL_SM);    
         
         this.conn.query("update medicamentos set estado=0 where id="+id+"");
         return true;
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return false;
        }
        
    }

    @Override
    public boolean guardarMedicamento(Medicamentos value) {
        String sql="";
        this.conn= con_db.open(con_db.MSSQL_SM);
        try 
        {   
            //Insert
           if(value.getId()==0)
            {
                sql="INSERT INTO medicamentos(fecha, hora, notasEvolucion, prescripcionMedica, idIngresos, estado) values('"+value.getFecha()+"','"+value.getHora()+"','"+value.getNotasEvolucion()+"','"+value.getPrescripcionMedica()+"','"+value.getIdIngresos().getId()+"','1')";
            }
            //Update
            else
            {
                sql="update medicamentos set notasEvolucion='"+value.getNotasEvolucion()+"', fecha='"+value.getFecha()+"', hora='"+value.getHora()+"', prescripcionMedica='"+value.getPrescripcionMedica()+"' where id='"+value.getId()+"'";
            }
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
    public List<Medicamentos> list(int idIngreso) {
        List<Medicamentos> list = new ArrayList<>();

              try {  
                  
                this.conn= con_db.open(con_db.MSSQL_SM); 
                ResultSet rs = this.conn.query("SELECT * FROM MEDICAMENTOS where idIngresos='"+idIngreso+"' and estado=1");
                while (rs.next()) {
                    Medicamentos value = new Medicamentos(rs.getInt("id"),rs.getDate("fecha"),rs.getTime("hora"),rs.getString("notasEvolucion"),rs.getString("prescripcionMedica"), new Ingresos(rs.getInt("idIngresos")));                    
                    value.setEstado(rs.getInt("estado"));
                    Ingresos ing= new Ingresos();
                    ing.setRegistros(0);
                    value.setIdIngresos(ing);
                    list.add(value);
                }
            } catch (SQLException ex) {
                excepcion=ex.getMessage();
                System.out.println(ex.getMessage());
            } finally {
                //this.conn.close();
            }

            return list;
    }

    @Override
    public List<Ingresos> listIngresos() {
        List<Ingresos> list = new ArrayList<>();

              try {  
                  
                this.conn= con_db.open(con_db.MSSQL_SM); 
                ResultSet rs = this.conn.query("SELECT TOP 5 BD_SM.dbo.ingresos.id,  BD_IP.dbo.paciente.cedula, BD_IP.dbo.paciente.nombre1, BD_IP.dbo.paciente.nombre2,\n" +
"			BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2,  BD_SM.dbo.ingresos.idTipoIngreso,\n" +
"			BD_SM.dbo.ingresos.idCaso,BD_SM.dbo.ingresos.idEspecialidadEgreso, BD_SM.dbo.especialidadEgreso.descripcion, BD_SM.dbo.ingresos.fechaEntrada, BD_SM.dbo.ingresos.fechaSalida,\n" +
"			BD_SM.dbo.ingresos.hora,BD_SM.dbo.ingresos.sos, BD_SM.dbo.ingresos.condicionEgreso,BD_SM.dbo.ingresos.definitivoEgreso,BD_SM.dbo.ingresos.secundarioEgreso,\n" +
"			BD_SM.dbo.ingresos.secundarioEgreso2, BD_SM.dbo.ingresos.causaExterna,BD_SM.dbo.ingresos.codigoDiagnosticoDefinitivo\n" +
"			from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_IP.dbo.paciente.id= BD_SM.dbo.historialClinico.idPaciente inner join\n" +
"			BD_SM.dbo.caso on BD_SM.dbo.caso.idHistorialClinico= BD_SM.dbo.historialClinico.id inner join BD_SM.dbo.ingresos on\n" +
"			BD_SM.dbo.caso.id= BD_SM.dbo.ingresos.idCaso INNER JOIN BD_SM.dbo.especialidadEgreso on BD_SM.dbo.especialidadEgreso.id= BD_SM.dbo.ingresos.idEspecialidadEgreso\n" +
"			where BD_SM.dbo.ingresos.estado=1 order by id desc");
                while (rs.next()) {
                    Ingresos value = new Ingresos();
                value.setId(rs.getInt("id"));
                
                Paciente unPaciente= new Paciente(rs.getString("cedula")!=null?rs.getString("cedula"):"-",rs.getString("nombre1"),rs.getString("nombre2"),rs.getString("apellido1"),rs.getString("apellido2"));
                value.setUnPaciente(unPaciente);
                value.setIdTipoIngreso(new TipoIngreso(rs.getInt("idTipoIngreso")));
                value.setIdCaso(new Caso(rs.getInt("idCaso")));
                value.setIdEspecialidadEgreso(new EspecialidadEgreso(rs.getInt("idEspecialidadEgreso"),rs.getString("descripcion")));
                value.setFechaEntrada(rs.getDate("fechaEntrada"));
                value.setFechaSalida(rs.getDate("fechaSalida"));
                value.setHora(rs.getTime("hora"));
                //value.setCondicionEgreso(rs.getObject("condicionEgreso", Types.BIT));
                value.setCondicionEgreso(rs.getInt("condicionEgreso"));
                value.setDefinitivoEgreso(rs.getString("definitivoEgreso"));
                value.setSecundarioEgreso(rs.getString("secundarioEgreso"));
                value.setSecundarioEgreso2(rs.getString("secundarioEgreso2"));
                value.setCausaExterna(rs.getString("causaExterna"));
                value.setCodigoDiagnosticoDefinitivo(rs.getString("codigoDiagnosticoDefinitivo"));
                
                
                /*.setIdPaciente(rs.getInt("idPaciente"));
                value.setNombre1(rs.getString("nombre1"));
                value.setNombre2(rs.getString("nombre2"));
                value.setApellido1(rs.getString("apellido1"));
                value.setApellido2(rs.getString("apellido2"));               
                value.setCedula(rs.getString("cedula"));   */            
                list.add(value);
                }
            } catch (SQLException ex) {
                excepcion=ex.getMessage();
                System.out.println(ex.getMessage());
            } finally {
                //this.conn.close();
            }

            return list;
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    
    
    
    
    
}
