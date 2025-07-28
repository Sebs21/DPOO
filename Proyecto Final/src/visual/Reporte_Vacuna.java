package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

// <-- CAMBIO: Se eliminó la importación de Control_vacunacion
import logico.Clinica;
import logico.Paciente;
import logico.vacunacion;

public class Reporte_Vacuna extends JDialog {

    private JPanel contentPanel;
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private static DefaultTableModel model;
    private JButton btnVerReporte;
    private JPanel panelBotones;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Para probar, es bueno cargar la clínica aquí si existe el archivo
                // Clinica.cargarClinica(); 
                Reporte_Vacuna dialog = new Reporte_Vacuna();
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reporte_Vacuna() {
        setTitle("Reporte de Vacunación por Paciente"); // Título más descriptivo
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());

        contentPanel = new JPanel(new CardLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        initFormularioPanel();
        initTablaPanel();
    }

    private void initFormularioPanel() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);

        JLabel lblCodigoDepaciente = new JLabel("Cédula del Paciente:"); // Es mejor buscar por cédula
        lblCodigoDepaciente.setBounds(21, 21, 192, 26);
        panelFormulario.add(lblCodigoDepaciente);

        txt_code_paciente = new JTextField();
        txt_code_paciente.setBounds(221, 21, 186, 32);
        panelFormulario.add(txt_code_paciente);

        txt_nombre_paciente = new JTextField();
        txt_nombre_paciente.setEditable(false);
        txt_nombre_paciente.setBounds(428, 21, 186, 32);
        panelFormulario.add(txt_nombre_paciente);

        panelBotones = new JPanel();
        panelBotones.setBounds(0, 448, 842, 45);
        panelFormulario.add(panelBotones);
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnVerReporte = new JButton("Ver Reporte");
        btnVerReporte.addActionListener(e -> {
            String codigoPaciente = txt_code_paciente.getText();
            load_reporte(codigoPaciente); 
            mostrarVista("tabla");
        });
        panelBotones.add(btnVerReporte);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panelBotones.add(btnCancelar);

        contentPanel.add(panelFormulario, "formulario");

        txt_code_paciente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarPaciente(txt_code_paciente.getText());
            }
        });
    }

    private void buscarPaciente(String codigoPaciente) {
        // <-- CAMBIO: La búsqueda se hace a través de Clinica
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(codigoPaciente);
        if (paciente != null) {
            txt_nombre_paciente.setText(paciente.getNombre());
        } else {
            txt_nombre_paciente.setText("Paciente no encontrado");
        }
    }

    // <-- CAMBIO: El método load_ fue completamente reescrito con la lógica correcta
    void load_reporte(String cedulaPaciente) {
        model.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

        if (cedulaPaciente == null || cedulaPaciente.trim().isEmpty()) {
            // Opcional: Mostrar un mensaje al usuario indicando que debe ingresar una cédula
            return;
        }

        // 1. Buscar al paciente en la instancia de la clínica
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaPaciente);
        
        // 2. Si el paciente existe, obtener su historial de vacunas
        if (paciente != null && paciente.getMiVacuna() != null) {
            ArrayList<vacunacion> historialVacunas = paciente.getMiVacuna();

            // 3. Recorrer el historial y llenar la tabla
            for (vacunacion vacunaAplicada : historialVacunas) {
                Object[] row = new Object[5];
                row[0] = vacunaAplicada.getIdVacuna();
                row[1] = paciente.getNombre() + " " + paciente.getApellido();
                row[2] = vacunaAplicada.getNombre();
                // Nota: Asumiendo que tu clase 'vacunacion' también guarda la fecha y la cantidad
                // Si no es así, necesitarías otra clase como 'RegistroVacunacion'
                // para guardar esos detalles de la aplicación.
                row[3] = "N/A"; // Ejemplo para cantidad ml
                row[4] = "N/A"; // Ejemplo para fecha
                model.addRow(row);
            }
        }
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        // Columnas actualizadas para ser más claras
        String[] identificadores = {"ID Vacuna", "Paciente", "Nombre Vacuna", "Cantidad (ml)", "Fecha Aplicación"};
        model.setColumnIdentifiers(identificadores);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarVista("formulario"));
        panelBotones.add(btnVolver);

        panelTabla.add(panelBotones, BorderLayout.SOUTH);

        contentPanel.add(panelTabla, "tabla");
    }

    void mostrarVista(String vista) {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, vista);
    }
}