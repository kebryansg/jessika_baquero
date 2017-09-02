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
@Table(name = "signosVitales")
@NamedQueries({
    @NamedQuery(name = "SignosVitales.findAll", query = "SELECT s FROM SignosVitales s")})
public class SignosVitales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private Integer idConsulta;
    @Column(name = "peso")
    private String peso;
    @Column(name = "talla")
    private String talla;
    @Column(name = "presion")
    private String presion;
    @Column(name = "temperatura")
    private String temperatura;
    @Column(name = "frecuenciaC")
    private String frecuenciaC;
    @Column(name = "fum")
    @Temporal(TemporalType.DATE)
    private Date fum;
    @Column(name = "fuc")
    @Temporal(TemporalType.DATE)
    private Date fuc;
    @Column(name = "periodo")
    private String periodo;
    @OneToMany(mappedBy = "idSignosvitales")
    private List<Consulta> consultaList;

    public SignosVitales() {
        this.idConsulta = this.id = 0;
        this.frecuenciaC = this.presion = this.talla = this.peso = this.temperatura = this.periodo = "";
    }

    public SignosVitales(Integer id) {
        this.id = id;
        this.idConsulta = 0;
        this.frecuenciaC = this.presion = this.talla = this.peso = this.temperatura = this.periodo = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getFrecuenciaC() {
        return frecuenciaC;
    }

    public void setFrecuenciaC(String frecuenciaC) {
        this.frecuenciaC = frecuenciaC;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public Date getFum() {
        return fum;
    }

    public void setFum(Date fum) {
        this.fum = fum;
    }

    public Date getFuc() {
        return fuc;
    }

    public void setFuc(Date fuc) {
        this.fuc = fuc;
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
        if (!(object instanceof SignosVitales)) {
            return false;
        }
        SignosVitales other = (SignosVitales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.SignosVitales[ id=" + id + " ]";
    }

}
