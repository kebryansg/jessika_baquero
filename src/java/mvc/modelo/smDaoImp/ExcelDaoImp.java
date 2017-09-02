/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import mvc.modelo.smDao.ExcelDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Excel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import mvc.controlador.C_BD;
import mvc.controlador.entidades.sm.Establecimiento;
import mvc.modelo.smDao.EstablecimientoDao;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 *
 * @author Deivi
 */
public class ExcelDaoImp implements ExcelDao {
C_BD conn;
String excepcion="";
int c=1;
    @Override
    public String generarExcelIngresos(Date fecha,String path,String nombreEstablecimiento) {
        try
        {
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=conexion.prepareCall("{call obtenerIngresosMensuales(?)}"); 
            cStmt.setDate(1, new java.sql.Date(fecha.getTime()));
            cStmt.execute();    
            final ResultSet rs = cStmt.getResultSet(); 
            int r=5;            
            XSSFWorkbook wb = new XSSFWorkbook(path+"plantillaIngresosMensuales2017.xlsx");
            XSSFSheet sheet = wb.getSheetAt(0);
            
            int c=5;
            XSSFCell cell=null;
            while(rs.next())
            //for (int r=5;r < 15; r++ )
            {
                XSSFRow row = sheet.getRow(r);
                //for (int c=5;c < 15; c++ )
                {
                    //MES_RECOLECCIÓN
                     cell= row.getCell(5);                     
                     cell.setCellValue(rs.getInt("MesRecoleccion"));
                     //No. HISTORIA CLÍNICA
                     cell= row.getCell(6);
                     cell.setCellValue(rs.getInt("NHistoriaClinica"));
                     //No. DE ARCHIVO
                     cell= row.getCell(7);
                     cell.setCellValue(rs.getInt("NoArchivo"));
                     //No. CÉDULA DE IDENTIDAD O PASAPORTE
                     cell= row.getCell(8);
                     cell.setCellValue(rs.getString("cedula"));
                     //PRIMER NOMBRE
                     cell= row.getCell(9);
                     cell.setCellValue(rs.getString("nombre1"));
                     //SEGUNDO NOMBRE
                     cell= row.getCell(10);
                     cell.setCellValue(rs.getString("nombre2"));
                     //PRIMER APELLIDO
                     cell= row.getCell(11);
                     cell.setCellValue(rs.getString("apellido1"));
                     //SEGUNDO APELLIDO
                     cell= row.getCell(12);
                     cell.setCellValue(rs.getString("apellido2"));
                     //NACIONALIDAD
                     cell= row.getCell(13);
                     cell.setCellValue(rs.getInt("Nacionalidad"));
                     //Indique el País en caso de escoger Nacionalidad, opción 2
                     cell= row.getCell(14);
                     cell.setCellValue(rs.getString("Pais"));
                     //SEXO
                     cell= row.getCell(16);
                     cell.setCellValue(rs.getInt("sexo"));
                     //"Fecha Nacimiento AÑO AAAA"
                     cell= row.getCell(17);
                     cell.setCellValue(rs.getInt("AñoNacimiento"));
                     //"FECHA NACIMIENTO MES MM"
                     cell= row.getCell(18);
                     cell.setCellValue(rs.getInt("MesNacimiento"));
                     //FECHA NACIMIENTO "DÍA DD"
                     cell= row.getCell(19);
                     cell.setCellValue(rs.getInt("DiaNacimiento"));
                     //"Condición de la edad
                     cell= row.getCell(21);
                     cell.setCellValue(rs.getInt("condicionEdad"));
                     //Edad del paciente
                     cell= row.getCell(22);
                     cell.setCellValue(rs.getInt("EdadCunplidaAlIngreso"));
                     //COMO SE IDENTIFICA (…) SEGÚN SU CULTURA Y COSTUMBRES
                     cell= row.getCell(23);
                     cell.setCellValue(rs.getInt("etnia"));
                     //"TIENE ALGUNA DISCAPACIDAD PERMANENTE (Al momento del ingreso)"
                     cell= row.getCell(24);
                     cell.setCellValue(rs.getInt("discapacidad"));                     
                     //Direccion PROVINCIA
                     cell= row.getCell(25);
                     cell.setCellValue(rs.getString("Provincia"));                     
                     //CANTÓN
                     cell= row.getCell(26);
                     cell.setCellValue(rs.getString("Canton"));                     
                     //PARROQUIA
                     cell= row.getCell(27);
                     cell.setCellValue(rs.getString("Parroquia"));                     
                     //DIRECCIÓN
                     cell= row.getCell(28);
                     cell.setCellValue(rs.getString("direccion"));                     
                     //"AÑO AAAA" INGRESO
                     cell= row.getCell(30);
                     cell.setCellValue(rs.getInt("AñoIngreso"));                     
                     //"MES MM" INGRESO
                     cell= row.getCell(31);
                     cell.setCellValue(rs.getInt("MesIngreso"));                     
                     //"DÍA DD" INGRESO
                     cell= row.getCell(32);
                     cell.setCellValue(rs.getInt("DiaIngreso"));                     
                     //"AÑO AAAA" EGRESO
                     cell= row.getCell(34);
                     cell.setCellValue(rs.getInt("AñoEgreso"));                     
                     //"MES MM" EGRESO
                     cell= row.getCell(35);
                     cell.setCellValue(rs.getInt("MesEgreso"));                     
                     //"DÍA DD" EGRESO
                     cell= row.getCell(36);
                     cell.setCellValue(rs.getInt("DiaEgreso"));                     
                     //DÍAS DE ESTADA
                     cell= row.getCell(38);
                     cell.setCellValue(rs.getInt("DiasEstada"));                     
                     //CONDICIÓN AL EGRESO
                     cell= row.getCell(39);
                     cell.setCellValue(rs.getInt("condicionEgreso"));                     
                     //ESPECIALIDAD DEL EGRESO
                     cell= row.getCell(40);
                     cell.setCellValue(rs.getInt("idEspecialidadEgreso"));                     
                     //DEFINITIVO DE EGRESO
                     cell= row.getCell(41);
                     cell.setCellValue(rs.getString("definitivoEgreso"));                     
                     //1. SECUNDARIOS DE EGRESO
                     cell= row.getCell(42);
                     cell.setCellValue(rs.getString("secundarioEgreso"));                     
                     //2. SECUNDARIOS DE EGRESO
                     cell= row.getCell(43);
                     cell.setCellValue(rs.getString("secundarioEgreso2"));                     
                     //3. CAUSA EXTERNA
                     cell= row.getCell(44);
                     cell.setCellValue(rs.getString("causaExterna"));                     
                    //"CÓDIGO CIE - 10  DIAGNÓSTICO DEFINITIVO"
                    cell= row.getCell(45);
                    cell.setCellValue(rs.getString("codigoDiagnosticoDefinitivo"));
                }
                r++;
            }
            //refresco las formulas del libro
             XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
            
            SimpleDateFormat dateFormat = null;
            dateFormat =new SimpleDateFormat("yyyy");
            int anio=Integer.parseInt(dateFormat.format(fecha));
            dateFormat =new SimpleDateFormat("MM");
            int mes=Integer.parseInt(dateFormat.format(fecha));
            String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciemrbre"};            
            EstablecimientoDao establecimiento= new EstablecimientoDaoImp();
            List<Establecimiento> list= establecimiento.list();              
            for (Establecimiento value : list)
            {
                nombreEstablecimiento=value.getNombre();
            }
            
            FileOutputStream fileOut = new FileOutputStream(path+meses[mes-1]+" del "+anio+" "+nombreEstablecimiento+".xlsx");
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            return meses[mes-1]+" del "+anio+" "+nombreEstablecimiento+".xlsx";
           
            
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return "";
            
            
        }
        
    }

