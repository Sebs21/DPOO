package logico;

import java.io.Serializable;

public class Reporte_vigilancia implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codReporteVigi;
	
	
	public Reporte_vigilancia( int codReporteVigi) 
	{
		super();
		this.codReporteVigi = codReporteVigi;
	
	}
	
	public int getCodReporteVigi() {
		return codReporteVigi;
	}
	
	public void setCodReporteVigi(int codReporteVigi) {
		this.codReporteVigi = codReporteVigi;
	}
	

	
}
