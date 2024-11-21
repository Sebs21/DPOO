package logico;

import java.util.ArrayList;

public class Paciente extends Persona {	
	
	private String idCodPaciente;
	private String enfermedad;
	private int edad;
	private Seguro seguro;
	private ArrayList<HistoriaClinica>miHistoriaClinica;
	
	public Paciente(String cedula, String nombre, String apellido, String idCodPaciente, String enfermedad, int edad,
			Seguro seguro, ArrayList<HistoriaClinica> miHistoriaClinica) {
		super(cedula, nombre, apellido);
		this.idCodPaciente = idCodPaciente;
		this.enfermedad = enfermedad;
		this.edad = edad;
		this.seguro = seguro;
		this.miHistoriaClinica = miHistoriaClinica;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public String getIdCodPaciente() {
		return idCodPaciente;
	}

	public void setIdCodPaciente(String idCodPaciente) {
		this.idCodPaciente = idCodPaciente;
	}

	public ArrayList<HistoriaClinica> getMiHistoriaClinica() {
		return miHistoriaClinica;
	}

	public void setMiHistoriaClinica(ArrayList<HistoriaClinica> miHistoriaClinica) {
		this.miHistoriaClinica = miHistoriaClinica;
	}
	
}

