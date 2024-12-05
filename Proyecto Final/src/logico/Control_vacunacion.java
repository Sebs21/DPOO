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
	    private static ArrayList<Paciente>panc;
	    
		public static int code_vacu =1;
	
	
		public Control_vacunacion() {
			 Control_vacunacion.setPacientes(new ArrayList<>());
		    Control_vacunacion.vacunaciones = new ArrayList<>();
		    Control_vacunacion.panc = new ArrayList<>();
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




    public ArrayList<vacunacion> obtenerVacunacionesPorPaciente(String codigoPaciente) {
        ArrayList<vacunacion> resultado = new ArrayList<>();
        for (vacunacion vac : vacunaciones) {
            if (vac.getCodigoPaciente() == codigoPaciente) {
                resultado.add(vac);
            }
        }
        return resultado;
    }
    
    
    
	 public void code_vacu (vacunacion C1)
		{
			vacunaciones.add(C1);
			code_vacu++;
		}
	 
	 
	 public static Paciente verificar_code_paciente(String codigo)
	 {
		 for(Paciente pac: panc)//aqui
		 {
			 if(pac.getCedula().equalsIgnoreCase(codigo))
			 {
				 return pac;
				 
			 }
		 }
		 return null;
		 
	 }



	public ArrayList<Paciente> getPanc() {
		return panc;
	}



	public void setPanc(ArrayList<Paciente> panc) {
		Control_vacunacion.panc = panc;
	}



	public static ArrayList<Paciente> getPacientes() {
		return pacientes;
	}



	public static void setPacientes(ArrayList<Paciente> pacientes) {
		Control_vacunacion.pacientes = pacientes;
	}
	    
	
	
}
