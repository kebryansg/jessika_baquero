/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Provincia;

/**
 *
 * @author kebryan
 */
public interface ProvinciaDao {
    public List<Provincia> list();
    public Provincia edit(int id);
    public boolean save(Provincia value);
    public boolean delete(int id);
}
