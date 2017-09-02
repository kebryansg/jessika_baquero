/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.ip;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "pariente_enfermedad_paciente", catalog = "bd_ip", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "ParienteEnfermedadPaciente.findAll", query = "SELECT p FROM ParienteEnfermedadPaciente p")})
public class ParienteEnfermedadPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idEnfermedad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Enfermedad idEnfermedad;
    @JoinColumn(name = "idPaciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paciente idPaciente;
    @JoinColumn(name = "idPariente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Parientes idPariente;

    public ParienteEnfermedadPaciente() {
    }

    public ParienteEnfermedadPaciente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enfermedad getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(Enfermedad idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Parientes getIdPariente() {
        return idPariente;
    }

    public void setIdPariente(Parientes idPariente) {
        this.idPariente = idPariente;
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
        if (!(object instanceof ParienteEnfermedadPaciente)) {
            return false;
        }
        ParienteEnfermedadPaciente other = (ParienteEnfermedadPaciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.ip.ParienteEnfermedadPaciente[ id=" + id + " ]";
    }
    
}
