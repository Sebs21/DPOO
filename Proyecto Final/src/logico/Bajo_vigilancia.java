package logico;

import java.io.Serializable;
import java.util.Date;

public class Bajo_vigilancia implements Serializable {
    private static final long serialVersionUID = 1L;

    private Paciente paciente;
    private String enfermedad;
    private Doctor doctorResponsable;
    private Date fechaInicio;
    private String estado; // Ej: "Activa", "Resuelta"
    private Consulta consultaOrigen;

    public Bajo_vigilancia(Paciente paciente, String enfermedad, Doctor doctorResponsable, Date fechaInicio, Consulta consultaOrigen) {
        this.paciente = paciente;
        this.enfermedad = enfermedad;
        this.doctorResponsable = doctorResponsable;
        this.fechaInicio = fechaInicio;
        this.estado = "Activa"; // Por defecto, una nueva vigilancia está activa
        this.consultaOrigen = consultaOrigen;
    }

    // Getters y Setters para todos los atributos
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
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