    @Override
    public String generarExcelCamas(Date fecha, String path) {
        try
        {
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=conexion.prepareCall("{call obtenerCamasMensuales(?)}"); 
            cStmt.setDate(1, new java.sql.Date(fecha.getTime()));
            cStmt.execute();    
            final ResultSet rs = cStmt.getResultSet(); 
            int r=5;
            File f= new File(path+"Formulario_digital_Camas_2017.xlsx");
            FileInputStream fileInputStream = new FileInputStream(f);
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            XSSFRow row;
            XSSFCell cell=null;
            //Calendar cal = new GregorianCalendar.cal(); 
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(fecha);
            // Get the number of days in that month 
            int totalDiasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
            while(rs.next())            
            {
                
                //Encabezado del excel 
                //Nombre establecimiento
                row = sheet.getRow(2);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("nombre"));
                //provincia
                row = sheet.getRow(3);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("provincia"));
                //canton
                row = sheet.getRow(4);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("canton"));
                //parroquia
                row = sheet.getRow(5);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("parroquia"));
                //direccion
                row = sheet.getRow(6);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("direccion"));
                //encargado
                row = sheet.getRow(7);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("encargado"));
                //encargado
                row = sheet.getRow(7);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("encargado"));                
                //telefono - email
                row = sheet.getRow(8);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("telefono")+"-"+rs.getString("email"));
                int camas=(5+(6*(cal.get(Calendar.MONTH))));
                int cRs=10;
                int i=17;
                int individual=-1,doble=-1,total;
                int totalCamas=0,totalIndividual=0,totalDoble=0;
                //camas 
                while(i<29)                
                {
                    //par
                    if(camas%2==0)
                        doble=rs.getInt(cRs);
                    else 
                        individual=rs.getInt(cRs);
                    row = sheet.getRow(i);
                    cell= row.getCell(camas);                     
                    cell.setCellValue(rs.getInt(cRs));                    
                    camas++;                    
                    if(camas==(5+(6*(cal.get(Calendar.MONTH))))+2)
                    {
                        totalIndividual=totalIndividual+individual;
                        totalDoble=totalDoble+doble;
                        totalCamas=totalCamas+(individual+doble);
                        camas=5+(6*(cal.get(Calendar.MONTH)));
                        cell= row.getCell(camas-1);
                        cell.setCellValue(individual+doble);   
                        i++;
                    } 
                    cRs++;
                } 
                camas=(5+(6*(cal.get(Calendar.MONTH))));
                
                //dias pacientes            
                row = sheet.getRow(37);
                cell= row.getCell(camas-1);
                cell.setCellValue(rs.getInt(36));
                //dias pacientes                            
                cell= row.getCell(camas);
                cell.setCellValue(rs.getInt(36));
                //dias pacientes                            
                cell= row.getCell(camas+1);
                cell.setCellValue(rs.getInt(36));
                
                 //dias camas
                row = sheet.getRow(38);
                cell= row.getCell(camas-1);
                cell.setCellValue(totalCamas*totalDiasMes);
                //dias camas
                cell= row.getCell(camas);
                cell.setCellValue(totalIndividual*totalDiasMes);
                //dias camas
                cell= row.getCell(camas+1);
                cell.setCellValue(totalDoble*totalDiasMes);
                
            }
            //refresco las formulas del libro
             XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);    
             File excelFileName= new File(path+"Formulario_digital_Camas_2017.xlsx");
            //FileOutputStream fileOut = new FileOutputStream(path+"camasGenerado.xlsx");            
            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            return "Formulario_digital_Camas_2017.xlsx";
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return "";
        }
    }

    @Override
    public String generarExcelCamasIndividual(Date fecha, String path) {
        try
        {
            Connection conexion;          
            conexion = con_db.open(con_db.MSSQL_SM).getConexion();
            CallableStatement cStmt=conexion.prepareCall("{call obtenerCamasMensuales(?)}"); 
            cStmt.setDate(1, new java.sql.Date(fecha.getTime()));
            cStmt.execute();    
            final ResultSet rs = cStmt.getResultSet(); 
            int r=5;
             XSSFWorkbook wb = new XSSFWorkbook(path+"Formulario_digital_Camas_ind_2017.xlsx");
            
            XSSFSheet sheet = wb.getSheetAt(0);
            
            XSSFRow row;
            XSSFCell cell=null;
            //Calendar cal = new GregorianCalendar.cal(); 
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(fecha);
            // Get the number of days in that month 
            int totalDiasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
            while(rs.next())            
            {
                
                //Encabezado del excel 
                //Nombre establecimiento
                row = sheet.getRow(2);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("nombre"));
                //provincia
                row = sheet.getRow(3);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("provincia"));
                //canton
                row = sheet.getRow(4);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("canton"));
                //parroquia
                row = sheet.getRow(5);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("parroquia"));
                //direccion
                row = sheet.getRow(6);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("direccion"));
                //encargado
                row = sheet.getRow(7);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("encargado"));
                //encargado
                row = sheet.getRow(7);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("encargado"));                
                //telefono - email
                row = sheet.getRow(8);
                cell= row.getCell(4);                     
                cell.setCellValue(rs.getString("telefono")+"-"+rs.getString("email"));
                int camas=(5+(6*(cal.get(Calendar.MONTH))));
                int cRs=10;
                int i=17;
                int individual=-1,doble=-1,total;
                int totalCamas=0,totalIndividual=0,totalDoble=0;
                //camas 
                while(i<29)                
                {
                    //par
                    if(camas%2==0)
                        doble=rs.getInt(cRs);
                    else 
                        individual=rs.getInt(cRs);
                    row = sheet.getRow(i);
                    cell= row.getCell(camas);                     
                    cell.setCellValue(rs.getInt(cRs));                    
                    camas++;                    
                    if(camas==(5+(6*(cal.get(Calendar.MONTH))))+2)
                    {
                        totalIndividual=totalIndividual+individual;
                        totalDoble=totalDoble+doble;
                        totalCamas=totalCamas+(individual+doble);
                        camas=5+(6*(cal.get(Calendar.MONTH)));
                        cell= row.getCell(camas-1);
                        cell.setCellValue(individual+doble);   
                        i++;
                    } 
                    cRs++;
                } 
                camas=(5+(6*(cal.get(Calendar.MONTH))));
                
                //dias pacientes            
                row = sheet.getRow(37);
                cell= row.getCell(camas-1);
                cell.setCellValue(rs.getInt(36));
                //dias pacientes                            
                cell= row.getCell(camas);
                cell.setCellValue(rs.getInt(36));
                //dias pacientes                            
                cell= row.getCell(camas+1);
                cell.setCellValue(rs.getInt(36));
                
                 //dias camas
                row = sheet.getRow(38);
                cell= row.getCell(camas-1);
                cell.setCellValue(totalCamas*totalDiasMes);
                //dias camas
                cell= row.getCell(camas);
                cell.setCellValue(totalIndividual*totalDiasMes);
                //dias camas
                cell= row.getCell(camas+1);
                cell.setCellValue(totalDoble*totalDiasMes);
                
            }
            //refresco las formulas del libro
             XSSFFormulaEvaluator.evaluateAllFormulaCells(wb); 
             SimpleDateFormat dateFormat = null;
            dateFormat =new SimpleDateFormat("yyyy");
            int anio=Integer.parseInt(dateFormat.format(fecha));
            dateFormat =new SimpleDateFormat("MM");
            int mes=Integer.parseInt(dateFormat.format(fecha));
            String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciemrbre"};            
            EstablecimientoDao establecimiento= new EstablecimientoDaoImp();
            List<Establecimiento> list= establecimiento.list();    
            String nombreEstablecimiento="";
            for (Establecimiento value : list)
            {
                nombreEstablecimiento=value.getNombre();
            }
            
              FileOutputStream fileOut = new FileOutputStream(path+"FormularioCamas.xlsx");
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            return "FormularioCamas.xlsx";
             
           
        }
        catch(Exception ex)
        {
            excepcion=ex.getMessage();
            return "";
        }
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    
    
}
