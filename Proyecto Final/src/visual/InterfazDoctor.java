package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

public class InterfazDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblPacientes;
	private DefaultTableModel modelo;
	private JTable table;
	private Doctor doc;
	private JTable tblCitas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazDoctor dialog = new InterfazDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	
	
	public InterfazDoctor() {
		
		//setTitle("Doctor" + doc.getNombre());
		setBounds(100, 100, 1275, 803);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			setIconImage(new ImageIcon (getClass().getResource("/visual/SIGIC_logo.jpg")).getImage());
			panel.setBorder(new TitledBorder(new LineBorder(new Color(224, 255, 255), 3, true), "Pacientes Consultados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(513, 11, 736, 709);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			
			tblPacientes = new JTable();
			scrollPane.setViewportView(tblPacientes);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(135, 206, 250), 3, true), "Citas Pendientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 460, 709);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tblCitas = new JTable();
		scrollPane.setViewportView(tblCitas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnInformacion = new JButton("M\u00E1s Informaci\u00F3n");
			buttonPane.add(btnInformacion);
			{
				JButton cancelButton = new JButton("Cancelar");
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
        String[] headers = {"Cedula", "Nombre", "Apellido", "Edad", "Seguro", "Enfermedad", "Vacunas"};
        modelo.setColumnIdentifiers(headers);
		
        table = new JTable(modelo);
        table.setBorder(new MatteBorder(4, 6, 4, 6, (Color) new Color(176, 196, 222)));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        
	}
	
	public void actualizarTablaCitas() {
		
	}
	
	public void actualizarTablaConsultas(String CedulaDoc) {
		modelo.setColumnCount(0);
		Doctor aux = Clinica.getInstance().buscarDoctorByCedula(CedulaDoc);
			for (Paciente paciente : aux.getMisPacientes()) {
				Object[] fila = { 
						paciente.getCedula(),
		            	paciente.getNombre(),
		            	paciente.getApellido(),
		            	paciente.getEdad(),
		            	paciente.getSeguro().getNombreEmpresa(),
		            	paciente.getEnfermedad(),
		            	paciente.getMiVacuna() 
		            };
		            modelo.addRow(fila);
			}
		}
}
