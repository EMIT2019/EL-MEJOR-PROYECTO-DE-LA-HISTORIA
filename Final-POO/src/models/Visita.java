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
public class Visita {
    private String codigo;
    private String usuario;
    private int numeroCasa;
    private String visitante;
    private Date fechaHora;
    private String razonVisita;
    private String transporte;
    private String matricula;
    
    public Visita() {
        
    }
    
    public Visita(String codigo, String usuario, int numeroCasa, String visitante, Date fechaHora, String razonVisita, String transporte, String matricula) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.numeroCasa = numeroCasa;
        this.visitante = visitante;
        this.fechaHora = fechaHora;
        this.razonVisita = razonVisita;
        this.transporte = transporte;
        this.matricula = matricula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getRazonVisita() {
        return razonVisita;
    }

    public void setRazonVisita(String razonVisita) {
        this.razonVisita = razonVisita;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
}
