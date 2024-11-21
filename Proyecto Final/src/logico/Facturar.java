package logico;

public class Facturar 
{
	
	private Consulta consulta;
	private Seguro seguro;
	private float montoPagar;
	private String id;
	
	public Facturar ( String id, float montoPagar )
	{
		this.id = id;
		this.montoPagar = montoPagar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
