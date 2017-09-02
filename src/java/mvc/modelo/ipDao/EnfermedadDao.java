/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Enfermedad;

/**
 *
 * @author kebryan
 */
public interface EnfermedadDao {
    public List<Enfermedad> list();
    public Enfermedad edit(int id);
    public boolean save(Enfermedad value);
    public boolean delete(int id);
}
