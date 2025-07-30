package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.RegistroVacunacion;
import logico.Seguro;
import logico.User;
import logico.vacunacion;

public class Cita extends JDialog {

    private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private JTable tableDoctores;
    private DefaultTableModel modeloDoctores;
    private JTextField txtCedulaPaciente;
    private JTextField txtNombrePaciente;
    private JTextField txtApellidoPaciente;
    private JTextField txtEdadPaciente;
    private JSpinner spnFechaCita;
    private JButton btnBuscarPaciente;
    private JComboBox<String> cbxSeguros;
    private JList<vacunacion> listaVacunas; // <-- CAMBIO: Se usa el tipo 'vacunacion'

    public Cita() {
        setTitle("Realizar Cita y Aplicar Vacunas");
        setBounds(100, 100, 850, 600);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        setModal(true);

        // --- (El resto de la UI se mantiene igual) ---
        JPanel panelPaciente = new JPanel();
        panelPaciente.setBorder(new TitledBorder(null, "Información del Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelPaciente.setBounds(10, 20, 340, 320);
        contentPanel.add(panelPaciente);
        panelPaciente.setLayout(null);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblCedula.setBounds(20, 40, 80, 25);
        panelPaciente.add(lblCedula);
        txtCedulaPaciente = new JTextField();
        txtCedulaPaciente.setBounds(110, 40, 140, 25);
        panelPaciente.add(txtCedulaPaciente);

        btnBuscarPaciente = new JButton("Buscar");
        btnBuscarPaciente.setBounds(255, 40, 75, 25);
        panelPaciente.add(btnBuscarPaciente);

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

        JLabel lblSeguro = new JLabel("Seguro:");
        lblSeguro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblSeguro.setBounds(20, 200, 80, 25);
        panelPaciente.add(lblSeguro);
        
        cbxSeguros = new JComboBox<>();
        cbxSeguros.setBounds(110, 200, 210, 25);
        panelPaciente.add(cbxSeguros);
        cargarSegurosDisponibles();
        
        JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
        lblFechaDeLa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblFechaDeLa.setBounds(20, 260, 120, 25);
        panelPaciente.add(lblFechaDeLa);
        
        spnFechaCita = new JSpinner();
        spnFechaCita.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        spnFechaCita.setBounds(150, 255, 170, 30);
        panelPaciente.add(spnFechaCita);
        
        JPanel panelVacunas = new JPanel();
        panelVacunas.setBorder(new TitledBorder(null, "Seleccionar Vacunas a Aplicar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelVacunas.setBounds(10, 350, 340, 150);
        contentPanel.add(panelVacunas);
        panelVacunas.setLayout(new BorderLayout(0, 0));

        listaVacunas = new JList<>();
        listaVacunas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPaneVacunas = new JScrollPane(listaVacunas);
        panelVacunas.add(scrollPaneVacunas, BorderLayout.CENTER);

        JPanel panelDoctores = new JPanel();
        panelDoctores.setBorder(new TitledBorder(null, "Seleccione un Doctor Disponible", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDoctores.setBounds(360, 20, 450, 480);
        contentPanel.add(panelDoctores);
        panelDoctores.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPaneDoctores = new JScrollPane();
        panelDoctores.add(scrollPaneDoctores, BorderLayout.CENTER);

        tableDoctores = new JTable();
        modeloDoctores = new DefaultTableModel();
        String[] identificadores = { "Nombre", "Apellido", "Especialidad" };
        modeloDoctores.setColumnIdentifiers(identificadores);
        tableDoctores.setModel(modeloDoctores);
        scrollPaneDoctores.setViewportView(tableDoctores);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRegistrar = new JButton("Registrar Cita y Aplicar Vacunas");
        btnRegistrar.addActionListener(e -> registrarCitaConVacunas());
        buttonPane.add(btnRegistrar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        btnBuscarPaciente.addActionListener(e -> buscarPaciente());
        txtCedulaPaciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente();
            }
        });

        cargarDoctores();
        cargarVacunasDisponibles();
    }

    private void cargarVacunasDisponibles() {
        ArrayList<vacunacion> vacunas = Clinica.getInstance().getInventarioDeVacunas();
        listaVacunas.setListData(vacunas.toArray(new vacunacion[0]));
    }

    private void buscarPaciente() {
        String cedula = txtCedulaPaciente.getText();
        if (cedula.trim().isEmpty()) return;
        
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedula);
        if (paciente != null) {
            txtNombrePaciente.setText(paciente.getNombre());
            txtApellidoPaciente.setText(paciente.getApellido());
            txtEdadPaciente.setText(paciente.getEdad());
            if (paciente.getSeguro() != null) {
                cbxSeguros.setSelectedItem(paciente.getSeguro().getNombreEmpresa());
            } else {
                cbxSeguros.setSelectedIndex(0);
            }
        } else {
            txtNombrePaciente.setText("");
            txtApellidoPaciente.setText("");
            txtEdadPaciente.setText("");
            cbxSeguros.setSelectedIndex(0);
            JOptionPane.showMessageDialog(this, "Paciente no encontrado. Puede registrarlo llenando los campos.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void registrarCitaConVacunas() {
        String cedula = txtCedulaPaciente.getText();
        String nombre = txtNombrePaciente.getText();
        String apellido = txtApellidoPaciente.getText();
        String edad = txtEdadPaciente.getText();
        if(cedula.trim().isEmpty() || nombre.trim().isEmpty() || apellido.trim().isEmpty() || edad.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los datos del paciente.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedRow = tableDoctores.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un doctor.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Doctor doctorSeleccionado = Clinica.getInstance().getMisDoctores().get(selectedRow);

        Paciente pacienteParaLaCita = Clinica.getInstance().buscarPacienteByCedula(cedula);
        if (pacienteParaLaCita == null) { 
            User usuarioPaciente = new User(nombre, cedula, "Paciente");
            pacienteParaLaCita = new Paciente(cedula, nombre, apellido, Clinica.getIdPaciente(), edad, usuarioPaciente);
            Clinica.getInstance().agregarPaciente(pacienteParaLaCita);
            Clinica.getInstance().agregarUsuario(usuarioPaciente);
            JOptionPane.showMessageDialog(this, "Nuevo paciente registrado.", "Paciente Nuevo", JOptionPane.INFORMATION_MESSAGE);
        }

        int indexSeguro = cbxSeguros.getSelectedIndex();
        if (indexSeguro > 0) {
            String nombreSeguro = cbxSeguros.getSelectedItem().toString();
            for (Seguro seguro : Clinica.getInstance().getMisSeguros()) {
                if (seguro.getNombreEmpresa().equals(nombreSeguro)) {
                    pacienteParaLaCita.setSeguro(seguro);
                    break;
                }
            }
        } else {
            pacienteParaLaCita.setSeguro(null);
        }

        
        List<vacunacion> vacunasSeleccionadas = listaVacunas.getSelectedValuesList();
        if (!vacunasSeleccionadas.isEmpty()) {
            for (vacunacion vacunaTipo : vacunasSeleccionadas) {
              
                RegistroVacunacion registro = new RegistroVacunacion(vacunaTipo, (Date) spnFechaCita.getValue(), 1);
                pacienteParaLaCita.agregarVacunaAplicada(registro);
            }
        }

        String idCita = "CITA-" + Clinica.getIdCita();
        Date fechaCita = (Date) spnFechaCita.getValue();
        logico.Cita nuevaCita = new logico.Cita(idCita, doctorSeleccionado, pacienteParaLaCita, fechaCita);
        Clinica.getInstance().agregarCita(nuevaCita);
        
        JOptionPane.showMessageDialog(this, "Cita registrada con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }

    public void cargarDoctores() {
        modeloDoctores.setRowCount(0); 
        ArrayList<Doctor> doctores = Clinica.getInstance().getMisDoctores();
        if (doctores != null) {
            for (Doctor doctor : doctores) {
                Object[] row = { doctor.getNombre(), doctor.getApellido(), doctor.getEspecialidad() };
                modeloDoctores.addRow(row);
            }
        }
    }
    
    private void cargarSegurosDisponibles() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("< Sin Seguro >");
        for (Seguro seguro : Clinica.getInstance().getMisSeguros()) {
            model.addElement(seguro.getNombreEmpresa());
        }
        cbxSeguros.setModel(model);
    }
}