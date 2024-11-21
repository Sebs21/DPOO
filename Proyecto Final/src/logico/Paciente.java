package logico;

public class Paciente 
{
	
	private String idCodPaciente;
	private String enfermedad;
	private int edad;
	private Seguro seguro;
	
	public Paciente ( String idCodPaciente, String enfermedad, int edad )
	{
		this.idCodPaciente = idCodPaciente;
		this.enfermedad = enfermedad;
		this.edad = edad;
		this.seguro = seguro;
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
	
}


