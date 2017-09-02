/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Establecimiento;

/**
 *
 * @author Deivi
 */
public interface EstablecimientoDao {
    public boolean save(Establecimiento value);
    public List<Establecimiento> list();
    public boolean updateLogo(String name);
     public String getExcepcion();
}
