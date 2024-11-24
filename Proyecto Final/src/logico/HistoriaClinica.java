package src.logico;

import java.util.ArrayList;

public class HistoriaClinica 
{
	
	private ArrayList<Consulta>misConsultas;

	public HistoriaClinica( ArrayList<Consulta> misConsultas ) 
	{
		super();
		this.misConsultas = new ArrayList<>();
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
}
