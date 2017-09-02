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
@Table(name = "consultaEstudiosImagen")
@NamedQueries({
    @NamedQuery(name = "ConsultaEstudiosImagen.findAll", query = "SELECT c FROM ConsultaEstudiosImagen c")})
public class ConsultaEstudiosImagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "detExtremidad")
    private String detExtremidad;
    @JoinColumn(name = "idConsulta", referencedColumnName = "id")
    @ManyToOne
    private Consulta idConsulta;
    @JoinColumn(name = "idDetalleEstudiosImagen", referencedColumnName = "id")
    @ManyToOne
    private DetallesEstudiosImg idDetalleEstudiosImagen;

    public ConsultaEstudiosImagen() {
        this.id = 0;
    }

    public ConsultaEstudiosImagen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetExtremidad() {
        return detExtremidad;
    }

    public void setDetExtremidad(String detExtremidad) {
        this.detExtremidad = detExtremidad;
    }
    

    public Consulta getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Consulta idConsulta) {
        this.idConsulta = idConsulta;
    }

    public DetallesEstudiosImg getIdDetalleEstudiosImagen() {
        return idDetalleEstudiosImagen;
    }

    public void setIdDetalleEstudiosImagen(DetallesEstudiosImg idDetalleEstudiosImagen) {
        this.idDetalleEstudiosImagen = idDetalleEstudiosImagen;
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
        if (!(object instanceof ConsultaEstudiosImagen)) {
            return false;
        }
        ConsultaEstudiosImagen other = (ConsultaEstudiosImagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.ConsultaEstudiosImagen[ id=" + id + " ]";
    }
    
}
