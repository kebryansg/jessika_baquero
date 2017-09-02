/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Canton;

/**
 *
 * @author kebryan
 */
public interface CantonDao {
    public List<Canton> list(int idProvincia);
    public Canton edit(int id);
    public boolean save(Canton value);
    public boolean delete(int id);
}
