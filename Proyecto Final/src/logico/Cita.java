package logico;

import java.util.Date;

public class Cita 
{
	private Doctor doctor;
	private Persona persona;
	private Date fechaCita;
	private boolean seleccionado;
	
	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Cita( Doctor doctor, Persona persona, Date fechaCita ) 
	{
		super();
		this.doctor = doctor;
		this.persona = persona;
		this.fechaCita = fechaCita;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Date getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	
}
