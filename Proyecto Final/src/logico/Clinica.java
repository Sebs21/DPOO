package logico;

import java.util.ArrayList;

public class Clinica 
{
	
	private ArrayList<Persona> misPersonas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Facturar> misFacturas;
	private ArrayList<Control_enfermedad> control_enfer;
	private ArrayList<Control_vacunacion> control_vacu;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Doctor> misDoctores;
	
	public static int idPersona;
	public static int idConsulta;
	public static int idFactura;
	public static int controlEnfemerdad;
	public static int controlVacuna;
	
	public static Clinica clinica = null;
	
	public Clinica() 
	{
		super();
		misPersonas = new ArrayList<>();
		misPacientes = new ArrayList<>();
		misDoctores = new ArrayList<>();
		misConsultas = new ArrayList<>();
		misFacturas = new ArrayList<>();
		control_enfer = new ArrayList<>();
		control_vacu = new ArrayList<>();
	}
	
	public static Clinica getInstance ()
	{
		if ( clinica == null )
		{
			clinica = new Clinica();
		}
		
		return clinica;
		
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public ArrayList<Doctor> getMisDoctores() {
		return misDoctores;
	}

	public void setMisDoctores(ArrayList<Doctor> misDoctores) {
		this.misDoctores = misDoctores;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}
	
	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}
	
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
	public ArrayList<Facturar> getMisFacturas() {
		return misFacturas;
	}
	
	public void setMisFacturas(ArrayList<Facturar> misFacturas) {
		this.misFacturas = misFacturas;
	}
	
	public ArrayList<Control_enfermedad> getControl_Enfer() {
		return control_enfer;
	}
	
	public void setControl_Enfer(ArrayList<Control_enfermedad> control_Enfer) {
		this.control_enfer = control_Enfer;
	}
	
	public ArrayList<Control_vacunacion> getControl_Vacu() {
		return control_vacu;
	}
	
	public void setControl_Vacu(ArrayList<Control_vacunacion> control_Vacu) {
		this.control_vacu = control_Vacu;
	}
	
	public Consulta buscarConsultaById ( String id )
	{
		
		boolean findit = false;
		Consulta aux = null;
		int ind = 0;
		
		while ( !findit && ind < misConsultas.size() )
		{
			if ( misConsultas.get( ind ).getId().equalsIgnoreCase( id ) )
			{
				aux = misConsultas.get( ind );
				findit = true;
			}
			
			ind++;
			
		}
		
		return aux;
		
	}
	
	public Paciente buscarPacienteById ( String id )
	{
		
		boolean findit = false;
		Paciente aux = null;
		int ind = 0;
		
		while ( !findit && ind < misPacientes.size() )
		{
			if ( misPacientes.get( ind ).getIdCodPaciente().equalsIgnoreCase( id ) )
			{
				aux = misPacientes.get( ind );
				findit = true;
			}
			
			ind++;
			
		}
		
		return aux;
		
	}
	
	public Doctor buscarDoctorByCedula ( String cedula )
	{
		
		boolean findit = false;
		Doctor aux = null;
		int ind = 0;
		
		while ( !findit && ind < misDoctores.size() )
		{
			if ( misDoctores.get( ind ).getCedula().equalsIgnoreCase( cedula ) )
			{
				aux = misDoctores.get( ind );
				findit = true;
			}
			
			ind++;
			
		}
		
		return aux;
		
	}
	
	public Facturar buscarFacturaById ( String id )
	{
		
		boolean findit = false;
		Facturar aux = null;
		int ind = 0;
		
		while ( !findit && ind < misFacturas.size() )
		{
			if ( misFacturas.get( ind ).getId().equalsIgnoreCase( id ) )
			{
				aux = misFacturas.get( ind );
				findit = true;
			}
			
			ind++;
			
		}
		
		return aux;
		
	}
	
	public void eliminarPaciente( Paciente aux )
	{
		misPacientes.remove( aux );
	}
	
	public void eliminarDoctor( Doctor aux )
	{
		misDoctores.remove( aux );
	}
	
	public void eliminarConsulta( Consulta aux )
	{
		misConsultas.remove( aux );
	}
	
	public void eliminarFactura( Facturar aux )
	{
		misFacturas.remove( aux );
	}
	
}
