
package mvc.modelo.smDao;

import java.util.List;
import mvc.controlador.entidades.sm.Causa;

public interface CausaDao {
    public Causa edit(int id);
    public boolean save(Causa value);
    public List<Causa> list_filter(String value);
}
