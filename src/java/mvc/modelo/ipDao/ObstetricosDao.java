/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Obstetricos;

/**
 *
 * @author kebryan
 */
public interface ObstetricosDao {
    public List<Obstetricos> list();
    public Obstetricos edit(int id);
    public Obstetricos edit_idPaciente(int idPaciente);
    public boolean save(Obstetricos value);
    public boolean delete(int id);
}
