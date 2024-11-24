package logico;

public class Doctor extends Persona 
{
	
	private String especialidad;
	private boolean seleccionado;

	public Doctor( String cedula, String nombre, String apellido, String especialidad ) 
	{
		super( cedula, nombre, apellido );
		this.especialidad = especialidad;
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
