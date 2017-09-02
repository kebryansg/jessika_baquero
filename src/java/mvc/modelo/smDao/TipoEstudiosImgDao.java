
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.EstudioImagen;
import mvc.controlador.entidades.sm.TipoEstudioImg;

public interface TipoEstudiosImgDao {
    public List<TipoEstudioImg> list();
    public TipoEstudioImg edit(int id);
    public List<EstudioImagen> list_estI(int idTipoEstudiosImg);
}
