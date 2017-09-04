package test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.modelo.smDaoImp.ConsultaDaoImp;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author kebryan
 */
public class test {

    public static void main(String[] args) throws ParseException, IOException {
        /*String[] elementos = "nancy,baidal,rodriguez".split(",");
        int n = elementos.length;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        test t = new test();
        t.Perm2(elementos, "", n, r);
        
        
        System.out.println(String.join(" or ", t.resultado));*/
        /*String[] elem = "kevin bryan suarez guzman".split(" ");
        for (int i = (elem.length - 1); i >= 0; i--) {
            //elem[i] = "\"" + elem[i] + "\"";
            System.out.println(elem[i]);
        }*/
        System.out.println(nvar("kevin bryan suarez"));

        /*String text = "    kevin  bryan  suarez    ";
        System.out.println(text);
        text = text.trim();
        String[] palabras = text.split(" ");
        ArrayList<String> frase = new ArrayList<String>();
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].trim();
            if (palabras[i].length() != 0) {
                frase.add(palabras[i]);
            }
        }
        System.out.println(String.join(" ", frase));*/
    }
    List<String> resultado = new ArrayList<>();

    public void Perm2(String[] elem, String act, int n, int r) {
        if (n == 0) {
            act = act.replace(",", "");
            //System.out.println("\"" + act.trim() + "\" or");
            resultado.add("\"" + act.trim() + "\"");
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    Perm2(elem, act + elem[i] + ", ", n - 1, r);
                }
            }
        }
    }

    public static String nvar(String frase) {
        String[] elem = clear_filter(frase).split(" ");
        for (int i = 0; i < elem.length; i++) {
            elem[i] = "\"" + elem[i] + "*\"";
        }
        String[] elem_r = new String[elem.length];
        int indice = 0;
        for (int i = (elem.length - 1); i >= 0; i--) {
            elem_r[indice++] = elem[i];
        }
        return String.join(" near ", elem) + " or " + String.join(" near ", elem_r);
    }

    public static String clear_filter(String text) {
        text = text.trim();
        String[] palabras = text.split(" ");
        ArrayList<String> frase = new ArrayList<String>();
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].trim();
            if (palabras[i].length() != 0) {
                frase.add(palabras[i]);
            }
        }
        return String.join(" ", frase);
    }

    public static int getID(String tabla) {
        int id = 0;
        C_BD conn = con_db.open(con_db.MSSQL_IP);
        try {
            ResultSet rs = conn.query("select IDENT_CURRENT('" + tabla + "') id");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
        } finally {
            conn.close();
        }
        return id;
    }

    public static int getID_SM(String tabla) {
        int id = 0;
        C_BD conn = con_db.open(con_db.MSSQL_SM);
        try {
            ResultSet rs = conn.query("select IDENT_CURRENT('" + tabla + "') id");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
        } finally {
            conn.close();
        }
        return id;
    }

    public static Date fechaSQL(String stringFecha) {
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            Date convertido = fechaHora.parse(stringFecha);
            return convertido;
        } catch (ParseException e) {
            return null;
        }

    }

    public static Date MesSQL(String stringFecha) {
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM");
            Date convertido = fechaHora.parse(stringFecha);
            return convertido;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static Date YearSQL(String stringFecha) {
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy");
            Date convertido = fechaHora.parse(stringFecha);
            return convertido;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static String SQLSave(Date fecha) {
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String convertido = fechaHora.format(fecha);
        return convertido;
    }

    public static String getSexo(Boolean sexo) {
        return sexo ? "1" : "0";
    }

    public static Boolean setSexo(String sexo) {
        return sexo.equals("1");
    }

    public static String ruta() throws IOException {
        return new File(".").getCanonicalPath().replace("\\", "/") + "/web/";
    }

}
