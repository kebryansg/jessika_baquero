/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

/**
 *
 * @author Deivi
 */
public class Establecimiento {
    private int id;
    private String nombre;
    private String encargado;    
    private int parroquia;
    private String direccion;
    private String telefono;
    private String email;
    private String logo;

    public Establecimiento(int id) {
        this.id = id;
    }
    public Establecimiento() {
        
    }
    public Establecimiento(int id, String nombre, String encargado, int parroquia, String direccion, String telefono, String email, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.encargado = encargado;        
        this.parroquia = parroquia;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
    }

    public Establecimiento(String nombre, String encargado, int parroquia, String direccion, String telefono, String email, String logo) {
        this.nombre = nombre;
        this.encargado = encargado;       
        
        this.parroquia = parroquia;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.logo = logo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    public int getParroquia() {
        return parroquia;
    }

    public void setParroquia(int parroquia) {
        this.parroquia = parroquia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    
}
