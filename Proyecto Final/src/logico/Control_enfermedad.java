package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Control_enfermedad implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Consulta consulta;
	private static ArrayList<Paciente> pacientes; 
	private static ArrayList<Bajo_vigilancia> vigilancia;
	
	public static int code_enfe =1;
	public Control_enfermedad()
	{
		Control_enfermedad.setPacientes(new ArrayList<>());
		Control_enfermedad.vigilancia = new ArrayList<>();
	}
	
	
	public static ArrayList<Paciente> getPacientes() {
		return pacientes;
	}


	public static void setPacientes(ArrayList<Paciente> pacientes) {
		Control_enfermedad.pacientes = pacientes;
	}


	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public ArrayList<Bajo_vigilancia> getVigilancia() {
		return vigilancia;
	}
	public void setVigilancia(ArrayList<Bajo_vigilancia> vigilancia) {
		Control_enfermedad.vigilancia = vigilancia;
	}
	
	 public void code_enfe (Bajo_vigilancia C1)
		{
		 vigilancia.add(C1);
		 code_enfe++;
		}
	 
	 public static Paciente verificar_code_paciente(String codigo)
	 {
		 for(Paciente pac: pacientes)
		 {
			 if(pac.getCedula().equalsIgnoreCase(codigo))
			 {
				 return pac;
				 
			 }
		 }
		 return null;
		 
	 }
	 
	 
	 
	
}
