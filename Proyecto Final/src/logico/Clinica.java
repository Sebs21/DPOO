package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date; 


public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

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
    public static int idEnfermedad = 1;
    public static int idVigilancia = 1;
    public static int idVacuna = 1;

    
    private ArrayList<Consulta> misConsultas;
    private ArrayList<Factura> misFacturas;
    private ArrayList<Cita> misCitas;
    private ArrayList<Paciente> misPacientes;
    private ArrayList<Doctor> misDoctores;
    private ArrayList<Seguro> misSeguros;
    private ArrayList<User> misUsuarios;
    private ArrayList<vacunacion> inventarioDeVacunas;
    private ArrayList<Bajo_vigilancia> misVigilancias;
    private ArrayList<Especialidad> misEspecialidades;
    private ArrayList<Enfermedad> misEnfermedades;


    private Clinica() {
    	this.misConsultas = new ArrayList<>();
        this.misFacturas = new ArrayList<>();
        this.misCitas = new ArrayList<>();
        this.misPacientes = new ArrayList<>();
        this.misDoctores = new ArrayList<>();
        this.misSeguros = new ArrayList<>();
        this.misUsuarios = new ArrayList<>();
        this.inventarioDeVacunas = new ArrayList<>();
        this.misVigilancias = new ArrayList<>();
        this.misEspecialidades = new ArrayList<>();
        this.misEnfermedades = new ArrayList<>();
    }

    public static Clinica getInstance() {
        if (clinica == null) {
            clinica = new Clinica();          
            clinica.cargarDatosDesdeDB();
        }
        return clinica;
    }

    private void cargarDatosDesdeDB() {
    	
    	cargarEspecialidadesDesdeDB();
        cargarSegurosDesdeDB();
        cargarEnfermedadesDesdeDB();
        cargarUsuariosDesdeDB();
        cargarDoctoresDesdeDB(); 
        cargarPacientesDesdeDB(); 
        cargarVacunasDesdeDB(); 
        cargarConsultasDesdeDB(); 
        cargarCitasDesdeDB();
        cargarFacturasDesdeDB(); 
        cargarVigilanciasDesdeDB(); 
        
        actualizarContadoresIDs();
    }

   
    public void poblarBaseDeDatosInicial() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) {            
            return;
        }

        //Insertar Especialidades
		ArrayList<Especialidad> especialidadesIniciales = new ArrayList<>();
		especialidadesIniciales.add(new Especialidad("ESP-1", "Medicina General"));
		especialidadesIniciales.add(new Especialidad("ESP-2", "Cardiología"));
		especialidadesIniciales.add(new Especialidad("ESP-3", "Pediatría"));
		especialidadesIniciales.add(new Especialidad("ESP-4", "Dermatología"));
		especialidadesIniciales.add(new Especialidad("ESP-5", "Ginecología y Obstetricia"));
		especialidadesIniciales.add(new Especialidad("ESP-6", "Neurología"));
		especialidadesIniciales.add(new Especialidad("ESP-7", "Ortopedia y Traumatología"));
		especialidadesIniciales.add(new Especialidad("ESP-8", "Oftalmología"));
		especialidadesIniciales.add(new Especialidad("ESP-9", "Otorrinolaringología"));
		especialidadesIniciales.add(new Especialidad("ESP-10", "Urología"));
		especialidadesIniciales.add(new Especialidad("ESP-11", "Gastroenterología"));
		especialidadesIniciales.add(new Especialidad("ESP-12", "Endocrinología"));
		especialidadesIniciales.add(new Especialidad("ESP-13", "Psiquiatría"));
		especialidadesIniciales.add(new Especialidad("ESP-14", "Oncología"));
		especialidadesIniciales.add(new Especialidad("ESP-15", "Neumología"));
		especialidadesIniciales.add(new Especialidad("ESP-16", "Reumatología"));
		especialidadesIniciales.add(new Especialidad("ESP-17", "Nefrología"));
		especialidadesIniciales.add(new Especialidad("ESP-18", "Medicina Interna"));
		especialidadesIniciales.add(new Especialidad("ESP-19", "Cirugía General"));
		especialidadesIniciales.add(new Especialidad("ESP-20", "Fisiatría"));

		for (Especialidad esp : especialidadesIniciales) {
		    agregarEspecialidad(esp);
		}

		//Insertar Seguros
		ArrayList<Seguro> segurosIniciales = new ArrayList<>();
		segurosIniciales.add(new Seguro("S-0", "No aplica", "Null", 0.0));
		segurosIniciales.add(new Seguro("S-1", "ARS Humano (Plan Básico)", "Básico", 0.50));
		segurosIniciales.add(new Seguro("S-2", "ARS Humano (Plan Superior)", "Superior", 0.85));
		segurosIniciales.add(new Seguro("S-3", "ARS Universal (Plan Básico)", "Básico", 0.45));
		segurosIniciales.add(new Seguro("S-4", "ARS Universal (Plan Premium)", "Premium", 0.80));
		segurosIniciales.add(new Seguro("S-5", "Seguros Reservas (Mi Salud)", "Básico", 0.55));
		segurosIniciales.add(new Seguro("S-6", "MAPFRE Salud ARS (Internacional)", "Internacional", 0.90));
		segurosIniciales.add(new Seguro("S-7", "Primera ARS (Plan Voluntario)", "Voluntario", 0.70));

		for (Seguro seg : segurosIniciales) {
		    agregarSeguro(seg);
		}

		//Insertar Enfermedades
		ArrayList<Enfermedad> enfermedadesIniciales = new ArrayList<>();
		enfermedadesIniciales.add(new Enfermedad("ENF-1", "Hipertensión Arterial"));
		enfermedadesIniciales.add(new Enfermedad("ENF-2", "Diabetes Mellitus Tipo 1"));
		enfermedadesIniciales.add(new Enfermedad("ENF-3", "Diabetes Mellitus Tipo 2"));
		enfermedadesIniciales.add(new Enfermedad("ENF-4", "Asma Bronquial"));
		enfermedadesIniciales.add(new Enfermedad("ENF-5", "Gripe Común (Influenza)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-6", "Faringitis Aguda"));
		enfermedadesIniciales.add(new Enfermedad("ENF-7", "Migraña Crónica"));
		enfermedadesIniciales.add(new Enfermedad("ENF-8", "Rinitis Alérgica"));
		enfermedadesIniciales.add(new Enfermedad("ENF-9", "Gastritis"));
		enfermedadesIniciales.add(new Enfermedad("ENF-10", "Enfermedad por Reflujo Gastroesofágico (ERGE)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-11", "Síndrome del Intestino Irritable"));
		enfermedadesIniciales.add(new Enfermedad("ENF-12", "Anemia por deficiencia de hierro"));
		enfermedadesIniciales.add(new Enfermedad("ENF-13", "Hipotiroidismo"));
		enfermedadesIniciales.add(new Enfermedad("ENF-14", "Hipertiroidismo"));
		enfermedadesIniciales.add(new Enfermedad("ENF-15", "Artritis Reumatoide"));
		enfermedadesIniciales.add(new Enfermedad("ENF-16", "Osteoartritis"));
		enfermedadesIniciales.add(new Enfermedad("ENF-17", "Depresión"));
		enfermedadesIniciales.add(new Enfermedad("ENF-18", "Trastorno de Ansiedad Generalizada"));
		enfermedadesIniciales.add(new Enfermedad("ENF-19", "Cefalea Tensional"));
		enfermedadesIniciales.add(new Enfermedad("ENF-20", "Infección del Tracto Urinario (ITU)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-21", "Neumonía"));
		enfermedadesIniciales.add(new Enfermedad("ENF-22", "Bronquitis Aguda"));
		enfermedadesIniciales.add(new Enfermedad("ENF-23", "Dermatitis Atópica (Eczema)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-24", "Acné Vulgar"));
		enfermedadesIniciales.add(new Enfermedad("ENF-25", "Psoriasis"));
		enfermedadesIniciales.add(new Enfermedad("ENF-26", "Insuficiencia Cardíaca Congestiva"));
		enfermedadesIniciales.add(new Enfermedad("ENF-27", "Enfermedad Coronaria"));
		enfermedadesIniciales.add(new Enfermedad("ENF-28", "Fibrilación Auricular"));
		enfermedadesIniciales.add(new Enfermedad("ENF-29", "Accidente Cerebrovascular (ACV)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-30", "Epilepsia"));
		enfermedadesIniciales.add(new Enfermedad("ENF-31", "Enfermedad de Alzheimer"));
		enfermedadesIniciales.add(new Enfermedad("ENF-32", "Enfermedad de Parkinson"));
		enfermedadesIniciales.add(new Enfermedad("ENF-33", "Cataratas"));
		enfermedadesIniciales.add(new Enfermedad("ENF-34", "Glaucoma"));
		enfermedadesIniciales.add(new Enfermedad("ENF-35", "Hernia de Disco Lumbar"));
		enfermedadesIniciales.add(new Enfermedad("ENF-36", "Escoliosis"));
		enfermedadesIniciales.add(new Enfermedad("ENF-37", "Cálculos Renales (Nefrolitiasis)"));
		enfermedadesIniciales.add(new Enfermedad("ENF-38", "Insuficiencia Renal Crónica"));
		enfermedadesIniciales.add(new Enfermedad("ENF-39", "Hepatitis C"));
		enfermedadesIniciales.add(new Enfermedad("ENF-40", "VIH/SIDA"));
		enfermedadesIniciales.add(new Enfermedad("ENF-41", "Cáncer de Mama"));
		enfermedadesIniciales.add(new Enfermedad("ENF-42", "Cáncer de Pulmón"));
		enfermedadesIniciales.add(new Enfermedad("ENF-43", "Cáncer de Próstata"));
		enfermedadesIniciales.add(new Enfermedad("ENF-44", "Cáncer Colorrectal"));
		enfermedadesIniciales.add(new Enfermedad("ENF-45", "Lupus Eritematoso Sistémico"));
		enfermedadesIniciales.add(new Enfermedad("ENF-46", "Fibromialgia"));
		enfermedadesIniciales.add(new Enfermedad("ENF-47", "Apendicitis Aguda"));
		enfermedadesIniciales.add(new Enfermedad("ENF-48", "Colecistitis Aguda"));
		enfermedadesIniciales.add(new Enfermedad("ENF-49", "Otitis Media"));
		enfermedadesIniciales.add(new Enfermedad("ENF-50", "Sinusitis"));
		enfermedadesIniciales.add(new Enfermedad("ENF-51", "Conjuntivitis"));

		for (Enfermedad enfermedad : enfermedadesIniciales) {
		    agregarEnfermedad(enfermedad);
		}
		
		agregarStockVacuna(new vacunacion("VAC-1", "Influenza (Gripe)", "Sanofi Pasteur", 1200.00), 200); 
		agregarStockVacuna(new vacunacion("VAC-2", "COVID-19 (Pfizer)", "Pfizer-BioNTech", 1500.00), 300); 
		agregarStockVacuna(new vacunacion("VAC-3", "COVID-19 (Moderna)", "Moderna", 1450.00), 250); 
		agregarStockVacuna(new vacunacion("VAC-4", "Tétanos, Difteria y Tos ferina (Tdap)", "GSK", 800.00), 150); 
		agregarStockVacuna(new vacunacion("VAC-5", "Sarampión, Paperas y Rubéola (MMR)", "Merck", 950.00), 180); 
		agregarStockVacuna(new vacunacion("VAC-6", "Hepatitis B", "Merck", 1100.00), 120); 
		agregarStockVacuna(new vacunacion("VAC-7", "Hepatitis A", "GSK", 1000.00), 100); 
		agregarStockVacuna(new vacunacion("VAC-8", "Virus del Papiloma Humano (VPH)", "Merck", 4500.00), 90); 
		agregarStockVacuna(new vacunacion("VAC-9", "Varicela", "Merck", 1800.00), 80); 
		agregarStockVacuna(new vacunacion("VAC-10", "Neumocócica (Prevenar 13)", "Pfizer", 2500.00), 130); 
		agregarStockVacuna(new vacunacion("VAC-11", "Meningocócica", "Sanofi Pasteur", 3000.00), 70); 
		agregarStockVacuna(new vacunacion("VAC-12", "Fiebre Amarilla", "Sanofi Pasteur", 2200.00), 50);
		
		User admin = new User("Admin", "Admin", "Administrador");
		agregarUsuario(admin);

		cargarDatosDesdeDB();       
        
    }

    //Metodos para obtener el siguiente ID
    public static int getIdDoctor() { 
    	return idDoctor; 
    	}
    public static int getIdPaciente() { 
    	return idPaciente; 
    }
    public static int getIdConsulta() { 
    	return idConsulta; 
    }
    public static int getIdFactura() {
    	return idFactura; 
    }
    public static int getIdCita() { 
    	return idCita; 
    }
    public static int getIdSeguro() { 
    	return idSeguro; 
    }
    public static int getIdUser() {    	
    	return idUser; 
    }
    public static int getIdEspecialidad() { 
    	return idEspecialidad; 
    }
    public static int getIdEnfermedad() { 
    	return idEnfermedad; 
    }
    public static int getIdVigilancia() { 
    	return idVigilancia; 
    }

    //Actualizar contadores de IDs desde la DB
    private void actualizarContadoresIDs() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        try {
            // Para especialidades (ESP-)
            String sql = "SELECT MAX(CAST(SUBSTRING(id_especialidad, 5, LEN(id_especialidad)) AS INT)) FROM Especialidad WHERE id_especialidad LIKE 'ESP-%' AND ISNUMERIC(SUBSTRING(id_especialidad, 5, LEN(id_especialidad))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idEspecialidad = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en especialidades: " + e.getMessage());
                idEspecialidad = 1;
            }
            
            // Para seguros (S-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_seguro, 3, LEN(id_seguro)) AS INT)) FROM Seguro WHERE id_seguro LIKE 'S-%' AND ISNUMERIC(SUBSTRING(id_seguro, 3, LEN(id_seguro))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idSeguro = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en seguros: " + e.getMessage());
                idSeguro = 1;
            }
            
            // Para vigilancias (IDENTITY - INT)
            sql = "SELECT MAX(id_vigilancia) FROM Bajo_vigilancia";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idVigilancia = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en vigilancias: " + e.getMessage());
                idVigilancia = 1;
            }
            
            // Para enfermedades (ENF-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_enfermedad, 5, LEN(id_enfermedad)) AS INT)) FROM Enfermedad WHERE id_enfermedad LIKE 'ENF-%' AND ISNUMERIC(SUBSTRING(id_enfermedad, 5, LEN(id_enfermedad))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idEnfermedad = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en enfermedades: " + e.getMessage());
                idEnfermedad = 1;
            }
            
            // Para consultas (CON-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_consulta, 5, LEN(id_consulta)) AS INT)) FROM Consulta WHERE id_consulta LIKE 'CON-%' AND ISNUMERIC(SUBSTRING(id_consulta, 5, LEN(id_consulta))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idConsulta = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en consultas: " + e.getMessage());
                idConsulta = 1;
            }
            
            // Para citas (CIT-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_cita, 5, LEN(id_cita)) AS INT)) FROM Cita WHERE id_cita LIKE 'CIT-%' AND ISNUMERIC(SUBSTRING(id_cita, 5, LEN(id_cita))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idCita = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en citas: " + e.getMessage());
                idCita = 1;
            }
            
            // Para facturas (FAC-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_factura, 5, LEN(id_factura)) AS INT)) FROM Factura WHERE id_factura LIKE 'FAC-%' AND ISNUMERIC(SUBSTRING(id_factura, 5, LEN(id_factura))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idFactura = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en facturas: " + e.getMessage());
                idFactura = 1;
            }
            
            // Para vacunas (VAC-)
            sql = "SELECT MAX(CAST(SUBSTRING(id_vacuna, 5, LEN(id_vacuna)) AS INT)) FROM Vacuna WHERE id_vacuna LIKE 'VAC-%' AND ISNUMERIC(SUBSTRING(id_vacuna, 5, LEN(id_vacuna))) = 1";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idVacuna = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en vacunas: " + e.getMessage());
                idVacuna = 1;
            }
            
            // Para pacientes
            sql = "SELECT MAX(id_paciente) FROM Paciente WHERE id_paciente IS NOT NULL";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idPaciente = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en pacientes: " + e.getMessage());
                idPaciente = 1;
            }
            
            // Para usuarios
            sql = "SELECT MAX(id_usuario) FROM Usuario";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next() && rs.getObject(1) != null) {
                    idUser = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en usuarios: " + e.getMessage());
                idUser = 1;
            }
            
            // Para doctores
            sql = "SELECT COUNT(*) FROM Doctor";
            try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next()) {
                    idDoctor = rs.getInt(1) + 1;
                }
            } catch (SQLException e) {
                System.err.println("Error en doctores: " + e.getMessage());
                idDoctor = 1;
            }          
          
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
    }    

    public ArrayList<Especialidad> getMisEspecialidades() {
        return misEspecialidades;
    }
    
    public void actualizarEstadoCita(Cita cita) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) 
        	return;
        
        String sql = "UPDATE Cita SET estado = ? WHERE id_cita = ?";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, cita.getEstado());
            ps.setString(2, cita.getIdCita());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    public void actualizarPaciente(Paciente paciente) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        String sql = "UPDATE Paciente SET id_seguro = ? WHERE cedula = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            if (paciente.getSeguro() != null) {
                ps.setString(1, paciente.getSeguro().getIdSeguro());
            } else {
                ps.setNull(1, java.sql.Types.VARCHAR);
            }
            ps.setString(2, paciente.getCedula());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }      
        ConexionDB.cerrarConexion(cnx);
    }

    private void cargarEspecialidadesDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        String sql = "SELECT id_especialidad, nombre FROM Especialidad";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                misEspecialidades.add(new Especialidad(rs.getString("id_especialidad"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Seguro> getMisSeguros() {
        return misSeguros;
    }

    private void cargarSegurosDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT id_seguro, nombre_empresa, tipo_seguro, descuento FROM Seguro";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                misSeguros.add(new Seguro(rs.getString("id_seguro"), rs.getString("nombre_empresa"), rs.getString("tipo_seguro"), rs.getDouble("descuento")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Enfermedad> getMisEnfermedades() {
        return misEnfermedades;
    }

    private void cargarEnfermedadesDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT id_enfermedad, nombre FROM Enfermedad";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                misEnfermedades.add(new Enfermedad(rs.getString("id_enfermedad"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<User> getMisUsuarios() {
        return misUsuarios;
    }

    private void cargarUsuariosDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT nombre, password, tipo FROM Usuario";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                misUsuarios.add(new User(rs.getString("nombre"), rs.getString("password"), rs.getString("tipo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Doctor> getMisDoctores() {
        return misDoctores;
    }

    private void cargarDoctoresDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT d.cedula, d.nombre, d.apellido, d.edad, d.sexo, e.nombre AS especialidad_nombre, u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Doctor d JOIN Especialidad e ON d.id_especialidad = e.id_especialidad " +
                     "LEFT JOIN Usuario u ON d.cedula = u.password AND u.tipo = 'Doctor'";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User user = null;
                if (rs.getString("user_nombre") != null) {
                    user = new User(rs.getString("user_nombre"), rs.getString("user_password"), rs.getString("user_tipo"));
                }
                Doctor doc = new Doctor(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("especialidad_nombre"),
                    rs.getString("edad"),
                    rs.getString("sexo"),
                    user
                );            
                misDoctores.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Paciente> getMisPacientes() {
        return misPacientes;
    }

    private void cargarPacientesDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT p.cedula, p.nombre, p.apellido, p.id_paciente, p.edad, p.sexo, " +
                     "s.id_seguro, s.nombre_empresa, s.tipo_seguro, s.descuento, " +
                     "u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Paciente p LEFT JOIN Seguro s ON p.id_seguro = s.id_seguro " +
                     "LEFT JOIN Usuario u ON p.cedula = u.password AND u.tipo = 'Paciente'"; 
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User user = null;
                if (rs.getString("user_nombre") != null) {
                    user = new User(rs.getString("user_nombre"), rs.getString("user_password"), rs.getString("user_tipo"));
                }
                Paciente paciente = new Paciente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("id_paciente"),
                    rs.getString("edad"),
                    rs.getString("sexo"),
                    user
                );
                if (rs.getString("id_seguro") != null) {
                    Seguro seguro = new Seguro(rs.getString("id_seguro"), rs.getString("nombre_empresa"), rs.getString("tipo_seguro"), rs.getDouble("descuento"));
                    paciente.setSeguro(seguro);
                }
                misPacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Consulta> getMisConsultas() {
        return misConsultas;
    }

    private void cargarConsultasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        String sql = "SELECT c.id_consulta, c.id_factura, c.descripcion, c.fecha_consulta, c.seguro_aplicado, c.importancia, c.precio, c.pagada, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                     "d.cedula AS doctor_cedula, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, d.edad AS doctor_edad, d.sexo AS doctor_sexo, " +
                     "esp.nombre AS doctor_especialidad, " +
                     "enf.id_enfermedad, enf.nombre AS enfermedad_nombre " +
                     "FROM Consulta c " +
                     "JOIN Paciente p ON c.cedula_paciente = p.cedula " +
                     "JOIN Doctor d ON c.cedula_doctor = d.cedula " +
                     "JOIN Especialidad esp ON d.id_especialidad = esp.id_especialidad " +
                     "JOIN Enfermedad enf ON c.id_enfermedad = enf.id_enfermedad";
        
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                
                Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula"));
                if (paciente == null) { 
                    paciente = new Paciente(rs.getString("paciente_cedula"), rs.getString("paciente_nombre"), rs.getString("paciente_apellido"), rs.getInt("paciente_id"), rs.getString("paciente_edad"), rs.getString("paciente_sexo"), null);                  
                }

                Doctor doctor = buscarDoctorByCedula(rs.getString("doctor_cedula")); 
                if (doctor == null) { 
                    doctor = new Doctor(rs.getString("doctor_cedula"), rs.getString("doctor_nombre"), rs.getString("doctor_apellido"), rs.getString("doctor_especialidad"), rs.getString("doctor_edad"), rs.getString("doctor_sexo"), null);
                }

                Enfermedad enfermedad = buscarEnfermedadById(rs.getString("id_enfermedad"));
                if (enfermedad == null) { 
                    enfermedad = new Enfermedad(rs.getString("id_enfermedad"), rs.getString("enfermedad_nombre"));               
                }
                
                Consulta consulta = new Consulta(
                    rs.getString("id_consulta"),
                    rs.getInt("id_factura"),
                    rs.getString("descripcion"),
                    enfermedad,
                    rs.getTimestamp("fecha_consulta"),
                    rs.getString("seguro_aplicado"),
                    doctor,
                    paciente,
                    rs.getBoolean("importancia"),
                    rs.getDouble("precio")
                );
                consulta.setPagada(rs.getBoolean("pagada"));
                misConsultas.add(consulta);
                
                paciente.agregarConsultaAlHistorial(consulta);
                paciente.agregarEnfermedadAlHistorial(enfermedad); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Cita> getMisCitas() {
        return misCitas;
    }

    private void cargarCitasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;            
        
        String sql = "SELECT c.id_cita, c.fecha_cita, c.estado, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                     "d.cedula AS doctor_cedula, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, d.edad AS doctor_edad, d.sexo AS doctor_sexo, " +
                     "esp.nombre AS doctor_especialidad " +
                     "FROM Cita c " +
                     "JOIN Paciente p ON c.cedula_paciente = p.cedula " +
                     "JOIN Doctor d ON c.cedula_doctor = d.cedula " +
                     "JOIN Especialidad esp ON d.id_especialidad = esp.id_especialidad";
        
        try (Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(sql)) {                         
               
               while (rs.next()) {
                   Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula"));
                   if (paciente == null) {
                       paciente = new Paciente(
                           rs.getString("paciente_cedula"), 
                           rs.getString("paciente_nombre"), 
                           rs.getString("paciente_apellido"), 
                           rs.getInt("id_paciente"), 
                           rs.getString("paciente_edad"), 
                           rs.getString("paciente_sexo"), 
                           null
                       );
                       misPacientes.add(paciente);
                   }

                   Doctor doctor = buscarDoctorByCedula(rs.getString("doctor_cedula"));
                   if (doctor == null) {
                       doctor = new Doctor(
                           rs.getString("doctor_cedula"), 
                           rs.getString("doctor_nombre"), 
                           rs.getString("doctor_apellido"), 
                           rs.getString("doctor_especialidad"), 
                           rs.getString("doctor_edad"), 
                           rs.getString("doctor_sexo"), 
                           null
                       );
                       misDoctores.add(doctor);
                   }
                   Cita cita = new Cita(
                       rs.getString("id_cita"),
                       doctor,
                       paciente,
                       rs.getTimestamp("fecha_cita")
                   );
                   cita.setEstado(rs.getString("estado"));
                   misCitas.add(cita);
                   
                   if (doctor.getMisCitas() == null) {
                       doctor.setMisCitas(new ArrayList<>());
                   }
                   doctor.getMisCitas().add(cita);
               }                                    
           } catch (SQLException e) {
            e.printStackTrace();
        }       
        ConexionDB.cerrarConexion(cnx);
    }


    public ArrayList<Factura> getMisFacturas() {
        return misFacturas;
    }

    private void cargarFacturasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT f.id_factura, f.fecha_factura, f.subtotal, f.descuento, f.total_pagado, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo " +
                     "FROM Factura f JOIN Paciente p ON f.cedula_paciente = p.cedula";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula"));
                if (paciente == null) {
                    paciente = new Paciente(rs.getString("paciente_cedula"), rs.getString("paciente_nombre"), rs.getString("paciente_apellido"), rs.getInt("paciente_id"), rs.getString("paciente_edad"), rs.getString("paciente_sexo"), null);
                }
               
                Factura factura = new Factura(
                    rs.getString("id_factura"),
                    paciente,
                    rs.getTimestamp("fecha_factura"),
                    new ArrayList<>(), 
                    new ArrayList<>(), 
                    rs.getDouble("subtotal"),
                    rs.getDouble("descuento"),
                    rs.getDouble("total_pagado")
                );
                misFacturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<vacunacion> getInventarioDeVacunas() {
        return inventarioDeVacunas;
    }

    private void cargarVacunasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT id_vacuna, nombre, fabricante, cantidad_disponible, precio FROM Vacuna";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                vacunacion vacuna = new vacunacion(
                    rs.getString("id_vacuna"),
                    rs.getString("nombre"),
                    rs.getString("fabricante"),
                    rs.getDouble("precio")
                );
                vacuna.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                inventarioDeVacunas.add(vacuna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public ArrayList<Bajo_vigilancia> getMisVigilancias() {
        return misVigilancias;
    }

    private void cargarVigilanciasDesdeDB() {
        misVigilancias.clear(); // Limpia la lista primero
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String sql = "SELECT bv.id_vigilancia, bv.fecha_inicio, bv.estado, bv.horas_vigilancia, " +
            "bv.cedula_paciente, bv.id_enfermedad, bv.cedula_doctor, bv.id_consulta_origen " +
            "FROM Bajo_vigilancia bv";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente paciente = buscarPacienteByCedula(rs.getString("cedula_paciente"));
                Enfermedad enfermedad = buscarEnfermedadById(rs.getString("id_enfermedad"));
                Doctor doctor = buscarDoctorByCedula(rs.getString("cedula_doctor"));
                Consulta consultaOrigen = buscarConsultaById(rs.getString("id_consulta_origen"));

                if (paciente != null && enfermedad != null && doctor != null) {
                    Bajo_vigilancia vigilancia = new Bajo_vigilancia(
                        paciente,
                        enfermedad,
                        doctor,
                        rs.getTimestamp("fecha_inicio"),
                        consultaOrigen,
                        rs.getInt("horas_vigilancia")
                    );
                    vigilancia.setEstado(rs.getString("estado"));
                    misVigilancias.add(vigilancia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
    }

    public void agregarEspecialidad(Especialidad especialidad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Especialidad (id_especialidad, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, especialidad.getIdEspecialidad());
            ps.setString(2, especialidad.getNombre());
            ps.executeUpdate();
            misEspecialidades.add(especialidad); 
            idEspecialidad++; 
        } catch (SQLException e) {      
            if (e.getErrorCode() == 2627) {
                System.out.println("Especialidad '" + especialidad.getNombre() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public void agregarSeguro(Seguro seguro) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Seguro (id_seguro, nombre_empresa, tipo_seguro, descuento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, seguro.getIdSeguro());
            ps.setString(2, seguro.getNombreEmpresa());
            ps.setString(3, seguro.getTipoDeSeguro());
            ps.setDouble(4, seguro.getDescuento());
            ps.executeUpdate();
            misSeguros.add(seguro); 
            idSeguro++; 
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Seguro '" + seguro.getNombreEmpresa() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    public ArrayList<Bajo_vigilancia> obtenerVigilanciasDelDoctor(String cedulaDoctor) {
        ArrayList<Bajo_vigilancia> vigilanciasDelDoctor = new ArrayList<>();
        for (Bajo_vigilancia v : misVigilancias) {
            if (v.getDoctorResponsable().getCedula().equalsIgnoreCase(cedulaDoctor)) {
                vigilanciasDelDoctor.add(v);
            }
        }
        return vigilanciasDelDoctor;
    }

    public void agregarEnfermedad(Enfermedad enfermedad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Enfermedad (id_enfermedad, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, enfermedad.getId());
            ps.setString(2, enfermedad.getNombre());
            ps.executeUpdate();
            misEnfermedades.add(enfermedad);
            idEnfermedad++; 
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Enfermedad '" + enfermedad.getNombre() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public void agregarUsuario(User user) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Usuario (nombre, password, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getTipo());
            ps.executeUpdate();
            misUsuarios.add(user); 
            idUser++; 
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Usuario '" + user.getUsuario() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public void agregarDoctor(Doctor doctor) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Doctor (cedula, nombre, apellido, edad, sexo,id_especialidad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, doctor.getCedula());
            ps.setString(2, doctor.getNombre());
            ps.setString(3, doctor.getApellido());
            ps.setString(4, doctor.getEdad());
            ps.setString(5, doctor.getSexo());
            
            Especialidad esp = buscarEspecialidadPorNombre(doctor.getEspecialidad());
            if (esp != null) {
                ps.setString(6, esp.getIdEspecialidad());
            } else {
                ps.setNull(6, java.sql.Types.VARCHAR);
            }
            ps.executeUpdate();
            misDoctores.add(doctor); 
            idDoctor++; 
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Doctor con cédula '" + doctor.getCedula() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public void agregarPaciente(Paciente paciente) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Paciente (cedula, nombre, apellido, id_paciente, edad, sexo, id_seguro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, paciente.getCedula());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setInt(4, paciente.getIdCodPaciente());
            ps.setString(5, paciente.getEdad());
            ps.setString(6, paciente.getSexo());
            if (paciente.getSeguro() != null) {
                ps.setString(7, paciente.getSeguro().getIdSeguro());
            } else {
                ps.setNull(7, java.sql.Types.VARCHAR);
            }
            ps.executeUpdate();
            misPacientes.add(paciente); 
            idPaciente++; 
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Paciente con cédula '" + paciente.getCedula() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }

    public void agregarConsulta(Consulta consulta) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        String sql = "INSERT INTO Consulta (id_consulta, id_factura, descripcion, fecha_consulta, seguro_aplicado, importancia, precio, pagada, cedula_paciente, cedula_doctor, id_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
        	ps.setString(1, consulta.getId());
            
            if (consulta.getIdFactura() > 0) {
                ps.setInt(2, consulta.getIdFactura());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            
            ps.setString(3, consulta.getDescripcion());
            ps.setTimestamp(4, new java.sql.Timestamp(consulta.getFechaConsulta().getTime()));
            ps.setString(5, consulta.getSeguro());
            ps.setBoolean(6, consulta.isImportancia());
            ps.setDouble(7, consulta.getPrecio());
            ps.setBoolean(8, consulta.isPagada());
            ps.setString(9, consulta.getPaciente().getCedula());
            ps.setString(10, consulta.getDoctor().getCedula());
            ps.setString(11, consulta.getEnfermedad().getId());                     
            this.misConsultas.add(consulta);
            idConsulta++;
            

        } catch (SQLException e) {           
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }        
    }

    public void agregarCita(Cita cita) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        
        if (cita.getIdCita() == null || cita.getIdCita().isEmpty()) {
            cita.setIdCita("CIT-" + idCita);
        }
        
        String sql = "INSERT INTO Cita (id_cita, fecha_cita, estado, cedula_paciente, cedula_doctor) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, cita.getIdCita());
            ps.setTimestamp(2, new java.sql.Timestamp(cita.getFechaCita().getTime()));
            ps.setString(3, cita.getEstado());
            ps.setString(4, cita.getPaciente().getCedula());
            ps.setString(5, cita.getDoctor().getCedula());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                if (this.misCitas == null) {
                    this.misCitas = new ArrayList<>();
                }
                this.misCitas.add(cita);

                Doctor doctor = cita.getDoctor();
                if (doctor.getMisCitas() == null) {
                    doctor.setMisCitas(new ArrayList<>());
                }
                doctor.getMisCitas().add(cita);
                
                idCita++;            
            } else {
            } 
        }catch (SQLException e) {
        	e.printStackTrace();
        }
            ConexionDB.cerrarConexion(cnx);        
        }  	 
    
    private void actualizarEstadoPagadoConsulta(String idConsulta, boolean pagada) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "UPDATE Consulta SET pagada = ? WHERE id_consulta = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setBoolean(1, pagada);
            ps.setString(2, idConsulta);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    public ArrayList<Doctor> obtenerDoctoresDesdeDB() {
        ArrayList<Doctor> doctores = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return doctores;

        String sql = "SELECT d.*, e.nombre AS especialidad_nombre FROM Doctor d JOIN Especialidad e ON d.id_especialidad = e.id_especialidad";
        
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("especialidad_nombre"),
                    rs.getString("edad"),
                    rs.getString("sexo"),
                    null
                );
                doctores.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return doctores;
    }
    
    public ArrayList<Paciente> obtenerPacientesDesdeDB() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return pacientes; 

        String sql = "SELECT p.*, s.nombre_empresa FROM Paciente p LEFT JOIN Seguro s ON p.id_seguro = s.id_seguro";
        
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Paciente paciente = new Paciente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("id_paciente"),
                    rs.getString("edad"),
                    rs.getString("sexo"),
                    null
                );
                
                if (rs.getString("id_seguro") != null) {
                    
                    paciente.setSeguro(new Seguro(rs.getString("id_seguro"), rs.getString("nombre_empresa"), "", 0));
                }
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return pacientes;
    }
    
    public ArrayList<Seguro> obtenerSegurosDesdeDB() {
        ArrayList<Seguro> seguros = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return seguros;
        String sql = "SELECT * FROM Seguro";
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Seguro seguro = new Seguro(
                    rs.getString("id_seguro"), rs.getString("nombre_empresa"), 
                    rs.getString("tipo_seguro"), rs.getDouble("descuento")
                );
                seguros.add(seguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return seguros;
    }

    public ArrayList<Consulta> obtenerConsultasDesdeDB() {
        ArrayList<Consulta> consultas = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return consultas;
        String sql = "SELECT * FROM Consulta"; 
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                
                Paciente p = buscarPacienteByCedula(rs.getString("cedula_paciente"));
                Doctor d = buscarDoctorByCedula(rs.getString("cedula_doctor"));
                Enfermedad e = buscarEnfermedadById(rs.getString("id_enfermedad"));

                if (p != null && d != null && e != null) {
                    Consulta consulta = new Consulta(
                        rs.getString("id_consulta"), rs.getInt("id_factura"), rs.getString("descripcion"),
                        e, rs.getTimestamp("fecha_consulta"), rs.getString("seguro_aplicado"),
                        d, p, rs.getBoolean("importancia"), rs.getDouble("precio")
                    );
                    consulta.setPagada(rs.getBoolean("pagada"));
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return consultas;
    }
    
    public void actualizarIdFacturaConsulta(String idConsulta, String idFactura) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "UPDATE Consulta SET id_factura = ? WHERE id_consulta = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, idFactura);
            ps.setString(2, idConsulta);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    public void agregarFactura(Factura factura) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Factura (id_factura, fecha_factura, subtotal, descuento, total_pagado, cedula_paciente) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, factura.getId());
            ps.setTimestamp(2, new java.sql.Timestamp(factura.getFecha().getTime()));
            ps.setDouble(3, factura.getSubTotal());
            ps.setDouble(4, factura.getDescuento());
            ps.setDouble(5, factura.getTotalPagado());
            ps.setString(6, factura.getPaciente().getCedula());
            ps.executeUpdate();
            misFacturas.add(factura);
            idFactura++;

            for (Consulta c : factura.getConsultasFacturadas()) {
                actualizarIdFacturaConsulta(c.getId(), factura.getId());
                actualizarEstadoPagadoConsulta(c.getId(), true);
            }
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Factura con ID '" + factura.getId() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    
    public ArrayList<Cita> obtenerCitasDesdeDB() {
        ArrayList<Cita> citas = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return citas;
        String sql = "SELECT * FROM Cita";
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = buscarPacienteByCedula(rs.getString("cedula_paciente"));
                Doctor d = buscarDoctorByCedula(rs.getString("cedula_doctor"));

                if (p != null && d != null) {
                    Cita cita = new Cita(
                        rs.getString("id_cita"), d, p, rs.getTimestamp("fecha_cita")
                    );
                    cita.setEstado(rs.getString("estado"));
                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return citas;
    }

    public ArrayList<Factura> obtenerFacturasDesdeDB() {
        ArrayList<Factura> facturas = new ArrayList<>();
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return facturas;
        String sql = "SELECT * FROM Factura";
        try (Statement st = cnx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = buscarPacienteByCedula(rs.getString("cedula_paciente"));
                if (p != null) {
                    
                    Factura factura = new Factura(
                        rs.getString("id_factura"), p, rs.getTimestamp("fecha_factura"),
                        new ArrayList<>(), new ArrayList<>(),
                        rs.getDouble("subtotal"), rs.getDouble("descuento"), rs.getDouble("total_pagado")
                    );
                    facturas.add(factura);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
        return facturas;
    }
    
    public void agregarStockVacuna(vacunacion vacuna, int cantidad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String updateSql = "UPDATE Vacuna SET cantidad_disponible = cantidad_disponible + ?, precio = ? WHERE id_vacuna = ?";
        String insertSql = "INSERT INTO Vacuna (id_vacuna, nombre, fabricante, cantidad_disponible, precio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement updatePs = cnx.prepareStatement(updateSql)) {
            updatePs.setInt(1, cantidad);
            updatePs.setDouble(2, vacuna.getPrecio());
            updatePs.setString(3, vacuna.getIdVacuna());
            int rowsAffected = updatePs.executeUpdate();

            if (rowsAffected == 0) {
                
                try (PreparedStatement insertPs = cnx.prepareStatement(insertSql)) {
                    insertPs.setString(1, vacuna.getIdVacuna());
                    insertPs.setString(2, vacuna.getNombre());
                    insertPs.setString(3, vacuna.getFabricante());
                    insertPs.setInt(4, cantidad);
                    insertPs.setDouble(5, vacuna.getPrecio());
                    insertPs.executeUpdate();
                    inventarioDeVacunas.add(vacuna); 
                }
            }
            
            vacunacion existingVacuna = buscarVacunaById(vacuna.getIdVacuna());
            if (existingVacuna != null) {
                existingVacuna.agregarStock(cantidad);
                existingVacuna.setPrecio(vacuna.getPrecio());
            } else {
               
                vacuna.setCantidadDisponible(cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }
    
    public boolean administrarVacuna(Paciente paciente, vacunacion vacunaAUsar, Date fecha, int cantMl) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return false;

        try {
            cnx.setAutoCommit(false);
            String checkStockSql = "SELECT cantidad_disponible FROM Vacuna WHERE id_vacuna = ?";
            int currentStock = 0;
            try (PreparedStatement ps = cnx.prepareStatement(checkStockSql)) {
                ps.setString(1, vacunaAUsar.getIdVacuna());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    currentStock = rs.getInt("cantidad_disponible");
                }
            }

            if (currentStock <= 0) {
                
                return false;
            }

            String updateStockSql = "UPDATE Vacuna SET cantidad_disponible = cantidad_disponible - 1 WHERE id_vacuna = ?";
            try (PreparedStatement ps = cnx.prepareStatement(updateStockSql)) {
                ps.setString(1, vacunaAUsar.getIdVacuna());
                ps.executeUpdate();
            }

            String insertRegistroSql = "INSERT INTO RegistroVacunacion (id_vacuna, cedula_paciente, fecha_aplicacion, cantidad_ml, pagada) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = cnx.prepareStatement(insertRegistroSql)) {
                ps.setString(1, vacunaAUsar.getIdVacuna());
                ps.setString(2, paciente.getCedula());
                ps.setTimestamp(3, new java.sql.Timestamp(fecha.getTime()));
                ps.setInt(4, cantMl);
                ps.setBoolean(5, false); 
                ps.executeUpdate();
            }

            vacunaAUsar.descontarStock(1);
            RegistroVacunacion nuevoRegistro = new RegistroVacunacion(vacunaAUsar, fecha, cantMl);
            paciente.agregarVacunaAplicada(nuevoRegistro);
            totalVacunasAplicadas++;

            cnx.commit();
            return true;

        } catch (SQLException e) {
            try {
                cnx.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    public void iniciarVigilancia(Consulta consulta, int horas) {
        if (consulta == null) return;

        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String sql = "INSERT INTO Bajo_vigilancia (cedula_paciente, id_enfermedad, cedula_doctor, fecha_inicio, estado, id_consulta_origen, horas_vigilancia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            
            Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(
                consulta.getPaciente(),
                consulta.getEnfermedad(),
                consulta.getDoctor(),
                new Date(),
                consulta,
                horas
            );

            ps.setString(1, nuevaVigilancia.getPaciente().getCedula());
            ps.setString(2, nuevaVigilancia.getEnfermedad().getId());
            ps.setString(3, nuevaVigilancia.getDoctorResponsable().getCedula());
            ps.setTimestamp(4, new java.sql.Timestamp(nuevaVigilancia.getFechaInicio().getTime()));
            ps.setString(5, nuevaVigilancia.getEstado());
            ps.setString(6, nuevaVigilancia.getConsultaOrigen().getId());
            ps.setInt(7, nuevaVigilancia.getHorasVigilancia());
            
            ps.executeUpdate();
                      
            this.misVigilancias.add(nuevaVigilancia);     
        } catch (SQLException e) {        
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
    }
    
    public void agregarVigilancia(Bajo_vigilancia vigilancia) {
        if (vigilancia == null) return;
        if (vigilancia.getPaciente() == null || vigilancia.getEnfermedad() == null || vigilancia.getDoctorResponsable() == null) return;

        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String sql = "INSERT INTO Bajo_vigilancia (cedula_paciente, id_enfermedad, cedula_doctor, fecha_inicio, estado, id_consulta_origen, horas_vigilancia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, vigilancia.getPaciente().getCedula());
            ps.setString(2, vigilancia.getEnfermedad().getId());
            ps.setString(3, vigilancia.getDoctorResponsable().getCedula());
            ps.setTimestamp(4, new java.sql.Timestamp(vigilancia.getFechaInicio().getTime()));
            ps.setString(5, vigilancia.getEstado());
            if (vigilancia.getConsultaOrigen() != null) {
                ps.setString(6, vigilancia.getConsultaOrigen().getId());
            } else {
                ps.setNull(6, java.sql.Types.VARCHAR);
            }
            ps.setInt(7, vigilancia.getHorasVigilancia());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                misVigilancias.add(vigilancia);
                idVigilancia++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion(cnx);
        }
    }

    public void finalizarVigilancia(Bajo_vigilancia registro) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String sql = "UPDATE Bajo_vigilancia SET estado = 'Finalizada' WHERE cedula_paciente = ? AND id_enfermedad = ? AND fecha_inicio = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, registro.getPaciente().getCedula());
            ps.setString(2, registro.getEnfermedad().getId());
            ps.setTimestamp(3, new java.sql.Timestamp(registro.getFechaInicio().getTime()));
            ps.executeUpdate();
            registro.setEstado("Finalizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConexionDB.cerrarConexion(cnx);
    }



    public Doctor buscarDoctorByCedula(String cedula) {

        for (Doctor doctor : misDoctores) {
            if (doctor.getCedula().equalsIgnoreCase(cedula)) {
                return doctor;
            }
        }

        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return null;
        String sql = "SELECT d.cedula, d.nombre, d.apellido, d.edad, d.sexo, e.nombre AS especialidad_nombre, u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Doctor d JOIN Especialidad e ON d.id_especialidad = e.id_especialidad " +
                     "LEFT JOIN Usuario u ON d.cedula = u.password AND u.tipo = 'Doctor' WHERE d.cedula = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = null;
                    if (rs.getString("user_nombre") != null) {
                        user = new User(rs.getString("user_nombre"), rs.getString("user_password"), rs.getString("user_tipo"));
                    }
                    Doctor doc = new Doctor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad_nombre"),
                        rs.getString("edad"),
                        rs.getString("sexo"),
                        user
                    );
                    misDoctores.add(doc);
                    return doc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Paciente buscarPacienteByCedula(String cedula) {
        for (Paciente paciente : misPacientes) {
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                return paciente;
            }
        }
        
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return null;
        String sql = "SELECT p.cedula, p.nombre, p.apellido, p.id_paciente, p.edad, p.sexo, " +
                     "s.id_seguro, s.nombre_empresa, s.tipo_seguro, s.descuento, " +
                     "u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Paciente p LEFT JOIN Seguro s ON p.id_seguro = s.id_seguro " +
                     "LEFT JOIN Usuario u ON p.cedula = u.password AND u.tipo = 'Paciente' WHERE p.cedula = ?";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = null;
                    if (rs.getString("user_nombre") != null) {
                        user = new User(rs.getString("user_nombre"), rs.getString("user_password"), rs.getString("user_tipo"));
                    }
                    Paciente paciente = new Paciente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id_paciente"),
                        rs.getString("edad"),
                        rs.getString("sexo"),
                        user
                    );
                    if (rs.getString("id_seguro") != null) {
                        Seguro seguro = new Seguro(rs.getString("id_seguro"), rs.getString("nombre_empresa"), rs.getString("tipo_seguro"), rs.getDouble("descuento"));
                        paciente.setSeguro(seguro);
                    }
                    misPacientes.add(paciente); 
                    return paciente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;       
    }

    public Especialidad buscarEspecialidadPorNombre(String nombre) {
        for (Especialidad esp : misEspecialidades) {
            if (esp.getNombre().equalsIgnoreCase(nombre)) {
                return esp;
            }
        }
        
        return null;
    }

    public Enfermedad buscarEnfermedadById(String id) {
        for (Enfermedad enf : misEnfermedades) {
            if (enf.getId().equalsIgnoreCase(id)) {
                return enf;
            }
        }
        return null;
    }

    public Consulta buscarConsultaById(String id) {
        for (Consulta cons : misConsultas) {
            if (cons.getId().equalsIgnoreCase(id)) {
                return cons;
            }
        }
        return null;
    }

    public vacunacion buscarVacunaById(String id) {
        for (vacunacion vac : inventarioDeVacunas) {
            if (vac.getIdVacuna().equalsIgnoreCase(id)) {
                return vac;
            }
        }
        return null;
    }

    public boolean ConfirmarLogin(String nombreUsuario, String password) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return false;
        String sql = "SELECT nombre, password, tipo FROM Usuario WHERE nombre = ? AND password = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    this.LoginUser = new User(
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("tipo")
                    );
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public int getTotalVacunasAplicadas() {
        return totalVacunasAplicadas;
    }
}
