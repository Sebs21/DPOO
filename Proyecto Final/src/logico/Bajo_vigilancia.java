package logico;

import java.io.Serializable;
import java.util.Date;

public class Bajo_vigilancia implements Serializable {
    private static final long serialVersionUID = 1L;

    private Paciente paciente;
    private Enfermedad enfermedad;
    private Doctor doctorResponsable;
    private Date fechaInicio;
    private String estado;
    private Consulta consultaOrigen;
    private int horasVigilancia; 
    
    public Bajo_vigilancia(Paciente paciente, Enfermedad enfermedad, Doctor doctorResponsable, Date fechaInicio, Consulta consultaOrigen, int horasVigilancia) {
        this.paciente = paciente;
        this.enfermedad = enfermedad;
        this.doctorResponsable = doctorResponsable;
        this.fechaInicio = fechaInicio;
        this.estado = "Activa";
        this.consultaOrigen = consultaOrigen;
        this.horasVigilancia = horasVigilancia; // <-- CAMBIO: Se asignan las horas
    }

    // --- Getters y Setters ---
    public int getHorasVigilancia() {
        return horasVigilancia;
    }

    public void setHorasVigilancia(int horasVigilancia) {
        this.horasVigilancia = horasVigilancia;
    }
    
    // (El resto de los getters y setters se mantienen igual)
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Doctor getDoctorResponsable() {
        return doctorResponsable;
    }

    public void setDoctorResponsable(Doctor doctorResponsable) {
        this.doctorResponsable = doctorResponsable;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Consulta getConsultaOrigen() {
        return consultaOrigen;
    }

    public void setConsultaOrigen(Consulta consultaOrigen) {
        this.consultaOrigen = consultaOrigen;
    }
}