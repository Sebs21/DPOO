package logico;

import java.util.ArrayList;

public class Paciente extends Persona {

    private static final long serialVersionUID = 1L;
    private int idCodPaciente;
    private String enfermedad;
    private Seguro seguro;
    private User user;

    private HistoriaClinica miHistoriaClinica;
    private ArrayList<Facturar> miFactura;
    private ArrayList<Bajo_vigilancia> vigi;
    private ArrayList<RegistroVacunacion> misVacunasAplicadas;

    private boolean seleccionado;
    private boolean consultaPagada;

    public Paciente(String cedula, String nombre, String apellido, int idCodPaciente, String edad, User user) {
        super(cedula, nombre, apellido, edad);
        this.idCodPaciente = idCodPaciente;
        this.user = user;

        this.miHistoriaClinica = new HistoriaClinica();
        this.miFactura = new ArrayList<>();
        this.vigi = new ArrayList<>();
        this.misVacunasAplicadas = new ArrayList<>();
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

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
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
	
	public ArrayList<Facturar> getMiFactura() {
		return miFactura;
	}

	public void setMiFactura(ArrayList<Facturar> miFactura) {
		this.miFactura = miFactura;
	}
    
    public void agregarenfermedad(Bajo_vigilancia neewnfe) {
        if(this.vigi == null){
            this.vigi = new ArrayList<>();
        }
        vigi.add(neewnfe);
    }
}