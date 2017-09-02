/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

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
@Table(name = "estudiosLaboratorio")
@NamedQueries({
    @NamedQuery(name = "EstudiosLaboratorio.findAll", query = "SELECT e FROM EstudiosLaboratorio e")})
public class EstudiosLaboratorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiosLab")
    private List<DetalleEstudiosLabs> detalleEstudiosLabsList;

    public EstudiosLaboratorio() {
    }

    public EstudiosLaboratorio(Integer id) {
        this.id = id;
    }

    public EstudiosLaboratorio(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleEstudiosLabs> getDetalleEstudiosLabsList() {
        return detalleEstudiosLabsList;
    }

    public void setDetalleEstudiosLabsList(List<DetalleEstudiosLabs> detalleEstudiosLabsList) {
        this.detalleEstudiosLabsList = detalleEstudiosLabsList;
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
        if (!(object instanceof EstudiosLaboratorio)) {
            return false;
        }
        EstudiosLaboratorio other = (EstudiosLaboratorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.EstudiosLaboratorio[ id=" + id + " ]";
    }

}
