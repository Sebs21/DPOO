package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

import logico.Bajo_vigilancia;
import logico.Clinica;
import logico.Paciente;

public class Reporte_control_enfermedades extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private DefaultTableModel model;
    private final CardLayout cardLayout;

    public Reporte_control_enfermedades() {
        setTitle("Reporte de Pacientes en Vigilancia");
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
        setModal(true);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        initFormularioPanel();
        initTablaPanel();
    }

    private void initFormularioPanel() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);

        JLabel lblCodigoDepaciente = new JLabel("Cédula del Paciente (Opcional):");
        lblCodigoDepaciente.setBounds(21, 21, 192, 26);
        panelFormulario.add(lblCodigoDepaciente);

        txt_code_paciente = new JTextField();
        txt_code_paciente.setBounds(221, 21, 186, 32);
        panelFormulario.add(txt_code_paciente);

        txt_nombre_paciente = new JTextField();
        txt_nombre_paciente.setEditable(false);
        txt_nombre_paciente.setBounds(428, 21, 186, 32);
        panelFormulario.add(txt_nombre_paciente);

        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(0, 448, 842, 45);
        panelFormulario.add(panelBotones);
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnVerReporte = new JButton("Ver Reporte");
        btnVerReporte.addActionListener(e -> {
            loadReporte(txt_code_paciente.getText());
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
        // <-- CAMBIO: Se busca en la lista correcta de la clínica
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(codigoPaciente);
        if (paciente != null) {
            txt_nombre_paciente.setText(paciente.getNombre());
        } else {
            txt_nombre_paciente.setText("");
        }
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        // <-- CAMBIO: Se actualizan las columnas
        String[] identificadores = {"Paciente", "Doctor Responsable", "Enfermedad", "Horas Vigilancia", "Fecha Inicio", "Estado"};
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

    private void loadReporte(String cedulaFiltro) {
        model.setRowCount(0);
        // <-- CAMBIO: Se lee desde la lista correcta de la clínica
        ArrayList<Bajo_vigilancia> vigilancias = Clinica.getInstance().getMisVigilancias();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Bajo_vigilancia vigi : vigilancias) {
            // Si se ingresó una cédula, se filtra. Si no, se muestran todos.
            if (cedulaFiltro.isEmpty() || vigi.getPaciente().getCedula().equalsIgnoreCase(cedulaFiltro)) {
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

    private void mostrarVista(String vista) {
        cardLayout.show(contentPanel, vista);
    }
}