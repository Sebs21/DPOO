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
	
}
