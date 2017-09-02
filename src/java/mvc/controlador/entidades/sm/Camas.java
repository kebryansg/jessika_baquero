/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

/**
 *
 * @author Deivi
 */
public class Camas {
    private int id;
    private int medicinaInternaIndividual;
    private int medicinaInternaDoble;
    private int cirugiaIndividual;
    private int cirugiaDoble;
    private int ginecologiaYobstetriciaIndividual;
    private int ginecologiaYobstetriciaDoble;
    private int pediatriaIndividual;
    private int pediatriaDoble;
    private int cardiologiaIndividual;
    private int cardiologiaDoble;
    private int neumologiaIndividual;
    private int neumologiaDoble;
    private int psiquiatriaIndividual;
    private int psiquiatriaDoble;
    private int traumatologiaIndividual;
    private int traumotologiaDoble;
    private int infectologiaIndividual;
    private int infectologiaDoble;
    private int oftalmologiaIndividual;
    private int oftalmologiaDoble;
    private int urologiaIndividual;
    private int urologiaDoble;
    private int gastroenterologiaIndividual;
    private int gastroenterologiaDoble;
    private int emergencia;
    private int cuidadosIntensivos;

    public Camas(int id, int medicinaInternaIndividual, int medicinaInternaDoble, int cirugiaIndividual, int cirugiaDoble, int ginecologiaYobstetriciaIndividual, int ginecologiaYobstetriciaDoble, int pediatriaIndividual, int pediatriaDoble, int cardiologiaIndividual, int cardiologiaDoble, int neumologiaIndividual, int neumologiaDoble, int psiquiatriaIndividual, int psiquiatriaDoble, int traumatologiaIndividual, int traumotologiaDoble, int infectologiaIndividual, int infectologiaDoble, int oftalmologiaIndividual, int oftalmologiaDoble, int urologiaIndividual, int urologiaDoble, int gastroenterologiaIndividual, int gastroenterologiaDoble, int emergencia, int cuidadosIntensivos) {
        this.id = id;
        this.medicinaInternaIndividual = medicinaInternaIndividual;
        this.medicinaInternaDoble = medicinaInternaDoble;
        this.cirugiaIndividual = cirugiaIndividual;
        this.cirugiaDoble = cirugiaDoble;
        this.ginecologiaYobstetriciaIndividual = ginecologiaYobstetriciaIndividual;
        this.ginecologiaYobstetriciaDoble = ginecologiaYobstetriciaDoble;
        this.pediatriaIndividual = pediatriaIndividual;
        this.pediatriaDoble = pediatriaDoble;
        this.cardiologiaIndividual = cardiologiaIndividual;
        this.cardiologiaDoble = cardiologiaDoble;
        this.neumologiaIndividual = neumologiaIndividual;
        this.neumologiaDoble = neumologiaDoble;
        this.psiquiatriaIndividual = psiquiatriaIndividual;
        this.psiquiatriaDoble = psiquiatriaDoble;
        this.traumatologiaIndividual = traumatologiaIndividual;
        this.traumotologiaDoble = traumotologiaDoble;
        this.infectologiaIndividual = infectologiaIndividual;
        this.infectologiaDoble = infectologiaDoble;
        this.oftalmologiaIndividual = oftalmologiaIndividual;
        this.oftalmologiaDoble = oftalmologiaDoble;
        this.urologiaIndividual = urologiaIndividual;
        this.urologiaDoble = urologiaDoble;
        this.gastroenterologiaIndividual = gastroenterologiaIndividual;
        this.gastroenterologiaDoble = gastroenterologiaDoble;
        this.emergencia = emergencia;
        this.cuidadosIntensivos = cuidadosIntensivos;
    }

    public Camas() {
    }

