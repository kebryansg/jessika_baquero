/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Parientes;

/**
 *
 * @author kebryan
 */
public interface ParientesDao {
    public List<Parientes> list();
    public Parientes edit(int id);
    public boolean save(Parientes value);
    public boolean delete(int id);
}
