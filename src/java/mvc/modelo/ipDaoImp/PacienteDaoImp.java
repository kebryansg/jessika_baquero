/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDaoImp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.ip.Parroquia;
import mvc.modelo.ipDao.PacienteDao;
import test.list_count;

/**
 *
 * @author kebryan
 */
public class PacienteDaoImp implements PacienteDao {

    C_BD conn;

    @Override
    public List<Paciente> list() {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Paciente> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select top 10 * from paciente inner join BD_SM.dbo.historialClinico on idPaciente = paciente.id where estado = '1'");
        try {
            while (rs.next()) {
                Paciente value = new Paciente();
                value.setCedula(rs.getNString("cedula"));
                value.setNombre1(rs.getNString("nombre1"));
                value.setNombre2(rs.getNString("nombre2"));
                value.setApellido1(rs.getNString("apellido1"));
                value.setApellido2(rs.getNString("apellido2"));
                //value.setCiudad(rs.getNString("ciudad"));
                value.setDiscapacidad(rs.getInt("discapacidad"));
                value.setDomicilio(rs.getNString("domicilio"));
                value.setEmail(rs.getNString("email"));
                value.setEstadoCivil(rs.getString("estadoCivil"));
                value.setEtnia(rs.getInt("etnia"));
                value.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                value.setId(rs.getInt("id"));
                value.setIdParroquia(new Parroquia(rs.getInt("idParroquia")));
                value.setImagen(rs.getNString("imagen"));
                //value.setLugarNacimiento(rs.getNString("lugarNacimiento"));
                value.setNacionalidad(rs.getString("nacionalidad"));
                value.setPaisNacimiento(rs.getNString("paisNacimiento"));
                value.setSexo(rs.getString("sexo"));
                value.setTelefonoDomicilio(rs.getNString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getNString("telefonoOficina"));
                value.setNombreContacto(rs.getNString("nombreContacto"));
                value.setMovilContacto(rs.getNString("movilContacto"));
                value.setParentezco(rs.getNString("parentezco"));
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public Paciente edit(int id) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        ResultSet rs = this.conn.query("select * from getPaciente where id = '" + id + "'");
        Paciente value = new Paciente();
        try {
            while (rs.next()) {
                value.setCedula(rs.getString("cedula"));
                value.setNombre1(rs.getString("nombre1"));
                value.setNombre2(rs.getString("nombre2"));
                value.setApellido1(rs.getString("apellido1"));
                value.setApellido2(rs.getString("apellido2"));
                value.setDiscapacidad(rs.getInt("discapacidad"));
                value.setDomicilio(rs.getString("domicilio"));
                value.setEmail(rs.getString("email"));
                value.setEstadoCivil(rs.getString("estadoCivil"));
                value.setEtnia(rs.getInt("etnia"));
                value.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                value.setId(rs.getInt("id"));
                value.setIdParroquia(new Parroquia(rs.getInt("idParroquia")));
                value.setNacionalidad(rs.getString("nacionalidad"));
                value.setPaisNacimiento(rs.getString("paisNacimiento"));
                value.setSexo(rs.getString("sexo"));
                value.setApp(rs.getString("app"));
                value.setApf(rs.getString("apf"));
                value.setObservaciones(rs.getString("observacion"));
                value.setTelefonoDomicilio(rs.getString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getString("telefonoOficina"));
                value.setNombreContacto(rs.getString("nombreContacto"));
                value.setMovilContacto(rs.getString("movilContacto"));
                value.setParentezco(rs.getString("parentezco"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

    @Override
    public boolean save(Paciente value) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        try {

            CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.savePaciente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            call.setInt("id", value.getId());
            call.setString("cedula", value.getCedula());
            call.setString("nombre1", value.getNombre1());
            call.setString("nombre2", value.getNombre2());
            call.setString("apellido1", value.getApellido1());
            call.setString("apellido2", value.getApellido2());
            call.setString("nombres", value.getNombres());
            call.setString("domicilio", value.getDomicilio());
            call.setString("nacionalidad", value.getNacionalidad());
            call.setString("estadocivil", value.getEstadoCivil());
            call.setString("telDomicilio", value.getTelefonoDomicilio());
            call.setString("telOficina", value.getTelefonoOficina());
            call.setString("email", value.getEmail());
            call.setString("sexo", value.getSexo());
            call.setString("paisNac", value.getPaisNacimiento());
            call.setDate("fechaNac", new java.sql.Date(value.getFechaNacimiento().getTime()));
            call.setInt("etnia", value.getEtnia());
            call.setInt("discapacidad", value.getDiscapacidad());
            call.setInt("idParroquia", value.getIdParroquia().getId());
            call.setString("nombreContacto", value.getNombreContacto());
            call.setString("parentezco", value.getParentezco());
            call.setString("movilContacto", value.getMovilContacto());
            call.setString("app", value.getApp());
            call.setString("apf", value.getApf());
            call.setString("observacion", value.getObservaciones());
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
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> list_Filter(String filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        List<Paciente> list = new ArrayList<>();
        String paginacion = (top != -1) ? "OFFSET " + pag + " ROWS FETCH NEXT " + top + " ROWS ONLY;" : "";
        String sql = ("select historialClinico.id as historia , paciente.* from paciente inner join BD_SM.dbo.historialClinico on idPaciente = paciente.id where estado = '1' and (nombre1 like '%" + filter + "%' or nombre2 like '%" + filter + "%' or apellido1 like '%" + filter + "%' or apellido2 like '%" + filter + "%' or cedula like '%" + filter + "%' or historialClinico.id like '%" + filter + "%') order by id " + paginacion);
        //String sql = "EXEC [dbo].[getPacientes] "+ top +", "+ pag +", '"+filter+"'";
        System.out.println(sql);
        ResultSet rs = this.conn.query(sql); //this.conn.query(sql);
        try {
            while (rs.next()) {
                Paciente value = new Paciente();
                value.setId(rs.getInt("id"));
                value.setHistoriaClinica(rs.getInt("historia"));
                value.setCedula(rs.getNString("cedula"));
                value.setNombre1(rs.getNString("nombre1"));
                value.setNombre2(rs.getNString("nombre2"));
                value.setApellido1(rs.getNString("apellido1"));
                value.setApellido2(rs.getNString("apellido2"));
                //value.setCiudad(rs.getNString("ciudad"));
                value.setDomicilio(rs.getNString("domicilio"));
                value.setSexo(rs.getString("sexo"));
                /*value.setDiscapacidad(rs.getInt("discapacidad"));
                value.setEmail(rs.getNString("email"));
                value.setEstadoCivil(rs.getString("estadoCivil"));
                value.setEtnia(rs.getInt("etnia"));
                value.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                value.setIdParroquia(new Parroquia(rs.getInt("idParroquia")));
                value.setImagen(rs.getNString("imagen"));
                value.setLugarNacimiento(rs.getNString("lugarNacimiento"));
                value.setNacionalidad(rs.getString("nacionalidad"));
                value.setPaisNacimiento(rs.getNString("paisNacimiento"));
                value.setTelefonoDomicilio(rs.getNString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getNString("telefonoOficina"));
                value.setNombreContacto(rs.getNString("nombreContacto"));
                value.setMovilContacto(rs.getNString("movilContacto"));
                value.setParentezco(rs.getNString("parentezco"));*/
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public list_count list_count_Filter(String value, String op_filter, int pag, int top) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        list_count l = new list_count();
        List<Paciente> list = new ArrayList<>();
        try {
            CallableStatement call = this.conn.getConexion().prepareCall("{call dbo.getPacientes(?,?,?,?)}");
            call.setInt("trows", top);
            call.setInt("inicio", pag);
            if (op_filter.equals("1")) {
                if (!value.equals("")) {
                    value = test.test.nvar(value);
                }
            }
            call.setString("op_filter", op_filter);
            call.setString("buscar", value);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                l.setTotal(rs.getInt("full_count"));
                Paciente paciente1 = new Paciente();
                paciente1.setId(rs.getInt("id"));
                paciente1.setHistoriaClinica(rs.getInt("historia"));
                paciente1.setCedula(rs.getNString("cedula"));
                paciente1.setNombre1(rs.getNString("nombres"));
                paciente1.setDomicilio(rs.getNString("domicilio"));
                paciente1.setSexo(rs.getString("sexo"));
                list.add(paciente1);
            }
            l.setList(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return l;
    }

    @Override
    public Paciente edit_HC(int hc) {
        this.conn = con_db.open(con_db.MSSQL_IP);
        ResultSet rs = this.conn.query("select p.* from BD_IP.dbo.paciente p\n"
                + "inner join BD_SM.dbo.historialClinico hc on hc.idPaciente = p.id\n"
                + "where hc.id = '" + hc + "'");
        Paciente value = new Paciente();
        try {
            while (rs.next()) {
                value.setCedula(rs.getNString("cedula"));
                value.setNombre1(rs.getNString("nombre1"));
                value.setNombre2(rs.getNString("nombre2"));
                value.setApellido1(rs.getNString("apellido1"));
                value.setApellido2(rs.getNString("apellido2"));
                //value.setCiudad(rs.getNString("ciudad"));
                value.setDiscapacidad(rs.getInt("discapacidad"));
                value.setDomicilio(rs.getNString("domicilio"));
                value.setEmail(rs.getNString("email"));
                value.setEstadoCivil(rs.getString("estadoCivil"));
                value.setEtnia(rs.getInt("etnia"));
                value.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                value.setId(rs.getInt("id"));
                value.setIdParroquia(new Parroquia(rs.getInt("idParroquia")));
                //value.setImagen(rs.getNString("imagen"));
                //value.setLugarNacimiento(rs.getNString("lugarNacimiento"));
                value.setNacionalidad(rs.getString("nacionalidad"));
                value.setPaisNacimiento(rs.getNString("paisNacimiento"));
                value.setSexo(rs.getString("sexo"));
                value.setApp(rs.getString("app"));
                value.setApf(rs.getString("apf"));
                value.setObservaciones(rs.getString("observacion"));
                value.setTelefonoDomicilio(rs.getNString("telefonoDomicilio"));
                value.setTelefonoOficina(rs.getNString("telefonoOficina"));
                value.setNombreContacto(rs.getNString("nombreContacto"));
                value.setMovilContacto(rs.getString("movilContacto"));
                value.setParentezco(rs.getString("parentezco"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return value;
    }

}
