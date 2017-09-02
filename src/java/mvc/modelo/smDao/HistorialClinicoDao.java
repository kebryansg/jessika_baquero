/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.HistorialClinico;

/**
 *
 * @author kebryan
 */
public interface HistorialClinicoDao {
    public List<HistorialClinico> list();
    public HistorialClinico edit(int id);
    public HistorialClinico edit_Paciente(int idPaciente);
    public HistorialClinico edit_Paciente(String opcion, String param);
    public boolean save(HistorialClinico value);
    public boolean delete(int id);
}
