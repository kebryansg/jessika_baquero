/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "caso")
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c")})
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(mappedBy = "idCaso")
    private List<Ingresos> ingresosList;
    @JoinColumn(name = "idHistorialClinico", referencedColumnName = "id")
    @ManyToOne
    private HistorialClinico idHistorialClinico;
    @OneToMany(mappedBy = "idCaso")
    private List<Consulta> consultaList;

    public Caso() {
        this.id =0;
    }

    public Caso(Integer id) {
        this.id = id;
    }
    public Caso(Integer id,Integer idHistorialClinico) {
        this.id = id;
        this.idHistorialClinico = new HistorialClinico(idHistorialClinico);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public List<Ingresos> getIngresosList() {
        return ingresosList;
    }

    public void setIngresosList(List<Ingresos> ingresosList) {
        this.ingresosList = ingresosList;
    }

    public HistorialClinico getIdHistorialClinico() {
        return idHistorialClinico;
    }

    public void setIdHistorialClinico(HistorialClinico idHistorialClinico) {
        this.idHistorialClinico = idHistorialClinico;
    }

    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
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
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Caso[ id=" + id + " ]";
    }
    
}
