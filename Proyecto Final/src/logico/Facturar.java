package logico;

public class Facturar 
{
	private Consulta consulta;
	private Seguro seguro;
	private float montoPagar;
	
	public Facturar ( float montoPagar )
	{
		this.montoPagar = montoPagar;
	}

	public float getMontoPagar() {
		return montoPagar;
	}

	public void setMontoPagar(float montoPagar) {
		this.montoPagar = montoPagar;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	
	
}