    public Camas(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicinaInternaIndividual() {
        return medicinaInternaIndividual;
    }

    public void setMedicinaInternaIndividual(int medicinaInternaIndividual) {
        this.medicinaInternaIndividual = medicinaInternaIndividual;
    }

    public int getMedicinaInternaDoble() {
        return medicinaInternaDoble;
    }

    public void setMedicinaInternaDoble(int medicinaInternaDoble) {
        this.medicinaInternaDoble = medicinaInternaDoble;
    }

    public int getCirugiaIndividual() {
        return cirugiaIndividual;
    }

    public void setCirugiaIndividual(int cirugiaIndividual) {
        this.cirugiaIndividual = cirugiaIndividual;
    }

    public int getCirugiaDoble() {
        return cirugiaDoble;
    }

    public void setCirugiaDoble(int cirugiaDoble) {
        this.cirugiaDoble = cirugiaDoble;
    }

    public int getGinecologiaYobstetriciaIndividual() {
        return ginecologiaYobstetriciaIndividual;
    }

    public void setGinecologiaYobstetriciaIndividual(int ginecologiaYobstetriciaIndividual) {
        this.ginecologiaYobstetriciaIndividual = ginecologiaYobstetriciaIndividual;
    }

    public int getGinecologiaYobstetriciaDoble() {
        return ginecologiaYobstetriciaDoble;
    }

    public void setGinecologiaYobstetriciaDoble(int ginecologiaYobstetriciaDoble) {
        this.ginecologiaYobstetriciaDoble = ginecologiaYobstetriciaDoble;
    }

    public int getPediatriaIndividual() {
        return pediatriaIndividual;
    }

    public void setPediatriaIndividual(int pediatriaIndividual) {
        this.pediatriaIndividual = pediatriaIndividual;
    }

    public int getPediatriaDoble() {
        return pediatriaDoble;
    }

    public void setPediatriaDoble(int pediatriaDoble) {
        this.pediatriaDoble = pediatriaDoble;
    }

    public int getCardiologiaIndividual() {
        return cardiologiaIndividual;
    }

    public void setCardiologiaIndividual(int cardiologiaIndividual) {
        this.cardiologiaIndividual = cardiologiaIndividual;
    }

    public int getCardiologiaDoble() {
        return cardiologiaDoble;
    }

    public void setCardiologiaDoble(int cardiologiaDoble) {
        this.cardiologiaDoble = cardiologiaDoble;
    }

    public int getNeumologiaIndividual() {
        return neumologiaIndividual;
    }

    public void setNeumologiaIndividual(int neumologiaIndividual) {
        this.neumologiaIndividual = neumologiaIndividual;
    }

    public int getNeumologiaDoble() {
        return neumologiaDoble;
    }

    public void setNeumologiaDoble(int neumologiaDoble) {
        this.neumologiaDoble = neumologiaDoble;
    }

    public int getPsiquiatriaIndividual() {
        return psiquiatriaIndividual;
    }

    public void setPsiquiatriaIndividual(int psiquiatriaIndividual) {
        this.psiquiatriaIndividual = psiquiatriaIndividual;
    }

    public int getPsiquiatriaDoble() {
        return psiquiatriaDoble;
    }

    public void setPsiquiatriaDoble(int psiquiatriaDoble) {
        this.psiquiatriaDoble = psiquiatriaDoble;
    }

    public int getTraumatologiaIndividual() {
        return traumatologiaIndividual;
    }

    public void setTraumatologiaIndividual(int traumatologiaIndividual) {
        this.traumatologiaIndividual = traumatologiaIndividual;
    }

    public int getTraumotologiaDoble() {
        return traumotologiaDoble;
    }

    public void setTraumotologiaDoble(int traumotologiaDoble) {
        this.traumotologiaDoble = traumotologiaDoble;
    }

    public int getInfectologiaIndividual() {
        return infectologiaIndividual;
    }

    public void setInfectologiaIndividual(int infectologiaIndividual) {
        this.infectologiaIndividual = infectologiaIndividual;
    }

    public int getInfectologiaDoble() {
        return infectologiaDoble;
    }

    public void setInfectologiaDoble(int infectologiaDoble) {
        this.infectologiaDoble = infectologiaDoble;
    }

    public int getOftalmologiaIndividual() {
        return oftalmologiaIndividual;
    }

    public void setOftalmologiaIndividual(int oftalmologiaIndividual) {
        this.oftalmologiaIndividual = oftalmologiaIndividual;
    }

    public int getOftalmologiaDoble() {
        return oftalmologiaDoble;
    }

    public void setOftalmologiaDoble(int oftalmologiaDoble) {
        this.oftalmologiaDoble = oftalmologiaDoble;
    }

    public int getUrologiaIndividual() {
        return urologiaIndividual;
    }

    public void setUrologiaIndividual(int urologiaIndividual) {
        this.urologiaIndividual = urologiaIndividual;
    }

    public int getUrologiaDoble() {
        return urologiaDoble;
    }

    public void setUrologiaDoble(int urologiaDoble) {
        this.urologiaDoble = urologiaDoble;
    }

    public int getGastroenterologiaIndividual() {
        return gastroenterologiaIndividual;
    }

    public void setGastroenterologiaIndividual(int gastroenterologiaIndividual) {
        this.gastroenterologiaIndividual = gastroenterologiaIndividual;
    }

    public int getGastroenterologiaDoble() {
        return gastroenterologiaDoble;
    }

    public void setGastroenterologiaDoble(int gastroenterologiaDoble) {
        this.gastroenterologiaDoble = gastroenterologiaDoble;
    }

    public int getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(int emergencia) {
        this.emergencia = emergencia;
    }

    public int getCuidadosIntensivos() {
        return cuidadosIntensivos;
    }

    public void setCuidadosIntensivos(int cuidadosIntensivos) {
        this.cuidadosIntensivos = cuidadosIntensivos;
    }
    
    

    
    
    
}
