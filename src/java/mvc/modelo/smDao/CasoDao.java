/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Caso;
import mvc.controlador.entidades.sm.Consulta;
import test.list_count;

/**
 *
 * @author kebryan
 */
public interface CasoDao {
    public boolean save(Caso value);
    public Caso edit(int id);
    public list_count listConsulta(int idHistoriaC, String filter, int pag, int top);
    public List<Consulta> listDetConsulta(int idCaso);
}
