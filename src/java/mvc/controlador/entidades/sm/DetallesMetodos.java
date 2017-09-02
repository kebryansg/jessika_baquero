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
@Table(name = "detallesMetodos", catalog = "bd_sm", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "DetallesMetodos.findAll", query = "SELECT d FROM DetallesMetodos d")
    , @NamedQuery(name = "DetallesMetodos.findById", query = "SELECT d FROM DetallesMetodos d WHERE d.id = :id")
    , @NamedQuery(name = "DetallesMetodos.findByDescripcion", query = "SELECT d FROM DetallesMetodos d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetallesMetodos.findBySexo", query = "SELECT d FROM DetallesMetodos d WHERE d.sexo = :sexo")})
public class DetallesMetodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "idMetodo")
    private Integer idMetodo;

    public Integer getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(Integer idMetodo) {
        this.idMetodo = idMetodo;
    }
    

    public DetallesMetodos() {
        this.id = 0;
    }

    public DetallesMetodos(Integer id) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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
        if (!(object instanceof DetallesMetodos)) {
            return false;
        }
        DetallesMetodos other = (DetallesMetodos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.DetallesMetodos[ id=" + id + " ]";
    }
    
}
