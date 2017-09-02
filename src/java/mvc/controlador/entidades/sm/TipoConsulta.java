/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "tipoConsulta", catalog = "bd_sm", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "TipoConsulta.findAll", query = "SELECT t FROM TipoConsulta t")
    , @NamedQuery(name = "TipoConsulta.findById", query = "SELECT t FROM TipoConsulta t WHERE t.id = :id")
    , @NamedQuery(name = "TipoConsulta.findByDescripcion", query = "SELECT t FROM TipoConsulta t WHERE t.descripcion = :descripcion")})
public class TipoConsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;

    public TipoConsulta() {
    }

    public TipoConsulta(Integer id) {
        this.id = id;
    }
    public TipoConsulta(Integer id,String descripcion) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConsulta)) {
            return false;
        }
        TipoConsulta other = (TipoConsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.TipoConsulta[ id=" + id + " ]";
    }
    
}
