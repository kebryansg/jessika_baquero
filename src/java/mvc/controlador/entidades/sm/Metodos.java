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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "metodos")
@NamedQueries({
    @NamedQuery(name = "Metodos.findAll", query = "SELECT m FROM Metodos m")})
public class Metodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    private int idTipoConsulta;
    private List<DetallesMetodos> list;

    public List<DetallesMetodos> getList() {
        return list;
    }

    public void setList(List<DetallesMetodos> list) {
        this.list = list;
    }

    public Metodos() {
    }

    public Metodos(Integer id) {
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

    public int getIdTipoConsulta() {
        return idTipoConsulta;
    }

    public void setIdTipoConsulta(int idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
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
        if (!(object instanceof Metodos)) {
            return false;
        }
        Metodos other = (Metodos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Metodos[ id=" + id + " ]";
    }
    
}
