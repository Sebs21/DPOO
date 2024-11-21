package logico;

import java.util.ArrayList;

public class Clinica {
	
	private ArrayList<Persona> misPersonas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Facturar> misFacturas;
	private ArrayList<Control_enfermedad> control_enfer;
	private ArrayList<Control_vacunacion> control_vacu;
	
	public Clinica(ArrayList<Persona> misPersonas, ArrayList<Consulta> misConsultas, ArrayList<Facturar> misFacturas,
			ArrayList<Control_enfermedad> control_Enfer, ArrayList<Control_vacunacion> control_Vacu) {
		super();
		this.misPersonas = misPersonas;
		this.misConsultas = misConsultas;
		this.misFacturas = misFacturas;
		this.control_enfer = control_Enfer;
		this.control_vacu = control_Vacu;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}
	
	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}
	
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
	public ArrayList<Facturar> getMisFacturas() {
		return misFacturas;
	}
	
	public void setMisFacturas(ArrayList<Facturar> misFacturas) {
		this.misFacturas = misFacturas;
	}
	
	public ArrayList<Control_enfermedad> getControl_Enfer() {
		return control_enfer;
	}
	
	public void setControl_Enfer(ArrayList<Control_enfermedad> control_Enfer) {
		this.control_enfer = control_Enfer;
	}
	
	public ArrayList<Control_vacunacion> getControl_Vacu() {
		return control_vacu;
	}
	
	public void setControl_Vacu(ArrayList<Control_vacunacion> control_Vacu) {
		this.control_vacu = control_Vacu;
	}
	
	
	
	
}
