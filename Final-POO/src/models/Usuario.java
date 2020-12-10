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
public class Usuario extends Persona {
    private String carnet;
    private String pw;
    
    public Usuario() {
        
    }
    
    public Usuario(String cedula, String nombres, String apellidos, String carnet, String pw) {
        super(cedula, nombres, apellidos);
        this.carnet = carnet;
        this.pw = pw;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    

    
    
    
    
    
    
}
