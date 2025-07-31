package logico;

import java.io.Serializable;

public abstract class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String edad;
	protected String sexo;

	public Persona( String cedula, String nombre, String apellido, String edad, String sexo) 
	{
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad= edad;
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEdad() {
		return edad;
	}
	
	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
