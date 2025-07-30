package logico;

import java.io.Serializable;
import java.util.ArrayList;
public class Control_vacunacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Consulta consulta;
	private static ArrayList<Paciente> pacientes; 
	private static ArrayList<vacunacion> vacunaciones; 
	  
	    
		public static int code_vacu =1;
	
		public Control_vacunacion() {
			 Control_vacunacion.setPacientes(new ArrayList<>());
		    Control_vacunacion.vacunaciones = new ArrayList<>();    
		}

	    
	 public Consulta getConsulta() {
			return consulta;
		}



		public void setConsulta(Consulta consulta) {
			this.consulta = consulta;
		}


		public static ArrayList<vacunacion> getVacunaciones() {
			return vacunaciones;
		}



		public void setVacunaciones(ArrayList<vacunacion> vacunaciones) {
			Control_vacunacion.vacunaciones = vacunaciones;
		}



		public static long getSerialversionuid() {
			return serialVersionUID;
		}




    
	 public void code_vacu (vacunacion C1)
		{
			vacunaciones.add(C1);
			code_vacu++;
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



	public ArrayList<Paciente> getPanc() {
		return pacientes;
	}



	public void setPanc(ArrayList<Paciente> panc) {
		Control_vacunacion.pacientes = panc;
	}



	public static ArrayList<Paciente> getPacientes() {
		return pacientes;
	}



	public static void setPacientes(ArrayList<Paciente> pacientes) {
		Control_vacunacion.pacientes = pacientes;
	}
	    
	
	
}
