/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author JJ
 */
public class Visitante extends Persona {
    private boolean vetado;
    
    public Visitante() {
        
    }
    
    public Visitante(String cedula, String nombres, String apellidos, boolean vetado) {
        super(cedula, nombres, apellidos);
        this.vetado = vetado;
    }

    public boolean getVetado() {
        return vetado;
    }

    public void setVetado(boolean vetado) {
        this.vetado = vetado;
    }
    
    
    
    
}
