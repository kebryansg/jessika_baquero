/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Medico;

/**
 *
 * @author Byron
 */
public interface MedicoDao {
    public List<Medico> list(int numeroPaginas, int totalRegistro,int bandera, String buscar);
    public Medico edit(int id);
    public boolean save(Medico value);
    public boolean delete (int id);
    public int id();
    public int totalRegistros(int flag, String buscar );
    public int validarCedula(String cedula);
    public String getExcepcion();
}
