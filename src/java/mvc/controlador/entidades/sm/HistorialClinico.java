/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "historialClinico")
@NamedQueries({
    @NamedQuery(name = "HistorialClinico.findAll", query = "SELECT h FROM HistorialClinico h")})
public class HistorialClinico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "paciente")
    private String paciente;
    @Column(name = "nombre1")
    private String nombre1;
    @Column(name = "nombre2")
    private String nombre2;
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String cedula;
    @Column(name = "cedula")
    private String apellido2;
    @Column(name = "estado")
    private String estado;

    
    @Column(name = "idPaciente")
    private Integer idPaciente;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "menarca")
    @Temporal(TemporalType.DATE)
    private Date menarca;
    @OneToMany(mappedBy = "idHistorialClinico")
    private List<Caso> casoList;
    private int registros;

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
    public HistorialClinico() {
        this.id = 0;
    }

    public HistorialClinico(Integer id) {
        this.id = id;
    }
    public HistorialClinico(String nada,Integer idPaciente) {
        this.id = 0;
        this.idPaciente = idPaciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getMenarca() {
        return menarca;
    }

    public void setMenarca(Date menarca) {
        this.menarca = menarca;
    }

    public List<Caso> getCasoList() {
        return casoList;
    }

    public void setCasoList(List<Caso> casoList) {
        this.casoList = casoList;
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
        if (!(object instanceof HistorialClinico)) {
            return false;
        }
        HistorialClinico other = (HistorialClinico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.HistorialClinico[ id=" + id + " ]";
    }
    
}