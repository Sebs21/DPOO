package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;

public class Consultar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDoctor;
	private JTextField txtIdConsulta;
	private JTextField txtSeguro;
	private JTextField txtDescripcion;
	private JTextField txtEnfermedad;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCedula;
	private JTextField txtEdad;
	private JRadioButton rdbtnNoImportante;
	private JRadioButton rdbtnImportante;
	private JTextField txtIdfactura;
	private JSpinner spnFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consultar dialog = new Consultar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consultar() {
		setTitle("Consulta");
		setBounds(100, 100, 1047, 723);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(175, 238, 238), 4, true), "Consulta",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(356, 370, 665, 270);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setBounds(331, 82, 324, 178);
		panel_3.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtEnfermedad = new JTextField();
		txtEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEnfermedad.setBounds(10, 82, 311, 177);
		panel_3.add(txtEnfermedad);
		txtEnfermedad.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(331, 49, 107, 23);
		panel_3.add(lblNewLabel_2);

		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblEnfermedad.setBounds(10, 48, 119, 23);
		panel_3.add(lblEnfermedad);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Seguro Medico",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 292, 336, 135);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Seguro:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(31, 56, 78, 23);
		panel_1.add(lblNewLabel_1);

		txtSeguro = new JTextField();
		txtSeguro.setEditable(false);
		txtSeguro.setBounds(140, 49, 165, 37);
		panel_1.add(txtSeguro);
		txtSeguro.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(176, 224, 230), 4, true), "Informaci\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 336, 270);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Doctor:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(32, 160, 91, 44);
		panel.add(lblNewLabel);

		txtDoctor = new JTextField();
		txtDoctor.setEditable(false);
		txtDoctor.setBounds(133, 165, 165, 37);
		panel.add(txtDoctor);
		txtDoctor.setColumns(10);

		txtIdConsulta = new JTextField();
		txtIdConsulta.setEditable(false);
		txtIdConsulta.setColumns(10);
		txtIdConsulta.setBounds(131, 30, 165, 37);
		panel.add(txtIdConsulta);

		JLabel lblIdConsulta = new JLabel("ID Consulta:");
		lblIdConsulta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdConsulta.setBounds(32, 24, 91, 44);
		panel.add(lblIdConsulta);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblFecha.setBounds(32, 215, 91, 44);
		panel.add(lblFecha);

		spnFecha = new JSpinner();
		spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spnFecha.setBounds(133, 222, 165, 37);
		panel.add(spnFecha);

		JLabel lblIdFactura = new JLabel("Id Factura:");
		lblIdFactura.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblIdFactura.setBounds(32, 90, 91, 44);
		panel.add(lblIdFactura);

		txtIdfactura = new JTextField();
		txtIdfactura.setEditable(false);
		txtIdfactura.setColumns(10);
		txtIdfactura.setBounds(133, 95, 165, 37);
		panel.add(txtIdfactura);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true), "Datos Paciente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(356, 11, 665, 348);
		contentPanel.add(panel_2);

		JLabel lblNombrel = new JLabel("Nombre:");
		lblNombrel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNombrel.setBounds(35, 134, 78, 23);
		panel_2.add(lblNombrel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(148, 127, 165, 37);
		panel_2.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(461, 127, 165, 37);
		panel_2.add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblApellido.setBounds(348, 134, 78, 23);
		panel_2.add(lblApellido);

		JButton btnHistoriaClinica = new JButton("Ver historia clinica");
		btnHistoriaClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoriaXPaciente hXp = new HistoriaXPaciente();
				hXp.actualizarTabla(txtCedula.getText());
				hXp.setVisible(true);
				hXp.setModal(true);
			}
		});
		btnHistoriaClinica.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnHistoriaClinica.setBounds(490, 300, 165, 37);
		panel_2.add(btnHistoriaClinica);

		JButton btnVerVacunas = new JButton("Ver vacunas");
		btnVerVacunas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnVerVacunas.setBounds(315, 300, 165, 37);
		panel_2.add(btnVerVacunas);

		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(148, 47, 165, 37);
		panel_2.add(txtCedula);

		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblCedula.setBounds(35, 54, 78, 23);
		panel_2.add(lblCedula);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblEdad.setBounds(348, 54, 78, 23);
		panel_2.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(461, 47, 165, 37);
		panel_2.add(txtEdad);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 4, true),
				"Importancia de la Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 463, 336, 177);
		contentPanel.add(panel_4);

		rdbtnNoImportante = new JRadioButton("No importante");
		rdbtnNoImportante.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		rdbtnNoImportante.setBounds(35, 77, 122, 23);
		panel_4.add(rdbtnNoImportante);

		rdbtnImportante = new JRadioButton("Importante");
		rdbtnImportante.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		rdbtnImportante.setBounds(192, 77, 109, 23);
		panel_4.add(rdbtnImportante);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Finalizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						finalizarConsulta();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	}

	public void actualizarCampos(Doctor doctor, Paciente paciente) {
		txtDoctor.setText(doctor.getNombre());
		txtIdConsulta.setText("CONS-" + System.currentTimeMillis());

		txtNombre.setText(paciente.getNombre());
		txtApellido.setText(paciente.getApellido());
		txtCedula.setText(paciente.getCedula());
		txtEdad.setText(String.valueOf(paciente.getEdad()));
		txtSeguro.setText(paciente.getSeguro().getNombreEmpresa());
	}

	public void finalizarConsulta() {
		boolean importancia;
		for (logico.Cita cita : Clinica.getInstance().getMisCitas()) {
			if (rdbtnImportante.isSelected()) {
				importancia = true;
			} else {
				importancia = false;
			}
			Consulta consulta = new Consulta(txtIdConsulta.getText(), Clinica.getIdFactura(), txtDescripcion.getText(),
					txtEnfermedad.getText(), (Date) spnFecha.getValue(), txtSeguro.getText(), cita.getDoctor(),
					cita.getPaciente(), importancia);
			Clinica.getInstance().agregarConsulta(consulta);
		}
		JOptionPane.showMessageDialog(null, "Consulta registrada Exitosamente ", "",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public JRadioButton getRdbtnNoImportante() {
		return rdbtnNoImportante;
	}

	public void setRdbtnNoImportante(JRadioButton rdbtnNoImportante) {
		this.rdbtnNoImportante = rdbtnNoImportante;
	}

	public JRadioButton getRdbtnImportante() {
		return rdbtnImportante;
	}

	public void setRdbtnImportante(JRadioButton rdbtnImportante) {
		this.rdbtnImportante = rdbtnImportante;
	}

}
