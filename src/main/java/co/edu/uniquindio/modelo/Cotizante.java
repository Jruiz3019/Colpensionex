/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Jorge
 */
public class Cotizante {
   
    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private char sexo;
    private double valorUnitario;
    private boolean estadoMora;
    private boolean embargable;

    public Cotizante(int id, String nombre, LocalDate fechaNacimiento, char sexo, double valorUnitario, boolean estadoMora, boolean embargable) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.valorUnitario = valorUnitario;
        this.estadoMora = estadoMora;
        this.embargable = embargable;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public boolean isEstadoMora() {
        return estadoMora;
    }

    public void setEstadoMora(boolean estadoMora) {
        this.estadoMora = estadoMora;
    }

    public boolean isEmbargable() {
        return embargable;
    }

    public void setEmbargable(boolean embargable) {
        this.embargable = embargable;
    }

    // Método para calcular la edad
    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + fechaNacimiento + "," + sexo + "," + valorUnitario + "," + estadoMora + "," + embargable;
    }
}


