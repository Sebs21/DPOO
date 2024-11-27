package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.HistoriaClinica;
import logico.Paciente;

public class InterfazDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblPacientes;
	private DefaultTableModel modelo;
	private JTable table;
	private Doctor doc;
	private JTable tblCitas;
	private DefaultTableModel modeloCitas;
	private Consulta consulta;
	
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
				
				JButton btnConsulta = new JButton("Proceder a Consulta");
				btnConsulta.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        int filaSeleccionada = tblCitas.getSelectedRow();
				        if (filaSeleccionada != -1) {
				        	Consultar consultar = new Consultar();
				            Cita cita = Clinica.getInstance().getMisCitas().get(filaSeleccionada);
				            Paciente paciente = (Paciente) cita.getPersona();
				            Doctor doctor = cita.getDoctor();
		
				            consultar.actualizarCampos(doctor, paciente);
				            consultar.setVisible(true);
				            consultar.setModal(true);

				            actualizarTablaConsultas(doctor.getCedula());
				            actualizarTablaCitas(doctor.getCedula());
				        }
				    }
				});

				buttonPane.add(btnConsulta);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		modelo = new DefaultTableModel();
		String[] headersConsultas = {"C�dula", "Edad", "Nombre", "Apellido", "Seguro", "Enfermedad"};
		modelo.setColumnIdentifiers(headersConsultas);
		tblPacientes.setModel(modelo);

        
        modeloCitas = new DefaultTableModel();
        String[] headersCitas = {"Edad", "Nombre", "Apellido", "Seguro"};
        modeloCitas.setColumnIdentifiers(headersCitas);
        tblCitas.setModel(modeloCitas);

	}
	
	public void actualizarTablaCitas(String cedulaDoctor) {
	    modeloCitas.setRowCount(0);
	    Doctor doctor = Clinica.getInstance().buscarDoctorByCedula(cedulaDoctor);

	    if (doctor != null) {
	        for (Cita cita : Clinica.getInstance().getMisCitas()) {
	            if (cita.getDoctor() == doctor) {
	                Paciente paciente = (Paciente) cita.getPersona();
	                Object[] fila = {    
	                	paciente.getEdad(),
	                	paciente.getNombre(),
	                    paciente.getApellido(),
	                    paciente.getSeguro().getNombreEmpresa()
	                };
	                modeloCitas.addRow(fila);
	            }
	        }
	    }
	}

	
	public void actualizarTablaConsultas(String CedulaDoc) {
		modelo.setColumnCount(0);
		Doctor aux = Clinica.getInstance().buscarDoctorByCedula(CedulaDoc);
			for (Paciente paciente : aux.getMisPacientes()) {
				Object[] fila = { 
						paciente.getCedula(),
						paciente.getEdad(),
		            	paciente.getNombre(),
		            	paciente.getApellido(),
		            	paciente.getSeguro().getNombreEmpresa(),
		            	paciente.getEnfermedad()
		            };
		            modelo.addRow(fila);
			}
		}

}