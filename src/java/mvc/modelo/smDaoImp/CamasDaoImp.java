/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.smDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.controlador.C_BD;
import mvc.controlador.con_db;
import mvc.controlador.entidades.sm.Camas;
import mvc.modelo.smDao.CamasDao;

/**
 *
 * @author Deivi
 */
public class CamasDaoImp implements CamasDao {
C_BD conn;
String excepcion="";
    @Override
    public boolean save(Camas value) {
        this.conn = con_db.open(con_db.MSSQL_SM);
        String sql = "";
        try 
        {
            if (value.getId() == 0) 
            {
                sql="INSERT INTO camas (medicinaInternaIndividual,medicinaInternaDoble,cirugiaIndividual,cirugiaDoble,ginecologiaYobstetriciaIndividual,ginecologiaYobstetriciaDoble,pediatriaIndividual,pediatriaDoble,cardiologiaIndividual,cardiologiaDoble,neumologiaIndividual,neumologiaDoble,psiquiatriaIndividual,psiquiatriaDoble,traumatologiaIndividual,traumotologiaDoble,infectologiaIndividual,infectologiaDoble,oftalmologiaIndividual,oftalmologiaDoble,urologiaIndividual,urologiaDoble,gastroenterologiaIndividual,gastroenterologiaDoble,emergencia,cuidadosIntensivos)\n" +
                "values("+value.getMedicinaInternaIndividual()+","+value.getMedicinaInternaDoble()+", "+value.getCirugiaIndividual()+", "+value.getCirugiaDoble()+", "+value.getGinecologiaYobstetriciaIndividual()+", "+ value.getGinecologiaYobstetriciaDoble()+ ", "+value.getPediatriaIndividual()+", "+value.getPediatriaDoble()+ ", "+value.getCardiologiaIndividual()+ ","+value.getCardiologiaDoble()+", "+value.getNeumologiaIndividual()+", "+value.getNeumologiaDoble()+", "+value.getPsiquiatriaIndividual()+", "+value.getPsiquiatriaDoble()+","+value.getTraumatologiaIndividual() + ", "+value.getTraumotologiaDoble()+","+value.getInfectologiaIndividual()+","+value.getInfectologiaDoble()+ ","+value.getOftalmologiaIndividual()+", "+value.getOftalmologiaDoble()+", "+value.getUrologiaIndividual()+ ","+value.getUrologiaDoble()+", "+value.getGastroenterologiaIndividual()+", " +value.getGastroenterologiaDoble()+ ", "+value.getEmergencia()+ ", "+value.getCuidadosIntensivos()+"  )";
            } 
            else
            {
                sql="update camas set medicinaInternaIndividual="+value.getMedicinaInternaIndividual()+",medicinaInternaDoble="+value.getMedicinaInternaDoble()+" ,cirugiaIndividual="+value.getCirugiaIndividual()+",cirugiaDoble="+value.getCirugiaDoble()+" ,ginecologiaYobstetriciaIndividual="+value.getGinecologiaYobstetriciaIndividual()+" ,ginecologiaYobstetriciaDoble="+value.getGinecologiaYobstetriciaDoble()+" ,pediatriaIndividual="+value.getPediatriaIndividual()+" ,pediatriaDoble="+value.getPediatriaDoble()+",cardiologiaIndividual="+value.getCardiologiaIndividual()+" ,cardiologiaDoble="+value.getCardiologiaDoble()+ " ,neumologiaIndividual="+value.getNeumologiaIndividual()+" ,neumologiaDoble="+value.getNeumologiaDoble()+ " ,psiquiatriaIndividual= "+value.getPsiquiatriaIndividual()+" ,psiquiatriaDoble="+value.getPsiquiatriaDoble()+ ",traumatologiaIndividual="+value.getTraumatologiaIndividual()+" ,traumotologiaDoble="+value.getTraumotologiaDoble()+",infectologiaIndividual="+value.getInfectologiaIndividual()+" ,infectologiaDoble="+value.getInfectologiaDoble()+",oftalmologiaIndividual="+value.getOftalmologiaIndividual()+",oftalmologiaDoble="+value.getOftalmologiaDoble()+",urologiaIndividual="+value.getUrologiaIndividual()+",urologiaDoble="+value.getUrologiaDoble()+",gastroenterologiaIndividual="+value.getGastroenterologiaIndividual()+",gastroenterologiaDoble="+value.getGastroenterologiaDoble()+",emergencia="+value.getEmergencia()+",cuidadosIntensivos="+value.getCuidadosIntensivos()+"";
            }
            conn.execute(sql);
            System.out.println(sql);
            return true;
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
            excepcion=ex.getMessage();
            return false;
        } 
        finally 
        {
            this.conn.close();
        }
    }

    @Override
    public List<Camas> list() {
        this.conn = con_db.open(con_db.MSSQL_SM);
        List<Camas> list = new ArrayList<>();
        ResultSet rs = this.conn.query("select * from camas");
        try {
            while (rs.next()) {
                Camas value = new Camas();                
                value.setId(rs.getInt("id")); 
                value.setMedicinaInternaIndividual(rs.getInt("medicinaInternaIndividual"));
                value.setMedicinaInternaDoble(rs.getInt("medicinaInternaDoble"));
                value.setCirugiaIndividual(rs.getInt("cirugiaIndividual"));
                value.setCirugiaDoble(rs.getInt("cirugiaDoble"));
                value.setGinecologiaYobstetriciaIndividual(rs.getInt("ginecologiaYobstetriciaIndividual"));
                value.setGinecologiaYobstetriciaDoble(rs.getInt("ginecologiaYobstetriciaDoble"));
                value.setPediatriaIndividual(rs.getInt("pediatriaIndividual"));
                value.setPediatriaDoble(rs.getInt("pediatriaDoble"));
                value.setCardiologiaIndividual(rs.getInt("cardiologiaIndividual"));
                value.setCardiologiaDoble(rs.getInt("cardiologiaDoble"));
                value.setNeumologiaIndividual(rs.getInt("neumologiaIndividual"));
                value.setNeumologiaDoble(rs.getInt("neumologiaDoble"));
                value.setPsiquiatriaIndividual(rs.getInt("psiquiatriaIndividual"));
                value.setPsiquiatriaDoble(rs.getInt("psiquiatriaDoble"));
                value.setTraumatologiaIndividual(rs.getInt("traumatologiaIndividual"));
                value.setTraumotologiaDoble(rs.getInt("traumotologiaDoble"));
                value.setInfectologiaIndividual(rs.getInt("infectologiaIndividual"));
                value.setInfectologiaDoble(rs.getInt("infectologiaDoble"));
                value.setOftalmologiaIndividual(rs.getInt("oftalmologiaIndividual"));
                value.setOftalmologiaDoble(rs.getInt("oftalmologiaDoble"));
                value.setUrologiaIndividual(rs.getInt("urologiaIndividual"));
                value.setUrologiaDoble(rs.getInt("urologiaDoble"));
                value.setGastroenterologiaIndividual(rs.getInt("gastroenterologiaIndividual"));
                value.setGastroenterologiaDoble(rs.getInt("gastroenterologiaDoble"));
                value.setEmergencia(rs.getInt("emergencia"));
                value.setCuidadosIntensivos(rs.getInt("cuidadosIntensivos"));
                
                             
                list.add(value);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            excepcion=ex.getMessage();
        } finally {
            this.conn.close();
        }
        return list;
    }

    @Override
    public String getExcepcion() {
        return excepcion;
    }
    
}
