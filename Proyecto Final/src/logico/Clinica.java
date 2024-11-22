package logico;

import java.util.ArrayList;

public class Clinica 
{
	
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Facturar> misFacturas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Control_enfermedad> control_enfer;
	private ArrayList<Control_vacunacion> control_vacu;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Doctor> misDoctores;
	
	public static int idPersona;
	public static int idConsulta;
	public static int idFactura;
	public static int idCita;
	public static int controlEnfermerdad;
	public static int controlVacuna;
	
	public static Clinica clinica = null;
	
	public Clinica() 
	{
		super();
		misPacientes = new ArrayList<>();
		misDoctores = new ArrayList<>();
		misConsultas = new ArrayList<>();
		misFacturas = new ArrayList<>();
		control_enfer = new ArrayList<>();
		control_vacu = new ArrayList<>();
		
		idPersona = 1;
		idConsulta = 1;
		idFactura = 1;
		controlEnfermedad = 1;
		controlVacuna = 1;
		idCita = 1;
		
	}
	
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public static int getControlEnfermerdad() {
		return controlEnfermerdad;
	}

	public static void setControlEnfermerdad(int controlEnfermerdad) {
		Clinica.controlEnfermerdad = controlEnfermerdad;
	}

	public static int getControlVacuna() {
		return controlVacuna;
	}

	public static void setControlVacuna(int controlVacuna) {
		Clinica.controlVacuna = controlVacuna;
	}

	public static Clinica getClinica() {
		return clinica;
	}

	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
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
	
	public void eliminarCita ( Cita aux )
	{
		misCitas.remove( aux );
	}
	
	public void agregarDoctor ( Doctor aux )
	{
		misDoctores.add( aux );
	}
	
	public void agregarPaciente ( Paciente aux )
	{
		misPacientes.add( aux );
	}
	
	public void agregarConsulta ( Consulta aux )
	{
		misConsultas.add( aux );
	}
	
	public void agregarCita ( Cita aux )
	{
		misCitas.add( aux );
	}
	
	public void agregarFacturar ( Facturar aux )
	{
		misFacturas.add( aux );
	}
	
	public Facturar calcularFacturasTotales ( Facturar aux )
	{
		
	}
	
	public ArrayList<Paciente> PacientesNoSeleccionados ()
	{
		
		ArrayList<Paciente> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Paciente paciente : misPacientes )
		{
			if ( !paciente )
			{
				noSeleccionadosArrayList.add( paciente );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Doctor> DoctoresNoSeleccionados ()
	{
		
		ArrayList<Paciente> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Queso queso : misQuesos )
		{
			if ( !queso.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( queso );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Consulta> ConsultasNoSeleccionadas ()
	{
		
		ArrayList<Paciente> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Queso queso : misQuesos )
		{
			if ( !queso.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( queso );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Cita> CitasNoSeleccionadas ()
	{
		
		ArrayList<Paciente> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Queso queso : misQuesos )
		{
			if ( !queso.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( queso );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	
}
