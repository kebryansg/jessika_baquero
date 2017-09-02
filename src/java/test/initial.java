package test;

import java.util.ArrayList;
import java.util.List;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.controlador.entidades.sm.TipoConsulta;
import mvc.modelo.smDaoImp.EspecialidadDaoImp;
import mvc.modelo.smDaoImp.TipoConsultaDaoImp;

public class initial {

    public static void main(String[] args) {
        //insertTipoConsulta();
    }

    public static boolean validarDatos() {
        int sizeTipoConsulta = new TipoConsultaDaoImp().list().size();
        if(sizeTipoConsulta == 0){
            insertTipoConsulta();
        }

        return true;
    }

    public static void insertTipoConsulta() {
            new TipoConsultaDaoImp().save();
    }
}
