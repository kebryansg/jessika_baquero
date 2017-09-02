package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Metodos;
import mvc.modelo.smDao.MetodosDao;

public class MetodosDaoImp  implements MetodosDao
{
    C_BD conn;

    @Override
    public Metodos edit_detMetodos(int id) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = ("SELECT * FROM detallesMetodos WHERE id = '" + id + "'");
        System.out.println(sql);
        Metodos metodos = new Metodos();
        ResultSet rs = this.conn.query(sql);
        try {
            while (rs.next()) {
                metodos.setId(rs.getInt("id"));
                metodos.setDescripcion(rs.getString("descripcion").toUpperCase());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conn.close();
        }
        return metodos;
    }
    
}
