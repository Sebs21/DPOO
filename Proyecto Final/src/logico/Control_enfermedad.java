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
	private ArrayList<Bajo_vigilancia> vigilancia;
	
	public Control_enfermedad( Consulta consulta, ArrayList<Bajo_vigilancia> vigilancia ) 
	{
		super();
		this.consulta = consulta;
		this.vigilancia = vigilancia;
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
		this.vigilancia = vigilancia;
	}
	
}
