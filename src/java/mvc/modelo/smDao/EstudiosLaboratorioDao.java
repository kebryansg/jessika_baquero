
package mvc.modelo.smDao;

import java.util.List;
//import mvc.controlador.entidades.sm.DetalleEstudiosLabs;
import mvc.controlador.entidades.sm.EstudiosLaboratorio;
import test.list_count;

public interface EstudiosLaboratorioDao {
    public List<EstudiosLaboratorio> list();
    public List<EstudiosLaboratorio> list(int idConsulta);
    public list_count list_Det(int value,String filter, int pag, int top);
    public EstudiosLaboratorio edit(int value);
}
