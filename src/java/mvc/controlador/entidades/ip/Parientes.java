/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.ip;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "parientes", catalog = "bd_ip", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Parientes.findAll", query = "SELECT p FROM Parientes p")})
public class Parientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "parentesco")
    private String parentesco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPariente")
    private List<ParienteEnfermedadPaciente> parienteEnfermedadPacienteList;

    public Parientes() {
    }

    public Parientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public List<ParienteEnfermedadPaciente> getParienteEnfermedadPacienteList() {
        return parienteEnfermedadPacienteList;
    }

    public void setParienteEnfermedadPacienteList(List<ParienteEnfermedadPaciente> parienteEnfermedadPacienteList) {
        this.parienteEnfermedadPacienteList = parienteEnfermedadPacienteList;
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
        if (!(object instanceof Parientes)) {
            return false;
        }
        Parientes other = (Parientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.ip.Parientes[ id=" + id + " ]";
    }
    
}
