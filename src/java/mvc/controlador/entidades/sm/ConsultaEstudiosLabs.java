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
@Table(name = "consultaEstudiosLabs")
@NamedQueries({
    @NamedQuery(name = "ConsultaEstudiosLabs.findAll", query = "SELECT c FROM ConsultaEstudiosLabs c")})
public class ConsultaEstudiosLabs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "valores")
    private String valores;
    @JoinColumn(name = "idConsulta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Consulta idConsulta;
    @JoinColumn(name = "idDetalleEstudiosLabs", referencedColumnName = "id")
    @ManyToOne
    private DetalleEstudiosLabs idDetalleEstudiosLabs;

    public ConsultaEstudiosLabs() {
        this.id = 0;
        this.valores = "";
    }

    public ConsultaEstudiosLabs(Integer id) {
        this.id = id;
        this.valores = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public DetalleEstudiosLabs getIdDetalleEstudiosLabs() {
        return idDetalleEstudiosLabs;
    }

    public void setIdDetalleEstudiosLabs(DetalleEstudiosLabs idDetalleEstudiosLabs) {
        this.idDetalleEstudiosLabs = idDetalleEstudiosLabs;
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
        if (!(object instanceof ConsultaEstudiosLabs)) {
            return false;
        }
        ConsultaEstudiosLabs other = (ConsultaEstudiosLabs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.ConsultaEstudiosLabs[ id=" + id + " ]";
    }

}
