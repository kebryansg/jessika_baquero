/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.ParienteEnfermedadPaciente;

/**
 *
 * @author kebryan
 */
public interface ParienteEnfermedadPacienteDao {
    public List<ParienteEnfermedadPaciente> list();
    public List<ParienteEnfermedadPaciente> list_Paciente(int idPaciente);
    public ParienteEnfermedadPaciente edit(int id);
    public boolean save(ParienteEnfermedadPaciente value);
    public boolean delete(int id);
    
}
