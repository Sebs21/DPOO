package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    
    public Enfermedad(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Sobrescribimos toString() para que los JComboBox muestren el nombre
    @Override
    public String toString() {
        return nombre;
    }
}