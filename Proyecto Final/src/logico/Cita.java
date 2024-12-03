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
	private Paciente paciente;
	private Date fechaCita;
	private boolean seleccionado;

	public Cita( String idCita, Doctor doctor, Paciente paciente, Date fechaCita ) 
	{
		super();
		this.idCita = idCita;
		this.doctor = doctor;
		this.paciente = paciente;
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
	
	public Date getFechaCita() {
		return fechaCita;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	
}
