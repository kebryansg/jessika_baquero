package mvc.modelo.smDao;

import java.util.Date;
//import java.util.List;
import mvc.controlador.entidades.sm.Consulta;
import test.list_count;

public interface ConsultaDao {

    public list_count listConsultas(Date fechaI, Date fechaF,int opFecha, int idHC,String idsEspecialidad,int idTipoConsulta, int tops, int pag, String filter);

    public list_count listConsultas(Date fecha, int opFecha, int idHC,String idsEspecialidad,int idTipoConsulta, int tops, int pag, String filter);
    
    public list_count listConsultas(int idHC,String idsEspecialidad,int idTipoConsulta, int tops, int pag, String filter);

    public boolean save(Consulta value);
    
    public boolean save_edit(Consulta value);

    public Consulta edit(int id);
}
