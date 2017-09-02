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
public class Rol {
    private int id;
    private String descripcion;
    private int val;

    public Rol(int id, String descripcion,int val) {
        this.id = id;
        this.descripcion = descripcion;
        this.val = val;
    }
    
    public Rol(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.val = 0;
    }
    
    public Rol(int id) {
        this.id = id;
    }
    
    public Rol() {
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
