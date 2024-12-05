

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

import logico.Bajo_vigilancia;
import logico.Clinica;
import logico.Control_enfermedad;
import logico.Paciente;

public class Reporte_control_enfermedades extends JDialog {

    private JPanel contentPanel;
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private static DefaultTableModel model;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reporte_control_enfermedades dialog = new Reporte_control_enfermedades();
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

   
    public Reporte_control_enfermedades() {
   
        setTitle("Reporte vigilancia");
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
    
    private void buscarPaciente(String codigoPaciente)
    {
        Paciente paciente = Control_enfermedad.verificar_code_paciente(codigoPaciente);
        if (paciente != null)
        {
            txt_nombre_paciente.setText(paciente.getNombre()); 
        } else {
            txt_nombre_paciente.setText(""); 
        }
    }

    public static void load_(String codigoPaciente) {
        model.setRowCount(0); 

        ArrayList<Control_enfermedad> controles = Clinica.getInstance().getControl_Enfer();

        for (Control_enfermedad control : controles) {
            if (control instanceof Bajo_vigilancia) { 
                Bajo_vigilancia vacu = (Bajo_vigilancia) control;

                if (codigoPaciente.isEmpty() || vacu.getCodigoPaciente().equals(codigoPaciente)) {
                    Object[] row = new Object[5];
                    row[0] = vacu.getCodVigilancia();
                    row[1] = vacu.getCodigoPaciente(); 
                    row[2] = vacu.getCodigodoctor();
                    row[3] = vacu.getTiempoVigilancia();
                    row[4] = vacu.getfecha_enfemeda_vigi();

                    model.addRow(row);
                }
            }
        }
    }

    private void initTablaPanel() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        String[] identificadores = {"Código", "Paciente", "Doctor", "Cantidad de hora",  "Fecha"};
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
