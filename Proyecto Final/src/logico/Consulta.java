package logico;

import java.util.Date;

public class Consulta 
{
	private String id;
	private String descripcion;
	private String enfermedad;
	private Date fechaConsulta;
	private Seguro seguro;
	private Doctor doctor;
	private Paciente paciente;
	private boolean importancia;
	private boolean noimportancia;
	private boolean seleccionado;
	
	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Consulta ( String id, String enfermedad, Date fechaConsulta, String descripcion, boolean importancia )
	{
		this.id = id;
		this.enfermedad = enfermedad;
		this.fechaConsulta = fechaConsulta;
		this.descripcion = descripcion;
		
		if ( importancia == false )
		{
			noimportancia = true;
		}
		else
		{
			importancia = true;
			noimportancia = false;
		}
		
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isImportancia() {
		return importancia;
	}

	public void setImportancia(boolean importancia) {
		this.importancia = importancia;
	}

	public boolean isNoimportancia() {
		return noimportancia;
	}

	public void setNoimportancia(boolean noimportancia) {
		this.noimportancia = noimportancia;
	}
	
	
}
