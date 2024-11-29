package visual;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Reporte_Vacuna extends JFrame {

    private JPanel contentPanel;
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reporte_Vacuna frame = new Reporte_Vacuna();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reporte_Vacuna() {
        setTitle("Reporte Vacuna");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel lblCodigoDepaciente = new JLabel("Código de Paciente:");
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
        
        btnVerReporte.addActionListener(e -> mostrarVista("tabla"));
        panelBotones.add(btnVerReporte);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> System.exit(0));
        panelBotones.add(btnCancelar);

        contentPanel.add(panelFormulario, "formulario");
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        String[] identificadores = {"Código", "Paciente",  "Vacuna", "cantidad ml", "Fecha"};
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

    private void mostrarVista(String vista) {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, vista);
    }
}
