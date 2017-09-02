/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "medico_especialidad")
@NamedQueries({
    @NamedQuery(name = "MedicoEspecialidad.findAll", query = "SELECT m FROM MedicoEspecialidad m")})
public class MedicoEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "id")
    @ManyToOne
    private Especialidad idEspecialidad;
    @JoinColumn(name = "idMedico", referencedColumnName = "id")
    @ManyToOne
    private Medico idMedico;
    @OneToMany(mappedBy = "idMedicoEspecialidad")
    private List<Consulta> consultaList;
    @OneToMany(mappedBy = "idMedicoEspecialidad")
    private List<VisitaDomicilio> visitaDomicilioList;

    public MedicoEspecialidad() {
    }

    /**
     *
     * @param medico
     * @param especialidad
     */
    public MedicoEspecialidad(Medico medico, Especialidad especialidad) {
       this.idEspecialidad=especialidad;
       this.idMedico=medico;
    }

    public MedicoEspecialidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Medico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medico idMedico) {
        this.idMedico = idMedico;
    }

    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    public List<VisitaDomicilio> getVisitaDomicilioList() {
        return visitaDomicilioList;
    }

    public void setVisitaDomicilioList(List<VisitaDomicilio> visitaDomicilioList) {
        this.visitaDomicilioList = visitaDomicilioList;
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
        if (!(object instanceof MedicoEspecialidad)) {
            return false;
        }
        MedicoEspecialidad other = (MedicoEspecialidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.MedicoEspecialidad[ id=" + id + " ]";
    }
    
}
