package logico;

import java.util.ArrayList;

public class Paciente extends Persona 
{	
	private int idCodPaciente;
	private String enfermedad;
	private int edad;
	private Seguro seguro;
	private ArrayList<HistoriaClinica>miHistoriaClinica;
	private ArrayList<vacunacion>miVacuna;
	private ArrayList<Facturar> miFactura;
	private User user;
	
	private boolean seleccionado;

	public Paciente(String cedula, String nombre, String apellido, int idCodPaciente) {
		super(cedula, nombre, apellido);
		this.idCodPaciente = idCodPaciente;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
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

	public Seguro getSeguro() 
	{
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public int getIdCodPaciente() {
		return idCodPaciente;
	}

	public void setIdCodPaciente(int idCodPaciente) {
		this.idCodPaciente = idCodPaciente;
	}

	public ArrayList<HistoriaClinica> getMiHistoriaClinica() {
		return miHistoriaClinica;
	}

	public void setMiHistoriaClinica(ArrayList<HistoriaClinica> miHistoriaClinica) {
		this.miHistoriaClinica = miHistoriaClinica;
	}
	
	public ArrayList<vacunacion> getMiVacuna() {
		return miVacuna;
	}

	public void setMiVacuna(ArrayList<vacunacion> miVacuna) {
		this.miVacuna = miVacuna;
	}
	
	public ArrayList<Facturar> getMiFactura() {
		return miFactura;
	}

	public void setMiFactura(ArrayList<Facturar> miFactura) {
		this.miFactura = miFactura;
	}
	
}

