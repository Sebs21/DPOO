package logico;

import java.io.Serializable;

public class vacunacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idVacuna;
    private String nombre;
    private String fabricante;
    private int cantidadDisponible; // ATRIBUTO CLAVE PARA EL INVENTARIO

    public vacunacion(String idVacuna, String nombre, String fabricante) {
        this.idVacuna = idVacuna;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.cantidadDisponible = 0; // Inicia en 0 por defecto
    }

    // Métodos para manejar el stock
    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidadDisponible += cantidad;
        }
    }

    public void descontarStock(int cantidad) {
        if (cantidad > 0 && this.cantidadDisponible >= cantidad) {
            this.cantidadDisponible -= cantidad;
        }
    }

    // Getters y Setters
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