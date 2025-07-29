package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;

public class HistoriaXPaciente extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable tblConsultas;
    private DefaultTableModel modelo;

    public HistoriaXPaciente() {
        setBounds(100, 100, 824, 513);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setModal(true); // Para que bloquee la ventana de atrás

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton cancelButton = new JButton("Salir");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        modelo = new DefaultTableModel();
        String[] headers = { "Doctor", "Paciente", "Fecha", "Diagnóstico", "Descripción", "Importancia" };
        modelo.setColumnIdentifiers(headers);
        contentPanel.setLayout(null);
        tblConsultas = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tblConsultas);
        scrollPane.setBounds(10, 11, 788, 419);
        contentPanel.add(scrollPane);
    }

    public void actualizarTabla(String cedulaPaciente) {
        modelo.setRowCount(0);
        Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(cedulaPaciente);

        // <-- CAMBIO: Se lee desde la nueva estructura de datos
        if (paciente != null && paciente.getMiHistoriaClinica() != null) {
            ArrayList<Consulta> consultas = paciente.getMiHistoriaClinica().getMisConsultas();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Consulta consulta : consultas) {
                Doctor doctor = consulta.getDoctor();
                Object[] fila = {
                    doctor.getNombre() + " " + doctor.getApellido(),
                    paciente.getNombre() + " " + paciente.getApellido(),
                    sdf.format(consulta.getFechaConsulta()),
                    consulta.getEnfermedad(),
                    consulta.getDescripcion(),
                    consulta.isImportancia() ? "Urgente" : "Normal"
                };
                modelo.addRow(fila);
            }
        }
    }
}