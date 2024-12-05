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

import logico.Clinica;
import logico.Control_vacunacion;
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
                Reporte_Vacuna dialog = new Reporte_Vacuna(null, true); 
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

 
    public Reporte_Vacuna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Reporte Vacuna");
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

        panelBotones = new JPanel();
        panelBotones.setBounds(0, 448, 842, 45);
        panelFormulario.add(panelBotones);
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnVerReporte = new JButton("Ver Reporte");
        btnVerReporte.addActionListener(e -> {
            String codigoPaciente = txt_code_paciente.getText();
            load_(codigoPaciente); 
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
        Paciente paciente = Control_vacunacion.verificar_code_paciente(codigoPaciente); 
        if (paciente != null) {
            txt_nombre_paciente.setText(paciente.getNombre()); 
        } else {
            txt_nombre_paciente.setText(""); 
        }
    }

    public static void load_(String codigoPaciente) {
        model.setRowCount(0); 

        ArrayList<Control_vacunacion> controles = Clinica.getInstance().getControl_vacu();

        for (Control_vacunacion control : controles) {
            if (control instanceof vacunacion) { 
                vacunacion vacu = (vacunacion) control;

                if (codigoPaciente.isEmpty() || vacu.getCodigoPaciente().equals(codigoPaciente)) {
                    Object[] row = new Object[5];
                    row[0] = vacu.getCodVacu(); 
                    row[1] = vacu.getCodigoPaciente(); 
                    row[2] = vacu.getTipoVacuna();
                    row[3] = vacu.getCant_ml(); 
                    row[4] = vacu.getFecha(); 

                    model.addRow(row);
                }
            }
        }
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        String[] identificadores = {"Código", "Paciente", "Vacuna", "cantidad ml", "Fecha"};
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
