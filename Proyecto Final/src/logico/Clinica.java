package logico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Clinica implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Consulta> misConsultas;//
	private ArrayList<Facturar> misFacturas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Control_enfermedad> control_enfer;//
	private ArrayList<Control_vacunacion> control_vacu;//
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Doctor> misDoctores;
	private ArrayList<Seguro> misSeguros;
	private ArrayList<User> misUsuarios;
	private ArrayList<vacunacion> misvacunas;
	
	public static int idDoctor;
	public static int idPaciente;
	public static int idConsulta;
	public static int idFactura;
	public static int idCita;	
	public static int idSeguro;
	public static int idcontrolEnfermerdad;
	public static int idcontrolVacuna;
	public static int idVacuna;
	public static int idUser;
	
	private static User LoginUser;
	public static Clinica clinica = null;

	
	public Clinica() 
	{
		super();
		misPacientes = new ArrayList<>();
		misDoctores = new ArrayList<>();
		misConsultas = new ArrayList<>();
		misFacturas = new ArrayList<>();		
		misSeguros = new ArrayList<>();
		misCitas = new ArrayList<>();
		control_enfer = new ArrayList<>();
		control_vacu = new ArrayList<>();
		
		misUsuarios = new ArrayList<>();
		
		idPaciente = 1;
		idDoctor = 1;
		idConsulta = 1;
		idFactura = 1;
		idcontrolEnfermerdad = 1;
		idcontrolVacuna = 1;
		idCita = 1;
		idSeguro = 1;
		idVacuna = 1;
		idUser=1;
		
	}
	
	public static Clinica getInstance ()
	{
		
		if ( clinica == null )
		{
			clinica = new Clinica();
		}
		
		return clinica;
		
	}
	//guarda informacion 
	 public void guardarClinica() 
	 {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clinica_info.dat"))) {
	            oos.writeObject(this);
	          
	        } catch (IOException e) {
	          
	            e.printStackTrace();
	        }
	    }

	   
	 public static Clinica cargarClinica() 
	 {
		 
		    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Clinica_info.dat"))) {
		    	
		        clinica = (Clinica) ois.readObject();
		        System.out.println("Archivo no encontrado");

		        return clinica;
		    } catch (IOException | ClassNotFoundException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	    
	    //agregar paciente.
	    
	    public void agregar_Paciente(Paciente con)
	    {
	    	if(misPacientes == null)
	    	{
	    		misPacientes = new ArrayList<>();
	    		
	    	}
	    	misPacientes.add(con);
	    }
	    
	    
	    public ArrayList<Paciente> obtener_Paciente() {
	        return misPacientes;
	        
	    }    
	    
	    
	
	    public void agregar_doctor(Doctor con)
	    {
	    	if(misDoctores == null)
	    	{
	    		misDoctores = new ArrayList<>();
	    		
	    	}
	    	misDoctores.add(con);
	    }
	    
	    
	    public ArrayList<Doctor> obtener_doctor() {
	        return misDoctores;
	        
	    }    
	    
	
	    public void agregar_seguro(Seguro con)
	    {
	    	if(misSeguros == null)
	    	{
	    		misSeguros = new ArrayList<>();
	    		
	    	}
	    	misSeguros.add(con);
	    }
	    
	    
	    public ArrayList<Seguro> obtener_seguro() {
	        return misSeguros;
	        
	    }    
	
	   
	
	    public void agregar_cita(Cita con)
	    {
	    	if(misCitas == null)
	    	{
	    		misCitas = new ArrayList<>();
	    		
	    	}
	    	misCitas.add(con);
	    }
	    
	    
	    public ArrayList<Cita> obtener_cita() {
	        return misCitas;
	        
	    }    
	
	    public void agregar_Factura(Facturar con)
	    {
	    	if(misFacturas == null)
	    	{
	    		misFacturas = new ArrayList<>();
	    		
	    	}
	    	misFacturas.add(con);
	    }
	    
	    
	    public ArrayList<Facturar> obtener_facturar() {
	        return misFacturas;
	        
	    }    
	
	    
	    
	    
	    public void agregar_consulta(Consulta con)
	    {
	    	if(misConsultas == null)
	    	{
	    		misConsultas = new ArrayList<>();
	    		
	    	}
	    	misConsultas.add(con);
	    }
	    
	    
	    public ArrayList<Consulta> obtener_consulta() {
	        return misConsultas;
	        
	    }    
	
	    
	    
	    public void agregar_control_enfermdedad(Control_enfermedad control)
	    {
	    	if(control_enfer == null)
	    	{
	    		control_enfer = new ArrayList<>();
	    		
	    	}
	    	control_enfer.add(control);
	    }
	    public ArrayList<Control_enfermedad> obtener_control_enfermedad() {
	        return control_enfer;
	        
	    }
	    
	    public void agregar_control_vacunacion(Control_vacunacion vacun)
	    {
	    	if(control_vacu == null)
	    	{
	    		control_vacu = new ArrayList<>();
	    		
	    	}
	    	control_vacu.add(vacun);
	    }
	    
	    
	    public ArrayList<Control_vacunacion> obtener_vaunacion() {
	        return control_vacu;
	        
	    }    
	    
	    //terminar de agregar paciente
	    
	
	
	
	

	public ArrayList<Control_enfermedad> getControl_enfer() {
		return control_enfer;
	}

	public ArrayList<vacunacion> getMisvacunas() {
		return misvacunas;
	}

	public void setMisvacunas(ArrayList<vacunacion> misvacunas) {
		this.misvacunas = misvacunas;
	}

	public void setControl_enfer(ArrayList<Control_enfermedad> control_enfer) {
		this.control_enfer = control_enfer;
	}

	public ArrayList<Control_vacunacion> getControl_vacu() {
		return control_vacu;
	}

	public void setControl_vacu(ArrayList<Control_vacunacion> control_vacu) {
		this.control_vacu = control_vacu;
	}

	public ArrayList<User> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<User> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public static User getLoginUser() {
		return LoginUser;
	}

	public static void setLoginUser(User loginUser) {
		LoginUser = loginUser;
	}
	
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public static Clinica getClinica() {
		return clinica;
	}

	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
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
	
	public static int getIdDoctor() {
		return idDoctor;
	}

	public static void setIdDoctor(int idDoctor) {
		Clinica.idDoctor = idDoctor;
	}

	public static int getIdPaciente() {
		return idPaciente;
	}

	public static void setIdPaciente(int idPaciente) {
		Clinica.idPaciente = idPaciente;
	}

	public static int getIdConsulta() {
		return idConsulta;
	}

	public static void setIdConsulta(int idConsulta) {
		Clinica.idConsulta = idConsulta;
	}

	public static int getIdCita() {
		return idCita;
	}

	public static void setIdCita(int idCita) {
		Clinica.idCita = idCita;
	}

	public static int getIdSeguro() {
		return idSeguro;
	}

	public static void setIdSeguro(int idSeguro) {
		Clinica.idSeguro = idSeguro;
	}

	public static int getIdVacuna() {
		return idVacuna;
	}

	public static void setIdVacuna(int idVacuna) {
		Clinica.idVacuna = idVacuna;
	}

	public static int getIdUser() {
		return idUser;
	}

	public static void setIdUser(int idUser) {
		Clinica.idUser = idUser;
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
	
	public Paciente buscarPacienteById ( int id )
	{
		
		boolean findit = false;
		Paciente aux = null;
		int ind = 0;
		
		while ( !findit && ind < misPacientes.size() )
		{
			if ( misPacientes.get( ind ).getIdCodPaciente() == id )
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
		idPaciente--;
	}
	
	public void eliminarDoctor( Doctor aux )
	{
		misDoctores.remove( aux );
		idDoctor--;
	}
	
	public void eliminarConsulta( Consulta aux )
	{
		misConsultas.remove( aux );
		idConsulta--;
	}
	
	public void eliminarFactura( Facturar aux )
	{
		misFacturas.remove( aux );
		idFactura--;
	}
	
	public void eliminarCita ( Cita aux )
	{
		misCitas.remove( aux );
		idCita--;
	}
	
	public void eliminarSeguro ( Seguro aux )
	{
		misSeguros.remove( aux );
		idSeguro--;
	}
	

	
	public void agregarDoctor ( Doctor aux )
	{
		misDoctores.add( aux );
		idDoctor++;
	}
	
	public void agregarPaciente ( Paciente aux )
	{
		misPacientes.add( aux );
		idPaciente++;
	}
	
	public void agregarConsulta ( Consulta aux )
	{
		misConsultas.add( aux );
		idConsulta++;
	}
	
	public void agregarCita ( Cita aux )
	{
		misCitas.add( aux );
		idCita++;
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
	
	public void agregarUsuario(User user) {
		misUsuarios.add(user);
		idUser++;
	}
	
	
	public void agregarControlVacunacion ( Control_vacunacion aux )
	{
		control_vacu.add( aux );
		idcontrolVacuna++;
	}
	
	public ArrayList<Paciente> pacientesSeleccionados ()
	{
		
		ArrayList<Paciente> seleccionadosArrayList = new ArrayList<>();
		
		for ( Paciente paciente : misPacientes )
		{
			if ( !paciente.getSeleccionado() )
			{
				seleccionadosArrayList.add( paciente );
			}
		}
		
		return seleccionadosArrayList;
		
	}
	
	public ArrayList<Paciente> pacientesNoSeleccionados ()
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

	public boolean ConfirmarLogin( String Nombre, String Cedula ) 
	{
		boolean aux = false;
		
		for ( User usuario : misUsuarios ) 
		{
	        if ( usuario.getUsuario().equals( Nombre ) && usuario.getPass().equals( Cedula ) ) 
	        {
	            LoginUser = usuario;
	            aux = true;
			}
		}
		
		return aux;
		
	}
	
	public Doctor buscarDoctorByNombreEspecialidad(String nombre, String especialidad) {
	    if (nombre == null || especialidad == null || misDoctores == null || misDoctores.isEmpty()) {
	        return null;
	    }

	    for (Doctor doc : misDoctores) {
	        if (nombre.equals(doc.getNombre()) && especialidad.equals(doc.getEspecialidad())) {
	            return doc;
	        }
	    }

	    return null; 
	}
	
}





