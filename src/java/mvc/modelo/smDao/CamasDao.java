/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;
import mvc.controlador.entidades.sm.Camas;
import java.util.List;
/**
 *
 * @author Deivi
 */
public interface CamasDao {
    public boolean save(Camas value);
    public List<Camas> list();
     public String getExcepcion();
}
