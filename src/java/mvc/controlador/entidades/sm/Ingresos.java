/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import mvc.controlador.entidades.ip.Paciente;


/**
 *
 * @author kebryan
 */
public class Ingresos {

   
    private Integer estado;
    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Date hora;
    private Boolean sos;
    private Integer condicionEgreso;
    private String definitivoEgreso;
    private String secundarioEgreso;
    private String secundarioEgreso2;
    private String causaExterna;
    private String codigoDiagnosticoDefinitivo;
    private Caso idCaso;
    private EspecialidadEgreso idEspecialidadEgreso;
    private TipoIngreso idTipoIngreso;
    private Integer totalIngresos;
    private Integer registros;

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }
    
    private Paciente unPaciente;
   
    private List<DetalleIngresos> detalleIngresosList;

    public Ingresos() {
    }

    public Ingresos(Integer id, Date fechaEntrada, Date fechaSalida, Date hora, Boolean sos, Integer condicionEgreso, String definitivoEgreso, String secundarioEgreso, String secundarioEgreso2, String causaExterna, String codigoDiagnosticoDefinitivo, Caso idCaso, EspecialidadEgreso idEspecialidadEgreso, TipoIngreso idTipoIngreso, Paciente unPaciente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.hora = hora;
        this.sos = sos;
        this.condicionEgreso = condicionEgreso;
        this.definitivoEgreso = definitivoEgreso;
        this.secundarioEgreso = secundarioEgreso;
        this.secundarioEgreso2 = secundarioEgreso2;
        this.causaExterna = causaExterna;
        this.codigoDiagnosticoDefinitivo = codigoDiagnosticoDefinitivo;
        this.idCaso = idCaso;
        this.idEspecialidadEgreso = idEspecialidadEgreso;
        this.idTipoIngreso = idTipoIngreso;
        this.unPaciente = unPaciente;
    }

    public Ingresos(Date fechaEntrada, Date fechaSalida, Date hora, Boolean sos, Integer condicionEgreso, String definitivoEgreso, String secundarioEgreso, String secundarioEgreso2, String causaExterna, String codigoDiagnosticoDefinitivo, EspecialidadEgreso idEspecialidadEgreso, TipoIngreso idTipoIngreso) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.hora = hora;
        this.sos = sos;
        this.condicionEgreso = condicionEgreso;
        this.definitivoEgreso = definitivoEgreso;
        this.secundarioEgreso = secundarioEgreso;
        this.secundarioEgreso2 = secundarioEgreso2;
        this.causaExterna = causaExterna;
        this.codigoDiagnosticoDefinitivo = codigoDiagnosticoDefinitivo;        
        this.idEspecialidadEgreso = idEspecialidadEgreso;
        this.idTipoIngreso = idTipoIngreso;
    }
    

    public Ingresos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Boolean getSos() {
        return sos;
    }

    public void setSos(Boolean sos) {
        this.sos = sos;
    }

    public Integer getCondicionEgreso() {
        return condicionEgreso;
    }

    public void setCondicionEgreso(Integer condicionEgreso) {
        this.condicionEgreso = condicionEgreso;
    }

    public String getDefinitivoEgreso() {
        return definitivoEgreso;
    }

    public void setDefinitivoEgreso(String definitivoEgreso) {
        this.definitivoEgreso = definitivoEgreso;
    }

    public String getSecundarioEgreso() {
        return secundarioEgreso;
    }

    public void setSecundarioEgreso(String secundarioEgreso) {
        this.secundarioEgreso = secundarioEgreso;
    }

    public String getSecundarioEgreso2() {
        return secundarioEgreso2;
    }

    public void setSecundarioEgreso2(String secundarioEgreso2) {
        this.secundarioEgreso2 = secundarioEgreso2;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public String getCodigoDiagnosticoDefinitivo() {
        return codigoDiagnosticoDefinitivo;
    }

    public void setCodigoDiagnosticoDefinitivo(String codigoDiagnosticoDefinitivo) {
        this.codigoDiagnosticoDefinitivo = codigoDiagnosticoDefinitivo;
    }

    public Caso getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Caso idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Integer totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
    

    public EspecialidadEgreso getIdEspecialidadEgreso() {
        return idEspecialidadEgreso;
    }

    public void setIdEspecialidadEgreso(EspecialidadEgreso idEspecialidadEgreso) {
        this.idEspecialidadEgreso = idEspecialidadEgreso;
    }

    public TipoIngreso getIdTipoIngreso() {
        return idTipoIngreso;
    }

    public void setIdTipoIngreso(TipoIngreso idTipoIngreso) {
        this.idTipoIngreso = idTipoIngreso;
    }

    public List<DetalleIngresos> getDetalleIngresosList() {
        return detalleIngresosList;
    }

    public void setDetalleIngresosList(List<DetalleIngresos> detalleIngresosList) {
        this.detalleIngresosList = detalleIngresosList;
    }

    public Paciente getUnPaciente() {
        return unPaciente;
    }

    public void setUnPaciente(Paciente unPaciente) {
        this.unPaciente = unPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingresos)) {
            return false;
        }
        Ingresos other = (Ingresos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Ingresos[ id=" + id + " ]";
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    
}
