/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.sql.Date;
import java.util.List;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.controlador.entidades.sm.Ingresos;
import mvc.controlador.entidades.sm.Medicamentos;


/**
 *
 * @author Deivi
 */
public interface IngresosDao {
    public HistorialClinico historia(String cedula);
    public boolean Save(Ingresos value, Integer idHistoria);
    public int totalRegistros(int flag, String buscar);
    public List<HistorialClinico> list(int numeroPaginas, int totalRegistro, int bandera, String buscar) ;
    public List<Ingresos> listIngresos(int numeroPaginas, int totalRegistro,Date fechaIngreso, Date fechaSalida) ;
    public int totalIngresos(Date fechaIngreso, Date fechaSalida);
    public boolean Delete(int id);
    public boolean DeleteMedicamento(int id);
    public boolean guardarMedicamento(Medicamentos value);
    public List<Medicamentos> list(int idRegistro);
    public List<Ingresos> listIngresos();
     public String getExcepcion();
    
    
    
    
}
