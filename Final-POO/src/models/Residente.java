/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.Date;

/**
 *
 * @author JJ
 */
public class Residente extends Persona {
    private int dependientes;
    private int numeroCasa;
    
    public Residente () {
        
    }
    
    public Residente(String cedula, String nombres, String apellidos, int dependientes, int numeroCasa) {
        super(cedula, nombres, apellidos);
        this.dependientes = dependientes;
        this.numeroCasa = numeroCasa;
    }

    public int getDependientes() {
        return dependientes;
    }

    public void setDependientes(int dependientes) {
        this.dependientes = dependientes;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }
    
    
    
    
    
}
