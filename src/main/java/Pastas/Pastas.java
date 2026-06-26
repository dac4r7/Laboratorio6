/*
 

*/
package Pastas;

import Pastas.TipoPasta;

/**
 *
 * @author diego
 */
 public abstract class Pastas
 {
     private int cantidad ;
     public TipoPasta tipo;
     private String medidaCantidad;
     private double precio;
     
    Pastas( double precio){
    
    this.precio = precio;
    }
    
    Pastas(){
        
    }
     public abstract void agregarCantidad();
    
    public abstract int obtenerCantidad();
    
    public abstract TipoPasta obtenerTipo(); 
    
    public abstract void descripcion();
    
    public abstract double getPrecio();
             
 }