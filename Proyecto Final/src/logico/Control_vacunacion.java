package logico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
public class Control_vacunacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Consulta consulta;
	  private HashMap<Integer, Paciente> pacientes; 
	    private static ArrayList<vacunacion> vacunaciones; 
	    private static ArrayList<Paciente>panc;
	    
		public static int code_vacu =1;
	
	
	    public Control_vacunacion() {
	        this.pacientes = new HashMap<>();
	        Control_vacunacion.vacunaciones = new ArrayList<>();
	        
	    }
	    
	    
	
	 public Consulta getConsulta() {
			return consulta;
		}



		public void setConsulta(Consulta consulta) {
			this.consulta = consulta;
		}



		public HashMap<Integer, Paciente> getPacientes() {
			return pacientes;
		}



		public void setPacientes(HashMap<Integer, Paciente> pacientes) {
			this.pacientes = pacientes;
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
    
    
    public void Guarda_vacuna(String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            for (vacunacion vac : vacunaciones) {
                Paciente paciente = pacientes.get(vac.getCodigoPaciente());
                String nombrePaciente =  paciente.getNombre() ;
                
                
                String registro = "Vacunación: " + vac.getCodVacu() +
                                  ", Paciente: " + nombrePaciente +
                                  ", Vacuna: " + vac.getTipoVacuna() +
                                  ", Fecha: " + vac.getFechaVacunacion();
                writer.write(registro);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la vacunación: " + e.getMessage());
        }
    }
	
	 public void code_vacu (vacunacion C1)
		{
			vacunaciones.add(C1);
			code_vacu++;
		}
	 
	 
	 public static Paciente verificar_code_paciente(String codigo)
	 {
		 for(Paciente pac: panc)
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
	    
	
	
}
