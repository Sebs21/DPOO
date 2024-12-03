package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int idFactura;
	private String descripcion;
	private String enfermedad;
	private Date fechaConsulta;
	private String seguro;
	private Doctor doctor;
	private Paciente paciente;
	private boolean importancia; 
	
	public Consulta(String id, int idFactura, String descripcion, String enfermedad, Date fechaConsulta,
			String seguro, Doctor doctor, Paciente paciente, boolean importancia) {
		super();
		this.id = id;
		this.idFactura = idFactura;
		this.descripcion = descripcion;
		this.enfermedad = enfermedad;
		this.fechaConsulta = fechaConsulta;
		this.seguro = seguro;
		this.doctor = doctor;
		this.paciente = paciente;
		this.importancia = importancia;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
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

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
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
	
}
