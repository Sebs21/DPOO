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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Paciente;
import logico.RegistroVacunacion;

public class Reporte_Vacuna extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private DefaultTableModel model;

    
    public Reporte_Vacuna(JFrame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
    }

   
    public Reporte_Vacuna(JDialog parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
    }
    
   
    private void initComponents() {
        setTitle("Reporte de Vacunación por Paciente");
        setBounds(100, 100, 868, 564);
        setIconImage(new ImageIcon(getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());

        contentPanel.setLayout(cardLayout);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        initFormularioPanel();
        initTablaPanel();
    }


    private void initFormularioPanel() {
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);

        JLabel lblCodigoDepaciente = new JLabel("Cédula del Paciente:");
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
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(codigoPaciente);
        if (paciente != null) {
            txt_nombre_paciente.setText(paciente.getNombre());
        } else {
            txt_nombre_paciente.setText("Paciente no encontrado");
        }
    }

    public void load_reporte(String cedulaPaciente) {
        model.setRowCount(0);

        if (cedulaPaciente == null || cedulaPaciente.trim().isEmpty()) {
            return;
        }

        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaPaciente);
        
        if (paciente != null && paciente.getMisVacunasAplicadas() != null) {
            ArrayList<RegistroVacunacion> historialVacunas = paciente.getMisVacunasAplicadas();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (RegistroVacunacion registro : historialVacunas) {
                Object[] row = new Object[5];
                row[0] = registro.getTipoVacuna().getIdVacuna();
                row[1] = paciente.getNombre() + " " + paciente.getApellido();
                row[2] = registro.getTipoVacuna().getNombre();
                row[3] = registro.getCantidadMl();
                row[4] = sdf.format(registro.getFechaAplicacion());
                model.addRow(row);
            }
        }
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
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

    public void mostrarVista(String vista) {
        cardLayout.show(contentPanel, vista);
    }
}