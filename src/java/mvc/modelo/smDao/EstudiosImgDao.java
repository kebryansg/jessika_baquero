
package mvc.modelo.smDao;

import mvc.controlador.entidades.sm.EstudioImagen;
import test.list_count;


public interface EstudiosImgDao {
    public EstudioImagen edit(int id);
    public list_count list_det(int idTipoEstudiosImg, int idEstudiosImg,String filter, int pag, int top);
}
