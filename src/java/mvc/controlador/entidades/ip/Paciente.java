/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.ip;

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

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "paciente", catalog = "bd_ip", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "historiaClinica")
    private Integer historiaClinica;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "nombre1")
    private String nombre1;
    @Column(name = "nombre2")
    private String nombre2;
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    //@Column(name = "ciudad")
    //private String ciudad;
    @Column(name = "estadoCivil")
    private String estadoCivil;

    public Integer getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(Integer historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    @Column(name = "telefonoDomicilio")
    private String telefonoDomicilio;
    @Column(name = "telefonoOficina")
    private String telefonoOficina;
    @Column(name = "email")
    private String email;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "paisNacimiento")
    private String paisNacimiento;
    //@Column(name = "lugarNacimiento")
    //private String lugarNacimiento;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "etnia")
    private Integer etnia;
    @Column(name = "discapacidad")
    private Integer discapacidad;
    @Column(name = "imagen")
    private String imagen;
    
    @Column(name = "nombreContacto")
    private String nombreContacto;
    @Column(name = "movilContacto")
    private String movilContacto;
    @Column(name = "parentezco")
    private String parentezco;
    
    private String app;
    private String apf;
    private String observacion;

    
    
    
    
    
    
    @OneToMany(mappedBy = "idPaciente")
    private List<Obstetricos> obstetricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<ParienteEnfermedadPaciente> parienteEnfermedadPacienteList;
    @JoinColumn(name = "idParroquia", referencedColumnName = "id")
    @ManyToOne
    private Parroquia idParroquia;

    public Paciente() {
    }

    public Paciente(Integer id) {
        this.id = id;
        this.observacion = "";
        this.cedula = "";
    }
    public Paciente(String cedula, String nombre1, String nombre2, String apellido1, String apellido2) {
           this.cedula = cedula;
           this.nombre1 = nombre1;
           this.nombre2 = nombre2;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
       }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observacion;
    }

    public void setObservaciones(String observacion) {
        this.observacion = observacion;
    }
    
    

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApf() {
        return apf;
    }

    public void setApf(String apf) {
        this.apf = apf;
    }
    
    

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

//    public String getCiudad() {
//        return ciudad;
//    }
//
//    public void setCiudad(String ciudad) {
//        this.ciudad = ciudad;
//    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

//    public String getLugarNacimiento() {
//        return lugarNacimiento;
//    }
//
//    public void setLugarNacimiento(String lugarNacimiento) {
//        this.lugarNacimiento = lugarNacimiento;
//    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEtnia() {
        return etnia;
    }

    public void setEtnia(Integer etnia) {
        this.etnia = etnia;
    }

    public Integer getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Integer discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Obstetricos> getObstetricosList() {
        return obstetricosList;
    }

    public void setObstetricosList(List<Obstetricos> obstetricosList) {
        this.obstetricosList = obstetricosList;
    }

    public List<ParienteEnfermedadPaciente> getParienteEnfermedadPacienteList() {
        return parienteEnfermedadPacienteList;
    }

    public void setParienteEnfermedadPacienteList(List<ParienteEnfermedadPaciente> parienteEnfermedadPacienteList) {
        this.parienteEnfermedadPacienteList = parienteEnfermedadPacienteList;
    }

    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Parroquia idParroquia) {
        this.idParroquia = idParroquia;
    }
    
    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getMovilContacto() {
        return movilContacto;
    }

    public void setMovilContacto(String movilContacto) {
        this.movilContacto = movilContacto;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }
    
    
    public String getNombres(){
        return (this.apellido1 +" "+ this.apellido2 +" "+ this.nombre1 +" "+ this.nombre2).toUpperCase();
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.ip.Paciente[ id=" + id + " ]";
    }
    
}
