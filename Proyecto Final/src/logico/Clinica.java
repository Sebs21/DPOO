package logico;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Consulta> misConsultas;
    private ArrayList<Facturar> misFacturas;
    private ArrayList<Cita> misCitas;
    private ArrayList<Paciente> misPacientes;
    private ArrayList<Doctor> misDoctores;
    private ArrayList<Seguro> misSeguros;
    private ArrayList<User> misUsuarios;
    private ArrayList<vacunacion> inventarioDeVacunas;
    private ArrayList<Bajo_vigilancia> misVigilancias;
    private ArrayList<Especialidad> misEspecialidades;
    
    private User LoginUser;
    private int totalVacunasAplicadas;
    
    private static Clinica clinica = null;

    // --- Contadores de IDs ---
    public static int idDoctor = 1;
    public static int idPaciente = 1;
    public static int idConsulta = 1;
    public static int idFactura = 1;
    public static int idCita = 1;
    public static int idSeguro = 1;
    public static int idUser = 1;
    public static int idEspecialidad = 1;

    private Clinica() {
        
        super();
        this.misPacientes = new ArrayList<>();
        this.misDoctores = new ArrayList<>();
        this.misConsultas = new ArrayList<>();
        this.setMisFacturas(new ArrayList<>());
        this.misSeguros = new ArrayList<>();
        this.misCitas = new ArrayList<>();
        this.misUsuarios = new ArrayList<>();
        this.inventarioDeVacunas = new ArrayList<>();
        this.misVigilancias = new ArrayList<>();
        this.misEspecialidades = new ArrayList<>();
        this.totalVacunasAplicadas = 0;
        
        misSeguros.add(new Seguro("S-1", "ARS Humano (Plan Básico)", "Básico", 0.50));
        misSeguros.add(new Seguro("S-2", "ARS Humano (Plan Superior)", "Superior", 0.85));
        misSeguros.add(new Seguro("S-3", "ARS Universal (Plan Básico)", "Básico", 0.45));
        misSeguros.add(new Seguro("S-4", "ARS Universal (Plan Premium)", "Premium", 0.80));
        misSeguros.add(new Seguro("S-5", "Seguros Reservas (Mi Salud)", "Básico", 0.55));
        misSeguros.add(new Seguro("S-6", "MAPFRE Salud ARS (Internacional)", "Internacional", 0.90));
        misSeguros.add(new Seguro("S-7", "Primera ARS (Plan Voluntario)", "Voluntario", 0.70));
        
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Medicina General"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Cardiología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Pediatría"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Dermatología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Ginecología y Obstetricia"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Neurología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Ortopedia y Traumatología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Oftalmología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Otorrinolaringología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Urología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Gastroenterología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Endocrinología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Psiquiatría"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Oncología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Neumología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Reumatología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Nefrología"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Medicina Interna"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Cirugía General"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Fisiatría"));
    }

    public static Clinica getInstance() {
        if (clinica == null) {
            clinica = new Clinica();
        }
        return clinica;
    }
    
    public static int getIdDoctor() { return idDoctor; }
    public static int getIdPaciente() { return idPaciente; }
    public static int getIdConsulta() { return idConsulta; }
    public static int getIdFactura() { return idFactura; }
    public static int getIdCita() { return idCita; }
    public static int getIdSeguro() { return idSeguro; }
    public static int getIdUser() { return idUser; }
    public static int getIdEspecialidad() { return idEspecialidad; }

  
    public void guardarClinica() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clinica_info.dat"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean ConfirmarLogin(String nombreUsuario, String password) {
        if (nombreUsuario == null || password == null) return false;
        for (User usuario : misUsuarios) {
            if (usuario.getUsuario().equals(nombreUsuario) && usuario.getPass().equals(password)) {
                this.LoginUser = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean administrarVacuna(Paciente paciente, vacunacion vacunaAUsar, Date fecha, int cantMl) {
        if (paciente != null && vacunaAUsar != null && vacunaAUsar.getCantidadDisponible() > 0) {
            vacunaAUsar.descontarStock(1);
            RegistroVacunacion nuevoRegistro = new RegistroVacunacion(vacunaAUsar, fecha, cantMl);
            paciente.agregarVacunaAplicada(nuevoRegistro);
            totalVacunasAplicadas++;
            guardarClinica();
            return true;
        }
        return false;
    }
    
    public void agregarStockVacuna(vacunacion vacuna, int cantidad) {
        if (inventarioDeVacunas.contains(vacuna)) {
            vacuna.agregarStock(cantidad);
        } else {
            vacuna.setCantidadDisponible(cantidad);
            inventarioDeVacunas.add(vacuna);
        }
        guardarClinica();
    }
    
    public void iniciarVigilancia(Consulta consulta) {
        if (consulta != null) {
            Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(
                consulta.getPaciente(),
                consulta.getEnfermedad(),
                consulta.getDoctor(),
                new Date(),
                consulta
            );
            this.misVigilancias.add(nuevaVigilancia);
            guardarClinica();
        }
    }
    
    public ArrayList<Especialidad> getMisEspecialidades() {
        if (misEspecialidades == null) {
            misEspecialidades = new ArrayList<>();
        }
        return misEspecialidades;
    }
    
    
    public void agregarEspecialidad(Especialidad especialidad) {
        if (misEspecialidades == null) {
            misEspecialidades = new ArrayList<>();
        }
        misEspecialidades.add(especialidad);
        idEspecialidad++;
        guardarClinica();
    }
    
    public void agregarDoctor(Doctor doctor) {
        misDoctores.add(doctor);
        idDoctor++;
        guardarClinica();
    }

    public void agregarPaciente(Paciente paciente) {
        misPacientes.add(paciente);
        idPaciente++;
        guardarClinica();
    }
    
    public void agregarConsulta(Consulta consulta) {
        misConsultas.add(consulta);
        idConsulta++;
        guardarClinica();
    }

    public void agregarCita(Cita cita) {
        misCitas.add(cita);
        idCita++;
        guardarClinica();
    }

    public void agregarSeguro(Seguro seguro) {
        misSeguros.add(seguro);
        idSeguro++;
        guardarClinica();
    }

    public void agregarUsuario(User user) {
        misUsuarios.add(user);
        idUser++;
        guardarClinica();
    }

    public Doctor buscarDoctorByCedula(String cedula) {
        for (Doctor doctor : misDoctores) {
            if (doctor.getCedula().equalsIgnoreCase(cedula)) {
                return doctor;
            }
        }
        return null;
    }

    public Paciente buscarPacienteByCedula(String cedula) {
        for (Paciente paciente : misPacientes) {
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                return paciente;
            }
        }
        return null;
    }
    
    public void eliminarCita(Cita cita) {
        if (cita != null) {
            misCitas.remove(cita);
            guardarClinica();
        }
    }

    public User getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(User loginUser) {
        LoginUser = loginUser;
    }

    public static void setClinica(Clinica clinica) {
        Clinica.clinica = clinica;
    }

    public ArrayList<Paciente> getMisPacientes() {
        return misPacientes;
    }

    public ArrayList<Doctor> getMisDoctores() {
        return misDoctores;
    }

    public ArrayList<Consulta> getMisConsultas() {
        return misConsultas;
    }
    
    public ArrayList<Cita> getMisCitas() {
        return misCitas;
    }

    public ArrayList<Seguro> getMisSeguros() {
        return misSeguros;
    }

    public ArrayList<vacunacion> getInventarioDeVacunas() {
        return inventarioDeVacunas;
    }
    
    public int getTotalVacunasAplicadas() {
        return totalVacunasAplicadas;
    }

	public ArrayList<Facturar> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Facturar> misFacturas) {
		this.misFacturas = misFacturas;
	}
}