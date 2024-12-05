package logico;

import java.io.Serializable;
import java.util.Date;

public class Bajo_vigilancia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codVigilancia;
	private String enfermedad;
	private float tiempoVigilancia;
	 private String codigoPaciente; 
	 private String codigodoctor; 
	 private Date fecha_Vacunacion;
	//
	
	 
	 
	 

	public String getCodVigilancia() {
		return codVigilancia;
	}

	

	public Bajo_vigilancia(String codVigilancia, String enfermedad, float tiempoVigilancia, 
			String codigoPaciente, String codigodoctor, Date fecha_Vacunacion) {
		super();
		this.codVigilancia = codVigilancia;
		this.enfermedad = enfermedad;
		this.tiempoVigilancia = tiempoVigilancia;
	
		this.codigoPaciente = codigoPaciente;
		this.codigodoctor = codigodoctor;
		this.fecha_Vacunacion = fecha_Vacunacion;
	}



	public String getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public String getCodigodoctor() {
		return codigodoctor;
	}

	public void setCodigodoctor(String codigodoctor) {
		this.codigodoctor = codigodoctor;
	}

	public void setTiempoVigilancia(float tiempoVigilancia) {
		this.tiempoVigilancia = tiempoVigilancia;
	}

	public void setCodVigilancia(String codVigilancia) {
		this.codVigilancia = codVigilancia;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public float getTiempoVigilancia() {
		return tiempoVigilancia;
	}

	public void setTiempoVigilancia(int tiempoVigilancia) {
		this.tiempoVigilancia = tiempoVigilancia;
	}



	public Date getFecha_Vacunacion() {
		return fecha_Vacunacion;
	}

	public void setFecha_Vacunacion(Date fecha_Vacunacion) {
		this.fecha_Vacunacion = fecha_Vacunacion;
	}
	
}
