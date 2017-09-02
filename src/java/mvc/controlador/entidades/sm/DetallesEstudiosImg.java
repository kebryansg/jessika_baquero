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
@Table(name = "detallesEstudiosImg")
@NamedQueries({
    @NamedQuery(name = "DetallesEstudiosImg.findAll", query = "SELECT d FROM DetallesEstudiosImg d")})
public class DetallesEstudiosImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "extremidades")
    private String extremidades;
    @OneToMany(mappedBy = "idDetalleEstudiosImagen")
    private List<ConsultaEstudiosImagen> consultaEstudiosImagenList;
    @JoinColumn(name = "idEstudiosImg", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstudioImagen idEstudiosImg;

    public DetallesEstudiosImg() {
        this.id = 0;
        this.extremidades = "";
    }

    public DetallesEstudiosImg(Integer id) {
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

    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String extremidades) {
        this.extremidades = extremidades;
    }
    

    public List<ConsultaEstudiosImagen> getConsultaEstudiosImagenList() {
        return consultaEstudiosImagenList;
    }

    public void setConsultaEstudiosImagenList(List<ConsultaEstudiosImagen> consultaEstudiosImagenList) {
        this.consultaEstudiosImagenList = consultaEstudiosImagenList;
    }

    public EstudioImagen getIdEstudiosImg() {
        return idEstudiosImg;
    }

    public void setIdEstudiosImg(EstudioImagen idEstudiosImg) {
        this.idEstudiosImg = idEstudiosImg;
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
        if (!(object instanceof DetallesEstudiosImg)) {
            return false;
        }
        DetallesEstudiosImg other = (DetallesEstudiosImg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.DetallesEstudiosImg[ id=" + id + " ]";
    }

}
