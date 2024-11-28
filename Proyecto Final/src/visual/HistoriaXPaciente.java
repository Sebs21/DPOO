package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoriaXPaciente extends JDialog {
//
	private final JPanel contentPanel = new JPanel();
	private JTable tblConsultas;
	private DefaultTableModel modelo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistoriaXPaciente dialog = new HistoriaXPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HistoriaXPaciente() {
		setBounds(100, 100, 824, 513);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		modelo = new DefaultTableModel();
        String[] headers = {"Doctor", "Nombre Paciente", "Fecha", "Enfermedad"};
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
        if (paciente != null) {
            ArrayList<Consulta> consultas = paciente.getMiHistoriaClinica().get(0).getMisConsultas();
            for (Consulta consulta : consultas) {
                Doctor doctor = consulta.getDoctor();
                String nombreDoctor = doctor.getNombre() + " " + doctor.getApellido();
                String nombrePaciente = paciente.getNombre() + " " + paciente.getApellido();
                String fecha = consulta.getFechaConsulta().toString(); 
                String enfermedad = consulta.getEnfermedad();

                Object[] fila = {nombreDoctor, nombrePaciente, fecha, enfermedad};
                modelo.addRow(fila);
            }
        }
    }

}
