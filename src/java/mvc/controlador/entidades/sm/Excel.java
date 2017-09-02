/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador.entidades.sm;

import java.io.File;

/**
 *
 * @author Deivi
 */
public class Excel {
    private File archivoEntrada;
    private File archivoSalida;

    public File getArchivoEntrada() {
        return archivoEntrada;
    }

    public void setArchivoEntrada(File archivoEntrada) {
        this.archivoEntrada = archivoEntrada;
    }

    public File getArchivoSalida() {
        return archivoSalida;
    }

    public void setArchivoSalida(File archivoSalida) {
        this.archivoSalida = archivoSalida;
    }

    public Excel(File archivoEntrada, File archivoSalida) {
        this.archivoEntrada = archivoEntrada;
        this.archivoSalida = archivoSalida;
    }

    public Excel() {
    }
    
    
}
