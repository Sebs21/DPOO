package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import logico.User;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEdadPaciente;
	private JSpinner spnFechaCita;
	private JTextField txtIdCita;
	private JTextField txtPacienteSesion;

	private JTable tableDoctores;
	private static Object[] doctoresRow;
	private static DefaultTableModel modelo;

	private User pacienteSelect = Clinica.getInstance().getLoginUser();
	private Paciente pacienteCita = Clinica.getInstance().buscarPacienteByCedula(pacienteSelect.getPass());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Cita dialog = new Cita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Cita() {

		if (Clinica.getInstance().getMisDoctores().isEmpty() || Clinica.getInstance().getMisPacientes().isEmpty()) {
			JOptionPane.showMessageDialog( null, "Debe haber registrado un doctor y un paciente para poder proceder a cita." );
			dispose();
			return;
		}

		setTitle("Registrar Cita");
		setBounds(100, 100, 781, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblPaciente.setBounds(37, 66, 59, 16);
		contentPanel.add(lblPaciente);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(37, 95, 59, 16);
		contentPanel.add(lblEdad);

		txtEdadPaciente = new JTextField();
		txtEdadPaciente.setText(pacienteCita.getEdad());
		txtEdadPaciente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtEdadPaciente.setEditable(false);
		txtEdadPaciente.setEnabled(false);
		txtEdadPaciente.setColumns(10);
		txtEdadPaciente.setBounds(133, 92, 155, 22);
		contentPanel.add(txtEdadPaciente);

		JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
		lblFechaDeLa.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFechaDeLa.setBounds(92, 141, 118, 16);
		contentPanel.add(lblFechaDeLa);

		spnFechaCita = new JSpinner();
		spnFechaCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		spnFechaCita.setBounds(60, 170, 165, 37);
		spnFechaCita.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));

		contentPanel.add(spnFechaCita);

		JLabel lblIdCita = new JLabel("ID cita:");
		lblIdCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdCita.setBounds(40, 37, 56, 16);
		contentPanel.add(lblIdCita);

		txtIdCita = new JTextField();
		txtIdCita.setText(" C - " + Clinica.getInstance().idCita);
		txtIdCita.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtIdCita.setEnabled(false);
		txtIdCita.setEditable(false);
		txtIdCita.setColumns(10);
		txtIdCita.setBounds(133, 34, 155, 22);
		contentPanel.add(txtIdCita);

		txtPacienteSesion = new JTextField();
		txtPacienteSesion.setText(pacienteSelect.getUsuario());
		txtPacienteSesion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		txtPacienteSesion.setEnabled(false);
		txtPacienteSesion.setEditable(false);
		txtPacienteSesion.setColumns(10);
		txtPacienteSesion.setBounds(133, 63, 155, 22);
		contentPanel.add(txtPacienteSesion);

		JPanel panelDoctores = new JPanel();
		panelDoctores.setBounds(338, 24, 388, 204);
		contentPanel.add(panelDoctores);
		panelDoctores.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 388, 204);
		panelDoctores.add(scrollPane);

		tableDoctores = new JTable();
		modelo = new DefaultTableModel();
		String[] identificadores = { "Nombre", "Especialidad" };
		modelo.setColumnIdentifiers(identificadores);
		tableDoctores.setModel(modelo);
		scrollPane.setViewportView(tableDoctores);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int selectedRow = tableDoctores.getSelectedRow();

						if (selectedRow < 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un doctor.", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
							return;
						}

						String nombreDoctor = (String) modelo.getValueAt(selectedRow, 0);
						String especialidadDoctor = (String) modelo.getValueAt(selectedRow, 1);
						Doctor doctorSeleccionado = Clinica.getInstance().buscarDoctorByNombreEspecialidad(nombreDoctor,
								especialidadDoctor);

						if (doctorSeleccionado == null) {
							JOptionPane.showMessageDialog( null, "Error al seleccionar el doctor.","Error", JOptionPane.ERROR_MESSAGE );
							return;
						}

						String idCita = txtIdCita.getText();
						Date fechaCita = (Date) spnFechaCita.getValue();

						logico.Cita cita = new logico.Cita(idCita, doctorSeleccionado, pacienteCita, fechaCita);
						Clinica.getInstance().agregarCita(cita);
						 JOptionPane.showMessageDialog( null, "Cita registrada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE );
						clean();

					}
				});
				btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		datosDoctor();
		datos();
		
		clean();

	}

	public void datos() {
	    if (pacienteCita == null) {
	        JOptionPane.showMessageDialog(this, "No se encontró al paciente.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    txtPacienteSesion.setText(pacienteCita.getNombre());
	    txtEdadPaciente.setText(String.valueOf(pacienteCita.getEdad()));
	}


	public void clean() {
		txtIdCita.setText(" C - " + Clinica.getInstance().idCita);
		txtPacienteSesion.setText("");
		txtEdadPaciente.setText("");
	}

	public void datosDoctor() {
	    modelo.setRowCount(0); 
	    ArrayList<Doctor> aux = Clinica.getInstance().getMisDoctores();

	    for (Doctor doctor : aux) {
	       
	        Object[] doctoresRow = new Object[2]; 
	        doctoresRow[0] = doctor.getNombre();
	        doctoresRow[1] = doctor.getEspecialidad();
	        modelo.addRow(doctoresRow); 
	    }
	}

}