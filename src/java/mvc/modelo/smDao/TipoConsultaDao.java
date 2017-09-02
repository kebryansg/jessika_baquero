/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Metodos;
import mvc.controlador.entidades.sm.TipoConsulta;

/**
 *
 * @author kebryan
 */
public interface TipoConsultaDao {
    public boolean save();
    public TipoConsulta edit(int id);
    public List<TipoConsulta> list();
    public List<Metodos> list_Metodos(int idTipoConsulta);
}
