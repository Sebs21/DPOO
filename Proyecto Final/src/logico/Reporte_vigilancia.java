package logico;

public class Reporte_vigilancia 
{
	
	private int codReporteVigi;
	private float totalHoras;
	
	public Reporte_vigilancia( int codReporteVigi, float totalHoras ) 
	{
		super();
		this.codReporteVigi = codReporteVigi;
		this.totalHoras = totalHoras;
	}
	
	public int getCodReporteVigi() {
		return codReporteVigi;
	}
	
	public void setCodReporteVigi(int codReporteVigi) {
		this.codReporteVigi = codReporteVigi;
	}
	
	public float getTotalHoras() {
		return totalHoras;
	}
	
	public void setTotalHoras(float totalHoras) {
		this.totalHoras = totalHoras;
	}
	
}
