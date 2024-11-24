package logico;

import java.util.Date;

public class inve_vacunacion 
{
	
	private int cant_vacuna;
	private String nombre_vacuna;
	private Date fechaRegistroVacuna;
	
	public inve_vacunacion( int cant_vacuna, String nombre_vacuna, Date fechaRegistroVacuna ) 
	{
		super();
		this.cant_vacuna = cant_vacuna;
		this.nombre_vacuna = nombre_vacuna;
		this.fechaRegistroVacuna = fechaRegistroVacuna;
	}

	public int getCant_vacuna() {
		return cant_vacuna;
	}
	
	public void setCant_vacuna(int cant_vacuna) {
		this.cant_vacuna = cant_vacuna;
	}
	
	public String getNombre_vacuna() {
		return nombre_vacuna;
	}
	
	public void setNombre_vacuna(String nombre_vacuna) {
		this.nombre_vacuna = nombre_vacuna;
	}
	
	public Date getFechaRegistroVacuna() {
		return fechaRegistroVacuna;
	}
	
	public void setFechaRegistroVacuna(Date fechaRegistroVacuna) {
		this.fechaRegistroVacuna = fechaRegistroVacuna;
	}
	
	
	
}
