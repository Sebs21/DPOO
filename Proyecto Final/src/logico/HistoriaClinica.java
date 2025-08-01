package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Consulta>misConsultas;

	// <-- CAMBIO CR�TICO: Se reemplaza el constructor anterior por uno vac�o -->
	public HistoriaClinica() 
	{
		super();
		this.misConsultas = new ArrayList<>(); // Se inicializa la lista vac�a
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
}