package logico;

import java.util.ArrayList;

public class Paciente extends Persona {

    private static final long serialVersionUID = 1L;
    private int idCodPaciente;
    private Seguro seguro;
    private User user;
    private ArrayList<Enfermedad> historialDeEnfermedades;

    private HistoriaClinica miHistoriaClinica;
    private ArrayList<Factura> miFactura;
    private ArrayList<Bajo_vigilancia> vigi;
    private ArrayList<RegistroVacunacion> misVacunasAplicadas;

    private boolean seleccionado;
    private boolean consultaPagada;

    public Paciente(String cedula, String nombre, String apellido, int idCodPaciente, String edad, String sexo, User user) {
        super(cedula, nombre, apellido, edad, sexo);
        this.idCodPaciente = idCodPaciente;
        this.user = user;

        this.miHistoriaClinica = new HistoriaClinica();
        this.miFactura = new ArrayList<>();
        this.vigi = new ArrayList<>();
        this.misVacunasAplicadas = new ArrayList<>();
        this.historialDeEnfermedades = new ArrayList<>();
    }
    
    public ArrayList<Enfermedad> getHistorialDeEnfermedades() {
        return historialDeEnfermedades;
    }

    public void setHistorialDeEnfermedades(ArrayList<Enfermedad> historialDeEnfermedades) {
        this.historialDeEnfermedades = historialDeEnfermedades;
    }
    
    public void agregarEnfermedadAlHistorial(Enfermedad enfermedad) {
        if (this.historialDeEnfermedades == null) {
            this.historialDeEnfermedades = new ArrayList<>();
        }
        
        if (!this.historialDeEnfermedades.contains(enfermedad)) {
            this.historialDeEnfermedades.add(enfermedad);
        }
    }
    
    public void agregarConsultaAlHistorial(Consulta consulta) {
        if (this.miHistoriaClinica == null) {
            this.miHistoriaClinica = new HistoriaClinica();
        }
        this.miHistoriaClinica.getMisConsultas().add(consulta);
    }

    public ArrayList<RegistroVacunacion> getMisVacunasAplicadas() {
        return misVacunasAplicadas;
    }

    public void agregarVacunaAplicada(RegistroVacunacion registro) {
        if (this.misVacunasAplicadas == null) {
            this.misVacunasAplicadas = new ArrayList<>();
        }
        misVacunasAplicadas.add(registro);
    }

    public boolean isConsultaPagada() {
		return consultaPagada;
	}

	public void setConsultaPagada(boolean consultaPagada) {
		this.consultaPagada = consultaPagada;
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

	
	
	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
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

	public HistoriaClinica getMiHistoriaClinica() {
		return miHistoriaClinica;
	}

	public void setMiHistoriaClinica(HistoriaClinica miHistoriaClinica) {
		this.miHistoriaClinica = miHistoriaClinica;
	}
	
	public ArrayList<Factura> getMiFactura() {
		return miFactura;
	}

	public void setMiFactura(ArrayList<Factura> miFactura) {
		this.miFactura = miFactura;
	}
    
    public void agregarenfermedad(Bajo_vigilancia neewnfe) {
        if(this.vigi == null){
            this.vigi = new ArrayList<>();
        }
        vigi.add(neewnfe);
    }
}