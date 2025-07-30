package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String idCita;
    private Doctor doctor;
    private Paciente paciente;
    private Date fechaCita;
    private String estado;

    public Cita(String idCita, Doctor doctor, Paciente paciente, Date fechaCita) {
        super();
        this.idCita = idCita;
        this.doctor = doctor;
        this.paciente = paciente;
        this.fechaCita = fechaCita;
        this.estado = "Pendiente"; 
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }
}