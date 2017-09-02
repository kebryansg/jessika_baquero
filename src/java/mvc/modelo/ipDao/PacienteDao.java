/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.ipDao;

import java.util.List;
import mvc.controlador.entidades.ip.Paciente;
import test.list_count;

/**
 *
 * @author kebryan
 */
public interface PacienteDao {
    public List<Paciente> list();
    public List<Paciente> list_Filter(String value,int pag,int top);
    public list_count list_count_Filter(String value,String op_filter,int pag,int top);
    public Paciente edit(int id);
    public Paciente edit_HC(int hc);
    public boolean save(Paciente value);
    public boolean delete(int id);
}
