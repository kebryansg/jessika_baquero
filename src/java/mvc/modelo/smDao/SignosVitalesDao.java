/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import mvc.controlador.entidades.sm.SignosVitales;

/**
 *
 * @author kebryan
 */
public interface SignosVitalesDao {
    public boolean save(SignosVitales value);
    public SignosVitales editar(int idConsulta);
}
