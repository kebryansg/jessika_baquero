/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.ConsultaEstudiosImagen;

/**
 *
 * @author kebryan
 */
public interface ConsultaEstudiosImagenDao {
    public boolean save(ConsultaEstudiosImagen value);
    public ConsultaEstudiosImagen edit(int id);
    public List<ConsultaEstudiosImagen> list(int idConsulta);
}
