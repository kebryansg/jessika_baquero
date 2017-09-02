/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Parroquia;

/**
 *
 * @author kebryan
 */
public interface ParroquiaDao {
    public List<Parroquia> list(int idCanton);
    public Parroquia edit(int id);
    public String detParroquia (int id);
    public boolean save(Parroquia value);
    public boolean delete(int id);
}
