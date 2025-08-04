package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Paciente paciente;
    private Date fecha;
    private ArrayList<Consulta> consultasFacturadas;
    private ArrayList<RegistroVacunacion> vacunasFacturadas;
    private double subTotal;
    private double descuento;
    private double totalPagado;

    public Factura(String id, Paciente paciente, Date fecha, ArrayList<Consulta> consultas, ArrayList<RegistroVacunacion> vacunas, double subTotal, double descuento, double totalPagado) {
        this.id = id;
        this.paciente = paciente;
        this.fecha = fecha;
        this.consultasFacturadas = consultas;
        this.vacunasFacturadas = vacunas;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.totalPagado = totalPagado;
    }

    public String getId() { 
    	return id; 
    	}
    
    public Paciente getPaciente() { 
    	return paciente; 
    }
    
    public Date getFecha() { 
    	return fecha; 
    }
    
    public ArrayList<Consulta> getConsultasFacturadas() { 
    	return consultasFacturadas; 
    }
    
    public ArrayList<RegistroVacunacion> getVacunasFacturadas() { 
    	return vacunasFacturadas; 
    }
    
    public double getSubTotal() { 
    	return subTotal; 
    }
    
    public double getDescuento() { 
    	return descuento; 
    }
    
    public double getTotalPagado() { 
    	return totalPagado; 
    }
}