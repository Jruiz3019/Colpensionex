/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.repositorio.csv;
import java.util.LinkedList;
/**
 *
 * @author Jorge
 */
   

public abstract class RepositorioBase<T> {
    
    public abstract LinkedList<T> leerTodasLasFilas();
    public abstract void escribirFila(T elemento);
}


