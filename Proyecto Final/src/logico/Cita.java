package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCita;
	private Doctor doctor;
	private Persona persona;
	private Date fechaCita;
	private boolean seleccionado;

	public Cita( String idCita, Doctor doctor, Persona persona, Date fechaCita ) 
	{
		super();
		this.idCita = idCita;
		this.doctor = doctor;
		this.persona = persona;
		this.fechaCita = fechaCita;
	}
	
	public String getIdCita() {
		return idCita;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	public boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
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
