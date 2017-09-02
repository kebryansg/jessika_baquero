
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estudioImagen")
@NamedQueries({
    @NamedQuery(name = "EstudioImagen.findAll", query = "SELECT e FROM EstudioImagen e")})
public class EstudioImagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiosImg")
    private List<DetallesEstudiosImg> detallesEstudiosImgList;
    @JoinColumn(name = "idTipoEstudioImg", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoEstudioImg idTipoEstudioImg;

    public EstudioImagen() {
    }

    public EstudioImagen(Integer id) {
        this.id = id;
    }
    public EstudioImagen(Integer id,String descripcion) {
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

    public TipoEstudioImg getIdTipoEstudioImg() {
        return idTipoEstudioImg;
    }

    public void setIdTipoEstudioImg(TipoEstudioImg idTipoEstudioImg) {
        this.idTipoEstudioImg = idTipoEstudioImg;
    }
    

    public List<DetallesEstudiosImg> getDetallesEstudiosImgList() {
        return detallesEstudiosImgList;
    }

    public void setDetallesEstudiosImgList(List<DetallesEstudiosImg> detallesEstudiosImgList) {
        this.detallesEstudiosImgList = detallesEstudiosImgList;
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
        if (!(object instanceof EstudioImagen)) {
            return false;
        }
        EstudioImagen other = (EstudioImagen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.EstudioImagen[ id=" + id + " ]";
    }
    
}
