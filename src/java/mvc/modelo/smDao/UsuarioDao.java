/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Medico;
import mvc.controlador.entidades.sm.Rol;
import mvc.controlador.entidades.sm.Usuario;



/**
 *
 * @author Deivi
 */
public interface UsuarioDao {
    //public int Login(String usuario, String clave);
    public Usuario Login(String usuario, String clave,int rol);
    public List<Rol> list_rol();
    public Medico getMedico();
    public int getIdRol();
    public int getId();
    
}
