/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.ConsultaEstudiosLabs;

/**
 *
 * @author kebryan
 */
public interface ConsultaEstudiosLabsDao {
    public boolean save(ConsultaEstudiosLabs value);
    public ConsultaEstudiosLabs edit(int id);
    public List<ConsultaEstudiosLabs> list(int idConsulta);
}
