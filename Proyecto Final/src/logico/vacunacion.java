package logico;

import java.util.Date;

public class vacunacion 
{
	private int cod_Vacu;
	private String tipo_Vacuna;
	private Date fecha_Vacunacion;
	private boolean verifica;
	
	public vacunacion( int codVacu, String tipoVacuna, Date fechaVacunacion, boolean verifica ) 
	{
		super();
		this.cod_Vacu = codVacu;
		this.tipo_Vacuna = tipoVacuna;
		this.fecha_Vacunacion = fechaVacunacion;
		this.verifica = verifica;
	}
	
	public int getCodVacu() {
		return cod_Vacu;
	}
	
	public void setcodVacu(int cod_Vacu) {
		this.cod_Vacu = cod_Vacu;
	}
	
	public String getTipoVacuna() {
		return tipo_Vacuna;
	}
	
	public void setTipoVacuna(String tipoVacuna) {
		this.tipo_Vacuna = tipoVacuna;
	}
	
	public Date getFechaVacunacion() {
		return fecha_Vacunacion;
	}
	
	public void setFechaVacunacion(Date fechaVacunacion) {
		this.fecha_Vacunacion = fechaVacunacion;
	}
	
	public boolean isVerifica() {
		return verifica;
	}
	
	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}
	
	
}
