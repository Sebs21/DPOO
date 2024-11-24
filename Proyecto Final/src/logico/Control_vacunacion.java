package logico;

import java.util.ArrayList;

public class Control_vacunacion 
{
	private Consulta consulta;
	private ArrayList<vacunacion> vacuna;
	private ArrayList<inve_vacunacion> inve_vac;
	
	public Control_vacunacion( Consulta consulta, ArrayList<vacunacion> vacuna, ArrayList<inve_vacunacion> inve_vac ) 
	{
		super();
		this.consulta = consulta;
		this.vacuna = vacuna;
		this.inve_vac = inve_vac;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public ArrayList<vacunacion> getVacuna() {
		return vacuna;
	}
	public void setVacuna(ArrayList<vacunacion> vacuna) {
		this.vacuna = vacuna;
	}
	public ArrayList<inve_vacunacion> getInve_vac() {
		return inve_vac;
	}
	public void setInve_vac(ArrayList<inve_vacunacion> inve_vac) {
		this.inve_vac = inve_vac;
	}
	
	
	
}
