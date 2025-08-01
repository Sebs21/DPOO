package logico;

import java.io.Serializable;
import java.util.Date;

public class RegistroVacunacion implements Serializable {
    private static final long serialVersionUID = 1L;

    private vacunacion tipoVacuna; 
    private Date fechaAplicacion;
    private int cantidadMl;
    private boolean pagada; 

    public RegistroVacunacion(vacunacion tipoVacuna, Date fechaAplicacion, int cantidadMl) {
        this.tipoVacuna = tipoVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.cantidadMl = cantidadMl;
        this.pagada = false; // <-- CAMBIO: Por defecto, no est� pagada
    }
    
    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
    
    // --- Getters ---
    public vacunacion getTipoVacuna() {
        return tipoVacuna;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public int getCantidadMl() {
        return cantidadMl;
    }
}