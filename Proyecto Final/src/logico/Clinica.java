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
	private ArrayList<Seguro> misSeguros;
	//
	public static int idPersona;
	public static int idConsulta;
	public static int idFactura;
	public static int idCita;
	public static int idSeguro;
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
		controlEnfermerdad = 1;
		controlVacuna = 1;
		idCita = 1;
		idSeguro = 1;
		
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
	
	public static int getIdFactura() {
		return idFactura;
	}

	public static void setIdFactura(int idFactura) {
		Clinica.idFactura = idFactura;
	}
	
	public ArrayList<Seguro> getMisSeguros() {
		return misSeguros;
	}

	public void setMisSeguros(ArrayList<Seguro> misSeguros) {
		this.misSeguros = misSeguros;
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
	
	public Paciente buscarPacienteByCedula ( String cedula )
	{
		
		boolean findit = false;
		Paciente aux = null;
		int ind = 0;
		
		while ( !findit && ind < misPacientes.size() )
		{
			if ( misPacientes.get( ind ).getCedula().equalsIgnoreCase( cedula ) )
			{
				aux = misPacientes.get( ind );
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
	
	public void eliminarSeguro ( Seguro aux )
	{
		misSeguros.remove( aux );
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
		idFactura++;
	}
	
	public void agregarSeguro ( Seguro aux )
	{
		misSeguros.add( aux );
		idSeguro++;
	}
	
	/*
	
	public ArrayList<Paciente> pacientesNoSeleccionados ()
	{
		
		ArrayList<Paciente> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Paciente paciente : misPacientes )
		{
			if ( !paciente.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( paciente );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Doctor> doctoresNoSeleccionados ()
	{
		
		ArrayList<Doctor> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Doctor doctor : misDoctores )
		{
			if ( !doctor.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( doctor );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Consulta> consultasNoSeleccionadas ()
	{
		
		ArrayList<Consulta> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Consulta consulta : misConsultas )
		{
			if ( !consulta.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( consulta );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Cita> citasNoSeleccionadas ()
	{
		
		ArrayList<Cita> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Cita cita : misCitas )
		{
			if ( !cita.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( cita );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	public ArrayList<Seguro> segurosNoSeleccionados ()
	{
		
		ArrayList<Seguro> noSeleccionadosArrayList = new ArrayList<>();
		
		for ( Seguro seguro : misSeguros )
		{
			if ( !seguro.getSeleccionado() )
			{
				noSeleccionadosArrayList.add( seguro );
			}
		}
		
		return noSeleccionadosArrayList;
		
	}
	
	*/
	
	public ArrayList<Paciente> pacientesSeleccionados ()
	{
		
		ArrayList<Paciente> seleccionadosArrayList = new ArrayList<>();
		
		for ( Paciente paciente : misPacientes )
		{
			if ( paciente.getSeleccionado() )
			{
				seleccionadosArrayList.add( paciente );
			}
		}
		
		return seleccionadosArrayList;
		
	}
	
	public ArrayList<Doctor> doctoresSeleccionados ()
	{
		
		ArrayList<Doctor> seleccionadosArrayList = new ArrayList<>();
		
		for ( Doctor doctor : misDoctores )
		{
			if ( doctor.getSeleccionado() )
			{
				seleccionadosArrayList.add( doctor );
			}
		}
		
		return seleccionadosArrayList;
		
	}
	
	public ArrayList<Consulta> consultasSeleccionadas ()
	{
		
		ArrayList<Consulta> seleccionadosArrayList = new ArrayList<>();
		
		for ( Consulta consulta : misConsultas )
		{
			if ( consulta.getSeleccionado() )
			{
				seleccionadosArrayList.add( consulta );
			}
		}
		
		return seleccionadosArrayList;
		
	}
	
	public ArrayList<Cita> citasSeleccionadas ()
	{
		
		ArrayList<Cita> seleccionadosArrayList = new ArrayList<>();
		
		for ( Cita cita : misCitas )
		{
			if ( cita.getSeleccionado() )
			{
				seleccionadosArrayList.add( cita );
			}
		}
		
		return seleccionadosArrayList;
		
	}

	public ArrayList<Seguro> segurosSeleccionados ()
	{
		
		ArrayList<Seguro> seleccionadosArrayList = new ArrayList<>();
		
		for ( Seguro seguro : misSeguros )
		{
			if ( seguro.getSeleccionado() )
			{
				seleccionadosArrayList.add( seguro );
			}
		}
		
		return seleccionadosArrayList;
		
	}
	
}
