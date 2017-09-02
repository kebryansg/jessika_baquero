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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author kebryan
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m")})
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre1")
    private String nombre1;
    @Column(name = "nombre2")
    private String nombre2;
    @Column(name = "apellidos1")
    private String apellidos1;
    @Column(name = "apellidos2")
    private String apellidos2;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "telefonoDomicilio")
    private String telefonoDomicilio;
    @Column(name = "telefonoOficina")
    private String telefonoOficina;
    @Column(name = "telefonoMovil")
    private String telefonoMovil;
    @Column(name = "email")
    private String email;
     @Column(name = "visible")
    private int visible;
     private int registros;
    public int getVisible() {
        return visible;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
    @Column(name = "cedula")
    private String cedula;
    @OneToMany(mappedBy = "idMedico")
    private List<MedicoEspecialidad> medicoEspecialidadList;

    public Medico() {
    }
    public Medico(int id)
    {
        this.id=id;
    }
    public Medico(String nombre1, String nombre2, String apellidos1, String apellidos2,String domicilio,String ciudad, String telefonoDomicilio,String telefonoOficina, String telefonoMovil, String email, String cedula, int visible)
    {
        this.nombre1=nombre1;
        this.nombre2=nombre2;
        this.apellidos1=apellidos1;
        this.apellidos2=apellidos2;
        this.domicilio=domicilio;
        this.ciudad=ciudad;
        this.telefonoDomicilio=telefonoDomicilio;
        this.telefonoOficina=telefonoOficina;
        this.telefonoMovil=telefonoMovil;
        this.email=email;
        this.cedula=cedula;
        this.visible=visible;
    }

    public Medico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApellidos1() {
        return apellidos1;
    }

    public void setApellidos1(String apellidos1) {
        this.apellidos1 = apellidos1;
    }

    public String getApellidos2() {
        return apellidos2;
    }

    public void setApellidos2(String apellidos2) {
        this.apellidos2 = apellidos2;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<MedicoEspecialidad> getMedicoEspecialidadList() {
        return medicoEspecialidadList;
    }

    public void setMedicoEspecialidadList(List<MedicoEspecialidad> medicoEspecialidadList) {
        this.medicoEspecialidadList = medicoEspecialidadList;
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
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvc.controlador.entidades.sm.Medico[ id=" + id + " ]";
    }
    
}
