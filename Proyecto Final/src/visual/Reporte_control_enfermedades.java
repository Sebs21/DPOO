package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

import logico.Bajo_vigilancia;
import logico.Clinica;
import logico.Doctor;

public class Reporte_control_enfermedades extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JTextField txt_code_paciente;
    private JTable table;
    private DefaultTableModel model;
    private Doctor doctorFiltro = null; // <-- CAMBIO: Para filtrar por doctor
    private JPanel panelBusqueda;

    /**
     * Constructor para el Administrador (muestra la búsqueda).
     */
    public Reporte_control_enfermedades() {
        initComponents();
        loadReporte(""); // Cargar todos los pacientes inicialmente
    }
    
    /**
     * Constructor para el Doctor (oculta la búsqueda y filtra la tabla).
     */
    public Reporte_control_enfermedades(Doctor doctor) {
        this.doctorFiltro = doctor;
        initComponents();
        panelBusqueda.setVisible(false); // Ocultar el panel de búsqueda para el doctor
        setTitle("Mis Pacientes en Vigilancia");
        loadReporte(""); // Cargar solo los pacientes del doctor
    }

    private void initComponents() {
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setModal(true);

        contentPanel = new JPanel(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        panelBusqueda.setPreferredSize(new java.awt.Dimension(0, 80));

        JLabel lblCodigoDepaciente = new JLabel("Cédula del Paciente (Opcional):");
        lblCodigoDepaciente.setBounds(21, 21, 192, 26);
        panelBusqueda.add(lblCodigoDepaciente);

        txt_code_paciente = new JTextField();
        txt_code_paciente.setBounds(221, 21, 186, 32);
        panelBusqueda.add(txt_code_paciente);
        
        JButton btnBuscar = new JButton("Filtrar");
        btnBuscar.addActionListener(e -> loadReporte(txt_code_paciente.getText()));
        btnBuscar.setBounds(420, 21, 80, 32);
        panelBusqueda.add(btnBuscar);

        contentPanel.add(panelBusqueda, BorderLayout.NORTH);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(new TitledBorder(null, "Pacientes en Vigilancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panelTabla, BorderLayout.CENTER);

        model = new DefaultTableModel();
        String[] identificadores = {"Paciente", "Doctor Responsable", "Enfermedad", "Horas Vigilancia", "Fecha Inicio", "Estado"};
        model.setColumnIdentifiers(identificadores);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelBotones.add(btnCerrar);
    }
    
    private void loadReporte(String cedulaFiltro) {
        model.setRowCount(0);
        ArrayList<Bajo_vigilancia> vigilancias = Clinica.getInstance().getMisVigilancias();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Bajo_vigilancia vigi : vigilancias) {
            boolean doctorCoincide = (doctorFiltro == null || vigi.getDoctorResponsable().getCedula().equals(doctorFiltro.getCedula()));
            boolean pacienteCoincide = (cedulaFiltro.isEmpty() || vigi.getPaciente().getCedula().equalsIgnoreCase(cedulaFiltro));

            if (doctorCoincide && pacienteCoincide) {
                Object[] fila = {
                    vigi.getPaciente().getNombre() + " " + vigi.getPaciente().getApellido(),
                    vigi.getDoctorResponsable().getNombre() + " " + vigi.getDoctorResponsable().getApellido(),
                    vigi.getEnfermedad(),
                    vigi.getHorasVigilancia(),
                    sdf.format(vigi.getFechaInicio()),
                    vigi.getEstado()
                };
                model.addRow(fila);
            }
        }
    }
}