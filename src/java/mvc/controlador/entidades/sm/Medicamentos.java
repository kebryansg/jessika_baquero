/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Deivi
 */
@MappedSuperclass
@Table(name = "medicamentos")
@XmlRootElement
public class Medicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Lob
    @Column(name = "notasEvolucion")
    private String notasEvolucion;
    @Lob
    @Column(name = "prescripcionMedica")
    private String prescripcionMedica;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "idIngresos", referencedColumnName = "id")
    @ManyToOne
    private Ingresos idIngresos;

    public Medicamentos() {
    }

    public Medicamentos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNotasEvolucion() {
        return notasEvolucion;
    }

    public void setNotasEvolucion(String notasEvolucion) {
        this.notasEvolucion = notasEvolucion;
    }

    public String getPrescripcionMedica() {
        return prescripcionMedica;
    }

    public void setPrescripcionMedica(String prescripcionMedica) {
        this.prescripcionMedica = prescripcionMedica;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Ingresos getIdIngresos() {
        return idIngresos;
    }

    public void setIdIngresos(Ingresos idIngresos) {
        this.idIngresos = idIngresos;
    }

    public Medicamentos(Integer id, Date fecha, Date hora, String notasEvolucion, String prescripcionMedica, Ingresos idIngresos) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.notasEvolucion = notasEvolucion;
        this.prescripcionMedica = prescripcionMedica;
        this.idIngresos = idIngresos;
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
        if (!(object instanceof Medicamentos)) {
            return false;
        }
        Medicamentos other = (Medicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Medicamentos[ id=" + id + " ]";
    }
    
}
