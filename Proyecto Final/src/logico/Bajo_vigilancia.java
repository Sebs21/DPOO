package logico;

public class Bajo_vigilancia {
	
	private int codVigilancia;
	private String enfermedad;
	private int tiempoVigilancia;
	private String monitoreo;
	
	public Bajo_vigilancia(int codVigilancia, String enfermedad, int tiempoVigilancia, String monitoreo) 
	{
		super();
		this.codVigilancia = codVigilancia;
		this.enfermedad = enfermedad;
		this.tiempoVigilancia = tiempoVigilancia;
		this.monitoreo = monitoreo;
	}

	public int getCodVigilancia() {
		return codVigilancia;
	}

	public void setCodVigilancia(int codVigilancia) {
		this.codVigilancia = codVigilancia;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public int getTiempoVigilancia() {
		return tiempoVigilancia;
	}

	public void setTiempoVigilancia(int tiempoVigilancia) {
		this.tiempoVigilancia = tiempoVigilancia;
	}

	public String getMonitoreo() {
		return monitoreo;
	}

	public void setMonitoreo(String monitoreo) {
		this.monitoreo = monitoreo;
	}
}
