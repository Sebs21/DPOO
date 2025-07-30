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
        
        agregarStockVacuna(new vacunacion("VAC-1", "Influenza (Gripe)", "Sanofi Pasteur", 1200.00), 200);
        agregarStockVacuna(new vacunacion("VAC-2", "COVID-19 (Pfizer)", "Pfizer-BioNTech", 1500.00), 300);
        agregarStockVacuna(new vacunacion("VAC-3", "COVID-19 (Moderna)", "Moderna", 1450.00), 250);
        agregarStockVacuna(new vacunacion("VAC-4", "T�tanos, Difteria y Tos ferina (Tdap)", "GSK", 800.00), 150);
        agregarStockVacuna(new vacunacion("VAC-5", "Sarampi�n, Paperas y Rub�ola (MMR)", "Merck", 950.00), 180);
        agregarStockVacuna(new vacunacion("VAC-6", "Hepatitis B", "Merck", 1100.00), 120);
        agregarStockVacuna(new vacunacion("VAC-7", "Hepatitis A", "GSK", 1000.00), 100);
        agregarStockVacuna(new vacunacion("VAC-8", "Virus del Papiloma Humano (VPH)", "Merck", 4500.00), 90);
        agregarStockVacuna(new vacunacion("VAC-9", "Varicela", "Merck", 1800.00), 80);
        agregarStockVacuna(new vacunacion("VAC-10", "Neumoc�cica (Prevenar 13)", "Pfizer", 2500.00), 130);
        agregarStockVacuna(new vacunacion("VAC-11", "Meningoc�cica", "Sanofi Pasteur", 3000.00), 70);
        agregarStockVacuna(new vacunacion("VAC-12", "Fiebre Amarilla", "Sanofi Pasteur", 2200.00), 50);

        
        misSeguros.add(new Seguro("S-1", "ARS Humano (Plan B�sico)", "B�sico", 0.50));
        misSeguros.add(new Seguro("S-2", "ARS Humano (Plan Superior)", "Superior", 0.85));
        misSeguros.add(new Seguro("S-3", "ARS Universal (Plan B�sico)", "B�sico", 0.45));
        misSeguros.add(new Seguro("S-4", "ARS Universal (Plan Premium)", "Premium", 0.80));
        misSeguros.add(new Seguro("S-5", "Seguros Reservas (Mi Salud)", "B�sico", 0.55));
        misSeguros.add(new Seguro("S-6", "MAPFRE Salud ARS (Internacional)", "Internacional", 0.90));
        misSeguros.add(new Seguro("S-7", "Primera ARS (Plan Voluntario)", "Voluntario", 0.70));
        
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Medicina General"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Cardiolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Pediatr�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Dermatolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Ginecolog�a y Obstetricia"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Neurolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Ortopedia y Traumatolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Oftalmolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Otorrinolaringolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Urolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Gastroenterolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Endocrinolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Psiquiatr�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Oncolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Neumolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Reumatolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Nefrolog�a"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Medicina Interna"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Cirug�a General"));
        agregarEspecialidad(new Especialidad("ESP-" + idEspecialidad, "Fisiatr�a"));
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
    
    public void agregarFactura(Facturar factura) {
        if(this.misFacturas == null) {
            this.misFacturas = new ArrayList<>();
        }
        misFacturas.add(factura);
        idFactura++;
        guardarClinica();
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
    }
    
    public void iniciarVigilancia(Consulta consulta, int horas) {
        if (consulta != null) {
            Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(
                consulta.getPaciente(),
                consulta.getEnfermedad(),
                consulta.getDoctor(),
                new Date(),
                consulta,
                horas
            );
            agregarVigilancia(nuevaVigilancia); // Llama al nuevo m�todo centralizado
        }
    }
    
    public void finalizarVigilancia(Bajo_vigilancia registro) {
        if (registro != null) {
            registro.setEstado("Finalizada");
            guardarClinica(); // Se guardan los cambios en el archivo
        }
    }
    
    public void agregarVigilancia(Bajo_vigilancia vigilancia) {
        if (this.misVigilancias == null) {
            this.misVigilancias = new ArrayList<>();
        }
        this.misVigilancias.add(vigilancia);
        guardarClinica();
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

	public ArrayList<Bajo_vigilancia> getMisVigilancias() {
		return misVigilancias;	
	}
}