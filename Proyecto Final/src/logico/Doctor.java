package logico;

import java.util.ArrayList;

public class Doctor extends Persona  
{
	private static final long serialVersionUID = 1L;
	private String especialidad;
	private ArrayList<Paciente>misPacientes;
	private User user;
	
	private boolean seleccionado;

	public Doctor( String cedula, String nombre, String apellido, String especialidad, String edad, String sexo, User user ) 
	{
		super(cedula, nombre, apellido, edad, sexo);
		this.especialidad = especialidad;
		this.misPacientes = new ArrayList<Paciente>();
		this.user = user;
	}
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}
