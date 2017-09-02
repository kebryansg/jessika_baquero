/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.MedicoEspecialidad;

/**
 *
 * @author Deivi
 */
public interface MedicoEspecialidadDao {
    public List<MedicoEspecialidad> list(int id);
    public MedicoEspecialidad edit(int id);
    public boolean save(MedicoEspecialidad value);
    public boolean delete (int id);    
     public String getExcepcion();
    
}
