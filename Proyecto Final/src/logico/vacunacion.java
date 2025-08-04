package logico;

import java.io.Serializable;

public class vacunacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idVacuna;
    private String nombre;
    private String fabricante;
    private int cantidadDisponible;
    private double precio;

    public vacunacion(String idVacuna, String nombre, String fabricante, double precio) {
        this.idVacuna = idVacuna;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.cantidadDisponible = 0;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void agregarStock(int cantidad) { 
    	if (cantidad > 0) this.cantidadDisponible += cantidad; 
    }
    
    public void descontarStock(int cantidad) { 
    	if (cantidad > 0 && this.cantidadDisponible >= cantidad) this.cantidadDisponible -= cantidad; 
    }
    public int getCantidadDisponible() { 
    	return cantidadDisponible; 
    }
    
    public void setCantidadDisponible(int cantidadDisponible) { 
    	this.cantidadDisponible = cantidadDisponible; 
    }
    
    public String getIdVacuna() { 
    	return idVacuna; 
    }
    
    public void setIdVacuna(String idVacuna) { 
    	this.idVacuna = idVacuna; 
    }
    
    public String getNombre() { 
    	return nombre; 
    }
    
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }
    
    public String getFabricante() { 
    	return fabricante; 
    }
    
    public void setFabricante(String fabricante) { 
    	this.fabricante = fabricante; 
    }
}