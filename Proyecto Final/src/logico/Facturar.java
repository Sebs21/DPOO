package logico;

import java.io.Serializable;

public class Facturar implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Consulta consulta;
	private Seguro seguro;
	private float montoPagar;
	private String id;
	
	private boolean seleccionado;
	private double precioApagar;

	public Facturar ( String id, float montoPagar )
	{
		this.id = id;
		this.montoPagar = montoPagar;
	}
	
	public boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
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
	
	public double getPrecioApagar() {
		return precioApagar;
	}

	public void setPrecioApagar(double precioApagar) {
		this.precioApagar = precioApagar;
	}
	
	
}
