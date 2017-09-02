/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "visitaDomicilio")
@NamedQueries({
    @NamedQuery(name = "VisitaDomicilio.findAll", query = "SELECT v FROM VisitaDomicilio v")})
public class VisitaDomicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idMedico_Especialidad", referencedColumnName = "id")
    @ManyToOne
    private MedicoEspecialidad idMedicoEspecialidad;

    public VisitaDomicilio() {
    }

    public VisitaDomicilio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MedicoEspecialidad getIdMedicoEspecialidad() {
        return idMedicoEspecialidad;
    }

    public void setIdMedicoEspecialidad(MedicoEspecialidad idMedicoEspecialidad) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
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
        if (!(object instanceof VisitaDomicilio)) {
            return false;
        }
        VisitaDomicilio other = (VisitaDomicilio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.VisitaDomicilio[ id=" + id + " ]";
    }
    
}
