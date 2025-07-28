package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.User;

public class Cita extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tableDoctores;
    private DefaultTableModel modeloDoctores;
    
    // Campos de texto para los datos del paciente
    private JTextField txtCedulaPaciente;
    private JTextField txtNombrePaciente;
    private JTextField txtApellidoPaciente;
    private JTextField txtEdadPaciente;
    private JSpinner spnFechaCita;

    public Cita() {
        setTitle("Realizar Cita");
        setBounds(100, 100, 820, 480);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        // --- Panel de Datos del Paciente ---
        JPanel panelPaciente = new JPanel();
        panelPaciente.setBorder(new TitledBorder(null, "Información del Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPaciente.setBounds(10, 20, 340, 350);
        contentPanel.add(panelPaciente);
        panelPaciente.setLayout(null);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblCedula.setBounds(20, 40, 80, 25);
        panelPaciente.add(lblCedula);
        txtCedulaPaciente = new JTextField();
        txtCedulaPaciente.setBounds(110, 40, 210, 25);
        panelPaciente.add(txtCedulaPaciente);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNombre.setBounds(20, 80, 80, 25);
        panelPaciente.add(lblNombre);
        txtNombrePaciente = new JTextField();
        txtNombrePaciente.setBounds(110, 80, 210, 25);
        panelPaciente.add(txtNombrePaciente);
        
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblApellido.setBounds(20, 120, 80, 25);
        panelPaciente.add(lblApellido);
        txtApellidoPaciente = new JTextField();
        txtApellidoPaciente.setBounds(110, 120, 210, 25);
        panelPaciente.add(txtApellidoPaciente);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblEdad.setBounds(20, 160, 80, 25);
        panelPaciente.add(lblEdad);
        txtEdadPaciente = new JTextField();
        txtEdadPaciente.setBounds(110, 160, 210, 25);
        panelPaciente.add(txtEdadPaciente);

        JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
        lblFechaDeLa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblFechaDeLa.setBounds(20, 220, 120, 25);
        panelPaciente.add(lblFechaDeLa);
        
        spnFechaCita = new JSpinner();
        spnFechaCita.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spnFechaCita.setBounds(150, 215, 170, 30);
        panelPaciente.add(spnFechaCita);
        
        // --- Panel para seleccionar al Doctor ---
        JPanel panelDoctores = new JPanel();
        panelDoctores.setBorder(new TitledBorder(null, "Seleccione un Doctor Disponible", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDoctores.setBounds(360, 20, 430, 350);
        contentPanel.add(panelDoctores);
        panelDoctores.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panelDoctores.add(scrollPane, BorderLayout.CENTER);

        tableDoctores = new JTable();
        modeloDoctores = new DefaultTableModel();
        String[] identificadores = { "Nombre", "Apellido", "Especialidad" };
        modeloDoctores.setColumnIdentifiers(identificadores);
        tableDoctores.setModel(modeloDoctores);
        scrollPane.setViewportView(tableDoctores);
        
        // --- Botones de Acción ---
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRegistrar = new JButton("Registrar Cita");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                registrarCita();
            }
        });
        buttonPane.add(btnRegistrar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        // Cargar la lista de doctores al iniciar la ventana
        cargarDoctores();
    }

    private void registrarCita() {
        // 1. Validar los datos del paciente
        String cedula = txtCedulaPaciente.getText();
        String nombre = txtNombrePaciente.getText();
        String apellido = txtApellidoPaciente.getText();
        String edad = txtEdadPaciente.getText();
        if(cedula.trim().isEmpty() || nombre.trim().isEmpty() || apellido.trim().isEmpty() || edad.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los datos del paciente.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validar que se seleccionó un doctor
        int selectedRow = tableDoctores.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un doctor de la lista.", "Error de Selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Obtener el doctor seleccionado de forma segura
        Doctor doctorSeleccionado = Clinica.getInstance().getMisDoctores().get(selectedRow);

        // 4. Buscar o crear al paciente
        Paciente pacienteParaLaCita = Clinica.getInstance().buscarPacienteByCedula(cedula);
        if (pacienteParaLaCita == null) { // Si el paciente no existe, se crea uno nuevo
            // Para el nuevo paciente, se crea un User genérico. La contraseña es su cédula.
            User usuarioPaciente = new User(nombre, cedula, "Paciente");
            
            pacienteParaLaCita = new Paciente(cedula, nombre, apellido, Clinica.getIdPaciente(), edad, usuarioPaciente);
            
            // Se agregan ambos objetos a la clínica
            Clinica.getInstance().agregarPaciente(pacienteParaLaCita);
            Clinica.getInstance().agregarUsuario(usuarioPaciente);
            
            JOptionPane.showMessageDialog(this, "Nuevo paciente registrado en el sistema.", "Paciente Nuevo", JOptionPane.INFORMATION_MESSAGE);
        }

        // 5. Crear la Cita
        String idCita = "CITA-" + Clinica.getIdCita();
        Date fechaCita = (Date) spnFechaCita.getValue();
        logico.Cita nuevaCita = new logico.Cita(idCita, doctorSeleccionado, pacienteParaLaCita, fechaCita);
        Clinica.getInstance().agregarCita(nuevaCita);
        
        JOptionPane.showMessageDialog(this, "Cita registrada con éxito para el paciente " + pacienteParaLaCita.getNombre(), "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        
        // Se cierra la ventana después de registrar
        dispose();
    }

    /**
     * Carga la lista de doctores disponibles en la tabla.
     */
    public void cargarDoctores() {
        modeloDoctores.setRowCount(0); 
        ArrayList<Doctor> doctores = Clinica.getInstance().getMisDoctores();
        if (doctores != null) {
            for (Doctor doctor : doctores) {
                Object[] row = new Object[3];
                row[0] = doctor.getNombre();
                row[1] = doctor.getApellido();
                row[2] = doctor.getEspecialidad();
                modeloDoctores.addRow(row);
            }
        }
    }
}