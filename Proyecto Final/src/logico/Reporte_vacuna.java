package logico;

import java.io.Serializable;

public class Reporte_vacuna implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codReporteVacuna;
	
	public Reporte_vacuna( int codReporteVacuna ) 
	{
		super();
		this.codReporteVacuna = codReporteVacuna;
	}

	public int getCodReporteVacuna() {
		return codReporteVacuna;
	}

	public void setCodReporteVacuna(int codReporteVacuna) {
		this.codReporteVacuna = codReporteVacuna;
	}
	
	
}
