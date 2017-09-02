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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consulta")
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "idConsulta")
    private List<ConsultaEstudiosImagen> consultaEstudiosImagenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<Prescripcion> prescripcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<ConsultaEstudiosLabs> consultaEstudiosLabsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConsulta")
    private List<Diagnostico> diagnosticoList;
    @JoinColumn(name = "idCaso", referencedColumnName = "id")
    @ManyToOne
    private Caso idCaso;
    @JoinColumn(name = "idMedico_Especialidad", referencedColumnName = "id")
    @ManyToOne
    private MedicoEspecialidad idMedicoEspecialidad;
    //@JoinColumn(name = "idMetodo", referencedColumnName = "id")
    //@ManyToOne
    private int idMetodo;
    private int idTipoConsulta;

    
    
    @JoinColumn(name = "idSignosvitales", referencedColumnName = "id")
    @ManyToOne
    private SignosVitales idSignosvitales;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "prescripcion")
    private String prescripcion;
    @Column(name = "diagnostico")
    private String diagnostico;
    @Column(name = "sintoma")
    private String sintoma;
    
    private String observacion;
    
    
    public int getIdTipoConsulta() {
        return idTipoConsulta;
    }

    public void setIdTipoConsulta(int idTipoConsulta) {
        this.idTipoConsulta = idTipoConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(String prescripcion) {
        this.prescripcion = prescripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }
    
    
    
    

    public Consulta() {
        this.id = 0;
    }

    public Consulta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ConsultaEstudiosImagen> getConsultaEstudiosImagenList() {
        return consultaEstudiosImagenList;
    }

    public void setConsultaEstudiosImagenList(List<ConsultaEstudiosImagen> consultaEstudiosImagenList) {
        this.consultaEstudiosImagenList = consultaEstudiosImagenList;
    }

    public List<Prescripcion> getPrescripcionList() {
        return prescripcionList;
    }

    public void setPrescripcionList(List<Prescripcion> prescripcionList) {
        this.prescripcionList = prescripcionList;
    }

    public List<ConsultaEstudiosLabs> getConsultaEstudiosLabsList() {
        return consultaEstudiosLabsList;
    }

    public void setConsultaEstudiosLabsList(List<ConsultaEstudiosLabs> consultaEstudiosLabsList) {
        this.consultaEstudiosLabsList = consultaEstudiosLabsList;
    }

    public List<Diagnostico> getDiagnosticoList() {
        return diagnosticoList;
    }

    public void setDiagnosticoList(List<Diagnostico> diagnosticoList) {
        this.diagnosticoList = diagnosticoList;
    }

    public Caso getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Caso idCaso) {
        this.idCaso = idCaso;
    }

    public MedicoEspecialidad getIdMedicoEspecialidad() {
        return idMedicoEspecialidad;
    }

    public void setIdMedicoEspecialidad(MedicoEspecialidad idMedicoEspecialidad) {
        this.idMedicoEspecialidad = idMedicoEspecialidad;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public SignosVitales getIdSignosvitales() {
        return idSignosvitales;
    }

    public void setIdSignosvitales(SignosVitales idSignosvitales) {
        this.idSignosvitales = idSignosvitales;
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
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Consulta[ id=" + id + " ]";
    }
    
}
