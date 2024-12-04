package logico;

import java.io.Serializable;

public class Seguro implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idSeguro;
	private String nombreEmpresa;
	private String tipoDeSeguro;
	private double descuento;
	private boolean seleccionado;

	public Seguro ( String idSeguro, String nombreEmpresa, String tipoDeSeguro, double descuento )
	{
		this.idSeguro = idSeguro;
		this.nombreEmpresa = nombreEmpresa;
		this.tipoDeSeguro = tipoDeSeguro;
		this.descuento = descuento;
	}
	
	
	public double getDescuento() {
		return descuento;
	}


	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(String idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTipoDeSeguro() {
		return tipoDeSeguro;
	}

	public void setTipoDeSeguro(String tipoDeSeguro) {
		this.tipoDeSeguro = tipoDeSeguro;
	}
	
}
