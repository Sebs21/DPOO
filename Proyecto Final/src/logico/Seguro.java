package logico;

public class Seguro 
{
	private String idSeguro;
	private String nombreEmpresa;
	private String tipoDeSeguro;
	//
	private boolean seleccionado;

	public Seguro ( String idSeguro, String nombreEmpresa, String tipoDeSeguro )
	{
		this.idSeguro = idSeguro;
		this.nombreEmpresa = nombreEmpresa;
		this.tipoDeSeguro = tipoDeSeguro;
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
