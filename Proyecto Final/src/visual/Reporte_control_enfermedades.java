package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToolBar;

public class Reporte_control_enfermedades extends JFrame {

    private final JPanel contentPanel = new JPanel(new CardLayout()); // CardLayout para cambiar vistas
    private JTextField txt_code_paciente;
    private JTextField txt_nombre_paciente;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reporte_control_enfermedades frame = new Reporte_control_enfermedades();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reporte_control_enfermedades() {
        setTitle("Reporte vigilancia");
        setBounds(100, 100, 868, 564);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Crear las vistas
        crearVistaFormulario();
        crearVistaTabla();
    }

    private void crearVistaFormulario() {
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

        contentPanel.add(panelFormulario, "formulario");
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 448, 842, 45);
        panelFormulario.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                        
                                JButton btnGuardar = new JButton("Ver Reporte");
                                panel.add(btnGuardar);
                                btnGuardar.addActionListener(e -> {
                                    mostrarVista("tabla");
                                });
                
                        JButton btnCancelar = new JButton("Cancelar");
                        panel.add(btnCancelar);
                        btnCancelar.addActionListener(e -> {
                            System.exit(0);
                        });
    }

    private void crearVistaTabla() {
        JPanel panelTabla = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        String[] identificadores = {"Código", "Vehículo", "Modelo", "Marca", "Precio", "Cantidad"};
        model.setColumnIdentifiers(identificadores);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarVista("formulario"));
        buttonPane.add(btnVolver);

        JButton btnModificar = new JButton("Modificar");
        buttonPane.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        buttonPane.add(btnEliminar);

        panelTabla.add(buttonPane, BorderLayout.SOUTH);

        contentPanel.add(panelTabla, "tabla");
    }

    private void mostrarVista(String vista) {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, vista);
    }
}
