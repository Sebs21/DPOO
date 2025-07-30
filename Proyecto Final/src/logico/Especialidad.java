package logico;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String idEspecialidad;

    
    public Especialidad(String idEspecialidad, String nombre) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

	public String getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(String idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
}