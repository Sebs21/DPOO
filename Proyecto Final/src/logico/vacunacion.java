package logico;

import java.io.Serializable;
import java.util.Date;

public class vacunacion extends Control_vacunacion implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod_Vacu;
	private String tipo_Vacuna;
	private Date fecha_Vacunacion;
	private boolean verifica;
	 private String codigoPaciente; 
	 private float cant_ml;
	

	
	

	public vacunacion(int cod_Vacu, String tipo_Vacuna, Date fecha_Vacunacion, boolean verifica, String codigo_paciente,
			float cant_ml) {
		super();
		this.cod_Vacu = cod_Vacu;
		this.tipo_Vacuna = tipo_Vacuna;
		this.fecha_Vacunacion = fecha_Vacunacion;
		this.verifica = verifica;
		this.codigoPaciente = codigo_paciente;
		this.setCant_ml(cant_ml);
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

	public String getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public float getCant_ml() {
		return cant_ml;
	}

	public void setCant_ml(float cant_ml) {
		this.cant_ml = cant_ml;
	}

	public Object getFecha() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
