package logico;

import java.io.Serializable;
import java.sql.Connection; // Importar java.sql.Connection
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date; // Para fechas

// NOTA: Las listas ArrayList aquí son para mantener la estructura de tu código original.
// En un sistema real con DB, muchas de estas listas se cargarían bajo demanda desde la DB.
// Para este ejercicio, las mantendremos y los métodos de agregar las insertarán en la DB.
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    private User LoginUser;
    private int totalVacunasAplicadas; // Este contador podría gestionarse en la DB también.

    private static Clinica clinica = null;

    // Contadores para IDs (ahora se gestionarán más por la DB o por secuencias/identidades)
    // Los mantengo para compatibilidad con tu código, pero su uso cambiará.
    public static int idDoctor = 1;
    public static int idPaciente = 1;
    public static int idConsulta = 1;
    public static int idFactura = 1;
    public static int idCita = 1;
    public static int idSeguro = 1;
    public static int idUser = 1;
    public static int idEspecialidad = 1;
    public static int idEnfermedad = 1;

    // Listas para mantener los objetos en memoria (para facilitar la transición)
    // En un sistema puramente basado en DB, estas listas se cargarían de la DB.
    private ArrayList<Especialidad> misEspecialidades;
    private ArrayList<Seguro> misSeguros;
    private ArrayList<Enfermedad> misEnfermedades;
    private ArrayList<User> misUsuarios; // Necesario para el login
    private ArrayList<Doctor> misDoctores; // Se cargarán de la DB
    private ArrayList<Paciente> misPacientes; // Se cargarán de la DB
    private ArrayList<Consulta> misConsultas; // Se cargarán de la DB
    private ArrayList<Cita> misCitas; // Se cargarán de la DB
    private ArrayList<Factura> misFacturas; // Se cargarán de la DB
    private ArrayList<vacunacion> inventarioDeVacunas; // Se cargarán de la DB
    private ArrayList<Bajo_vigilancia> misVigilancias; // Se cargarán de la DB


    private Clinica() {
        // Inicializar las listas aquí para evitar NullPointerExceptions
        misEspecialidades = new ArrayList<>();
        misSeguros = new ArrayList<>();
        misEnfermedades = new ArrayList<>();
        misUsuarios = new ArrayList<>();
        misDoctores = new ArrayList<>();
        misPacientes = new ArrayList<>();
        misConsultas = new ArrayList<>();
        misCitas = new ArrayList<>();
        misFacturas = new ArrayList<>();
        inventarioDeVacunas = new ArrayList<>();
        misVigilancias = new ArrayList<>();
    }

    public static Clinica getInstance() {
        if (clinica == null) {
            clinica = new Clinica();
            // Al obtener la instancia por primera vez, intentamos cargar los datos de la DB.
            // Esto es crucial para que los datos persistan entre ejecuciones.
            clinica.cargarDatosDesdeDB();
        }
        return clinica;
    }

    // --- NUEVO MÉTODO: Cargar todos los datos desde la base de datos ---
    private void cargarDatosDesdeDB() {
        System.out.println("Cargando datos desde la base de datos...");
        // Limpiar las listas en memoria antes de cargar para evitar duplicados
        misEspecialidades.clear();
        misSeguros.clear();
        misEnfermedades.clear();
        misUsuarios.clear();
        misDoctores.clear();
        misPacientes.clear();
        misConsultas.clear();
        misCitas.clear();
        misFacturas.clear();
        inventarioDeVacunas.clear();
        misVigilancias.clear();

        // Llama a los métodos de carga específicos para cada entidad
        cargarEspecialidadesDesdeDB();
        cargarSegurosDesdeDB();
        cargarEnfermedadesDesdeDB();
        cargarUsuariosDesdeDB();
        cargarDoctoresDesdeDB(); // Los doctores necesitan especialidades cargadas
        cargarPacientesDesdeDB(); // Los pacientes necesitan seguros cargados
        cargarVacunasDesdeDB(); // Cargar inventario de vacunas
        cargarConsultasDesdeDB(); // Las consultas necesitan doctores, pacientes, enfermedades
        cargarCitasDesdeDB(); // Las citas necesitan doctores y pacientes
        cargarFacturasDesdeDB(); // Las facturas necesitan pacientes, consultas, vacunas
        cargarVigilanciasDesdeDB(); // Las vigilancias necesitan pacientes, doctores, enfermedades, consultas

        // Actualizar los contadores de IDs basados en el máximo ID existente en la DB
        // Esto es importante para que los nuevos IDs no colisionen con los existentes.
        actualizarContadoresIDs();
    }

    // --- NUEVO MÉTODO: Poblar la base de datos por primera vez ---
    public void poblarBaseDeDatosInicial() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) {
            System.err.println("No se pudo poblar la base de datos: Conexión nula.");
            return;
        }

        try {
            // Desactivar el auto-commit para una transacción más eficiente
            cnx.setAutoCommit(false);

            // 1. Insertar Especialidades
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
                agregarEspecialidad(esp); // Este método ya inserta en DB
            }

            // 2. Insertar Seguros
            ArrayList<Seguro> segurosIniciales = new ArrayList<>();
            segurosIniciales.add(new Seguro("S-0", "No aplica", "Null", 0.0)); // Seguro por defecto
            segurosIniciales.add(new Seguro("S-1", "ARS Humano (Plan Básico)", "Básico", 0.50));
            segurosIniciales.add(new Seguro("S-2", "ARS Humano (Plan Superior)", "Superior", 0.85));
            segurosIniciales.add(new Seguro("S-3", "ARS Universal (Plan Básico)", "Básico", 0.45));
            segurosIniciales.add(new Seguro("S-4", "ARS Universal (Plan Premium)", "Premium", 0.80));
            segurosIniciales.add(new Seguro("S-5", "Seguros Reservas (Mi Salud)", "Básico", 0.55));
            segurosIniciales.add(new Seguro("S-6", "MAPFRE Salud ARS (Internacional)", "Internacional", 0.90));
            segurosIniciales.add(new Seguro("S-7", "Primera ARS (Plan Voluntario)", "Voluntario", 0.70));

            for (Seguro seg : segurosIniciales) {
                agregarSeguro(seg); // Este método ya inserta en DB
            }

            // 3. Insertar Enfermedades
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
                agregarEnfermedad(enfermedad); // Este método ya inserta en DB
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
            
            // 4. Insertar Usuario Administrador por defecto
            User admin = new User("Admin", "Admin", "Administrador");
            agregarUsuario(admin); // Este método ya inserta en DB

            // Confirmar la transacción
            cnx.commit();
            System.out.println("Base de datos poblada con datos iniciales exitosamente.");

            // Después de poblar, recargar los datos en memoria
            cargarDatosDesdeDB();

        } catch (SQLException e) {
            try {
                if (cnx != null) {
                    cnx.rollback(); // Revertir la transacción en caso de error
                    System.err.println("Transacción revertida debido a un error.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (cnx != null) {
                    cnx.setAutoCommit(true); // Volver a activar el auto-commit
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // --- Métodos para obtener el siguiente ID (ahora basados en el máximo de la DB) ---
    public static int getIdDoctor() { return idDoctor; }
    public static int getIdPaciente() { return idPaciente; }
    public static int getIdConsulta() { return idConsulta; }
    public static int getIdFactura() { return idFactura; }
    public static int getIdCita() { return idCita; }
    public static int getIdSeguro() { return idSeguro; }
    public static int getIdUser() { return idUser; }
    public static int getIdEspecialidad() { return idEspecialidad; }
    public static int getIdEnfermedad() { return idEnfermedad; }

    // --- NUEVO MÉTODO: Actualizar contadores de IDs desde la DB ---
    private void actualizarContadoresIDs() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        try (Statement st = cnx.createStatement()) {
            ResultSet rs;

            rs = st.executeQuery("SELECT ISNULL(MAX(CAST(SUBSTRING(id_especialidad, 5, LEN(id_especialidad)) AS INT)), 0) + 1 FROM Especialidad");
            if (rs.next()) idEspecialidad = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(CAST(SUBSTRING(id_seguro, 3, LEN(id_seguro)) AS INT)), 0) + 1 FROM Seguro");
            if (rs.next()) idSeguro = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(CAST(SUBSTRING(id_enfermedad, 5, LEN(id_enfermedad)) AS INT)), 0) + 1 FROM Enfermedad");
            if (rs.next()) idEnfermedad = rs.getInt(1);

            // Para IDs que no son strings (si los tienes)
            rs = st.executeQuery("SELECT ISNULL(MAX(id_paciente), 0) + 1 FROM Paciente"); // Asumiendo que id_paciente es INT
            if (rs.next()) idPaciente = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(id_consulta), 0) + 1 FROM Consulta"); // Asumiendo que id_consulta es INT
            if (rs.next()) idConsulta = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(id_factura), 0) + 1 FROM Factura"); // Asumiendo que id_factura es INT
            if (rs.next()) idFactura = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(id_cita), 0) + 1 FROM Cita"); // Asumiendo que id_cita es INT
            if (rs.next()) idCita = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(id_usuario), 0) + 1 FROM Usuario"); // Asumiendo que id_usuario es INT
            if (rs.next()) idUser = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(id_doctor), 0) + 1 FROM Doctor"); // Asumiendo que id_doctor es INT
            if (rs.next()) idDoctor = rs.getInt(1);

            rs = st.executeQuery("SELECT ISNULL(MAX(total_vacunas_aplicadas), 0) FROM ClinicaConfig"); // Si tienes una tabla de configuración
            if (rs.next()) totalVacunasAplicadas = rs.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // --- Métodos para cargar datos desde la DB a las listas en memoria ---

    public ArrayList<Especialidad> getMisEspecialidades() {
        // Siempre devuelve la lista en memoria, que se carga al inicio
        return misEspecialidades;
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
    }

    public ArrayList<Doctor> getMisDoctores() {
        return misDoctores;
    }

    private void cargarDoctoresDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT d.cedula, d.nombre, d.apellido, d.edad, d.sexo, e.nombre AS especialidad_nombre, u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Doctor d JOIN Especialidad e ON d.fk_id_especialidad = e.id_especialidad " +
                     "LEFT JOIN Usuario u ON d.cedula = u.password AND u.tipo = 'Doctor'"; // Asumiendo que la cédula es la contraseña del doctor
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
                // Cargar pacientes atendidos por este doctor (si es necesario)
                // Esto podría ser una consulta separada o un JOIN más complejo.
                // Por simplicidad, no lo implemento aquí, pero es una consideración.
                misDoctores.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                     "FROM Paciente p LEFT JOIN Seguro s ON p.fk_id_seguro = s.id_seguro " +
                     "LEFT JOIN Usuario u ON p.cedula = u.password AND u.tipo = 'Paciente'"; // Asumiendo que la cédula es la contraseña del paciente
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
                    rs.getInt("id_paciente"), // Asumiendo que id_paciente es INT
                    rs.getString("edad"),
                    rs.getString("sexo"),
                    user
                );
                if (rs.getString("id_seguro") != null) {
                    Seguro seguro = new Seguro(rs.getString("id_seguro"), rs.getString("nombre_empresa"), rs.getString("tipo_seguro"), rs.getDouble("descuento"));
                    paciente.setSeguro(seguro);
                }
                // Cargar historial clínico, vacunas aplicadas, etc. para este paciente
                // Esto requeriría más consultas o JOINs complejos.
                // Por simplicidad, no lo implemento aquí, pero es una consideración.
                misPacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Consulta> getMisConsultas() {
        return misConsultas;
    }

    private void cargarConsultasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT c.id_consulta, c.id_factura, c.descripcion, c.fecha_consulta, c.seguro_aplicado, c.importancia, c.precio, c.pagada, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                     "d.cedula AS doctor_cedula, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, e.nombre AS doctor_especialidad, d.edad AS doctor_edad, d.sexo AS doctor_sexo, " +
                     "enf.id_enfermedad, enf.nombre AS enfermedad_nombre " +
                     "FROM Consulta c " +
                     "JOIN Paciente p ON c.fk_cedula_paciente = p.cedula " +
                     "JOIN Doctor d ON c.fk_cedula_doctor = d.cedula " +
                     "JOIN Enfermedad enf ON c.fk_id_enfermedad = enf.id_enfermedad";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // Reconstruir objetos anidados
                Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula")); // Buscar en memoria o cargar si no existe
                if (paciente == null) { // Si no se cargó antes, crear uno básico
                    paciente = new Paciente(rs.getString("paciente_cedula"), rs.getString("paciente_nombre"), rs.getString("paciente_apellido"), rs.getInt("paciente_id"), rs.getString("paciente_edad"), rs.getString("paciente_sexo"), null);
                    // Considerar agregar a misPacientes si no está ya
                }

                Doctor doctor = buscarDoctorByCedula(rs.getString("doctor_cedula")); // Buscar en memoria o cargar si no existe
                if (doctor == null) { // Si no se cargó antes, crear uno básico
                    doctor = new Doctor(rs.getString("doctor_cedula"), rs.getString("doctor_nombre"), rs.getString("doctor_apellido"), rs.getString("doctor_especialidad"), rs.getString("doctor_edad"), rs.getString("doctor_sexo"), null);
                    // Considerar agregar a misDoctores si no está ya
                }

                Enfermedad enfermedad = buscarEnfermedadById(rs.getString("id_enfermedad")); // Buscar en memoria o cargar si no existe
                if (enfermedad == null) { // Si no se cargó antes, crear uno básico
                    enfermedad = new Enfermedad(rs.getString("id_enfermedad"), rs.getString("enfermedad_nombre"));
                    // Considerar agregar a misEnfermedades si no está ya
                }

                Consulta consulta = new Consulta(
                    rs.getString("id_consulta"),
                    rs.getInt("id_factura"),
                    rs.getString("descripcion"),
                    enfermedad,
                    rs.getTimestamp("fecha_consulta"), // Usar getTimestamp para Date
                    rs.getString("seguro_aplicado"),
                    doctor,
                    paciente,
                    rs.getBoolean("importancia"),
                    rs.getDouble("precio")
                );
                consulta.setPagada(rs.getBoolean("pagada"));
                misConsultas.add(consulta);
                // Asegurarse de que la consulta se agregue al historial del paciente
                paciente.agregarConsultaAlHistorial(consulta);
                paciente.agregarEnfermedadAlHistorial(enfermedad); // Asegurarse de que la enfermedad se agregue al historial del paciente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cita> getMisCitas() {
        return misCitas;
    }

    private void cargarCitasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT c.id_cita, c.fecha_cita, c.estado, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                     "d.cedula AS doctor_cedula, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, e.nombre AS doctor_especialidad, d.edad AS doctor_edad, d.sexo AS doctor_sexo " +
                     "FROM Cita c " +
                     "JOIN Paciente p ON c.fk_cedula_paciente = p.cedula " +
                     "JOIN Doctor d ON c.fk_cedula_doctor = d.cedula " +
                     "JOIN Especialidad e ON d.fk_id_especialidad = e.id_especialidad";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula"));
                if (paciente == null) {
                    paciente = new Paciente(rs.getString("paciente_cedula"), rs.getString("paciente_nombre"), rs.getString("paciente_apellido"), rs.getInt("paciente_id"), rs.getString("paciente_edad"), rs.getString("paciente_sexo"), null);
                }
                Doctor doctor = buscarDoctorByCedula(rs.getString("doctor_cedula"));
                if (doctor == null) {
                    doctor = new Doctor(rs.getString("doctor_cedula"), rs.getString("doctor_nombre"), rs.getString("doctor"), rs.getString("doctor_especialidad"), rs.getString("doctor_edad"), rs.getString("doctor_sexo"), null);
                }

                Cita cita = new Cita(
                    rs.getString("id_cita"),
                    doctor,
                    paciente,
                    rs.getTimestamp("fecha_cita")
                );
                cita.setEstado(rs.getString("estado"));
                misCitas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Factura> getMisFacturas() {
        return misFacturas;
    }

    private void cargarFacturasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT f.id_factura, f.fecha_factura, f.subtotal, f.descuento, f.total_pagado, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo " +
                     "FROM Factura f JOIN Paciente p ON f.fk_cedula_paciente = p.cedula";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Paciente paciente = buscarPacienteByCedula(rs.getString("paciente_cedula"));
                if (paciente == null) {
                    paciente = new Paciente(rs.getString("paciente_cedula"), rs.getString("paciente_nombre"), rs.getString("paciente_apellido"), rs.getInt("paciente_id"), rs.getString("paciente_edad"), rs.getString("paciente_sexo"), null);
                }
                // Para las consultas y vacunas facturadas, necesitarías consultas adicionales
                // o un diseño de DB que permita cargarlas eficientemente.
                // Por simplicidad, aquí se inicializan vacías.
                Factura factura = new Factura(
                    rs.getString("id_factura"),
                    paciente,
                    rs.getTimestamp("fecha_factura"),
                    new ArrayList<>(), // Consultas facturadas (necesitaría otra tabla de unión)
                    new ArrayList<>(), // Vacunas facturadas (necesitaría otra tabla de unión)
                    rs.getDouble("subtotal"),
                    rs.getDouble("descuento"),
                    rs.getDouble("total_pagado")
                );
                misFacturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    }

    public ArrayList<Bajo_vigilancia> getMisVigilancias() {
        return misVigilancias;
    }

    private void cargarVigilanciasDesdeDB() {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "SELECT bv.id_vigilancia, bv.fecha_inicio, bv.estado, bv.horas_vigilancia, " +
                     "p.cedula AS paciente_cedula, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, p.id_paciente AS paciente_id, p.edad AS paciente_edad, p.sexo AS paciente_sexo, " +
                     "d.cedula AS doctor_cedula, d.nombre AS doctor_nombre, d.apellido AS doctor_apellido, e.nombre AS doctor_especialidad, d.edad AS doctor_edad, d.sexo AS doctor_sexo, " +
                     "enf.id_enfermedad, enf.nombre AS enfermedad_nombre, " +
                     "c.id_consulta AS consulta_id " + // ID de la consulta de origen
                     "FROM Bajo_vigilancia bv " +
                     "JOIN Paciente p ON bv.fk_cedula_paciente = p.cedula " +
                     "JOIN Doctor d ON bv.fk_cedula_doctor = d.cedula " +
                     "JOIN Enfermedad enf ON bv.fk_id_enfermedad = enf.id_enfermedad " +
                     "LEFT JOIN Consulta c ON bv.fk_id_consulta_origen = c.id_consulta"; // LEFT JOIN si la consulta de origen es opcional
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
                Consulta consultaOrigen = null;
                if (rs.getString("consulta_id") != null) {
                    consultaOrigen = buscarConsultaById(rs.getString("consulta_id")); // Buscar la consulta de origen
                }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Métodos para agregar datos a la DB y a las listas en memoria ---

    public void agregarEspecialidad(Especialidad especialidad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Especialidad (id_especialidad, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, especialidad.getIdEspecialidad());
            ps.setString(2, especialidad.getNombre());
            ps.executeUpdate();
            misEspecialidades.add(especialidad); // Añadir también a la lista en memoria
            idEspecialidad++; // Incrementar el contador local
        } catch (SQLException e) {
            // Si la especialidad ya existe (por ejemplo, por un ID duplicado), no la agregamos
            if (e.getErrorCode() == 2627) { // SQL Server: Violation of PRIMARY KEY constraint
                System.out.println("Especialidad '" + especialidad.getNombre() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
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
            misSeguros.add(seguro); // Añadir también a la lista en memoria
            idSeguro++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Seguro '" + seguro.getNombreEmpresa() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarEnfermedad(Enfermedad enfermedad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Enfermedad (id_enfermedad, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, enfermedad.getId());
            ps.setString(2, enfermedad.getNombre());
            ps.executeUpdate();
            misEnfermedades.add(enfermedad); // Añadir también a la lista en memoria
            idEnfermedad++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Enfermedad '" + enfermedad.getNombre() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
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
            misUsuarios.add(user); // Añadir también a la lista en memoria
            idUser++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Usuario '" + user.getUsuario() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarDoctor(Doctor doctor) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Doctor (cedula, nombre, apellido, edad, sexo, fk_id_especialidad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, doctor.getCedula());
            ps.setString(2, doctor.getNombre());
            ps.setString(3, doctor.getApellido());
            ps.setString(4, doctor.getEdad());
            ps.setString(5, doctor.getSexo());
            // Necesitas el ID de la especialidad, no el nombre
            Especialidad esp = buscarEspecialidadPorNombre(doctor.getEspecialidad());
            if (esp != null) {
                ps.setString(6, esp.getIdEspecialidad());
            } else {
                ps.setNull(6, java.sql.Types.VARCHAR); // O manejar error
            }
            ps.executeUpdate();
            misDoctores.add(doctor); // Añadir también a la lista en memoria
            idDoctor++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Doctor con cédula '" + doctor.getCedula() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarPaciente(Paciente paciente) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Paciente (cedula, nombre, apellido, id_paciente, edad, sexo, fk_id_seguro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, paciente.getCedula());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setInt(4, paciente.getIdCodPaciente()); // Asumiendo que este ID es generado por tu lógica
            ps.setString(5, paciente.getEdad());
            ps.setString(6, paciente.getSexo());
            if (paciente.getSeguro() != null) {
                ps.setString(7, paciente.getSeguro().getIdSeguro());
            } else {
                ps.setNull(7, java.sql.Types.VARCHAR); // Si no tiene seguro
            }
            ps.executeUpdate();
            misPacientes.add(paciente); // Añadir también a la lista en memoria
            idPaciente++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Paciente con cédula '" + paciente.getCedula() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarConsulta(Consulta consulta) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Consulta (id_consulta, id_factura, descripcion, fecha_consulta, seguro_aplicado, importancia, precio, pagada, fk_cedula_paciente, fk_cedula_doctor, fk_id_enfermedad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, consulta.getId());
            ps.setInt(2, consulta.getIdFactura());
            ps.setString(3, consulta.getDescripcion());
            ps.setTimestamp(4, new java.sql.Timestamp(consulta.getFechaConsulta().getTime()));
            ps.setString(5, consulta.getSeguro());
            ps.setBoolean(6, consulta.isImportancia());
            ps.setDouble(7, consulta.getPrecio());
            ps.setBoolean(8, consulta.isPagada());
            ps.setString(9, consulta.getPaciente().getCedula());
            ps.setString(10, consulta.getDoctor().getCedula());
            ps.setString(11, consulta.getEnfermedad().getId());
            ps.executeUpdate();
            misConsultas.add(consulta); // Añadir también a la lista en memoria
            idConsulta++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Consulta con ID '" + consulta.getId() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarCita(Cita cita) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Cita (id_cita, fecha_cita, estado, fk_cedula_paciente, fk_cedula_doctor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, cita.getIdCita());
            ps.setTimestamp(2, new java.sql.Timestamp(cita.getFechaCita().getTime()));
            ps.setString(3, cita.getEstado());
            ps.setString(4, cita.getPaciente().getCedula());
            ps.setString(5, cita.getDoctor().getCedula());
            ps.executeUpdate();
            misCitas.add(cita); // Añadir también a la lista en memoria
            idCita++; // Incrementar el contador local
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Cita con ID '" + cita.getIdCita() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void agregarFactura(Factura factura) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        String sql = "INSERT INTO Factura (id_factura, fecha_factura, subtotal, descuento, total_pagado, fk_cedula_paciente) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, factura.getId());
            ps.setTimestamp(2, new java.sql.Timestamp(factura.getFecha().getTime()));
            ps.setDouble(3, factura.getSubTotal());
            ps.setDouble(4, factura.getDescuento());
            ps.setDouble(5, factura.getTotalPagado());
            ps.setString(6, factura.getPaciente().getCedula());
            ps.executeUpdate();
            misFacturas.add(factura); // Añadir también a la lista en memoria
            idFactura++; // Incrementar el contador local

            // Opcional: Actualizar el estado de pagado de las consultas y vacunas en la DB
            for (Consulta c : factura.getConsultasFacturadas()) {
                actualizarEstadoPagadoConsulta(c.getId(), true);
            }
            for (RegistroVacunacion rv : factura.getVacunasFacturadas()) {
                actualizarEstadoPagadoRegistroVacunacion(rv.getTipoVacuna().getIdVacuna(), rv.getFechaAplicacion(), true);
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                System.out.println("Factura con ID '" + factura.getId() + "' ya existe en la DB.");
            } else {
                e.printStackTrace();
            }
        }
    }

    // Método auxiliar para actualizar el estado de pagado de una consulta en la DB
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
    }

    // Método auxiliar para actualizar el estado de pagado de un registro de vacunación en la DB
    // Esto asume que tienes una tabla de RegistroVacunacion con una clave primaria compuesta
    // o un ID único para cada registro. Aquí uso idVacuna y fechaAplicacion como ejemplo.
    private void actualizarEstadoPagadoRegistroVacunacion(String idVacuna, Date fechaAplicacion, boolean pagada) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;
        // Necesitarías una tabla de RegistroVacunacion en tu DB para esto
        String sql = "UPDATE RegistroVacunacion SET pagada = ? WHERE fk_id_vacuna = ? AND fecha_aplicacion = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setBoolean(1, pagada);
            ps.setString(2, idVacuna);
            ps.setTimestamp(3, new java.sql.Timestamp(fechaAplicacion.getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void agregarStockVacuna(vacunacion vacuna, int cantidad) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        // Primero, intenta actualizar si ya existe
        String updateSql = "UPDATE Vacuna SET cantidad_disponible = cantidad_disponible + ?, precio = ? WHERE id_vacuna = ?";
        String insertSql = "INSERT INTO Vacuna (id_vacuna, nombre, fabricante, cantidad_disponible, precio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement updatePs = cnx.prepareStatement(updateSql)) {
            updatePs.setInt(1, cantidad);
            updatePs.setDouble(2, vacuna.getPrecio());
            updatePs.setString(3, vacuna.getIdVacuna());
            int rowsAffected = updatePs.executeUpdate();

            if (rowsAffected == 0) {
                // Si no se actualizó ninguna fila, significa que la vacuna no existe, entonces inserta
                try (PreparedStatement insertPs = cnx.prepareStatement(insertSql)) {
                    insertPs.setString(1, vacuna.getIdVacuna());
                    insertPs.setString(2, vacuna.getNombre());
                    insertPs.setString(3, vacuna.getFabricante());
                    insertPs.setInt(4, cantidad);
                    insertPs.setDouble(5, vacuna.getPrecio());
                    insertPs.executeUpdate();
                    inventarioDeVacunas.add(vacuna); // Añadir a la lista en memoria
                }
            }
            // Actualizar la cantidad en el objeto en memoria
            vacunacion existingVacuna = buscarVacunaById(vacuna.getIdVacuna());
            if (existingVacuna != null) {
                existingVacuna.agregarStock(cantidad);
                existingVacuna.setPrecio(vacuna.getPrecio()); // Asegurar que el precio también se actualice
            } else {
                // Si se insertó una nueva, asegúrate de que el objeto en memoria tenga la cantidad correcta
                vacuna.setCantidadDisponible(cantidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean administrarVacuna(Paciente paciente, vacunacion vacunaAUsar, Date fecha, int cantMl) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return false;

        try {
            cnx.setAutoCommit(false); // Iniciar transacción

            // 1. Verificar stock y descontar en la DB
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
                cnx.rollback(); // Revertir si no hay stock
                return false;
            }

            String updateStockSql = "UPDATE Vacuna SET cantidad_disponible = cantidad_disponible - 1 WHERE id_vacuna = ?";
            try (PreparedStatement ps = cnx.prepareStatement(updateStockSql)) {
                ps.setString(1, vacunaAUsar.getIdVacuna());
                ps.executeUpdate();
            }

            // 2. Registrar la aplicación de la vacuna en la DB
            String insertRegistroSql = "INSERT INTO RegistroVacunacion (fk_id_vacuna, fk_cedula_paciente, fecha_aplicacion, cantidad_ml, pagada) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = cnx.prepareStatement(insertRegistroSql)) {
                ps.setString(1, vacunaAUsar.getIdVacuna());
                ps.setString(2, paciente.getCedula());
                ps.setTimestamp(3, new java.sql.Timestamp(fecha.getTime()));
                ps.setInt(4, cantMl);
                ps.setBoolean(5, false); // Por defecto no pagada
                ps.executeUpdate();
            }

            // 3. Actualizar objetos en memoria
            vacunaAUsar.descontarStock(1);
            RegistroVacunacion nuevoRegistro = new RegistroVacunacion(vacunaAUsar, fecha, cantMl);
            paciente.agregarVacunaAplicada(nuevoRegistro);
            totalVacunasAplicadas++; // Este contador también debería persistir en DB si es crítico.

            cnx.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                cnx.rollback(); // Revertir en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cnx != null) cnx.setAutoCommit(true); // Restaurar auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void iniciarVigilancia(Consulta consulta, int horas) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        String sql = "INSERT INTO Bajo_vigilancia (fk_cedula_paciente, fk_id_enfermedad, fk_cedula_doctor, fecha_inicio, estado, fk_id_consulta_origen, horas_vigilancia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, consulta.getPaciente().getCedula());
            ps.setString(2, consulta.getEnfermedad().getId());
            ps.setString(3, consulta.getDoctor().getCedula());
            ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime())); // Fecha actual
            ps.setString(5, "Activa");
            ps.setString(6, consulta.getId()); // ID de la consulta de origen
            ps.setInt(7, horas);
            ps.executeUpdate();

            Bajo_vigilancia nuevaVigilancia = new Bajo_vigilancia(
                consulta.getPaciente(),
                consulta.getEnfermedad(),
                consulta.getDoctor(),
                new Date(),
                consulta,
                horas
            );
            misVigilancias.add(nuevaVigilancia); // Añadir a la lista en memoria
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finalizarVigilancia(Bajo_vigilancia registro) {
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return;

        // Asumiendo que tienes un ID único para Bajo_vigilancia en tu DB
        // Si no, necesitarías una clave compuesta (ej. paciente_cedula, enfermedad_id, fecha_inicio)
        String sql = "UPDATE Bajo_vigilancia SET estado = 'Finalizada' WHERE fk_cedula_paciente = ? AND fk_id_enfermedad = ? AND fecha_inicio = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, registro.getPaciente().getCedula());
            ps.setString(2, registro.getEnfermedad().getId());
            ps.setTimestamp(3, new java.sql.Timestamp(registro.getFechaInicio().getTime()));
            ps.executeUpdate();

            registro.setEstado("Finalizada"); // Actualizar el objeto en memoria
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Métodos de búsqueda (ahora también pueden buscar en la DB si no están en memoria) ---

    public Doctor buscarDoctorByCedula(String cedula) {
        // Primero busca en la lista en memoria
        for (Doctor doctor : misDoctores) {
            if (doctor.getCedula().equalsIgnoreCase(cedula)) {
                return doctor;
            }
        }
        // Si no está en memoria, intenta cargarlo de la DB
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return null;
        String sql = "SELECT d.cedula, d.nombre, d.apellido, d.edad, d.sexo, e.nombre AS especialidad_nombre, u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Doctor d JOIN Especialidad e ON d.fk_id_especialidad = e.id_especialidad " +
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
                    misDoctores.add(doc); // Añadir a la lista en memoria para futuras búsquedas
                    return doc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Paciente buscarPacienteByCedula(String cedula) {
        // Primero busca en la lista en memoria
        for (Paciente paciente : misPacientes) {
            if (paciente.getCedula().equalsIgnoreCase(cedula)) {
                return paciente;
            }
        }
        // Si no está en memoria, intenta cargarlo de la DB
        Connection cnx = ConexionDB.obtenerConexion();
        if (cnx == null) return null;
        String sql = "SELECT p.cedula, p.nombre, p.apellido, p.id_paciente, p.edad, p.sexo, " +
                     "s.id_seguro, s.nombre_empresa, s.tipo_seguro, s.descuento, " +
                     "u.nombre AS user_nombre, u.password AS user_password, u.tipo AS user_tipo " +
                     "FROM Paciente p LEFT JOIN Seguro s ON p.fk_id_seguro = s.id_seguro " +
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
                    misPacientes.add(paciente); // Añadir a la lista en memoria
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
        // Si no está en memoria, no la buscaremos en DB aquí, ya que se cargan todas al inicio.
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

    // --- Métodos de persistencia (guardar todo) ---
    // Este método ahora es menos crítico si cada 'agregar' ya persiste.
    // Podría usarse para guardar configuraciones globales o si se hacen cambios masivos.
    public void guardarClinica() {
        // Si todos los métodos de 'agregar' y 'actualizar' ya persisten en la DB,
        // este método podría ser para guardar configuraciones globales o para
        // un "guardar todo" si se hacen cambios masivos en memoria sin persistir inmediatamente.
        // Por ahora, lo dejamos vacío o para futuras configuraciones.
        System.out.println("Guardando configuraciones de la clínica (si las hay).");
    }

    // --- Getters y Setters restantes ---
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
    // No hay setters para las listas, ya que se gestionan internamente.
}
