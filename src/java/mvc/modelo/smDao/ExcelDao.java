/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDao;

import java.util.Date;

/**
 *
 * @author Deivi
 */
public interface ExcelDao {
    public String generarExcelIngresos(Date fecha, String path, String nombreEstablecimiento);
    public String generarExcelCamas(Date fecha,String path);
    public String generarExcelCamasIndividual(Date fecha,String path);
     public String getExcepcion();
}
