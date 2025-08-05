package visual;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import logico.Bajo_vigilancia;

public class Reporte_control_enfermedades extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;

    public Reporte_control_enfermedades(ArrayList<Bajo_vigilancia> vigilancias) {
        setTitle("Reporte de Enfermedades Bajo Vigilancia");
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new TitledBorder(null, "Vigilancias del Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        model = new DefaultTableModel();
        String[] headers = {"Paciente", "Enfermedad", "Doctor", "Horas", "Fecha Inicio", "Estado"};
        model.setColumnIdentifiers(headers);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        cargarDatos(vigilancias);
    }

    private void cargarDatos(ArrayList<Bajo_vigilancia> vigilancias) {
        model.setRowCount(0);
        for (Bajo_vigilancia vigi : vigilancias) {
            Object[] row = {
                vigi.getPaciente().getNombre() + " " + vigi.getPaciente().getApellido(),
                vigi.getEnfermedad().getNombre(),
                vigi.getDoctorResponsable().getNombre() + " " + vigi.getDoctorResponsable().getApellido(),
                vigi.getHorasVigilancia(),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(vigi.getFechaInicio()),
                vigi.getEstado()
            };
            model.addRow(row);
        }
    }
}